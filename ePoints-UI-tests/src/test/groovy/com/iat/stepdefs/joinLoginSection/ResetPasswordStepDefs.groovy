package com.iat.stepdefs.joinLoginSection

import com.iat.Config
import com.iat.pages.ForgotPasswordPage
import com.iat.pages.ResetPasswordPage
import com.iat.pages.home.EpointsHomePage
import com.iat.repository.impl.UserRepositoryImpl
import com.iat.stepdefs.envVariables
import geb.Browser

import static cucumber.api.groovy.EN.*

def epointsHomePage = new EpointsHomePage()
def changePasswordPage = new ResetPasswordPage()
def forgotPasswordPage = new ForgotPasswordPage()

def browser = new Browser()
def envVar = new envVariables()

// 1.1 //  ------------------------------------------------------------------------------------ Change password page
// ---------------------------------------------------------------------------------------------------- page content
Given(~/^Change password page is opened$/) { ->
    to EpointsHomePage
    epointsHomePage = page
    epointsHomePage.cookiePanelModule.clickAcceptButtonOnCookiePanel()
    epointsHomePage.goToForgotPasswordPage()
    at ForgotPasswordPage
    forgotPasswordPage = page
    forgotPasswordPage.enterEmail(Config.epointsUser)
    forgotPasswordPage.clickSendButton()
    sleep(2000)
    def resetPasswordLink = envVar.epointsLink + "/reset/" + new UserRepositoryImpl().getTokenValueByUUID(new UserRepositoryImpl().findByEmail(Config.epointsUser).getUuid())
    browser.go(resetPasswordLink)
    at ResetPasswordPage
    changePasswordPage = page
}
When(~/^User look on change password page$/) { ->
    //leave empty
}
Then(~/^He can see that it contains all needed elements$/) { ->
    waitFor { changePasswordPage.pageHeader.text() == envVar.changePasswordHeader }
    assert changePasswordPage.pageHeader.text() == envVar.changePasswordHeader
    assert changePasswordPage.labelPassword.text() == envVar.changePasswordPasswordLabel
    assert changePasswordPage.labelConfirmPassword.text() == envVar.changePasswordConfirmPasswordLabel
    assert changePasswordPage.passwordInputField.isDisplayed()
    assert changePasswordPage.confirmPasswordInputField.isDisplayed()
    assert changePasswordPage.changeButton.isDisplayed()
    assert !changePasswordPage.passwordStrenghtElement.isDisplayed()
}

// 1.2 //  ------------------------------------------------------------------------------------ Change password page
// -------------------------------------------------------------------------------------------- no input data alerts
When(~/^User enter password and password confirmation$/) { ->
    changePasswordPage.fillChangePasswordForm('password', 'password')
}
When(~/^User delete entered new password data$/) { ->
    changePasswordPage.fillChangePasswordForm('', '')
}
Then(~/^Password is required and confirm password is required alerts will be displayed$/) { ->
    waitFor { changePasswordPage.passwordAlert.isDisplayed() }
    waitFor { changePasswordPage.passwordConfirmationAlert.isDisplayed() }
    assert changePasswordPage.passwordAlert.text() == envVar.changePasswordisRequiredAlert
    assert changePasswordPage.passwordConfirmationAlert.text() == envVar.changeConfirmPasswordisRequiredAlert
}

// 1.3 //  ------------------------------------------------------------------------------------ Change password page
// ----------------------------------------------------------------------------------- password do notch match alert
When(~/^Enter two different passwords into input fields$/) { ->
    changePasswordPage.fillChangePasswordForm('password1', 'password2')
}
Then(~/^Then passwords do not match alerts will be displayed$/) { ->
    waitFor { changePasswordPage.passwordDoNotMatchnAlert.isDisplayed() }
    assert changePasswordPage.passwordDoNotMatchnAlert.text() == envVar.changePasswordDoNotMatchAlert
}
Then(~/^Password strength element will be displayed$/) { ->
    waitFor { changePasswordPage.passwordStrenghtElement.isDisplayed() }
    assert changePasswordPage.passwordStrenghtElement.isDisplayed()
}

// 1.4 //  ------------------------------------------------------------------------------------ Change password page
// --------------------------------------------------------------------------------------------------- invalid token
Given(~/^Change password page is opened with expired token$/) { ->
    to EpointsHomePage
    epointsHomePage = page
    epointsHomePage.cookiePanelModule.clickAcceptButtonOnCookiePanel()
    resetPasswordLink = envVar.epointsLink + "reset/" + "231vh3h24vhv34h213v42hj4vb23hj4v12"
    browser.go(resetPasswordLink)
    at ResetPasswordPage
    changePasswordPage = page
}
When(~/^Click change password button$/) { ->
    changePasswordPage.clickChangePasswordbutton()
}
Then(~/^He will see invalid token message$/) { ->
    epointsHomePage = page
    waitFor { changePasswordPage.invalidTokenMessage.isDisplayed() }
    assert changePasswordPage.invalidTokenMessage.text() == envVar.changePasswordInvalidToken
}