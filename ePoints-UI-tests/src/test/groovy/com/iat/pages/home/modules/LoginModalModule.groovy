package com.iat.pages.home.modules

import geb.Module

class LoginModalModule extends Module {

    static content = {
        loginModal(wait: true) { $('.modal-body') }
        loginModalHeader(wait: true) { loginModal.find('.sign-in-form').find('h3') }
        loginModalEmailInputFieldLabel(wait: true) { loginModal.find('label', for: 'email') }
        loginModalEmailInputField(wait: true) { loginModal.find('#email') }
        loginModalErrorBasic(wait: true) { loginModal.find('.valid-error') }
        loginModalErrorDanger(wait: true) { loginModal.find('.alert.alert-danger') }
        loginModalPasswordInputFieldLabel(wait: true) { loginModal.find('label', for: 'password') }
        loginModalPasswordInputField(wait: true) { loginModal.find('#password') }
        loginModalForgotPasswordLink(wait: true) { loginModal.find('a', text: 'Forgot password?') }
        loginModalSignInButton(wait: true) { loginModal.find('.btn-green') }
        loginModalJoinHereLink(wait: true, required: false) { loginModal.find('a', text: 'Join here') }
        loginModalSignInWithFacebookButton(wait: true, required: false) { loginModal.find('.btn-facebook ') }
        loginModalCloseButton { $('.modal-close-btn') }
    }

    def enterEmailAddresModal(email) {
        waitFor { loginModalEmailInputField.isDisplayed() }; loginModalEmailInputField.value(email)
    }

    def enterPasswordModal(password) {
        waitFor { loginModalPasswordInputField.isDisplayed() }
        loginModalPasswordInputField.value(password)
    }

    def enterLoginDataModal(email, password) {
        enterEmailAddresModal(email); enterPasswordModal(password)
    }

    def clickSignInButtonModal() {
        waitFor { loginModalSignInButton.isDisplayed() }; loginModalSignInButton.click()
    }

    def clickForgotPasswordModalLink() {
        waitFor { loginModalForgotPasswordLink.isDisplayed() }; loginModalForgotPasswordLink.click()
    }

}
