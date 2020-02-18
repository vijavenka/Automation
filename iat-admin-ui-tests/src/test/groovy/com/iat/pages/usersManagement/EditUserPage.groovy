package com.iat.pages.usersManagement

import com.iat.Config
import com.iat.stepdefs.utils.HelpFunctions

class EditUserPage extends CreateUserPage {

    static url = '/hr/users-management/edit/'
    static atCheckWaiting = true
    static at = { waitFor { browser.currentUrl.contains("users-management/edit") } }
    def functions = new HelpFunctions()

    static content = {

        deleteEditUserButton(wait: true) { $('.btn-danger') }

        deleteEditUserPopup(required: false) { $('md-dialog') }
        deleteEditUserPopupHeader(required: false) { deleteEditUserPopup.find('.md-title') }
        deleteEditUserPopupCancelButton(required: false) { deleteEditUserPopup.find('.md-cancel-button') }
        deleteEditUserPopupDeleteEditButton(required: false) { deleteEditUserPopup.find('.md-confirm-button') }

        emailChangeConfirmationPopup(required: false) { $('md-dialog') }
        emailChangeConfirmationPopupHeader(required: false) { emailChangeConfirmationPopup.find('h2') }
        emailChangeConfirmationPopupCancelButton(required: false) {
            emailChangeConfirmationPopup.find('button', 2)
        }
        emailChangeConfirmationPopupAdminOnlyButton(required: false) {
            emailChangeConfirmationPopup.find('button', 0)
        }
        emailChangeConfirmationPopupBothButton(required: false) {
            emailChangeConfirmationPopup.find('button', 1)
        }

        emailChangeHistoryArea(required: false) { $('.Users-changes') }
        emailChangeHistoryheader(required: false) { emailChangeHistoryArea.find('label') }
        emailChangeHistorySingleEmail(required: false) {
            emailChangeHistoryArea.find('.Users-changes-table-cell-first')
        }
        emailChangeHistorySingleDate(required: false) {
            emailChangeHistorySingleEmail.next("td")
        }
        emailChangeHistoryAreaUserUuid(required: false) {
            emailChangeHistoryArea.find('.Users-changes-title').find("div")
        }

        cancelButton(wait: true, required: true) { $('button[ng-click="vm.backToList()"]') }

    }

    def clickDeleteEditButton() {
        waitFor { deleteEditUserButton.isDisplayed() }
        deleteEditUserButton.click()
    }

    def clickCancelButtonOnDeleteEditUserConfirmationPopup() {
        helpFunctions.waitSomeTime(Config.waitShort)
        waitFor() { deleteEditUserPopupCancelButton.isDisplayed() }; deleteEditUserPopupCancelButton.click()
    }

    def clickDeleteEditButtonOnDeleteEditUserConfirmationPopup() {
        helpFunctions.waitSomeTime(Config.waitLong)
        waitFor { deleteEditUserPopupDeleteEditButton.isDisplayed() }; deleteEditUserPopupDeleteEditButton.click()
    }

    def clickCancelButton() {
        functions.scrolPageUpDown("down")
        waitFor { cancelButton.isDisplayed() }; cancelButton.click()
    }

    def clickCancelButtonOnEmailChangeConfirmationPopup() {
        helpFunctions.waitSomeTime(Config.waitShort)
        waitFor() { emailChangeConfirmationPopupCancelButton.isDisplayed() }
        emailChangeConfirmationPopupCancelButton.click()
    }

    def clickAdminOnlyButtonOnEmailChangeConfirmationPopup() {
        helpFunctions.waitSomeTime(Config.waitShort)
        waitFor() { emailChangeConfirmationPopupAdminOnlyButton.isDisplayed() }
        emailChangeConfirmationPopupAdminOnlyButton.click()
    }

    def clickBothButtonOnEmailChangeConfirmationPopup() {
        helpFunctions.waitSomeTime(Config.waitShort)
        waitFor() { emailChangeConfirmationPopupBothButton.isDisplayed() }
        emailChangeConfirmationPopupBothButton.click()
    }

}