package com.iat.stepdefs.joinLoginSection

import com.iat.Config
import com.iat.pages.SignInPage
import com.iat.pages.home.EpointsHomePage
import com.iat.pages.userAccount.profile.ProfilePage
import com.iat.stepdefs.envVariables
import com.iat.stepdefs.utils.Functions

import static cucumber.api.groovy.EN.*

def epointsHomePage = new EpointsHomePage()
def signInPage = new SignInPage()
def profilePage = new ProfilePage()

def func = new Functions()
def envVar = new envVariables()

// 1.1 //  -------------------------------------------------------------------------------------- Login into epoints
// ------------------------------------------------------------------------------------------- sign in panel content
Given(~/^Not registered user open ePoints\.com$/) { ->
    to EpointsHomePage
    epointsHomePage = page
    epointsHomePage.cookiePanelModule.clickAcceptButtonOnCookiePanel()
}
When(~/^User click in sign in option$/) { ->
    epointsHomePage = page
    epointsHomePage.headerModule.clickOnSignInButton()
    sleep(500)
}
Then(~/^Authentication panel is shown$/) { ->
    waitFor { epointsHomePage.signInModule.signInMainPanel.isDisplayed() }
    assert epointsHomePage.signInModule.signInMainPanel.isDisplayed()
}
Then(~/^Email label is available$/) { ->
    waitFor { epointsHomePage.signInModule.signInEmailLabel.isDisplayed() }
    assert epointsHomePage.signInModule.signInEmailLabel.text() == envVar.signInPanelEmailLabel
}
Then(~/^Email input field is available$/) { ->
    waitFor { epointsHomePage.signInModule.signInEmailInputField.isDisplayed() }
    assert epointsHomePage.signInModule.signInEmailInputField.attr('placeholder') == envVar.signInPanelEmailPlaceholder
}
Then(~/^Password label is available$/) { ->
    waitFor { epointsHomePage.signInModule.signInPasswordLabel.isDisplayed() }
    assert epointsHomePage.signInModule.signInPasswordLabel.text() == envVar.signInPanelPasswordLabel
}
Then(~/^Password input field is available$/) { ->
    waitFor { epointsHomePage.signInModule.signInPasswordInputField.isDisplayed() }
    assert epointsHomePage.signInModule.signInPasswordInputField.attr('placeholder') == envVar.signInPanelPasswordPlaceholder
}
Then(~/^Forgot password link is available$/) { ->
    waitFor { epointsHomePage.signInModule.signInForgotPasswordLink.isDisplayed() }
    assert epointsHomePage.signInModule.signInForgotPasswordLink.isDisplayed()
}
Then(~/^Sign In button is available$/) { ->
    waitFor { epointsHomePage.signInModule.signInSubmitButton.isDisplayed() }
    assert epointsHomePage.signInModule.signInSubmitButton.isDisplayed()
}
Then(~/^Join here Link is available$/) { ->
    waitFor { epointsHomePage.signInModule.joinHereLink.isDisplayed() }
    assert epointsHomePage.signInModule.joinHereLink.isDisplayed()
}
Then(~/^Sign in using facebook button is available$/) { ->
    waitFor { epointsHomePage.signInModule.signInWithFacebookButton.isDisplayed() }
    assert epointsHomePage.signInModule.signInWithFacebookButton.text() == envVar.signInPanelFacebookButonPlaceholder
}
Then(~/^Close Link is available$/) { ->
    waitFor { epointsHomePage.signInModule.closeButton.isDisplayed() }
    assert epointsHomePage.signInModule.closeButton.isDisplayed()
}

// 1.2 //  -------------------------------------------------------------------------------------- Login into epoints
// ------------------------------------------------------------------------------------------ required fields alerts
Given(~/^Sign in panel is opened$/) { ->
    epointsHomePage.headerModule.clickOnSignInButton()
    waitFor { epointsHomePage.signInModule.signInMainPanel.isDisplayed() }
    assert epointsHomePage.signInModule.signInMainPanel.isDisplayed()
}
When(~/^User enter some phrases into input fields and delete them$/) { ->
    epointsHomePage.signInModule.enterLoginData('email', 'password')
    epointsHomePage.signInModule.enterLoginData('', '')
}
Then(~/^Fields should be marked$/) { ->
    //leave empty
}
Then(~/^Fields required alerts will be displayed$/) { ->
    waitFor { epointsHomePage.signInModule.errorBasic[0].isDisplayed() }
    waitFor { epointsHomePage.signInModule.errorBasic.size() == 2 }
    assert epointsHomePage.signInModule.errorBasic[0].text() == envVar.signInPanelEmailRequiredAlert
    assert epointsHomePage.signInModule.errorBasic[1].text() == envVar.signInPanelPasswordRequiredAlert
}

// 1.3 //  -------------------------------------------------------------------------------------- Login into epoints
// ------------------------------------------------------------------------------- sign in into not existing account
When(~/^User fill email address using email which is not in the database$/) { ->
    epointsHomePage.signInModule.enterEmailAddress(envVar.testUserEmailNotExisting)
}
When(~/^Fill Password field with random data$/) { ->
    epointsHomePage.signInModule.enterPassword(func.returnRandomValue(1000000))
}
When(~/^Click On sign in button$/) { ->
    epointsHomePage.signInModule.clickSignInSubmitButton()
}
Then(~/^Warning authentication system error should shown$/) { ->
    waitFor { epointsHomePage.signInModule.errorDanger.isDisplayed() }
    assert epointsHomePage.signInModule.errorDanger.text() == envVar.signInPanelAuthorizationFailed
}

// 1.4 //  -------------------------------------------------------------------------------------- Login into epoints
// ------------------------------------------------------------ sign in into existing account which is not activated
When(~/^User fill email address using email which is not activated$/) { ->
    epointsHomePage.signInModule.enterEmailAddress(envVar.testUserEmailNotActivated)
}

// 1.5 //  -------------------------------------------------------------------------------------- Login into epoints
// -------------------------------------------------------------- sign in into existing account with random password
When(~/^User enter correct email address$/) { ->
    epointsHomePage.signInModule.enterEmailAddress(Config.epointsUser)
}

// 1.6 //  -------------------------------------------------------------------------------------- Login into epoints
// ------------------------------------------------------------------------------------------------- correct sign in
When(~/^User fill correct data into sign in form$/) { ->
    epointsHomePage.signInModule.enterLoginData(Config.epointsUser, Config.epointsUserPassword)
}
Then(~/^He will be correctly logged in$/) { ->
    waitFor { !epointsHomePage.headerModule.headerSignInButton.isDisplayed() }
    assert !epointsHomePage.headerModule.headerSignInButton.isDisplayed()
}
Then(~/^Points counter will be displayed$/) { ->
    waitFor { epointsHomePage.headerModule.headerPointsBalancePending.isDisplayed() }
    waitFor { epointsHomePage.headerModule.headerPointsBalanceConfirmed.isDisplayed() }
    assert epointsHomePage.headerModule.headerPointsBalancePending.isDisplayed()
    assert epointsHomePage.headerModule.headerPointsBalanceConfirmed.isDisplayed()
}
Then(~/^Navigation options for logged user will be displayed$/) { ->
    waitFor { epointsHomePage.headerModule.headerUserNameLabel.isDisplayed() }
    assert epointsHomePage.headerModule.headerUserNameLabel.isDisplayed()
    assert !epointsHomePage.headerModule.headerJoinButton.isDisplayed()

}

// 1.7 //  -------------------------------------------------------------------------------------- Login into epoints
// ------------------------------------------------------------------------------------------------- sign out option
Given(~/^Registered user open ePoints\.com$/) { ->
    to EpointsHomePage
    epointsHomePage = page
    func.clearCookiesAndStorage()
    epointsHomePage.cookiePanelModule.clickAcceptButtonOnCookiePanel()
    epointsHomePage.signInUserToEpoints(Config.epointsUser, Config.epointsUserPassword)
}
When(~/^User click on sign out button$/) { ->
    sleep(1000)
    if (!epointsHomePage.userDropDownMenuModule.userDropDownMenuSignOutOption.isDisplayed()) {
        epointsHomePage.headerModule.clickOnUserNameLabel()
    }
    epointsHomePage.userDropDownMenuModule.clickOnSignOutOption()
}
Then(~/^He will be correctly logged out$/) { ->
    waitFor { epointsHomePage.headerModule.headerSignInButton.isDisplayed() }
    assert epointsHomePage.headerModule.headerSignInButton.isDisplayed()
}
Then(~/^Points counter will not be displayed$/) { ->
    waitFor { !epointsHomePage.headerModule.headerPointsBalancePending.isDisplayed() }
    waitFor { !epointsHomePage.headerModule.headerPointsBalanceConfirmed.isDisplayed() }
    assert !epointsHomePage.headerModule.headerPointsBalancePending.isDisplayed()
    assert !epointsHomePage.headerModule.headerPointsBalanceConfirmed.isDisplayed()
}
Then(~/^Navigation options for not logged user will be displayed$/) { ->
    waitFor { epointsHomePage.headerModule.headerJoinButton.isDisplayed() }
    waitFor { epointsHomePage.headerModule.headerSignInButton.isDisplayed() }
    assert epointsHomePage.headerModule.headerJoinButton.isDisplayed()
    assert epointsHomePage.headerModule.headerSignInButton.isDisplayed()
}
Then(~/^Join modules will be displayed on home page$/) { ->
    waitFor { epointsHomePage.joinInputField.isDisplayed() }
    assert epointsHomePage.joinInputField.isDisplayed()
    assert epointsHomePage.joinForFreeButton.isDisplayed()
    assert epointsHomePage.signInWithFacebookButton.isDisplayed()
}

// 1.8 //  -------------------------------------------------------------------------------------- Login into epoints
// ------------------------------------------------- sign in when user is provide by cookie and is not authenticated
Given(~/^Identified user open ePoints\.com$/) { ->
    to EpointsHomePage
    epointsHomePage = page
    func.clearCookiesAndStorage()
    epointsHomePage.cookiePanelModule.clickAcceptButtonOnCookiePanel()
    epointsHomePage.signInUserToEpoints(Config.epointsUser, Config.epointsUserPassword)
    func.clearParticularCookieAndRefreshPage('SESSION')
}
When(~/^User tries to reach his profile page$/) { ->
    epointsHomePage.headerModule.clickOnUserNameLabel()
    epointsHomePage.userDropDownMenuModule.clickOnProfileOption()
}
Then(~/^Login page with all needed elements will be displayed$/) { ->
    at SignInPage
    signInPage = page
    waitFor { signInPage.signInFormHeader.isDisplayed() }
    assert signInPage.signInFormHeader.isDisplayed()
    assert signInPage.loginPageEmailInputFieldLabel.isDisplayed()
    assert signInPage.loginPageEmailInputField.isDisplayed()
    assert signInPage.loginPagePasswordInputFieldLabel.isDisplayed()
    assert signInPage.loginPagePasswordInputField.isDisplayed()
    assert signInPage.loginPageForgotPasswordLink.isDisplayed()
    assert signInPage.loginPageSignInButton.isDisplayed()
    assert signInPage.loginPageSignInWithFacebookButton.isDisplayed()
}
When(~/^User fill correct data into sign in modal$/) { ->
    signInPage.enterLoginDataLoginPage(Config.epointsUser, Config.epointsUserPassword)
}
When(~/^Click on sign in button in modal window$/) { ->
    signInPage.clickSignInButtonLoginPage()
}
Then(~/^User will be redirected to your account area$/) { ->
    at ProfilePage
    profilePage = page
}
When(~/^User is signing in via FB account ([^"]*):([^"]*)$/) { String email, String password ->

    epointsHomePage.headerModule.clickOnSignInButton()
    waitFor { epointsHomePage.headerModule.headerFacebookSigInButton.isDisplayed() }

    browser.withNewWindow({
        epointsHomePage.headerModule.headerFacebookSigInButton.click()
    }, close: false, wait: true) {
        waitFor { $("#email").isDisplayed() }
        $("#email").value(email)
        $("#pass").value(password)
        $("input", type: "submit").click()
    }
}