package com.iat.stepdefs.userProfile

import com.iat.Config
import com.iat.pages.DashboardPage
import com.iat.pages.userProfile.ChangePasswordPage
import com.iat.pages.userProfile.LoginPage
import com.iat.stepdefs.utils.JdbcDatabaseConnector
import geb.Browser

import static cucumber.api.groovy.EN.*
import static cucumber.api.groovy.Hooks.Before

def loginPage = new LoginPage()
def dashboardPage = new DashboardPage()
def changePasswordPage = new ChangePasswordPage()
def mySQLConnector = new JdbcDatabaseConnector(Config.mysqlConnectionPoolIatAdmin)

def browser = new Browser()

Before('@resetAdminPasswordBefore') {
    def userId = mySQLConnector.getSingleResult("SELECT id FROM User WHERE username = '" + Config.profileTestsAdminLogin + "'")
    mySQLConnector.execute("UPDATE User SET password = 'T1U8KXcwPmcn5OZob8FpccztBqLMZmmnPZ84lCz0yu8=' WHERE id = '" + userId + "'")
}

//Scenario: User profile - change password component availability
Given(~/^Change password page is opened$/) { ->
    at DashboardPage
    dashboardPage = page
    dashboardPage.navigateToChangePasswordPage()
    at ChangePasswordPage
    changePasswordPage = page
}

Then(~/^He can see a section to change a password$/) { ->
    waitFor { changePasswordPage.changePasswordComponent.isDisplayed() }
}

Then(~/^It contains "current password","new password" and "confirm password" fields$/) { ->
    assert changePasswordPage.currentPasswordLabel.text() == 'Current password'
    assert changePasswordPage.currentPasswordInputField.isDisplayed()
    assert changePasswordPage.passwordLabel.text() == 'New password'
    assert changePasswordPage.passwordInputField.isDisplayed()
    assert changePasswordPage.passwordConfirmationLabel.text() == 'Confirm new password'
    assert changePasswordPage.passwordConfirmationInputField.isDisplayed()
}

Then(~/^It contains "Confirm" button$/) { ->
    assert changePasswordPage.confirmPasswordChangeButton.isDisplayed()
    assert changePasswordPage.confirmPasswordChangeButton.attr('disabled') == 'true'
}

//Scenario: User profile - change password component, wrong current password
When(~/^He provides improper current password$/) { ->
    changePasswordPage.enterCurrentPassword("wrongPassword'")
    changePasswordPage.enterPassword("newPassword")
    changePasswordPage.enterPasswordConfirmation("newPassword")
}

When(~/^Confirms password change$/) { ->
    waitFor { !(changePasswordPage.confirmPasswordChangeButton.attr('disabled') == 'true') }
    changePasswordPage.clickPasswordChangeConfirmButton()
}

Then(~/^He is informed that provided current password is incorrect$/) { ->
    waitFor { changePasswordPage.dangerAlert.isDisplayed() }
    assert changePasswordPage.dangerAlert.text() == 'Incorrect current password.'
}

//Scenario: User profile - change password component, passwords do not match
When(~/^He provides new password confirmation different than new password$/) { ->
    changePasswordPage.enterCurrentPassword("password'")
    changePasswordPage.enterPassword("password")
    changePasswordPage.enterPasswordConfirmation("passwordConfirmation")
    changePasswordPage.passwordInputField.click()//just for change focus of input field
}

Then(~/^He is informed that both passwords do not match$/) { ->
    waitFor { changePasswordPage.alertBasic.isDisplayed() }
    assert changePasswordPage.alertBasic.text() == 'Passwords do not match'
}

//Scenario: User profile - change password component, passwords to short
When(~/^He provides to short new password, below 6 signs$/) { ->
    changePasswordPage.enterCurrentPassword("password'")
    changePasswordPage.enterPassword("pass")
    changePasswordPage.passwordConfirmationInputField.click()//just for change focus of input field
}

Then(~/^He is informed that new password is to short$/) { ->
    waitFor { changePasswordPage.alertBasic.isDisplayed() }
    assert changePasswordPage.alertBasic.text() == 'This text is too short'
}

//Scenario Outline: User profile - change password component, correct password change
Given(~/^Admin is logged into system with password (.+?)$/) { String password ->
    to LoginPage
    at LoginPage
    loginPage = page
    loginPage.signInUser(Config.profileTestsAdminLogin, password)
}

When(~/^He provides new password data '(.+?)', '(.+?)'$/) { String oldPassword, String newPassword ->
    changePasswordPage.enterCurrentPassword(oldPassword)
    changePasswordPage.enterPassword(newPassword)
    changePasswordPage.enterPasswordConfirmation(newPassword)
}

Then(~/^He is informed that password was changed$/) { ->
    waitFor { changePasswordPage.succesAlert.isDisplayed() }
    assert changePasswordPage.succesAlert.text() == 'Password has been successfully changed'
}

Then(~/^He is able to login into system with new password '(.+?)' next time$/) { String newPassword ->
    changePasswordPage.topNavigation.expandUserImageDDL()
    changePasswordPage.topNavigation.clickLogoutOption()
    at LoginPage
    loginPage = page
    loginPage.signInUser(Config.profileTestsAdminLogin, newPassword)
    at DashboardPage
}