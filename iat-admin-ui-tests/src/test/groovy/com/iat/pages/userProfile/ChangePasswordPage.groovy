package com.iat.pages.userProfile

import com.iat.pages.modules.LeftSidePanelNavigationModule
import com.iat.pages.modules.TopNavigationModule
import geb.Page

class ChangePasswordPage extends Page {

    static url = '/change-password'
    static atCheckWaiting = true
    static at = { waitFor { browser.currentUrl.contains("change-password") } }

    static content = {
        topNavigation(wait: true) { module TopNavigationModule }
        leftSidePanelNavigation(wait: true) { module LeftSidePanelNavigationModule }

        topNavigation(wait: true) { module TopNavigationModule }

        changePasswordComponent(wait: true) { $('.ChangePassword').find('.card') }
        pageHeader(wait: true) { changePasswordComponent.find('h1') }

        currentPasswordLabel(wait: true) { changePasswordComponent.find('label', 0) }
        currentPasswordInputField(wait: true) { changePasswordComponent.find('input', 0) }
        passwordLabel(wait: true) { changePasswordComponent.find('label', 1) }
        passwordInputField(wait: true) { changePasswordComponent.find('input', 1) }
        passwordConfirmationLabel(wait: true) { changePasswordComponent.find('label', 2) }
        passwordConfirmationInputField(wait: true) { changePasswordComponent.find('input', 2) }

        alertBasic(wait: true) { changePasswordComponent.find('.message') }
        succesAlert(wait: true) { changePasswordComponent.find('.alert-info') }
        dangerAlert(wait: true) { changePasswordComponent.find('.alert-danger') }

        confirmPasswordChangeButton(wait: true) { changePasswordComponent.find('button') }
    }

    def enterCurrentPassword(password) {
        waitFor { currentPasswordInputField.isDisplayed() }; currentPasswordInputField.value(password)
    }

    def enterPassword(password) {
        waitFor { passwordInputField.isDisplayed() }; passwordInputField.value(password)
    }

    def enterPasswordConfirmation(password) {
        waitFor { passwordConfirmationInputField.isDisplayed() }; passwordConfirmationInputField.value(password)
    }

    def clickPasswordChangeConfirmButton() {
        waitFor { confirmPasswordChangeButton.isDisplayed() }; confirmPasswordChangeButton.click()
    }
}