package com.iat.pages.userProfile

import com.iat.Config
import com.iat.pages.modules.TopNavigationModule
import com.iat.stepdefs.utils.HelpFunctions
import geb.Page

class LoginPage extends Page {

    def helpFunctions = new HelpFunctions()

    static url = '/login'
    static at = { waitFor { browser.currentUrl.contains('login') } }

    static content = {
        topNavigation(wait: true) { module TopNavigationModule }

        loader(required: false) { $('#loader-container') }

        loginForm(wait: true) { $('div.card-content') }
        loginFormText { $('div.card-content').find('h1') }
        loginFormUsernameInputField { loginForm.find('input[ng-model="vm.user"]') }
        loginFormUsernameLabel { loginForm.find('label').first() }
        loginFormPasswordInputField { loginForm.find('input[ng-model="vm.password"]') }
        loginFormPasswordLabel { loginForm.find('label').last() }
        loginFormSignInButton { $('button[ng-click="vm.login(vm.user, vm.password)"]') }
        forgotYourPasswordLink(wait: true) { $('a', text: 'Forgot your password?') }

        alertDanger(wait: true, required: false) { $('.alert-danger') }
    }

    def enterUsernameOnLoginForm(String username) {
        waitFor { loginFormUsernameInputField.isDisplayed() }; loginFormUsernameInputField.value(username)
    }

    def enterPasswordOnLoginForm(String password) {
        waitFor { loginFormPasswordInputField.isDisplayed() }; loginFormPasswordInputField.value(password)
    }

    def clickOnSignInButton() {
        waitFor { loginFormSignInButton.isDisplayed() }; loginFormSignInButton.click()
    }

    def signInUser(String username, String password) {
        helpFunctions.waitSomeTime(Config.waitShort)
        waitFor { !loader.isDisplayed() }
        enterUsernameOnLoginForm(username)
        enterPasswordOnLoginForm(password)
        clickOnSignInButton()
    }

    def clickForgotYourPasswordLink() {
        waitFor { !loader.isDisplayed() }
        waitFor { forgotYourPasswordLink.isDisplayed() }; forgotYourPasswordLink.click()
    }
}