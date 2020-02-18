package com.iat.pages

class CompleteAccountPage extends AbstractPage {

    static url = '/join/confirm-email/token'
    static at = {
        waitFor(10) {
            getTitle().contains('Complete your epoints profile | epoints')
        }
    }

    static content = {

        header(wait: true) { $('h2') }
        passwordField(wait: true) { $('#pass') }
        confirmPasswordField(wait: true) { $('#retypePass') }
        nameField(wait: true) { $('input', name: 'firstname') }
        lastNameField(wait: true) { $('input', name: 'lastname') }
        genderMenWomenIcon(wait: true) { $('.gender-icon') }
        doneButton(wait: true) { $('.btn-green') }
        basicAlert(wait: true) { $('.valid-error') }
        basicLabel(wait: true) { $('label') }

        confirmationFailedMessage(wait: true) { $('.row').find('h1') }
        invalidLinkInformation(wait: true) { $('.row').find('p') }
    }

    def clickDoneButton() { waitFor { doneButton.isDisplayed() }; doneButton.click() }

    def enterPassword(password) {
        waitFor { passwordField.isDisplayed() }
        passwordField.value(password)
        sleep(200)
    }

    def enterPasswordConfirmation(passwordConfirmation) {
        waitFor { confirmPasswordField.isDisplayed() }
        confirmPasswordField.value(passwordConfirmation)
        sleep(200)
    }

    def enterName(name) {
        waitFor { nameField.isDisplayed() }
        nameField.value(name)
        sleep(200)
    }

    def enterLastName(lastName) {
        waitFor { lastNameField.isDisplayed() }
        lastNameField.value(lastName)
        sleep(200)
    }

    def fillAllCompliteAccountData(password, passwordConfirmation, name, lastName) {
        enterPassword(password);
        enterPasswordConfirmation(passwordConfirmation);
        enterName(name)
        enterLastName(lastName)
    }

    def selectGenderOption(number) { genderMenWomenIcon[number].click() }

    def clickgetEpointsNowButton() { waitFor { getEpointsNowButton.isDisplayed() }; getEpointsNowButton.click() }

    def clickTellMeABitMoreLink() { waitFor { tellMeABitMoreLink.isDisplayed() }; tellMeABitMoreLink.click() }
}