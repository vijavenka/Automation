package com.iat.pages.home.modules

import geb.Module

class SignInModule extends Module {

    static content = {
        signInMainPanel(wait: true) { $('.sign-in__panel').last() }
        signInMainPanelTitle(wait: true) { $('h3[translate="login.headline"]', 0) }
        signInMainForm(wait: true) { $('form[name="loginForm"]', 0) }

        signInEmailLabel(wait: true) { signInMainForm.find('label[for="email"]', 0) }
        signInEmailInputField(wait: true) { signInMainForm.find('input[id="email"]', 0) }
        signInPasswordLabel(wait: true) { signInMainForm.find('label[for="password"]', 0) }
        signInPasswordInputField(wait: true) { signInMainForm.find('input[id="password"]') }
        signInForgotPasswordLink(wait: true) { signInMainForm.find('a[ui-sref="app.forgot"]') }
        signInSubmitButton(wait: true) { signInMainForm.find('button[type="submit"]') }
        errorBasic(wait: true) { signInMainForm.find('.valid-error') }
        errorDanger(wait: true) { signInMainForm.find('.alert.alert-danger') }
        signInWithFacebookButton(wait: true, required: false) { signInMainPanel.find('.btn-facebook') }
        closeButton(wait: true) { signInMainPanel.find('a', text: 'Close') }

        joinHereLink(wait: true, required: false) { signInMainPanel.find('a[ui-sref="app.join"]') }
    }

    //Atomic Actions for Sign In Form (Module)
    def enterEmailAddress(email) { waitFor { signInEmailInputField.isDisplayed() }; signInEmailInputField.value(email) }

    def enterPassword(password) {
        waitFor { signInPasswordInputField.isDisplayed() }; signInPasswordInputField.value(password)
    }

    def enterLoginData(email, password) { enterEmailAddress(email); enterPassword(password) }

    def clickSignInSubmitButton() { waitFor { signInSubmitButton.isDisplayed() }; signInSubmitButton.click() }

    def clickForgotPasswordLink() {
        waitFor { signInForgotPasswordLink.isDisplayed() }; signInForgotPasswordLink.click()
    }

    //Complex behaviour for SignIn
    def signInUserToEpointsCom(email, password) {
        assert signInMainPanelTitle.text() == "Sign in" || signInMainPanelTitle.text() == "Aanmelden"
        assert signInEmailLabel.text() == "Email address" || signInEmailLabel.text() == "E-mailadres"
        enterEmailAddress(email)
        assert signInPasswordLabel.text() == "Password" || signInPasswordLabel.text() == "Wachtwoord"
        enterPassword(password)
        clickSignInSubmitButton()
    }

    def clickJoinHereLink() {
        waitFor { joinHereLink.isDisplayed() }; joinHereLink.click()
    }
}