package com.iat.pages.specsaversAdmin

import com.iat.pages.specsaversAdmin.modules.TabNaviagationModule
import geb.Page

class SpecsaversAdminImportPage extends Page {

    static url = '/admin/specsavers/import'
    static at = {
        waitFor {
            getTitle().contains('Specsavers - import | epoints')
        }
    }

    static content = {
        tabNaviagationModule { module TabNaviagationModule }

        importTab { $('.nav-tabs').find('li', 1).find('a') }
        bulkUploadInput { $('.tab-pane').find('input').last() }
        bulkUploadInputVisual { $('.drop-box') }
        importButton { $('.btn-green.spinner-button') }
        uploadAlertSuccess { $('.alert-success') }
        uploadAlerDanger { $('.alert-danger') }

        signOutButton { $('.Specsavers-logout') }
        successMessage(required: false) { $('span', text: 'Points awarded successfully.') }
    }
    //import tab
    def clickImportTab() { waitFor { importTab.isDisplayed() }; importTab.click() }

    def clickImportButton() { waitFor { importButton.isDisplayed() }; importButton.click() }

    def clickOnImportFileButton() { waitFor { bulkUploadInputVisual.isDisplayed() }; bulkUploadInputVisual.click() }

}