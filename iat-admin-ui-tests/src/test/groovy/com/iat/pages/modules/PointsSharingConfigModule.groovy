package com.iat.pages.modules

import geb.Module

class PointsSharingConfigModule extends Module {

    static content = {
        pageRoot(wait: true) { $("div.panel-body", 0) }
        optionRoot(wait: true) { pageRoot.find('form') }
        userWithUsersOption(wait: true) { optionRoot.find('div.formly-field', 0) }
        userWithUsersOptionLabel(wait: true) { userWithUsersOption.find('label') }
        userWithUsersOptionBasic(wait: true) { userWithUsersOption.find('md-radio-button') }
        managerWithUsersOption(wait: true) { optionRoot.find('div.formly-field', 1) }
        managerWithUsersOptionLabel(wait: true) { managerWithUsersOption.find('label') }
        managerWithUsersOptionBasic(wait: true) { managerWithUsersOption.find('md-radio-button') }
        userWithManagerOption(wait: true) { optionRoot.find('div.formly-field', 2) }
        userWithManagerOptionLabel(wait: true) { userWithManagerOption.find('label') }
        userWithManagerBasic(wait: true) { userWithManagerOption.find('md-radio-button') }
        saveButton(wait: true) { pageRoot.find('div').find('button') }
    }

    def clickUserWithUsersOption(String ariaLabel) {
        def temp = userWithUsersOption.find("md-radio-button[aria-label='" + ariaLabel + "']")
        waitFor { temp.isDisplayed(); temp.click() }
    }

    def clickManagerWithUsersOption(String ariaLabel) {
        def temp = managerWithUsersOption.find("md-radio-button[aria-label='" + ariaLabel + "']")
        waitFor { temp.isDisplayed(); temp.click() }
    }

    def clickUserWithManagerOption(String ariaLabel) {
        def temp = userWithManagerOption.find("md-radio-button[aria-label='" + ariaLabel + "']")
        waitFor { temp.isDisplayed(); temp.click() }
    }

    def checkIfUserWithUsersOption(String ariaLabel) {
        waitFor { userWithUsersOption.find("md-radio-button[aria-label='" + ariaLabel + "']").isDisplayed() }
        def temp = userWithUsersOption.find("md-radio-button[aria-label='" + ariaLabel + "']")
        temp.getAttribute('aria-checked') == 'true'
    }

    def checkIfManagerWithUsersOption(String ariaLabel) {
        waitFor { managerWithUsersOption.find("md-radio-button[aria-label='" + ariaLabel + "']").isDisplayed() }
        def temp = managerWithUsersOption.find("md-radio-button[aria-label='" + ariaLabel + "']")
        temp.getAttribute('aria-checked') == 'true'
    }

    def checkIfUserWithManagerOption(String ariaLabel) {
        waitFor { userWithManagerOption.find("md-radio-button[aria-label='" + ariaLabel + "']").isDisplayed() }
        def temp = userWithManagerOption.find("md-radio-button[aria-label='" + ariaLabel + "']")
        temp.getAttribute('aria-checked') == 'true'
    }

    def isConfigurationIdentical(def config) {
        boolean result = true

        if (config['userWithUsers'] != null)
            result = result && checkIfUserWithUsersOption(config['userWithUsers'])
        if (config['managerWithUsers'] != null)
            result = result && checkIfManagerWithUsersOption(config['managerWithUsers'])
        if (config['userWithManager'] != null)
            result = result && checkIfUserWithManagerOption(config['userWithManager'])

        result
    }

    def setManagerWithUsersOption(String managerWithUsers) {
        clickManagerWithUsersOption(managerWithUsers)
        saveButton.click()
    }

    def setConfiguration(def config) {
        if (config['userWithUsers'] != null)
            clickUserWithUsersOption(config['userWithUsers'])
        if (config['managerWithUsers'] != null)
            clickManagerWithUsersOption(config['managerWithUsers'])
        if (config['userWithManager'] != null)
            clickUserWithManagerOption(config['userWithManager'])
    }

    def setConfigurationWithSave(def config) {
        setConfiguration(config)
        saveButton.click()
    }

    def getConfiguration() {
        def config = [:]

        waitFor { userWithUsersOption.find("md-radio-button[aria-checked='true']").isDisplayed() }
        config['userWithUsers'] = userWithUsersOption.find("md-radio-button[aria-checked='true']").getAttribute('aria-label')

        waitFor { managerWithUsersOption.find("md-radio-button[aria-checked='true']").isDisplayed() }
        config['managerWithUsers'] = managerWithUsersOption.find("md-radio-button[aria-checked='true']").getAttribute('aria-label')

        waitFor { userWithManagerOption.find("md-radio-button[aria-checked='true']").isDisplayed() }
        config['userWithManager'] = userWithManagerOption.find("md-radio-button[aria-checked='true']").getAttribute('aria-label')

        return config
    }

    def clickSaveButton() {
        waitFor { saveButton.isDisplayed() }; saveButton.click()
    }
}