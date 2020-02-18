package com.iat.pages.points

import geb.Page

class TransitionPage extends Page {

    static url = '/transition'
    static at = {
        waitFor {
            browser.currentUrl.contains("/transition")
        }
    }

    static content = {
        transitionPagePointsInfo(wait: true) { $('.TransitionInfo-points') }
        transitionPageRetailerInformation(wait: true) { $('.TransitionInfo-retailer') }
        transitionPageEpointsAnimation(wait: true) { $('.TransitionInfo-anim') }

        transitionAnimation(required: false) { $('.TransitionInfo-anim') }

        transitonPageContent(wait: true) { $('.TransitionInfo') }
        signInWithFacebookButton(wait: true) { transitonPageContent.find('.btn-facebook') }
        signInForm(wait: true) { transitonPageContent.find('.sign-in-form') }
        emailAddressInputField(wait: true) { signInForm.find('#email') }
        passwordInputField(wait: true) { signInForm.find('#password') }
        signInLabelBasic(wait: true) { signInForm.find('label') }
        signInErrorBasic(wait: true) { signInForm.find('.valid-error') }
        signInButton(wait: true) { signInForm.find('.btn-green') }
        forgotPasswordLink(wait: true) { signInForm.find('a', text: 'Forgot password?') }
        authorizationFaildAlert(wait: true) { signInForm.find('.alert-danger') }
        joinNowLink(wait: true) { transitonPageContent.find('a', text: 'Join now') }
        joinNowInformation(wait: true) { transitonPageContent.find('p', 1) }
        continueAnywayButton(wait: true) { transitonPageContent.find('.btn-yellow') }
        continueAnywayInformation(wait: true) { transitonPageContent.find('.epoints-info') }
    }

    def clickSignInWithFacebookButton() { waitFor { signInWithFacebookButton }; signInWithFacebookButton.click() }

    def enterEmailAddress(email) {
        waitFor { emailAddressInputField.isDisplayed() }; emailAddressInputField.value(email)
    }

    def enterPassword(password) { waitFor { passwordInputField.isDisplayed() }; passwordInputField.value(password) }

    def fillLoginData(email, password) { enterEmailAddress(email); enterPassword(password) }

    def clickLoginButton() { waitFor { signInButton.isDisplayed() }; signInButton.click() }

    def clickJoinNowLink() { waitFor { joinNowLink.isDisplayed() }; joinNowLink.click() }

    def clickForgotPasswordLink() { waitFor { forgotPasswordLink.isDisplayed() }; forgotPasswordLink.click() }

    def clickContinueAnywayButton() { waitFor { continueAnywayButton.isDisplayed() }; continueAnywayButton.click() }
}
