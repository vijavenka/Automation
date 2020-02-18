package com.iat.pages.approvalProcess

import com.iat.pages.modules.LeftSidePanelNavigationModule
import com.iat.pages.modules.TopNavigationModule
import geb.Page

class ApprovalRejectionReasonPage extends Page {
    static at = {
        waitFor { browser.currentUrl.contains("points-allocation/for-approval/reject") }
    }

    static content = {
        topNavigation(wait: true) { module TopNavigationModule }
        leftSidePanelNavigation(wait: true) { module LeftSidePanelNavigationModule }

        pageBase { $('.page-form-ele') }
        headerTitle { pageBase.find('.section-header') }
        recipientsOnPopup { $('') }
        columnNames { pageBase.find('table th') }
        cancelButton { pageBase.find("button[ng-click*='cancelForm']") }
        rejectButton { pageBase.find("button[ng-click*='saveForm']") }
        confirmYes { $("md-dialog-actions > button > span", 1).parent() }
        confirmNo { $("md-dialog-actions > button > span", 0).parent() }
        rejectionReasonInput { pageBase.find('input') }
        fromInTransaction { pageBase.find('tbody').find('td', 1) }
        fromUser { fromInTransaction.find('small') }
        toInTransaction { pageBase.find('tbody').find('td', 2) }
        toUser { toInTransaction.find('small') }
        toUsers { toInTransaction.find('button > span') }
        pointsInTransaction { pageBase.find('tbody').find('td', 4).find('div', 0) }
        usersDialogHeader { $("div.modal-header > h3") }
        closeUsersDialogButton { $("div.modal-footer > button") }
        usersInDialog { $("small.HrAllUsers-email") }
    }

    def addRejectionReasonText(String reasonText) {
        waitFor { rejectionReasonInput.isDisplayed() }; rejectionReasonInput.value(reasonText)
    }

    def checkIfIdenticalTransactionData(String _fromUser, String _toUsers, String _pointsSum) {
        def result = true

        waitFor { fromUser.isDisplayed() }
        result = result && fromUser.text() == _fromUser

        int usersAmount = _toUsers.split(",").size()
        if (usersAmount == 1) {
            waitFor { toUser.isDisplayed() }
            result = result && toUser.text() == _toUsers
        } else {
            waitFor { toUsers.isDisplayed() }
            result = result && toUsers.text().toLowerCase() == usersAmount.toString() + " users"
        }

        waitFor { pointsInTransaction.isDisplayed() }
        result = result && pointsInTransaction.text() == _pointsSum

        result
    }

    def checkIfRecipientsListProper(String _recipients) {
        def recipientsList = []
        recipientsList = _recipients.split(",")

        if (recipientsList.size() == 1)
            return true
        else {
            waitFor { toUsers.parent().isDisplayed() }
            toUsers.parent().click()

            waitFor { usersDialogHeader.isDisplayed() }
            waitFor { usersInDialog.size() == recipientsList.size() }
            for (int i = 0; i < usersInDialog.size(); i++)
                assert usersInDialog[i].text() == recipientsList[i]

            closeUsersDialogButton.click()
            waitFor { $("body").hasNot(".modal-open") }
        }
    }

    def checkIfProperTitle(String expected) {
        waitFor { headerTitle.isDisplayed() }
        headerTitle.text() == expected
    }

    def checkIfProperColumnsTitle(String _column1, String _column2, String _column3, String _column4, String _column5, String _column6) {
        def result = true

        waitFor { columnNames[0].isDisplayed() }
        result = result && columnNames[0].text() == _column1

        waitFor { columnNames[1].isDisplayed() }
        result = result && columnNames[1].text() == _column2

        waitFor { columnNames[2].isDisplayed() }
        result = result && columnNames[2].text() == _column3

        waitFor { columnNames[3].isDisplayed() }
        result = result && columnNames[3].text() == _column4

        waitFor { columnNames[4].isDisplayed() }
        result = result && columnNames[4].text() == _column5

        waitFor { columnNames[5].isDisplayed() }
        result = result && columnNames[5].text() == _column6

        result
    }

    def clickRejectTransaction() {
        waitFor { rejectButton.isDisplayed() }; rejectButton.click()
    }

    def rejectTransactionAndConfirm(String reasonText) {
        addRejectionReasonText(reasonText)
        clickRejectTransaction()
        waitFor { confirmYes.isDisplayed() }
        confirmYes.click()
    }

    def rejectTransactionAndNo(String reasonText) {

        addRejectionReasonText(reasonText)
        clickRejectTransaction()
        waitFor { confirmNo.isDisplayed() }
        confirmNo.click()
    }

    def cancelRejectTransaction() {
        waitFor { cancelButton.isDisplayed() }; cancelButton.click()
    }
}