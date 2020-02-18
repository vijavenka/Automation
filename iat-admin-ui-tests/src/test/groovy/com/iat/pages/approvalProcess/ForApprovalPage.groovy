package com.iat.pages.approvalProcess

import com.iat.Config
import com.iat.pages.modules.LeftSidePanelNavigationModule
import com.iat.pages.modules.TopNavigationModule
import com.iat.stepdefs.utils.HelpFunctions
import geb.Page

class ForApprovalPage extends Page {

    def helpFunctions = new HelpFunctions()

    static url = '/hr/points-allocation/for-approval'
    static atCheckWaiting = true
    static at = { waitFor { browser.currentUrl.contains('points-allocation/for-approval') } }

    static content = {
        topNavigation(wait: true) { module TopNavigationModule }
        leftSidePanelNavigation(wait: true) { module LeftSidePanelNavigationModule }

        leftSidePanelNavigation(wait: true) { module LeftSidePanelNavigationModule }
        upperPanel(wait: true) { module TopNavigationModule }

        approveAwardsHeader(wait: true) { $('h2') }
        transactionsNumberToBeApprovedInfoBasic(wait: true) { $('.HrApprovalCounter') }
        transactionsToBeApprovedTable(wait: true) { $('.table') }
        transactionsToBeApprovedHeadersRowElement(wait: true) { transactionsToBeApprovedTable.find('thead').find('th') }
        transactionsToBeApprovedRowBasic(wait: true) { transactionsToBeApprovedTable.find('tbody').find('tr') }

        usersToBeAwardedModal(wait: true, required: false) { $('.modal-dialog') }
        usersToBeAwardedModalHeader(wait: true, required: false) { usersToBeAwardedModal.find('.modal-header') }
        usersToBeAwardeModalSingleUser(wait: true, required: false) {
            usersToBeAwardedModal.find('.HrAllUsers-list').find('li')
        }
        usersToBeAwardedModalCloseButton(wait: true, required: false) { usersToBeAwardedModal.find('button') }

        approveConfirmationModal(wait: true, required: false) { $('md-dialog') }
        approveConfirmationModalInfo(wait: true, required: false) { approveConfirmationModal.find('.md-title') }
        approveConfirmationModalApproveButton(wait: true, required: false) {
            approveConfirmationModal.find('button', 1)
        }
        approveConfirmationModalCancelButton(wait: true, required: false) { approveConfirmationModal.find('button', 0) }

        approvalProcesDisabledInfo(wait: true, required: false) { $('.callout') }
    }

    def returnForApprovalTableLocator(String column, int position) {
        switch (column) {
            case "Date":
                waitFor { transactionsToBeApprovedRowBasic[position].find('td', 0).isDisplayed() }
                return transactionsToBeApprovedRowBasic[position].find('td', 0)
                break
            case "From":
                waitFor { transactionsToBeApprovedRowBasic[position].find('td', 1).isDisplayed() }
                return transactionsToBeApprovedRowBasic[position].find('td', 1)
                break
            case "To":
                waitFor { transactionsToBeApprovedRowBasic[position].find('td', 2).isDisplayed() }
                return transactionsToBeApprovedRowBasic[position].find('td', 2)
                break
            case "To multiple":
                waitFor { transactionsToBeApprovedRowBasic[position].find('td', 2).find('.md-button').isDisplayed() }
                return transactionsToBeApprovedRowBasic[position].find('td', 2).find('.md-button')
                break
            case "Reason":
                waitFor { transactionsToBeApprovedRowBasic[position].find('td', 3).isDisplayed() }
                return transactionsToBeApprovedRowBasic[position].find('td', 3)
                break
            case "Points":
                waitFor { transactionsToBeApprovedRowBasic[position].find('td', 4).isDisplayed() }
                return transactionsToBeApprovedRowBasic[position].find('td', 4)
                break
            case "Amount":
                waitFor { transactionsToBeApprovedRowBasic[position].find('td', 5).isDisplayed() }
                return transactionsToBeApprovedRowBasic[position].find('td', 5)
                break
            case "Reject":
                waitFor { transactionsToBeApprovedRowBasic[position].find('td', 6).find('.btn').isDisplayed() }
                return transactionsToBeApprovedRowBasic[position].find('td', 6).find('.btn')
                break
            case "Approve":
                waitFor { transactionsToBeApprovedRowBasic[position].find('td', 7).find('.btn').isDisplayed() }
                return transactionsToBeApprovedRowBasic[position].find('td', 7).find('.btn')
                break
        }
    }

    def clickChosenTransactionRejectButton(rowNumber) {
        waitFor { returnForApprovalTableLocator('Reject', rowNumber).isDisplayed() }
        returnForApprovalTableLocator('Reject', rowNumber).click()
    }

    def clickChosenTransactionApproveButton(rowNumber) {
        waitFor { returnForApprovalTableLocator('Approve', rowNumber).isDisplayed() }
        returnForApprovalTableLocator('Approve', rowNumber).click()
    }

    def clickApproveConfirmationModalApproveButton() {
        waitFor { approveConfirmationModalApproveButton.isDisplayed() }; approveConfirmationModalApproveButton.click()
    }

    def clickApproveConfirmationModalNoButton() {
        waitFor { approveConfirmationModalCancelButton.isDisplayed() }; approveConfirmationModalCancelButton.click()
    }

    def findFirstIndexOfTransactionWithData(String _fromUser, String _toUsers, String _pointsSum) {
        int usersAmount = _toUsers.split(",").size()
        for (int i = getSizeOfTransactionList() - 1; i >= 0; i--) {
            if (returnForApprovalTableLocator("From", i).find('small').text() == _fromUser &&
                    returnForApprovalTableLocator("Points", i).find('div', 0).text() == _pointsSum) {
                if (usersAmount > 1) {
                    returnForApprovalTableLocator("To", i).find('button > span').text().toLowerCase() == usersAmount.toString() + " users"
                    return i
                } else if (returnForApprovalTableLocator("To", i).find('small').text() == _toUsers)
                    return i
            }
        }

        null
    }

    def getSizeOfTransactionList() {
        waitFor { transactionsToBeApprovedRowBasic.isDisplayed() }
        helpFunctions.waitSomeTime(Config.waitLong)
        transactionsToBeApprovedRowBasic.size()
    }

    def clickXUsersButtonInSelectedRow(rowNumber) {
        waitFor { returnForApprovalTableLocator('To multiple', rowNumber) };
        returnForApprovalTableLocator('To multiple', rowNumber).click()
    }

    def closeUsersToBeAwardedModal() {
        waitFor { usersToBeAwardedModalCloseButton.isDisplayed() }; usersToBeAwardedModalCloseButton.click()
    }
}