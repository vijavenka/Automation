package com.iat.pages.config

import com.iat.pages.modules.LeftSidePanelNavigationModule
import com.iat.pages.modules.TopNavigationModule
import geb.Page

class AddReasonPage extends Page {

    static url = '/hr/config/reasons/create'
    static atCheckWaiting = true
    static at = { waitFor { browser.currentUrl.contains("reasons/create") } }

    static content = {
        topNavigation(wait: true) { module TopNavigationModule }
        leftSidePanelNavigation(wait: true) { module LeftSidePanelNavigationModule }

        addReasonForm(wait: true) { $('.AddReason.page-form-ele') }
        reasonLabel(wait: true) { addReasonForm.find('label', 0) }
        reasonInputField(wait: true) { addReasonForm.find('input', 0) }
        managerToUserLimitLabel(wait: true) { addReasonForm.find('label', 1) }
        managerToUserGlobalCheckbox(wait: true) {
            addReasonForm.find('.FormlyForms-mdRadio', 0).find('md-radio-button', 0)
        }
        managerToUserDefineCheckbox { addReasonForm.find('.FormlyForms-mdRadio', 0).find('md-radio-button', 1) }
        managerToUserMinInput(wait: true) {
            addReasonForm.find('input[id="vm.formlyForm.form_input_managerToUserMin_3"]')
        }
        managerToUserMaxInput(wait: true) {
            addReasonForm.find('input[id="vm.formlyForm.form_input_managerToUserMax_4"]')
        }
        userToUserLimitLabel(wait: true) { addReasonForm.find('label', 2) }
        userToUserGlobalCheckbox { addReasonForm.find('.FormlyForms-mdRadio', 1).find('md-radio-button', 0) }
        userToUserDefineCheckbox { addReasonForm.find('.FormlyForms-mdRadio', 1).find('md-radio-button', 1) }
        userToUserMinInput(wait: true) { addReasonForm.find('input[id="vm.formlyForm.form_input_userToUserMin_6"]') }
        userToUserMaxInput(wait: true) { addReasonForm.find('input[id="vm.formlyForm.form_input_userToUserMax_7"]') }
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
}