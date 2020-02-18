package com.iat.pages

class ResetPasswordPage extends AbstractPage {

    static url = '/reset/token'
    static at = {
        waitFor() {
            getTitle().contains('Reset your password | epoints')
        }
    }

    static content = {

        pageSectionText(wait: true) { $('.section') }
        pageHeader(wait: true) { $('.ForgotPassword-title') }
        changePasswordForm(wait: true) { $('.ForgotPassword-form') }
        labelPassword(wait: true) { changePasswordForm.find('label', for: 'pass') }
        passwordInputField { changePasswordForm.find('#pass') }
        passwordAlert(wait: true, required: false) { changePasswordForm.find('.valid-error', 0) }
        labelConfirmPassword(wait: true) { changePasswordForm.find('label', for: 'retypePass') }
        confirmPasswordInputField(wait: true) { changePasswordForm.find('#retypePass') }
        passwordConfirmationAlert(wait: true, required: false) { changePasswordForm.find('.valid-error', 1) }
        passwordDoNotMatchnAlert(required: false) { changePasswordForm.find('.valid-error', 2) }
        changeButton(wait: true) { changePasswordForm.find('.btn-green') }
        passwordStrenghtElement(wait: true, required: false) { $('.meter') }
        invalidTokenMessage(wait: true) { changePasswordForm.find('.alert-danger') }
    }

    def enterPassword(password) { waitFor { passwordInputField.isDisplayed() }; passwordInputField.value(password) }

    def enterPasswordConfirmation(passConfirmation) {
        waitFor { confirmPasswordInputField.isDisplayed() }; confirmPasswordInputField.value(passConfirmation)
    }

    def fillChangePasswordForm(password, passConfirmation) {
        enterPassword(password); enterPasswordConfirmation(passConfirmation)
    }

    def clickChangePasswordbutton() { waitFor { changeButton.isDisplayed() }; changeButton.click() }
}