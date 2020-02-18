package com.iat.pages.userProfile

import com.iat.pages.modules.TopNavigationModule
import geb.Page

class ForgotPasswordPage extends Page {

    static url = '/forgot-password/'
    static atCheckWaiting = true
    static at = { waitFor { browser.currentUrl.contains("forgot-password") } }

    static content = {
        topNavigation(wait: true) { module TopNavigationModule }

        forgotPasswordForm(wait: true) { $('form[name="vm.forgotPasswordForm"]') }

        pageHeader(wait: true) { forgotPasswordForm.find('h1') }
        pageMainInfo(wait: true) { forgotPasswordForm.find('p') }

        emailLabel(wait: true) { forgotPasswordForm.find('label') }
        emailInputField(wait: true) { forgotPasswordForm.find('input') }
        sendButton(wait: true) { forgotPasswordForm.find('button', 1) }
        backButton(wait: true) { forgotPasswordForm.find('button', 0) }

        alertBasic(wait: true) { forgotPasswordForm.find('.md-input-message-animation') }
        succesAlert(wait: true) { forgotPasswordForm.find('.alert-info') }
        dangerAlert(wait: true) { forgotPasswordForm.find('.alert-danger') }
    }

    def enterEmail(email) {
        waitFor { emailInputField.isDisplayed() }; emailInputField.value(email)
    }

    def clickBackButton() {
        waitFor { backButton.isDisplayed() }; backButton.click()
    }

    def clickSendButton() {
        waitFor { sendButton.isDisplayed() }; sendButton.click()
    }
}