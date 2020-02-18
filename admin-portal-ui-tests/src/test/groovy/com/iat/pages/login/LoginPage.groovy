package com.iat.pages.login

import geb.Page

class LoginPage extends Page {

    static url = '/login'
    static atCheckWaiting = true
    static at = { waitFor { browser.currentUrl.contains("login") } }

    static content = {
        loginInputField(wait: true) { $('#username') }
        passwordInputField(wait: true) { $('#password') }
        rememberMeCheckbox(wait: true) { $('#remember-me') }

        loginButton(wait: true) { $('.submit') }
    }

    def enterLogin(login) { waitFor { loginInputField.isDisplayed() }; loginInputField.value(login) }

    def enterPassword(password) { waitFor { passwordInputField.isDisplayed() }; passwordInputField.value(password) }

    def checkRememberMeCheckbox() { waitFor { rememberMeCheckbox.isDisplayed() }; rememberMeCheckbox.click() }

    def clickLoginButon() { waitFor { loginButton.isDisplayed() }; loginButton.click() }

}