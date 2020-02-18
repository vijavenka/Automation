package com.iat.pages.modules

import geb.Module

class AddTemplateWizardModule extends Module {

    static content = {
        addTemplateForm(wait: true) { $('.step3-modal.page-form-ele') }
        templateNameInputField(wait: true) { addTemplateForm.find('input', 0) }
        dragAndDropImageInput(wait: true) { addTemplateForm.find('.drop-box') }
        uploadImageInput(wait: true) { $('input[id="ngf-formlyForm.form_imageInput_imageUrl_2"]').last() }
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
