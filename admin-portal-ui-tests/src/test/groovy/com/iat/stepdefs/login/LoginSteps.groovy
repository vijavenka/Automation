package com.iat.stepdefs.login

import geb.Browser
import com.iat.pages.login.LoginPage

import com.iat.pages.DashboardPage
import com.iat.stepdefs.utils.HelpFunctions

import static cucumber.api.groovy.EN.*

def loginPage = new LoginPage()
def dashboardPage = new DashboardPage()
def browser = new Browser()

//Admin user successful login
Given(~/^User is on login page$/) { ->
    to LoginPage
    loginPage = page
}

When(~/^He enters '(.+?)' as login$/) { String login ->
    loginPage.enterLogin(login)
}

When(~/^He enters '(.+?)' as password$/) { String password ->
    loginPage.enterPassword(password)
}

When(~/^He clicks on login button$/) { ->
    loginPage.clickLoginButon()
}

Then(~/^Admin user is correctly logged in$/) { ->
    at DashboardPage
    dashboardPage = page
    waitFor{ dashboardPage.headerModule.autoLogoutCounter.isDisplayed()}
}
