package com.iat.stepdefs.joinLoginSection

import com.iat.Config
import com.iat.pages.ForgotPasswordPage
import com.iat.pages.ResetPasswordPage
import com.iat.pages.SignInPage
import com.iat.pages.home.EpointsHomePage
import com.iat.repository.impl.UserRepositoryImpl
import com.iat.stepdefs.envVariables
import com.iat.stepdefs.utils.Functions
import geb.Browser

import static cucumber.api.groovy.EN.*

def epointsHomePage = new EpointsHomePage()
def forgotPasswordPage = new ForgotPasswordPage()
def signInPage = new SignInPage()
def changePasswordPage = new ResetPasswordPage()

def func = new Functions()
def browser = new Browser()
def envVar = new envVariables()

// 1.1 //  ----------------------------------------------------------------------------------------- Forgot password
// --------------------------------------------------------------------------------- page availability popup angular
Given(~/^Epoints page is opened$/) { ->
    to EpointsHomePage
    epointsHomePage = page
    func.clearCookiesAndStorage()
    epointsHomePage.cookiePanelModule.clickAcceptButtonOnCookiePanel()
}
When(~/^User click sign in button in main navbar$/) { ->
    epointsHomePage.headerModule.clickOnSignInButton()
}
When(~/^User click forgot password link$/) { ->
    epointsHomePage.signInModule.clickForgotPasswordLink()
}
Then(~/^Forgot password page will be opened$/) { ->
    at ForgotPasswordPage
    forgotPasswordPage = page
}
Then(~/^Forgot password page has proper content$/) { ->
    waitFor { forgotPasswordPage.pageHeader.isDisplayed() }
    assert forgotPasswordPage.pageHeader.text() == envVar.forgotPasswordHeader
    assert forgotPasswordPage.pageMainInformation.text() == envVar.forgotPasswordMainText
    assert forgotPasswordPage.emailInputFieldLabel.text() == envVar.forgotPasswordEmailLabel
    assert forgotPasswordPage.emailInputField.isDisplayed()
    assert forgotPasswordPage.sendButton.isDisplayed()
}

// 1.2 //  ----------------------------------------------------------------------------------------- Forgot password
// ---------------------------------------------------------------------------------- page availability sign in page
Given(~/^Identified (.+?) user open ePoints page clear$/) { String partner ->
    to EpointsHomePage
    epointsHomePage = page
    func.clearCookiesAndStorage()
    epointsHomePage.cookiePanelModule.clickAcceptButtonOnCookiePanel()
    epointsHomePage.signInUserToEpoints(Config.epointsUser, Config.epointsUserPassword)
    func.clearParticularCookieAndRefreshPage('SESSION')
}
Given(~/^Sign in page is opened$/) { ->
    epointsHomePage = page
    epointsHomePage.goToUserMyAccountPage()
    at SignInPage
    signInPage = page
}
Given(~/^User click forgot password link on sign in page$/) { ->
    signInPage.clickForgotPasswordLinkOnSignInPage()
}

// 1.3 //  ----------------------------------------------------------------------------------------- Forgot password
// -------------------------------------------------------- check if forgot your password page returns proper alerts
Given(~/^Forgot password page is opened$/) { ->
    epointsHomePage.goToForgotPasswordPage()
    at ForgotPasswordPage
    forgotPasswordPage = page
}
When(~/^User type invalid email$/) { ->
    forgotPasswordPage.enterEmail(envVar.testUserEmailInvalid)
}
Then(~/^Email is invalid alert should be displayed$/) { ->
    waitFor { forgotPasswordPage.emailIsRequiredInvalidAlert.isDisplayed() }
    assert forgotPasswordPage.emailIsRequiredInvalidAlert.text() == envVar.forgotPasswordEmailIsInvalidAlert
}
When(~/^User delete entered email$/) { ->
    forgotPasswordPage.enterEmail('')
}
Then(~/^Email is required alert should appear$/) { ->
    waitFor { forgotPasswordPage.emailIsRequiredInvalidAlert.isDisplayed() }
    assert forgotPasswordPage.emailIsRequiredInvalidAlert.text() == envVar.forgotPasswordEmailIsRequiredAlert
}
When(~/^User enter incorrect email$/) { ->
    forgotPasswordPage.enterEmail(envVar.testUserEmailNotExisting)
    forgotPasswordPage.clickSendButton()
}
Then(~/^Email does not exist alert should appear$/) { ->
    waitFor { forgotPasswordPage.emaildoesNotExistAlert.isDisplayed() }
    assert forgotPasswordPage.emaildoesNotExistAlert.text() == envVar.forgotPasswordUserDoesNotExistsAlert
}

// 1.4 //  ----------------------------------------------------------------------------------------- Forgot password
// ---------------------------------------------------- check if password restoration link is created and is working
When(~/^User enter correct email$/) { ->
    forgotPasswordPage.enterEmail(Config.epointsUser)
}
When(~/^Click send restoration link button$/) { ->
    forgotPasswordPage.clickSendButton()
}
Then(~/^Email sent info should be displayed$/) { ->
    waitFor { forgotPasswordPage.emailSentInfoHeader.isDisplayed() }
    assert forgotPasswordPage.emailSentInfoHeader.text() == envVar.forgotPasswordEmailSentHeader
    assert forgotPasswordPage.emailSentInfoDescription.text() == envVar.forgotPasswordEmailSentDescription
}
Then(~/^Link sent on user email should works$/) { ->
    sleep(2000)//has to stay
    resetPasswordLink = envVar.epointsLink + "/reset/" + new UserRepositoryImpl().getTokenValueByUUID(new UserRepositoryImpl().findByEmail(Config.epointsUser).getUuid())
    browser.go(resetPasswordLink)
    at ResetPasswordPage
    changePasswordPage = page
}
Then(~/^User can create new password '(.+?)'$/) { String newPassword ->
    changePasswordPage.fillChangePasswordForm(newPassword, newPassword)
    changePasswordPage.clickChangePasswordbutton()
}
Then(~/^User can check that new password works '(.+?)'$/) { String newPassword ->
    waitFor(15) { changePasswordPage.pageHeader.text() == envVar.changePasswordSuccessAlert }
    assert changePasswordPage.pageHeader.text() == envVar.changePasswordSuccessAlert

    changePasswordPage.headerModule.clickOnSignInButton()
    changePasswordPage.signInModule.signInUserToEpointsCom(Config.epointsUser, newPassword)
    waitFor { epointsHomePage.headerModule.headerUserNameLabel.isDisplayed() }
}