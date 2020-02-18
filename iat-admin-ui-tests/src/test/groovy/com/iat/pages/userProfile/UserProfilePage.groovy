package com.iat.pages.userProfile

import com.iat.Config
import com.iat.pages.modules.LeftSidePanelNavigationModule
import com.iat.pages.modules.TopNavigationModule
import com.iat.pages.usersManagement.EditUserPage
import com.iat.stepdefs.utils.HelpFunctions

class UserProfilePage extends EditUserPage {

    def helpFunctions = new HelpFunctions()

    static url = '/my-profile'
    static atCheckWaiting = true
    static at = { waitFor { browser.currentUrl.contains("profile") } }

    static content = {
        topNavigation(wait: true) { module TopNavigationModule }
        leftSidePanelNavigation(wait: true) { module LeftSidePanelNavigationModule }

        changePasswordButton(wait: true) { $('h2 > a[ui-sref="app.changePassword"]') }

        userRoleLabel(wait: true) { $('label[for="vm.formlyForm.form_mdRadio_role_5"]') }
        userRoleUserCheckbox(wait: true) {
            $('md-radio-group[id="vm.formlyForm.form_mdRadio_role_5"]').find('md-radio-button', 0)
        }
        userRoleManagerCheckbox(wait: true) {
            $('md-radio-group[id="vm.formlyForm.form_mdRadio_role_5"]').find('md-radio-button', 1)
        }
        adminPrivilagesLabel(wait: true) { $('label[for="vm.formlyForm.form_mdRadio_adminRole_7"]') }
        adminPrivilagesNoneOption(wait: true) {
            $('md-radio-group[id="vm.formlyForm.form_mdRadio_adminRole_6"]').find('md-radio-button', 0)
        }
        adminPrivilagesAdminOption(wait: true) {
            $('md-radio-group[id="vm.formlyForm.form_mdRadio_adminRole_6"]').find('md-radio-button', 1)
        }
        adminPrivilagesSuperAdminOption(wait: true) {
            $('md-radio-group[id="vm.formlyForm.form_mdRadio_adminRole_6"]').find('md-radio-button', 2)
        }

        resetButton(wait: true) { $('button[ng-click="vm.formlyForm.actions.cancelForm()"]') }
        resetConfirmationPopup(wait: true, required: false) { $('md-dialog') }
        resetConfirmationPopupHeader(wait: true, required: false) { resetConfirmationPopup.find('h2') }
        resetConfirmationPopupNoButton(wait: true, required: false) { resetConfirmationPopup.find('button', 0) }
        resetConfirmationPopupYesButton(wait: true, required: false) { resetConfirmationPopup.find('button', 1) }
    }

    def clickChangePasswordButton() {
        waitFor { changePasswordButton.isDisplayed() }; changePasswordButton.click()
    }

    def clickResetButton() {
        waitFor { resetButton.isDisplayed() }; resetButton.click()
    }

    def clickNoButtonOnResetConfirmationPopup() {
        helpFunctions.waitSomeTime(Config.waitShort)
        waitFor { resetConfirmationPopupNoButton.isDisplayed() }; resetConfirmationPopupNoButton.click()
    }

    def clickYesButtonOnResetConfirmationPopup() {
        helpFunctions.waitSomeTime(Config.waitShort)
        waitFor { resetConfirmationPopupYesButton.isDisplayed() }; resetConfirmationPopupYesButton.click()
    }
}