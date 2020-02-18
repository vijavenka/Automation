package com.iat.pages.config

import com.iat.pages.modules.LeftSidePanelNavigationModule
import com.iat.pages.modules.PointsSharingConfigModule
import com.iat.pages.modules.TopNavigationModule
import geb.Page

class PointsSharingPage extends Page {

    static url = '/hr/config/points-sharing'
    static at = { waitFor { browser.currentUrl.contains('/hr/config/points-sharing') } }

    static content = {
        topNavigation(wait: true) { module TopNavigationModule }
        leftSidePanelNavigation(wait: true) { module LeftSidePanelNavigationModule }
        mainModule { module PointsSharingConfigModule }

        pageRoot(wait: true) { $("div.panel-body", 0) }
        optionRoot(wait: true) { pageRoot.find('form') }
        globalPasswordOption(required: false) { optionRoot.find('div.formly-field', 4) }
        globalPasswordOptionLabel(required: false) { globalPasswordOption.find('label') }
        globalPasswordOptionBasic(required: false) { globalPasswordOption.find('md-radio-button') }
        globalPasswordInputField(required: false) { optionRoot.find('input') }
        globalPasswordInputFieldLabel(required: false) { optionRoot.find('label').last() }
    }

    def clickGlobalPasswordOption(String ariaLabel) {
        def temp = globalPasswordOption.find("md-radio-button[aria-label='" + ariaLabel + "']")
        waitFor { temp.isDisplayed(); temp.click() }
    }

    def enterGlobalPassword(String globalPassword) {
        waitFor { globalPasswordInputField.isDisplayed() }; globalPasswordInputField.value(globalPassword)
    }
}