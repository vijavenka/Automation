package com.iat.pages.allocationsHistory.modules

import geb.Module

class ExportPopupModule extends Module {

    static content = {
        popup(required: false) { $('md-dialog') }
        header(required: false) { popup.$('h2') }
        info(required: false) { popup.$('p') }
        closeButton(required: false) { popup.$('button') }
    }

    def closePopup() {
        waitFor { closeButton.isDisplayed() }; closeButton.click();
        waitFor { !popup.isDisplayed() }
    }

}