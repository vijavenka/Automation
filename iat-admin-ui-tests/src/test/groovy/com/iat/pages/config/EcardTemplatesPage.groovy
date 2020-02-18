package com.iat.pages.config

import com.iat.Config
import com.iat.pages.modules.LeftSidePanelNavigationModule
import com.iat.pages.modules.TopNavigationModule
import com.iat.stepdefs.utils.HelpFunctions
import geb.Page

class EcardTemplatesPage extends Page {

    def helpFunctions = new HelpFunctions()

    static url = '/hr/config/ecard-templates'
    static atCheckWaiting = true
    static at = { waitFor { browser.currentUrl.contains("ecard-templates") } }

    static content = {
        topNavigation(wait: true) { module TopNavigationModule }
        leftSidePanelNavigation(wait: true) { module LeftSidePanelNavigationModule }

        ecardTemplatesSettingsArea(wait: true) { $('.Ecards').find('.row.ui-section', 0) }
        templatesSettingUseDefaultAndCustomRadioButton(wait: true) {
            ecardTemplatesSettingsArea.find("md-radio-button", 0)
        }
        templatesSettingUseOnlyCustomRadioButton(wait: true) { ecardTemplatesSettingsArea.find("md-radio-button", 1) }
        templateSettingsSaveButton(wait: true) { ecardTemplatesSettingsArea.find("button") }
        customTemplatesArea(wait: true) { $('.Ecards').find('.row.ui-section', 1) }
        customTemplatesAddNewTemplateButton(wait: true) { customTemplatesArea.find('.btn-primary') }
        customTemplatesTemplateCardBasic(wait: true) { customTemplatesArea.find('.Ecards-ecard').find('.card') }
        customTemplatesTemplateCardImageBasic(wait: true) { customTemplatesTemplateCardBasic.find('img') }
        customTemplatesTemplateCardNameBasic(wait: true) { customTemplatesTemplateCardBasic.find('.card-title') }
        customTemplatesTemplateCardRemoveButtonBasic(wait: true) {
            customTemplatesTemplateCardBasic.find('.color-danger')
        }
        customTemplatesTemplateCardEditButtonBasic(wait: true) {
            customTemplatesTemplateCardBasic.find('.color-primary')
        }
        defaultTemplatesArea(wait: true) { $('.Ecards').find('.row.ui-section', 2) }
        defaultTemplatesAddNewTemplateButton(wait: true) { defaultTemplatesArea.find('.btn-primary') }
        defaultTemplatesTemplateCardBasic(wait: true) { defaultTemplatesArea.find('.Ecards-ecard').find('.card') }
        defaultTemplatesTemplateCardImageBasic(wait: true) { defaultTemplatesTemplateCardBasic.find('img') }
        defaultTemplatesTemplateCardNameBasic(wait: true) { defaultTemplatesTemplateCardBasic.find('.card-title') }
        defaultTemplatesTemplateCardRemoveButtonBasic(wait: true, required: false) {
            defaultTemplatesTemplateCardBasic.find('.color-danger')
        }
        defaultmTemplatesTemplateCardEditButtonBasic(wait: true, required: false) {
            defaultTemplatesTemplateCardBasic.find('.color-primary')
        }
        deleteConfirmationPopup(wait: true, required: false) { $('md-dialog') }
        deleteConfirmationPopupInfo(wait: true) { deleteConfirmationPopup.find('h2') }
        deleteConfirmationPopupCancelButton(wait: true) { deleteConfirmationPopup.find('button', 0) }
        deleteConfirmationPopupDeleteButton(wait: true) { deleteConfirmationPopup.find('button', 1) }
    }

    def clickSaveTemplateSettingsButton() {
        waitFor { templateSettingsSaveButton.isDisplayed() }; templateSettingsSaveButton.click()
    }

    def selectTemplatesSettingUseDefaultAndCustomRadioButton() {
        waitFor { templatesSettingUseDefaultAndCustomRadioButton.isDisplayed() };
        templatesSettingUseDefaultAndCustomRadioButton.click()
    }

    def selectTemplatesSettingUseOnlyCustomRadioButton() {
        waitFor { templatesSettingUseOnlyCustomRadioButton.isDisplayed() };
        templatesSettingUseOnlyCustomRadioButton.click()
    }

    def clickAddCustomTemplateButton() {
        waitFor { customTemplatesAddNewTemplateButton.isDisplayed() }; customTemplatesAddNewTemplateButton.click()
    }

    def clickCustomTemplateCardRemoveButton(number) {
        waitFor { customTemplatesTemplateCardRemoveButtonBasic[number].isDisplayed() };
        customTemplatesTemplateCardRemoveButtonBasic[number].click()
    }

    def clickCustomTemplateCardEditButton(number) {
        waitFor { customTemplatesTemplateCardEditButtonBasic[number].isDisplayed() };
        customTemplatesTemplateCardEditButtonBasic[number].click()
    }

    def clickCancelButtonOnDeletConfirmationPopup() {
        helpFunctions.waitSomeTime(Config.waitMedium)
        waitFor { deleteConfirmationPopupCancelButton.isDisplayed() }; deleteConfirmationPopupCancelButton.click()
    }

    def clickDeleteButtonOnDeleteConfirmationPopup() {
        helpFunctions.waitSomeTime(Config.waitMedium)
        waitFor { deleteConfirmationPopupDeleteButton.isDisplayed() }; deleteConfirmationPopupDeleteButton.click()
    }

    def getStateUseDefaultAndCustomRB() {
        return templatesSettingUseDefaultAndCustomRadioButton.attr('aria-checked') == 'true'
    }

    def checkIfCustomTemplateOnListByName(String name) {
        customTemplatesTemplateCardNameBasic.text() == name
    }
}