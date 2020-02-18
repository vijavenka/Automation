package com.iat.pages.modules

import com.iat.Config
import com.iat.stepdefs.utils.HelpFunctions
import geb.Module

class ReasonsGlobalSettingsModule extends Module {

    def helpFunctions = new HelpFunctions()

    static content = {
        globalSettingsForm(wait: true) { $('.EdiReason') }
        managerToUserMinInputLabel(wait: true) { globalSettingsForm.find('label', 0) }
        managerToUserMinInput(wait: true) { globalSettingsForm.find('input', 0) }
        managerToUserMaxInputLabel(wait: true) { globalSettingsForm.find('label', 1) }
        managerToUserMaxInput(wait: true) { globalSettingsForm.find('input', 1) }
        userToUserMinInputLabel(wait: true) { globalSettingsForm.find('label', 2) }
        userToUserMinInput(wait: true) { globalSettingsForm.find('input', 2) }
        userToUserMaxInputLabel(wait: true) { globalSettingsForm.find('label', 3) }
        userToUserMaxInput(wait: true) { globalSettingsForm.find('input', 3) }
        cancelButton(wait: true) { globalSettingsForm.find('button', 0) }
        saveButton(wait: true) { globalSettingsForm.find('button', 1) }
        saveConfirmationPopup(required: false) { $('md-dialog') }
        saveConfirmationPopupMessage(required: false) { saveConfirmationPopup.find('h2') }
        saveConfirmationPopupNoButton(required: false) { saveConfirmationPopup.find('button', 0) }
        saveConfirmationPopupYesButton(required: false) { saveConfirmationPopup.find('button', 1) }
        alert(wait: true, required: false) { globalSettingsForm.find('.callout') }
        alertSingleMessage(wait: true, required: false) { alert.find('p') }
    }

    def clickCancelButton() {
        waitFor { cancelButton.isDisplayed() }; cancelButton.click()
    }

    def clickSaveButton() {
        waitFor { saveButton.isDisplayed() }; saveButton.click()
    }

    def enterManagerToUserMin(String value) {
        waitFor { managerToUserMinInput.isDisplayed() }; managerToUserMinInput.value(value)
    }

    def enterManagerToUserMax(String value) {
        waitFor { managerToUserMaxInput.isDisplayed() }; managerToUserMaxInput.value(value)
    }

    def enterUserToUserMin(String value) {
        waitFor { userToUserMinInput.isDisplayed() }; userToUserMinInput.value(value)
    }

    def enterUserToUserMax(String value) {
        waitFor { userToUserMaxInput.isDisplayed() }; userToUserMaxInput.value(value)
    }

    def fillReasonsGlobalSettingsForm(String mUMin, String mUMax, String uUMin, String uUMax) {
        enterManagerToUserMin(mUMin)
        enterManagerToUserMax(mUMax)
        enterUserToUserMin(uUMin)
        enterUserToUserMax(uUMax)
    }

    def clickYesButtonOnSaveConfirmationPopup() {
        helpFunctions.waitSomeTime(Config.waitMedium)
        waitFor { saveConfirmationPopupYesButton.isDisplayed() }; saveConfirmationPopupYesButton.click()
    }

    def getCurrentSettings() {
        def globalConfig = new String[4]
        waitFor { managerToUserMinInput.isDisplayed() }; globalConfig[0] = managerToUserMinInput.value()
        waitFor { managerToUserMaxInput.isDisplayed() }; globalConfig[1] = managerToUserMaxInput.value()
        waitFor { userToUserMinInput.isDisplayed() }; globalConfig[2] = userToUserMinInput.value()
        waitFor { userToUserMaxInput.isDisplayed() }; globalConfig[3] = userToUserMaxInput.value()

        globalConfig
    }
}