package com.iat.pages

class SignInPage extends AbstractPage {

    static url = '/sign-in'
    static at = {
        waitFor {
            browser.currentUrl.contains("/sign-in")
        }
    }

    static content = {

        signInForm(wait: true) { $(".main-view .sign-in-form") }
        signInFormHeader(wait: true) { signInForm.find('h3') }
        loginPageEmailInputFieldLabel(wait: true) { signInForm.find('label', for: 'email') }
        loginPageEmailInputField(wait: true) { signInForm.find('#email') }
        loginPageErrorBasic(wait: true) { signInForm.find('.valid-error') }
        loginPageErrorDanger(wait: true) { signInForm.find('.alert.alert-danger') }
        loginPagePasswordInputFieldLabel(wait: true) { signInForm.find('label', for: 'password') }
        loginPagePasswordInputField(wait: true) { signInForm.find('#password') }
        loginPageForgotPasswordLink(wait: true) { signInForm.find('a', text: 'Forgot password?') }
        loginPageSignInButton(wait: true) { signInForm.find('.btn-green') }
        loginPageSignInWithFacebookButton(wait: true, required: false) { $(".main-view .btn-facebook") }
    }

    def enterEmailAddressLoginPage(email) {
        waitFor { loginPageEmailInputField.isDisplayed() }; loginPageEmailInputField.value(email)
    }

    def enterPasswordLoginPage(password) {
        waitFor { loginPagePasswordInputField.isDisplayed() }; loginPagePasswordInputField.value(password)
    }

    def enterLoginDataLoginPage(email, password) { enterEmailAddressLoginPage(email); enterPasswordLoginPage(password) }

    def clickSignInButtonLoginPage() { waitFor { loginPageSignInButton.isDisplayed() }; loginPageSignInButton.click() }

    def clickForgotPasswordLinkOnSignInPage() {
        waitFor { loginPageForgotPasswordLink.isDisplayed() }; loginPageForgotPasswordLink.click()
    }

    def signInWithCredentials(email, password) {
        enterLoginDataLoginPage(email, password)
        clickSignInButtonLoginPage()
    }
}