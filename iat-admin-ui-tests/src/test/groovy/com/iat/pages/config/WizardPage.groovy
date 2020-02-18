package com.iat.pages.config

import com.iat.Config
import com.iat.pages.modules.*
import com.iat.stepdefs.utils.HelpFunctions
import geb.Page

class WizardPage extends Page {

    static url = '/hr/wizard'
    static atCheckWaiting = true
    static at = { waitFor { browser.currentUrl.contains('/wizard') } }

    static content = {
        topNavigation(wait: true) { module TopNavigationModule }
        leftSidePanelNavigation(wait: true) { module LeftSidePanelNavigationModule }

        actualStepBase(wait: true) { $("div[class~='ui-wizard-form'][aria-hidden='false']") }
        nextButton(wait: true) { actualStepBase.find("button[ng-click*='next()']") }
        backButton(wait: true) { actualStepBase.find("button[ng-click~='vm.WizardService.currentStep']") }
        saveAllConfigButton(wait: true) { actualStepBase.find("button[ng-click='vm.finishWizard()']") }
        modulePointsSharing { module PointsSharingConfigWizardModule }
        moduleReasonsGlobals { module ReasonsGlobalSettingsWizardModule }
        moduleAddTemplate { module AddTemplateWizardModule }
        moduleAddReason { module AddReasonWizardModule }
        moduleReasons { module ReasonWizardModule }
        stepsList { $("div.wizard ul[role='tablist'] > li") }
        addNewTemplate(wait: true) { actualStepBase.find("button[ng-click*='EditTemplate()']") }
        useDefaultTemplateChBox(wait: true) { actualStepBase.find("md-checkbox[ng-model*='useDefaultTemplates']") }
        customTemplatesArea(wait: true) { $("div[ng-show*='customEcardTemplates']") }
        customTemplatesCardBasic(wait: true) { customTemplatesArea.find('.card-title') }
        customTemplatesTemplateCardRemoveButtonBasic(wait: true) { title ->
            customTemplatesArea[customTemplatesArea.findIndexOf {
                it.find(".card-title", text: title)
            }].find('div.card-action').find('a', text: 'REMOVE')
        }
        deleteConfirmationPopupDeleteButton(wait: true) { $('md-dialog').find('button').find(text: 'DELETE').parent() }

        loader(required: false) { $('div[id="loader-container"]') }
    }

    def helpFunctions = new HelpFunctions()

    def clickNext() {
        helpFunctions.waitSomeTime(Config.waitMedium)
        waitFor { actualStepBase.isDisplayed() }; waitFor { nextButton.isDisplayed() }; nextButton.click()
    }

    def clickBack() {
        helpFunctions.waitSomeTime(Config.waitMedium)
        waitFor { actualStepBase.isDisplayed() }; waitFor { backButton.isDisplayed() }; backButton.click()
    }

    def clickSaveAllConfig() {
        helpFunctions.waitSomeTime(Config.waitMedium)
        waitFor { actualStepBase.isDisplayed() }; waitFor { saveAllConfigButton.isDisplayed() };
        saveAllConfigButton.click()
    }

    def getActualStep() {
        waitFor { stepsList.size() == 4 }; stepsList.findIndexOf { it.hasClass("current") } + 1
    }

    def checkIfStepsDisabledFromNr(int firstInactiveStep) {
        stepsList.findIndexOf(firstInactiveStep - 1) { !it.hasClass("disabled") } == -1
    }

    def clickAddNewTemplate() {
        waitFor { addNewTemplate.isDisplayed() }; addNewTemplate.click()
    }

    def checkUseDefaultTemplates() {
        waitFor { useDefaultTemplateChBox.isDisplayed() }
        if (!useDefaultTemplateChBox.hasClass("md-checked")) {
            useDefaultTemplateChBox.click()
        }
    }

    def uncheckUseDefaultTemplates() {
        waitFor { useDefaultTemplateChBox.isDisplayed() }
        if (useDefaultTemplateChBox.hasClass("md-checked")) {
            useDefaultTemplateChBox.click()
        }
    }

    def goToAvailableStepByTopNavigation(int step) {
        waitFor { actualStepBase.isDisplayed() }
        waitFor { !loader.isDisplayed() }
        waitFor { stepsList.parent().find("li", step - 1).find("a").isDisplayed() }
        helpFunctions.scrolPageUpDown("up")
        stepsList.parent().find("li", step - 1).find("a").click()
        waitFor { getActualStep() == step }
    }

    def addCustomTemplate(def templateSettings) {
        clickAddNewTemplate()
        moduleAddTemplate.enterNewTemplateName(templateSettings.customTemplate.customTemplateName)
        moduleAddTemplate.addNewImage(templateSettings.customTemplate.imageName)
        waitFor { moduleAddTemplate.ecardPreview.isDisplayed() }
        waitFor { !(moduleAddTemplate.saveButton.attr('disabled') == 'true') }
        moduleAddTemplate.clickSaveButton()
    }

    def removeCustomTemplateByName(String name) {
        waitFor { customTemplatesTemplateCardRemoveButtonBasic(name).isDisplayed() }
        customTemplatesTemplateCardRemoveButtonBasic(name).click()
        deleteConfirmationPopupDeleteButton.click()
    }

    def deleteReasonFromListByName(String name) {
        moduleReasons.deleteReasonFromListByName()
    }

    def checkIfReasonOnList(def reasonSettings) {
        return moduleReasons.checkIfReasonOnList(reasonSettings)
    }

    def checkIfTemplateOnListByName(String name) {
        customTemplatesCardBasic.find(text: name).isEmpty() == false
    }

    def checkIfProperTemplateSettings(def templateSettings) {
        //templateSettings:
        //[useDefaultTemplates: bool, customTemplate: [customTemplateName: String, imageName: String]]
        def result = true
        if (templateSettings.customTemplate != null) {
            result = customTemplatesArea.children().size() == 1 &&
                    checkIfTemplateOnListByName(templateSettings.customTemplate.customTemplateName)
        }
        return result && templateSettings.useDefaultTemplates == useDefaultTemplateChBox.hasClass("md-checked")
    }

    def checkIfProperSettingsOnCurrentStep(def allSettings) {
        switch (getActualStep()) {
            case 1:
                return modulePointsSharing.isConfigurationIdentical(allSettings.pointsSharingSet)
            case 2:
                return moduleReasonsGlobals.getCurrentSettings() == allSettings.globalConfigSet
            case 3:
                return checkIfProperTemplateSettings(allSettings.templateSet)
            case 4:
                return checkIfReasonOnList(allSettings.reasonSet)
        }
    }

    def checkIfNoSettingsOnCurrentStep() {
        switch (getActualStep()) {
            case 2:
                return moduleReasonsGlobals.getCurrentSettings() == [:]
            case 3:
                return customTemplatesArea.children().size() == 0 && useDefaultTemplateChBox.hasClass("md-checked")
            case 4:
                return moduleReasons.reasonListRowsAmount() == 0
        }
    }

    def fillInCurrentStep(def allSettings) {
        switch (getActualStep()) {
            case 1:
                modulePointsSharing.setConfiguration(allSettings.pointsSharingSet)
                break
            case 2:
                moduleReasonsGlobals.fillReasonsGlobalSettingsForm(
                        allSettings.globalConfigSet[0],
                        allSettings.globalConfigSet[1],
                        allSettings.globalConfigSet[2],
                        allSettings.globalConfigSet[3])
                break
            case 3:
                if (!allSettings.templateSet.useDefaultTemplates)
                    uncheckUseDefaultTemplates()
                if (allSettings.templateSet.customTemplate != null)
                    addCustomTemplate(allSettings.templateSet)
                break
            case 4:
                moduleAddReason.addReasonWithSettings(allSettings.reasonSet)
                waitFor { actualStepBase.isDisplayed() }
                break
        }
    }

    def fillInSettingsUpToStep(def allSettings, int lastStep) {
        fillInSettingsUpToStepEx(allSettings, lastStep, true)
    }

    def fillInSettingsUpToStepEx(def allSettings, int lastStep, boolean fillInLastStep) {
        for (int i = 1; i < lastStep; i++) {
            fillInCurrentStep(allSettings)
            clickNext()
            waitFor { getActualStep() == i + 1 }
        }

        if (fillInLastStep) {
            fillInCurrentStep(allSettings)
        }
        helpFunctions.waitSomeTime(Config.waitMedium)
    }
}