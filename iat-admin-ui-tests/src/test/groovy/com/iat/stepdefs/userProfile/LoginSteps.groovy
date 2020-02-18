package com.iat.stepdefs.userProfile

import com.iat.pages.DashboardPage
import com.iat.pages.userProfile.LoginPage

import static cucumber.api.groovy.EN.*

loginPage = new LoginPage()
dashboardPage = new DashboardPage()

//Scenario: IAT admin login - general view
Given(~/^User goes to IAT admin login page$/) { ->
    to LoginPage
    loginPage = page
    waitFor { loginPage.loginFormText.isDisplayed() }
    assert loginPage.loginFormText.text().equals('epoints admin')
}

Then(~/^Login page has inputs for login and password$/) { ->
    waitFor { loginPage.loginFormUsernameInputField.isDisplayed() }
    waitFor { loginPage.loginFormPasswordInputField.isDisplayed() }
    assert loginPage.loginFormUsernameLabel.text().equals('Username')
    assert loginPage.loginFormPasswordLabel.text().equals('Password')
}

Then(~/^"Sign in" button is available$/) { ->
    assert loginPage.loginFormSignInButton.isDisplayed()
}

Then(~/^"Forgot your password" link is available$/) { ->
    assert loginPage.forgotYourPasswordLink.isDisplayed()
}

//Scenario Outline: IAT admin login - incorrect username or password
Then(~/^Alert "(.+?)" will be displayed$/) { String alert ->
    waitFor { loginPage.alertDanger.isDisplayed() }
    assert loginPage.alertDanger.text().equals(alert)
}

//Scenario Outline: IAT admin login - different roles
When(~/^He enters '(.+?)' and '(.+?)' for sign in$/) { String username, String password ->
    waitFor { !loginPage.loader.isDisplayed() }
    loginPage.enterUsernameOnLoginForm(username)
    loginPage.enterPasswordOnLoginForm(password)
    loginPage.clickOnSignInButton()
}

Then(~/^He should be logged in$/) { ->
    at DashboardPage
    dashboardPage = page
}

And(~/^Role type '(.*)' of user is displayed on the top bar$/) { String roleType ->
    waitFor { dashboardPage.topNavigation.userRoleType.isDisplayed() }
    assert dashboardPage.topNavigation.userRoleType.text() == roleType
}

Then(~/^User name '(.*)' is displayed on the top bar$/) { String username ->
    waitFor { dashboardPage.topNavigation.userName.isDisplayed() }
    assert dashboardPage.topNavigation.userName.text().toLowerCase() == username
}