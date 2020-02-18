package com.iat.pages.userProfile

import geb.Page

class SetNewPasswordPage extends Page {

    static url = '/reset-password'
    static atCheckWaiting = true
    static at = { waitFor { browser.currentUrl.contains("reset-password") } }

    static content = {
        setNewPasswordForm(wait: true, required: false) { $('.ResetPassword') }
        pageHeader(wait: true) { setNewPasswordForm.find('h1') }
        passwordLabel(wait: true) { setNewPasswordForm.find('label', 0) }
        passwordInputField(wait: true, required: false) { setNewPasswordForm.find('input', 0) }
        confirmPasswordLabel(wait: true) { setNewPasswordForm.find('label', 1) }
        confirmPasswordInputField(wait: true, required: false) { setNewPasswordForm.find('input', 1) }

        alertBasic(wait: true) { setNewPasswordForm.find('.message') }
        succesAlert(wait: true) { setNewPasswordForm.find('.alert-info') }
        dangerAlert(wait: true) { setNewPasswordForm.find('.alert-danger') }

        backButton(wait: true) { setNewPasswordForm.find('button', 0) }
        changePasswordButton(wait: true) { setNewPasswordForm.find('button', 1) }
        loginButton(wait: true, required: false) { setNewPasswordForm.find('.ResetPassword-login') }

        invalidTokenInfo(wait: true, required: false) { $('.ResetPassword-invalidToken') }
    }

    def enterPassword(password) {
        waitFor { passwordInputField.isDisplayed() }; passwordInputField.value(password)
    }

    def enterPasswordConfirmation(password) {
        waitFor { confirmPasswordInputField.isDisplayed() }; confirmPasswordInputField.value(password)
    }

    def clickBackButton() {
        waitFor { backButton.isDisplayed() }; backButton.click()
    }

    def clickChangePasswordButton() {
        waitFor { changePasswordButton.isDisplayed() }; changePasswordButton.click()
    }

    def clickLoginButton() {
        waitFor { loginButton.isDisplayed() }; loginButton.click()
    }
}