package com.iat.stepdefs.userProfile

import com.iat.Config
import com.iat.domain.createNewUser
import com.iat.pages.DashboardPage
import com.iat.pages.userProfile.LoginPage
import com.iat.pages.userProfile.SetPasswordPage
import com.iat.repository.impl.CreateUserRepositoryImpl
import com.iat.stepdefs.utils.HelpFunctions
import com.iat.stepdefs.utils.JdbcDatabaseConnector
import com.iat.stepdefs.utils.JsonParserUtils
import geb.Browser

import static cucumber.api.groovy.EN.*
import static cucumber.api.groovy.Hooks.After

def loginPage = new LoginPage()
def setPasswordPage = new SetPasswordPage()
def helpFunctions = new HelpFunctions()
JsonParserUtils jsonParserUtils = JsonParserUtils.getInstance()
def mySQLConnector = new JdbcDatabaseConnector(Config.mysqlConnectionPoolIatAdmin)

def browser = new Browser()
def response
def createdAdminId
def createdAdminEmail
def createdAdminPassword = 'password'

After('@deleteCreatedAdminAfterTest') {
    helpFunctions.waitSomeTime(5000) //to ensure user is in DDB and ES, otherwise call returns 404 not found
    response = new CreateUserRepositoryImpl().deleteChosenUser(createdAdminId, Config.superAdminLogin, Config.superAdminPassword)
}

//Scenario: User profile - activate account, follow account confirmation link
Given(~/^New admin account is already created and account activation link sent$/) { ->
    createdAdminEmail = "automatedtestemail" + helpFunctions.returnCurrentEpochTime() + "@gmail.com"
    def body = new createNewUser(helpFunctions.returnCurrentEpochTime().toString(), "UI Automation", "1986-12-27", createdAdminEmail, "MALE", "2015-12-27", "MANAGER", "ADMIN", "ACTIVE", 221516L)
    createdAdminId = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(new CreateUserRepositoryImpl().createNewUser(body, Config.superAdminLogin, Config.superAdminPassword)), 'id')
    System.out.println("Created admin id: $createdAdminId")
}

When(~/^User follows the account confirmation link$/) { ->
    //browser.go(browser.baseUrl + SetPasswordPage.url + new UserRepositoryImpl().getTokenValueByUUID(createdAdminId)) //TODO change to this when doorman will be ready
    def adminId = mySQLConnector.getSingleResult("SELECT id FROM User WHERE epointsUuid = '$createdAdminId'")
    waitFor {
        mySQLConnector.getSingleResult("SELECT tokenValue FROM UserToken WHERE tokenType = 'REGISTER' AND user_id = '$adminId'").length() > 5
    }
    def token = mySQLConnector.getSingleResult("SELECT tokenValue FROM UserToken WHERE tokenType = 'REGISTER' AND user_id = '$adminId'")
    browser.go(browser.baseUrl + SetPasswordPage.url + token)
    at SetPasswordPage
    setPasswordPage = page
}

Then(~/^He is shown a set password page$/) { ->
    at SetPasswordPage
    setPasswordPage = page
}

Then(~/^He is informed that he has to set up a password in order to start using IAT Admin$/) { ->
    waitFor { setPasswordPage.pageHeader.isDisplayed() }
    assert setPasswordPage.pageHeader.text() == 'Activate user'
    //TODO needed additional info here???
}

Then(~/^The form contains password and confirm password fields$/) { ->
    assert setPasswordPage.passwordLabel.text() == 'Password'
    assert setPasswordPage.passwordInputField.isDisplayed()
    assert setPasswordPage.confirmPasswordLabel.text() == 'Confirm password'
    assert setPasswordPage.confirmPasswordInputField.isDisplayed()
}

Then(~/^Activate button is available$/) { ->
    assert setPasswordPage.activateButton.isDisplayed()
    assert setPasswordPage.activateButton.attr('disabled') == 'true'
}

//Scenario: User profile - activate account, set password page, passwords do not match
When(~/^He provides new password confirmation different than new password on set password form$/) { ->
    helpFunctions.waitSomeTime(Config.waitMedium)
    setPasswordPage.enterPassword("password")
    setPasswordPage.enterPasswordConfirmation("passwordConfirmation")
    helpFunctions.waitSomeTime(Config.waitMedium)
    setPasswordPage.passwordInputField.click()//Only to change focus to other input
}

Then(~/^He is informed that both passwords do not match on set password form$/) { ->
    waitFor { setPasswordPage.alertBasic.isDisplayed() }
    assert setPasswordPage.alertBasic.text() == 'Passwords do not match'
}

//Scenario: User profile - activate account, set password page, correct password creation
When(~/^He provides correct new password data on set new password form$/) { ->
    setPasswordPage.enterPassword(createdAdminPassword)
    setPasswordPage.enterPasswordConfirmation(createdAdminPassword)
}

When(~/^Confirms creation of password$/) { ->
    waitFor { !(setPasswordPage.activateButton.attr('disabled') == 'false') }
    setPasswordPage.clickActivateButton()
}

Then(~/^He is informed that password was created$/) { ->
    waitFor { setPasswordPage.succesAlert.isDisplayed() }
    assert setPasswordPage.succesAlert.text() == 'Account has been activated successfully'
}

Then(~/^New admin is able to login into system with already set password$/) { ->
    waitFor { setPasswordPage.loginButton.isDisplayed() }
    setPasswordPage.clickLoginButton()
    at LoginPage
    loginPage = page
    loginPage.signInUser(createdAdminEmail, createdAdminPassword)
    at DashboardPage
}

// Scenario: User profile - activate account, set password page, invalid token
When(~/^User follows the account confirmation link which has invalid token$/) { ->
    browser.go(browser.baseUrl + SetPasswordPage.url + 'token')
}

Then(~/^Information about invalid token will be shown$/) { ->
    at SetPasswordPage
    setPasswordPage = page
    waitFor { setPasswordPage.invalidTokenInfo.isDisplayed() }
    assert setPasswordPage.invalidTokenInfo.text() == 'Token is invalid'
}

Then(~/^Set password form will not be available$/) { ->
    assert !setPasswordPage.passwordInputField.isDisplayed()
    assert !setPasswordPage.confirmPasswordInputField.isDisplayed()
}