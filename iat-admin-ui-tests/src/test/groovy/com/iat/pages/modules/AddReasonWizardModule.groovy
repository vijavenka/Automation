package com.iat.pages.modules

import com.iat.Config
import com.iat.stepdefs.utils.HelpFunctions
import geb.Module

class AddReasonWizardModule extends Module {

    def helpFunctions = new HelpFunctions()

    static content = {
        addReasonForm(wait: true) { $('form[name="vm.reasonsForm.form"]') }
        reasonLabel(wait: true) { addReasonForm.find('label', 0) }
        reasonInputField(wait: true) { addReasonForm.find('input', 0) }
        managerToUserLimitLabel(wait: true) { addReasonForm.find('label', 1) }
        managerToUserGlobalCheckbox(wait: true) {
            addReasonForm.find('.FormlyForms-mdRadio', 0).find('md-radio-button', 0)
        }
        managerToUserDefineCheckbox { addReasonForm.find('.FormlyForms-mdRadio', 0).find('md-radio-button', 1) }
        managerToUserMinInput(wait: true) { addReasonForm.find('input', 1) }
        managerToUserMaxInput(wait: true) { addReasonForm.find('input', 2) }
        userToUserLimitLabel(wait: true) { addReasonForm.find('label', 2) }
        userToUserGlobalCheckbox { addReasonForm.find('.FormlyForms-mdRadio', 1).find('md-radio-button', 0) }
        userToUserDefineCheckbox { addReasonForm.find('.FormlyForms-mdRadio', 1).find('md-radio-button', 1) }
        userToUserMinInput(wait: true) { addReasonForm.find('input', 3) }
        userToUserMaxInput(wait: true) { addReasonForm.find('input', 4) }
        cancelButton(wait: true) { addReasonForm.find('button', 0) }
        saveButton(wait: true) { addReasonForm.find('button', 1) }
        alert(wait: true) { addReasonForm.find('.callout') }
        alertSingleMessage(wait: true) { alert.find('p') }
    }

    def clickCancelButton() {
        waitFor { cancelButton.isDisplayed() }; cancelButton.click()
    }

    def enterReasonName(String reasonName) {
        waitFor { reasonInputField.isDisplayed() }; reasonInputField.value(reasonName)
    }

    def clickSaveButton() {
        waitFor { !(saveButton.attr('disabled') == 'true') }
        waitFor { saveButton.isDisplayed() }; saveButton.click()
    }

    def clickManagerToUserDefineCheckbox() {
        waitFor { managerToUserDefineCheckbox.isDisplayed() }; managerToUserDefineCheckbox.click()
    }

    def clickUserToUserDefineCheckbox() {
        waitFor { userToUserDefineCheckbox.isDisplayed() }; userToUserDefineCheckbox.click()
    }

    def enterManagerToUserMinValue(value) {
        waitFor { managerToUserMinInput.isDisplayed() }; managerToUserMinInput.value(value)
    }

    def enterManagerToUserMaxValue(value) {
        waitFor { managerToUserMaxInput.isDisplayed() }; managerToUserMaxInput.value(value)
    }

    def fillManagerToUserValues(min, max) {
        enterManagerToUserMinValue(min)
        enterManagerToUserMaxValue(max)
    }

    def enterUserToUserMinValue(value) {
        waitFor { userToUserMinInput.isDisplayed() }; userToUserMinInput.value(value)
    }

    def enterUserToUserMaxValue(value) {
        waitFor { userToUserMaxInput.isDisplayed() }; userToUserMaxInput.value(value)
    }

    def fillUserToUserValues(min, max) {
        enterUserToUserMinValue(min)
        enterUserToUserMaxValue(max)
    }

    def addReasonWithSettings(def reasonSettings) {
        //reasonSettings:
        //[reasonName: String, globals: [manager2User: bool, user2User: bool], reasonManager2User: String[2], reasonUser2User: String[2]]
        enterReasonName(reasonSettings.reasonName)
        if (!reasonSettings.globals.manager2User) {
            System.out.println(reasonSettings.reasonManager2User[0] + "  " + reasonSettings.reasonManager2User[1])
            clickManagerToUserDefineCheckbox()
            helpFunctions.waitSomeTime(Config.waitShort)
            fillManagerToUserValues(reasonSettings.reasonManager2User[0], reasonSettings.reasonManager2User[1])
        }

        if (!reasonSettings.globals.user2User) {
            System.out.println(reasonSettings.reasonUser2User[0] + "  " + reasonSettings.reasonUser2User[1])
            clickUserToUserDefineCheckbox()
            helpFunctions.waitSomeTime(Config.waitShort)
            fillUserToUserValues(reasonSettings.reasonUser2User[0], reasonSettings.reasonUser2User[1])
        }
        clickSaveButton()
    }
}
