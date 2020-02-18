package com.iat.pages.home.unitedModules

import geb.Module

class UnitedLandingJoinSectionModule extends Module {

    static content = {

        title(required: true, wait: true) { $('.BusinessJoinForm-header') }
        unitedIdInputLabel(required: true, wait: true) { $('.BusinessJoinForm-fieldLabel', 0) }
        unitedIdInput(required: true, wait: true) { $('.BusinessJoinForm-input.BusinessJoinForm-inputId') }
        emailInputLabel(required: true, wait: true) { $('.BusinessJoinForm-fieldLabel', 1) }
        emailInput(required: true, wait: true) { $('.BusinessJoinForm-input.BusinessJoinForm-inputEmail') }
        joinButton(required: true, wait: true) { $('.BusinessJoinForm-button') }
        alert(required: false, wait: true) { $('.BusinessJoinForm-messages.BusinessJoinForm--errorMessages') }

    }

    def enterUnitedId(unitedId) {
        waitFor { unitedIdInput.isDisplayed() }; unitedIdInput.value(unitedId)
    }

    def enterEmail(email) {
        waitFor { emailInput.isDisplayed() }; emailInput.value(email)
    }

    def clickJoinButton() {
        waitFor { joinButton.isDisplayed() }; joinButton.click()
    }

}