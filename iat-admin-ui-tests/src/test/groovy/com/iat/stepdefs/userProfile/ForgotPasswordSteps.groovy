package com.iat.stepdefs.userProfile

import com.iat.Config
import com.iat.domain.passwordRemind
import com.iat.pages.DashboardPage
import com.iat.pages.userProfile.ForgotPasswordPage
import com.iat.pages.userProfile.LoginPage
import com.iat.pages.userProfile.SetNewPasswordPage
import com.iat.repository.impl.UserAccountManagementRepositoryImpl
import com.iat.stepdefs.utils.HelpFunctions
import com.iat.stepdefs.utils.JdbcDatabaseConnector
import geb.Browser

import static cucumber.api.groovy.EN.*

def loginPage = new LoginPage()
def forgotPassowrdPage = new ForgotPasswordPage()
def setNewPasswordPage = new SetNewPasswordPage()
def helpFunctions = new HelpFunctions()

def response
def browser = new Browser()

def mySQLConnector = new JdbcDatabaseConnector(Config.mysqlConnectionPoolIatAdmin)

//Scenario: Forgot password - follow link
When(~/^He clicks on the "forgot password" link$/) { ->
    at LoginPage
    loginPage = page
    loginPage.clickForgotYourPasswordLink()
}

Then(~/^He is re-directed to forgot password page$/) { ->
    at ForgotPasswordPage
    forgotPassowrdPage = page
}

Then(~/^He is informed about the change password process$/) { ->
    waitFor { forgotPassowrdPage.pageHeader.isDisplayed() }
    assert forgotPassowrdPage.pageHeader.text() == 'Forgot your password?'
    assert forgotPassowrdPage.pageMainInfo.text() == 'Enter the email address associated with your epoints admin account below, we\'ll send you a link to reset your password.'
}

Then(~/^The field to provide an email address is displayed$/) { ->
    assert forgotPassowrdPage.emailLabel.text() == 'Email'
    assert forgotPassowrdPage.emailInputField.isDisplayed()
}

Then(~/^There are two buttons "back" & "send"$/) { ->
    assert forgotPassowrdPage.sendButton.isDisplayed()
    assert forgotPassowrdPage.backButton.isDisplayed()
}

Then(~/^Send button is disabled when email input is empty$/) { ->
    assert forgotPassowrdPage.sendButton.attr('disabled') == 'true'
}

//Scenario: Forgot password - back button
Given(~/^User is on the forgot password page$/) { ->
    at LoginPage
    loginPage = page
    loginPage.clickForgotYourPasswordLink()
    at ForgotPasswordPage
    forgotPassowrdPage = page
}

When(~/^He clicks on the "back" button on forgot password page$/) { ->
    forgotPassowrdPage.clickBackButton()
}

Then(~/^He is re-directed to IAT Admin sign in page$/) { ->
    at LoginPage
}

//Scenario: Forgot password - not existing in system email provided
When(~/^He provide email which not exists in the system$/) { ->
    forgotPassowrdPage.enterEmail("notExistingInSystemEmail@gmail.com")
}

Then(~/^And he click "send" button$/) { ->
    waitFor { !(forgotPassowrdPage.sendButton.attr('disabled') == 'true') }
    forgotPassowrdPage.clickSendButton()
}

Then(~/^He is informed about an error$/) { ->
    waitFor { forgotPassowrdPage.dangerAlert.isDisplayed() }
    assert forgotPassowrdPage.dangerAlert.text() == 'User with email notExistingInSystemEmail@gmail.com not found'
}

//Scenario: Forgot password - existing in system email provided
When(~/^He provide correct email which exists in the system$/) { ->
    forgotPassowrdPage.enterEmail(Config.profileTestsAdminLogin)
}

Then(~/^He is informed that password restoration link was sent$/) { ->
    waitFor { forgotPassowrdPage.succesAlert.isDisplayed() }
    assert forgotPassowrdPage.succesAlert.text() == 'Email sent! Follow the link in your email.'
}

//Scenario: Forgot password/changing password - set new password page, follow link
Given(~/^Password change link is already sent$/) { ->
    body = new passwordRemind(Config.profileTestsAdminLogin)
    response = new UserAccountManagementRepositoryImpl().remindUserPassword(body)
}

When(~/^User follow password change link$/) { ->
    //browser.go(browser.baseUrl + SetNewPasswordPage.url + new UserRepositoryImpl().getTokenValueByUUID(Config.managerUUID)) //TODO change to this when doorman will be ready
    def userId = mySQLConnector.getSingleResult("SELECT id FROM User WHERE username = '" + Config.profileTestsAdminLogin + "'")
    def token = mySQLConnector.getSingleResult("SELECT tokenValue FROM UserToken WHERE tokenType = 'FORGOTTEN_PASSWORD' AND user_Id = '$userId' ORDER BY id DESC")
    browser.go(browser.baseUrl + SetNewPasswordPage.url + '/' + token)
}

Then(~/^He is re-directed to change password interface$/) { ->
    at SetNewPasswordPage
    setNewPasswordPage = page
}

Then(~/^It contains new password and confirm password fields$/) { ->
    waitFor { setNewPasswordPage.pageHeader.isDisplayed() }
    assert setNewPasswordPage.pageHeader.text() == 'Set new password'
    assert setNewPasswordPage.passwordLabel.text() == 'Password'
    assert setNewPasswordPage.passwordInputField.isDisplayed()
    assert setNewPasswordPage.confirmPasswordLabel.text() == 'Confirm password'
    assert setNewPasswordPage.confirmPasswordInputField.isDisplayed()
}

Then(~/^Two buttons back & change$/) { ->
    assert setNewPasswordPage.backButton.isDisplayed()
    assert setNewPasswordPage.changePasswordButton.isDisplayed()
    assert setNewPasswordPage.changePasswordButton.attr('disabled') == 'true'
}

//Scenario: Forgot password/changing password - change password page, back button
When(~/^He clicks on the back button on set new password page$/) { ->
    at SetNewPasswordPage
    setNewPasswordPage = page
    helpFunctions.waitSomeTime(Config.waitLong)
    setNewPasswordPage.clickBackButton()
}

//Scenario: Forgot password/changing password - set new password page, passwords do not match
When(~/^He provides new password confirmation different than new password on set new password form$/) { ->
    at SetNewPasswordPage
    setNewPasswordPage = page
    setNewPasswordPage.enterPassword("password")
    setNewPasswordPage.enterPasswordConfirmation("passwordConfirmation")
    helpFunctions.waitSomeTime(Config.waitMedium)
    setNewPasswordPage.passwordInputField.click()//Only to change focus to other input
}

Then(~/^He is informed that both passwords do not match on set new password form$/) { ->
    waitFor { setNewPasswordPage.alertBasic.isDisplayed() }
    assert setNewPasswordPage.alertBasic.text() == 'Passwords do not match'
}

//Scenario Outline: Forgot password/changing password - set new password page, new password set
When(~/^He provides new password data '(.+?)', '(.+?)' on set new password form$/) { String currentPassword, String newPassword ->
    at SetNewPasswordPage
    setNewPasswordPage = page
    setNewPasswordPage.enterPassword(newPassword)
    setNewPasswordPage.enterPasswordConfirmation(newPassword)
}

When(~/^Confirms password change on set new password form$/) { ->
    waitFor { !(setNewPasswordPage.changePasswordButton.attr('disabled') == 'false') }
    helpFunctions.waitSomeTime(Config.waitMedium)//TODO jenkins fix to be checked
    setNewPasswordPage.clickChangePasswordButton()
}

Then(~/^He is informed that password was changed on set new password form$/) { ->
    waitFor { setNewPasswordPage.succesAlert.isDisplayed() }
    assert setNewPasswordPage.succesAlert.text() == 'Password has been successfully changed'
}

Then(~/^He is able to login into system with new password '(.+?)'$/) { String newPassword ->
    waitFor { setNewPasswordPage.loginButton.isDisplayed() }
    setNewPasswordPage.clickLoginButton()
    at LoginPage
    loginPage = page
    loginPage.signInUser(Config.profileTestsAdminLogin, newPassword)
    at DashboardPage
}

//Scenario: Forgot password/changing password - set new password page, invalid token
When(~/^User follow password change link and its token is invalid$/) { ->
    browser.go(browser.baseUrl + SetNewPasswordPage.url + "/token")
}

Then(~/^Information about invalid token will be displayed$/) { ->
    at SetNewPasswordPage
    setNewPasswordPage = page
    waitFor { setNewPasswordPage.invalidTokenInfo.isDisplayed() }
    assert setNewPasswordPage.invalidTokenInfo.text() == 'Token is invalid'
}

Then(~/^Set new password form will not be available$/) { ->
    assert !setNewPasswordPage.passwordInputField.isDisplayed()
    assert !setNewPasswordPage.confirmPasswordInputField.isDisplayed()
}