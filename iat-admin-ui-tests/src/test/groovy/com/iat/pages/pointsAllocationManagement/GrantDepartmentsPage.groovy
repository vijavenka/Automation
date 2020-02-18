package com.iat.pages.pointsAllocationManagement

import com.iat.Config
import com.iat.pages.modules.LeftSidePanelNavigationModule
import com.iat.pages.modules.TopNavigationModule
import com.iat.stepdefs.utils.HelpFunctions
import geb.Page

class GrantDepartmentsPage extends Page {

    def helpFunctions = new HelpFunctions()

    static url = '/hr/points-allocation/grant-departments'
    static atCheckWaiting = true
    static at = { waitFor { browser.currentUrl.contains("grant-departments") } }

    static content = {
        topNavigation(wait: true) { module TopNavigationModule }
        leftSidePanelNavigation(wait: true) { module LeftSidePanelNavigationModule }

        historyButton(wait: true) { $('.btn-primary') }
        allocationPanel(wait: true) { $('.animate-fade-up') }
        availablePointsForAllocation(wait: true) { allocationPanel.find('.text-center').find('strong') }
        availablePoundsForAllocation(wait: true) { allocationPanel.find('.text-center') }

        entitisTree(wait: true) { $('.angular-ui-tree') }
        entityNodeBasic(wait: true) { $('.angular-ui-tree-handle') }
        entityNodeNameBasic(wait: true) { entityNodeBasic }
        entityNodeExtendArrowBasic(wait: true) { entityNodeBasic.find('.angular-ui-tree-icon') }
        entityNodeAllocateButtonBasic(wait: true) { entityNodeBasic.find('.md-primary') }// required: false
        entityNodeCancelButtonBasic(wait: true) { entityNodeBasic.find('.md-warn') }// required: false
        entityNodePointsValueInputBasic(wait: true) { entityNodeBasic.find('input') }
        entityNodePointsToAlocate(wait: true) { entityNodeBasic.find('.angular-ui.tree-handle') }

        reasonMessageInputLabel(wait: true) { allocationPanel.find('label') }
        reasonMessageInputField(wait: true) { allocationPanel.find('input').last() }
        totalPointsToAlocate(wait: true) { allocationPanel.find('.lead', 0).find('strong') }
        totalPoundsToAlocate(wait: true) { allocationPanel.find('.lead', 1).find('strong') }
        saveButton(wait: true) { allocationPanel.find('.md-raised.md-button') }
        confirmationPopup(wait: true) { $('md-dialog') }
        confirmationPopupInfo(wait: true) { confirmationPopup.find('h2') }
        confirmationPopupCancelButton(wait: true) { confirmationPopup.find('button', 0) }
        confirmationPopupConfirmButton(wait: true) { confirmationPopup.find('button', 1) }
        loader(required: false) { $('.md-half-circle') }
    }

    def clickAllocationHistoryButton() {
        waitFor { historyButton.isDisplayed() }; historyButton.click()
    }

    def extendTreeFromChosenNode(nodeNumber) {
        waitFor { entityNodeExtendArrowBasic[nodeNumber].isDisplayed() }; entityNodeExtendArrowBasic[nodeNumber].click()
    }

    def clickChosenNodeAllocateButton(nodeNumber) {
        waitFor { entityNodeAllocateButtonBasic[nodeNumber].isDisplayed() }
        entityNodeAllocateButtonBasic[nodeNumber].click()
    }

    def clickChosenNodeCancelButton(nodeNumber) {
        waitFor { entityNodeCancelButtonBasic[nodeNumber].isDisplayed() };
        entityNodeCancelButtonBasic[nodeNumber].click()
    }

    def enterPointsValueInChosenNode(nodeNumber, pointsNumber) {
        waitFor { entityNodePointsValueInputBasic[nodeNumber].isDisplayed() };
        entityNodePointsValueInputBasic[nodeNumber].value(pointsNumber)
    }

    def enterPointsAllocationReason(reason) {
        waitFor { reasonMessageInputField.isDisplayed() }; reasonMessageInputField.value(reason)
    }

    def clickSaveButton() {
        waitFor { saveButton.isDisplayed() }; saveButton.click()
    }

    def clickCancelButtonOnAllocationConfirmationPopup() {
        helpFunctions.waitSomeTime(Config.waitMedium)
        waitFor { confirmationPopupCancelButton.isDisplayed() }; confirmationPopupCancelButton.click()
    }

    def clickConfirmButtonOnAllocationConfirmationPopup() {
        helpFunctions.waitSomeTime(Config.waitMedium)
        waitFor { confirmationPopupConfirmButton.isDisplayed() }; confirmationPopupConfirmButton.click()
    }

}