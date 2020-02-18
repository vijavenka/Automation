package com.iat.pages.specsaversAdmin

import com.iat.pages.specsaversAdmin.modules.TabNaviagationModule
import geb.Page

class SpecsaversAdminStorePage extends Page {

    static url = '/admin/specsavers/store'
    static at = {
        waitFor {
            getTitle().contains('Specsavers - store | epoints')
        }
    }

    static content = {
        tabNaviagationModule { module TabNaviagationModule }

        storeTab { $('.nav-tabs').find('li', 3).find('a') }
        storeHeader { $('.form-group', 0).find('label', 0) }
        storeNameLabel { $('.form-group', 1).find('label', 0) }
        storeNameInputField { $('.form-group', 1).find('input') }
        storeEmailLabel { $('.form-group', 2).find('label', 0) }
        storeEmailInputField { $('.form-group', 2).find('input') }
        clearNewStoreDataButton { $('.clear') }
        sendNewStoreButton { $('.btn-green') }
        sendAlertSuccess { $('.alert-success') }
        sendAlertDanger { $('.alert-danger') }
        nameEmailValidErrorBasic { $('.valid-error') }
    }

    //store tab
    def clickStoreTab() { waitFor { storeTab.isDisplayed() }; storeTab.click() }

    def enterNewStoreEmail(email) { waitFor { storeEmailInputField.isDisplayed() }; storeEmailInputField.value(email) }

    def enterNewStoreName(name) { waitFor { storeNameInputField.isDisplayed() }; storeNameInputField.value(name) }

    def clickSendButton() { waitFor { sendNewStoreButton.isDisplayed() }; sendNewStoreButton.click() }

    def clickClearButton() { waitFor { clearNewStoreDataButton.isDisplayed() }; clearNewStoreDataButton.click() }

}