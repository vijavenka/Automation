package com.iat.pages.userProfile

import com.iat.Config
import com.iat.stepdefs.utils.HelpFunctions
import geb.Page

class SetPasswordPage extends Page {

    def helpFunctions = new HelpFunctions()

    static url = '/activate-user/'
    static atCheckWaiting = true
    static at = { waitFor { browser.currentUrl.contains("activate-user") } }

    static content = {
        setPasswordForm(wait: true, required: false) { $('.ActivateUser') }
        pageHeader(wait: true) { setPasswordForm.find('h1') }
        passwordLabel(wait: true) { setPasswordForm.find('label', 0) }
        passwordInputField(wait: true, required: false) { setPasswordForm.find('input', 0) }
        confirmPasswordLabel(wait: true) { setPasswordForm.find('label', 1) }
        confirmPasswordInputField(wait: true, required: false) { setPasswordForm.find('input', 1) }

        alertBasic(wait: true) { setPasswordForm.find('.message') }
        succesAlert(wait: true) { setPasswordForm.find('.alert-info') }
        dangerAlert(wait: true) { setPasswordForm.find('.alert-danger') }

        activateButton(wait: true) { setPasswordForm.find('button') }
        loginButton(wait: true, required: false) { $('.ActivateUser-activate') }

        invalidTokenInfo(wait: true, required: false) { $('.ActivateUser-invalidToken') }
        loader(required: false) { $('div[id="loader-container"]') }
    }

    def enterPassword(password) {
        helpFunctions.waitSomeTime(Config.waitMedium)
        waitFor { passwordInputField.isDisplayed() }; passwordInputField.value(password)
    }

    def enterPasswordConfirmation(password) {
        waitFor { confirmPasswordInputField.isDisplayed() }; confirmPasswordInputField.value(password)
    }

    def clickActivateButton() {
        waitFor { !loader.isDisplayed() }
        waitFor { activateButton.isDisplayed() }; activateButton.click()
    }

    def clickLoginButton() {
        waitFor { loginButton.isDisplayed() }; loginButton.click()
    }
}