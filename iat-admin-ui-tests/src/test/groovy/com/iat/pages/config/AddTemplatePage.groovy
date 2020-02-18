package com.iat.pages.config

import com.iat.pages.modules.LeftSidePanelNavigationModule
import com.iat.pages.modules.TopNavigationModule
import geb.Page

class AddTemplatePage extends Page {

    static url = '/hr/config/ecard-templates/create'
    static atCheckWaiting = true
    static at = {
        waitFor {
            browser.currentUrl.contains("ecard-templates/create") || browser.currentUrl.contains("ecard-templates/edit")
        }
    }

    static content = {
        topNavigation(wait: true) { module TopNavigationModule }
        leftSidePanelNavigation(wait: true) { module LeftSidePanelNavigationModule }

        addTemplateForm(wait: true) { $('.AddTemplate') }
        templateNameInputField(wait: true) { addTemplateForm.find('input', 0) }
        dragAndDropImageInput(wait: true) { addTemplateForm.find('.drop-box') }
        uploadImageInput(wait: true) { $('input[name="vm.formlyForm.form_imageInput_imageUrl_2"]').last() }
        ecardPreview(wait: true) { addTemplateForm.find('img') }
        cancelButton(wait: true) { addTemplateForm.find('button', 0) }
        saveButton(wait: true) { addTemplateForm.find('button', 1) }
    }

    def enterNewTemplateName(templateName) {
        waitFor { templateNameInputField.isDisplayed() }; templateNameInputField.value(templateName)
    }

    def addNewImage(imageName) {
        uploadImageInput = (new File('src//images//' + imageName).getAbsolutePath().replace('\\', '\\\\'))

    }

    def clickCancelButton() {
        waitFor { cancelButton.isDisplayed() }; cancelButton.click()
    }

    def clickSaveButton() {
        waitFor { saveButton.isDisplayed() }; saveButton.click()
    }
}
