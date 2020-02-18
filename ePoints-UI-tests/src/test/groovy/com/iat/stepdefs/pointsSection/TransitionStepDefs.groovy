package com.iat.stepdefs.pointsSection

import com.iat.Config
import com.iat.pages.JoinConfirmationPage
import com.iat.pages.JoinPage
import com.iat.pages.home.EpointsHomePage
import com.iat.pages.points.LeadRetailerPage
import com.iat.pages.points.PointsPage
import com.iat.pages.points.TransitionPage
import com.iat.stepdefs.envVariables
import com.iat.stepdefs.utils.Functions
import geb.Browser

import static cucumber.api.groovy.EN.*

def epointsHomePage = new EpointsHomePage()
def transitionPage = new TransitionPage()
def pointsPage = new PointsPage()
def joinPage = new JoinPage()
def joinConfirmationPage = new JoinConfirmationPage()
def leadRetailerPage = new LeadRetailerPage()

def envVar = new envVariables()
def func = new Functions()
def browser = new Browser()
def random


Given(~'^(.+?) user is opening transition page$') { String loginState ->
    to EpointsHomePage
    epointsHomePage = page
    func.clearCookiesAndStorage()
    epointsHomePage.cookiePanelModule.clickAcceptButtonOnCookiePanel()
    if (!loginState.contains('Not'))
        epointsHomePage.signInUserToEpoints(Config.epointsUser, Config.epointsUserPassword)
    epointsHomePage.headerModule.clickOnPointsButton()
    at PointsPage
    pointsPage = page
    sleep(1000)
    waitFor { !pointsPage.loader.isDisplayed() }
    pointsPage.departmentsFacetModule.selectDepartmentByName('Specials')
    sleep(1000)
    waitFor { !pointsPage.loader.isDisplayed() }
    waitFor { pointsPage.retailersList[0].isDisplayed() }
    random = func.returnRandomValue(pointsPage.retailersList.size())
    pointsPage.retailersList[random].click()
    at LeadRetailerPage
    leadRetailerPage = page
    leadRetailerPage.earnPointsButton.click()

    at TransitionPage
    transitionPage = page
}

Then(~/^He can see that it has all needed elements$/) { ->
    waitFor { transitionPage.transitionPagePointsInfo.isDisplayed() }
    assert transitionPage.transitionPagePointsInfo.isDisplayed()
    assert transitionPage.transitionPageRetailerInformation.isDisplayed()
    assert transitionPage.signInWithFacebookButton.isDisplayed()
    assert transitionPage.emailAddressInputField.isDisplayed()
    assert transitionPage.passwordInputField.isDisplayed()
    assert transitionPage.forgotPasswordLink.isDisplayed()
    assert transitionPage.signInButton.isDisplayed()
    assert transitionPage.joinNowLink.isDisplayed()
    assert transitionPage.continueAnywayButton.isDisplayed()
    assert transitionPage.signInLabelBasic[0].text() == envVar.transitionPageEmailAddressLabel
    assert transitionPage.signInLabelBasic[1].text() == envVar.transitionPagePasswordLabel
    assert transitionPage.joinNowInformation.text() == envVar.transitionPageJoinInformation
    assert transitionPage.continueAnywayInformation.text() == envVar.transitionPageContinueAnywayInformation
}

When(~/^User use continue anyway button$/) { ->
    at TransitionPage
    transitionPage = page
    transitionPage.clickContinueAnywayButton()
}

Then(~/^He will be directly taken to retailer page$/) { ->
    waitFor { !browser.currentUrl.contains(transitionPage.pageUrl) }
}

When(~/^User enter sign in data and remove them$/) { ->
    transitionPage.fillLoginData(envVar, 'incorrectpassword')
    transitionPage.fillLoginData('', '')
}

Then(~/^Signing input fields alerts will be displayed$/) { ->
    waitFor { transitionPage.signInErrorBasic[0].text() == envVar.transitionPageLoginEmailAlert }
    waitFor { transitionPage.signInErrorBasic[1].text() == envVar.transitionPageLoginPasswordAlert }
    assert transitionPage.signInErrorBasic[0].text() == envVar.transitionPageLoginEmailAlert
    assert transitionPage.signInErrorBasic[1].text() == envVar.transitionPageLoginPasswordAlert
}

When(~/^User enter incorrect email address in signing form$/) { ->
    transitionPage.fillLoginData(envVar.testUserEmailInvalid, envVar.wrongPassword)
}

Then(~/^Email address is invalid alert will be displayed$/) { ->
    waitFor { transitionPage.signInErrorBasic[0].isDisplayed() }
    assert transitionPage.signInErrorBasic[0].isDisplayed()
    transitionPage.signInErrorBasic[0].text() == envVar.transitionPageLoginIncorrectEmailAlert
}

When(~/^User enter credentials of user which is not in database$/) { ->
    transitionPage.fillLoginData(envVar.testUserEmailNotExisting, envVar.wrongPassword)
}

Then(~/^Authorization failed alert will be displayed$/) { ->
    waitFor { transitionPage.authorizationFaildAlert.isDisplayed() }
    waitFor { transitionPage.authorizationFaildAlert.text() == envVar.transitionPageLoginAuthorizationFailedAlert }
    assert transitionPage.authorizationFaildAlert.isDisplayed()
    assert transitionPage.authorizationFaildAlert.text() == envVar.transitionPageLoginAuthorizationFailedAlert
}

When(~/^User enter correct login data$/) { ->
    transitionPage.fillLoginData(Config.epointsUser, Config.epointsUserPassword)
}

When(~/^User click sign in button$/) { ->
    transitionPage.clickLoginButton()
}

When(~/^User return to epoints page$/) { ->
    to EpointsHomePage
}

Then(~/^He will be logged in$/) { ->
    waitFor { epointsHomePage.headerModule.headerUserNameLabel.isDisplayed() }
    assert epointsHomePage.headerModule.headerUserNameLabel.isDisplayed()
}

When(~/^User click join now button$/) { ->
    transitionPage.clickJoinNowLink()
}

Then(~/^He will e redirected to epoints join page$/) { ->
    at JoinPage
    joinPage = page
}

When(~/^User correctly fill email input field$/) { ->
    joinPage.enterEmailAddressOnJoin('testemail' + func.returnEpochOfCurrentDay() + '@gmail.com')
}

When(~/^Click join now button on epoints join page$/) { ->
    joinPage.clickJoinNowButton()
    at JoinConfirmationPage
    joinConfirmationPage = page
}

Then(~/^Under confirmation screen will be displayed continue to retailer button$/) { ->
    waitFor { joinConfirmationPage.confirmationMessageOnJoin.text().contains(envVar.joinConfirmationMessage) }
    waitFor { joinConfirmationPage.continueToRetailerButtonOnJoin.isDisplayed() }
    assert joinConfirmationPage.confirmationMessageOnJoin.text().contains(envVar.joinConfirmationMessage)
    assert joinConfirmationPage.continueToRetailerButtonOnJoin.isDisplayed()
}

When(~/^User click continue anyway button$/) { ->
    joinConfirmationPage.clickContinueToRetailerButton()
}

Then(~/^He will be redirected to retailer page$/) { ->
    waitFor { !browser.currentUrl.contains(joinConfirmationPage.pageUrl) }
}

When(~/^User use forgot password link$/) { ->
    transitionPage.clickForgotPasswordLink()
}

When(~/^User will sign in with facebook$/) { ->
    browser.withNewWindow({ transitionPage.clickSignInWithFacebookButton() }, close: false, wait: true) {
        sleep(1000)
        waitFor { $('input', id: 'email').isDisplayed() }
        $('input', id: 'email').value(Config.facebookUser)
        $('input', id: 'pass').value(Config.facebookUserPassword)
        $('input', type: 'submit').click()
    }
}

When(~/^User go to '(.+?)' transition page from points page$/) { String zone ->
    at PointsPage
    pointsPage = page

    pointsPage.departmentsFacetModule.selectDepartmentByName('Books')
    waitFor { pointsPage.retailersList[0].isDisplayed() }
    random = func.returnRandomValue(pointsPage.retailersList.size())

    browser.withNewWindow({ pointsPage.retailersList[random].click() }, close: true, wait: true) {
        at TransitionPage
        transitionPage = page
        waitFor { transitionPage.transitionPagePointsInfo.isDisplayed() }
        if (zone == 'UK')
            assert transitionPage.transitionPagePointsInfo.text().contains('£')
        else if (zone == 'IE')
            assert transitionPage.transitionPagePointsInfo.text().contains('€')
    }

}

Then(~/^He can see that currency is properly set for '(.+?)'$/) { String zone ->
    //done in previous step
}