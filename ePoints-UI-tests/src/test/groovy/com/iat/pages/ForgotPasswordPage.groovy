package com.iat.pages

class ForgotPasswordPage extends AbstractPage {

    static url = '/forgot'
    static at = {
        waitFor {
            getTitle().contains('Forgotten your password | epoints')
        }
    }

    static content = {

        pageHeader(wait: true) { $('.ForgotPassword-title', 0) }
        pageMainInformation(wait: true) { $('.ForgotPassword-description', 0) }
        forgotPasswordForm(wait: true) { $('.ForgotPassword-form') }
        emailInputFieldLabel(wait: true) { forgotPasswordForm.find('label') }
        emailInputField(wait: true) { forgotPasswordForm.find('input') }
        sendButton(wait: true) { forgotPasswordForm.find('.btn-green') }
        emailIsRequiredInvalidAlert(wait: true) { forgotPasswordForm.find('.valid-error div') }
        emaildoesNotExistAlert(wait: true) { forgotPasswordForm.find('.alert-danger') }
        emailSentInfoHeader(wait: true) { $('.ForgotPassword-title', 1) }
        emailSentInfoDescription(wait: true) { $('.ForgotPassword-description', 1) }
    }

    def enterEmail(email) { waitFor { emailInputField.isDisplayed() }; emailInputField.value(email) }

    def clickSendButton() { waitFor { sendButton.isDisplayed() }; sendButton.click() }
}