package com.iat.pages.specsaversAdmin

import com.iat.pages.specsaversAdmin.modules.TabNaviagationModule
import geb.Page

class SpecsaversAdminPage extends Page {

    static url = '/admin/specsavers'
    static at = {
        waitFor {
            getTitle().contains('Specsavers - award points | epoints')
        }
    }

    static content = {
        tabNaviagationModule { module TabNaviagationModule }

        //login panel
        mainHeader { $('.u-pageTitle') }
        loginForm { $('.sign-in-form') }
        emailLabel { loginForm.find('label', for: 'email') }
        emailInputField { loginForm.find('#email') }
        emailError { loginForm.find('.valid-error div', 0) }
        passwordLabel { loginForm.find('label', for: 'password') }
        passwordInputField { loginForm.find('#password') }
        passwordError { loginForm.find('.valid-error div', 1) }
        authorizationError { loginForm.find('.alert-danger') }
        signInButton { loginForm.find('.btn-green') }
        //control panel
        selectBranchesLabel { $('label', 0) }
        selectAllCheckbox { $('span', text: 'Select all') }
        selectAllCheckboxLabel { $('label', 1) }
        pickBranchInput { $('.selectize-control', 0).find('input') }
        pickBranchOption(required: false) { $('.form-group', 0).find('.selectize-dropdown-content').find('.option') }
        pickBranchChosenOptionBasic(required: false) { $('.form-group', 0).find('.item') }
        pickBranchChosenOptionXButtonBasic(required: false) { $('.remove') }
        useSpecificTagLabel { $('label', 2) }
        useSpecificTagDDL { $('.selectize-control', 1) }
        useSpecificTagInput { useSpecificTagDDL.find('input') }
        useSpecificTagOption(required: false) { $('.selectize-dropdown.single').find('.option') }
        useSpecificTagChosenOptionBasic(required: false) { $('.form-group', 1).find('.item') }
        reasonForAwardingLabel { $('label', 3) }
        reasonForAwardingDDL { $('.selectize-control', 2) }
        reasonForAwardingInput { reasonForAwardingDDL.find('input') }
        reasonForAwardingOption(required: false) {
            $('.form-group', 2).find('.selectize-dropdown-content').find('.option')
        }
        pickBranchChosenSelectedOption { $('.form-group', 0).find('.item').last() }
        pointsToAwardLabel { $('label', 4) }
        pointsToAwardInput { $('input', name: 'amount') }
        pointsCounter { $('.spec-points').find('strong') }
        clearButton { $('.clear') }
        awardPointsButton { $('.btn-green', 0) }
        basicAlert { $('.valid-error div') }

        successMessage(required: false) { $('span', text: 'Points awarded successfully.') }
        //confirmation modal
        confirmationModal(required: false) { $('.modal-content') }
        confirmModalHeader(required: false) { confirmationModal.find('.modal-title') }
        confirmModalInfo(required: false) { confirmationModal.find('.modal-body') }
        confirmModalYesButton(required: false) { confirmationModal.find('.btn-primary') }
        confirmModalNoButton(required: false) { confirmationModal.find('.btn-warning') }
    }

    def enterEmail(email) { waitFor { emailInputField.isDisplayed() }; emailInputField.value(email) }

    def enterPassword(password) { waitFor { passwordInputField.isDisplayed() }; passwordInputField.value(password) }

    def enterLoginData(email, password) { enterEmail(email); enterPassword(password) }

    def clickSignInButton() { waitFor { signInButton.isDisplayed() }; signInButton.click() }

    def clickSelectAllCheckbox() { waitFor { selectAllCheckbox.isDisplayed() }; selectAllCheckbox.click() }

    def clickSelectBranchInputField() { waitFor { pickBranchInput.isDisplayed() }; pickBranchInput.click() }

    def clickChosenBranchOption(number) {
        waitFor { pickBranchOption[number].isDisplayed() }; pickBranchOption[number].click()
    }

    def removeChosenBranchOption(number) {
        waitFor { pickBranchChosenOptionXButtonBasic[number].isDisplayed() }
        pickBranchChosenOptionXButtonBasic[number].click()
    }

    def clickUseSpecificTagDDL() { waitFor { useSpecificTagDDL.isDisplayed() }; useSpecificTagDDL.click() }

    def clickUseSpecificTagInput() { waitFor { useSpecificTagInput.isDisplayed() }; useSpecificTagInput.click() }

    def clickChosenUseSpecificTagOption(number) {
        waitFor { useSpecificTagOption[number].isDisplayed() }; useSpecificTagOption[number].click()
    }

    def enterPhraseIntoUseSpecificTagInput(phrase) {
        waitFor { useSpecificTagInput.isDisplayed() }; useSpecificTagInput.value(phrase)
    }

    def clickReasonForAwardingDDL() { waitFor { reasonForAwardingDDL.isDisplayed() }; reasonForAwardingDDL.click() }

    def clickReasonForAwardingInput() {
        waitFor { reasonForAwardingInput.isDisplayed() }; reasonForAwardingInput.click()
    }

    def clickChosenReasonForAwardingOption(number) {
        waitFor { reasonForAwardingOption[number].isDisplayed() }; reasonForAwardingOption[number].click()
    }

    def enterPhraseIntoReasonForAwardinInput(phrase) {
        waitFor { reasonForAwardingInput.isDisplayed() }; reasonForAwardingInput.value(phrase)
    }

    def enterValueIntoPointsToAwardInput(value) {
        waitFor { pointsToAwardInput.isDisplayed() }; pointsToAwardInput.value(value)
    }

    def clickAwardPointsButton() { waitFor { awardPointsButton.isDisplayed() }; awardPointsButton.click() }

    def clickYesButtonInConfirmPointsModalWindow() {
        waitFor { confirmModalYesButton.isDisplayed() }; confirmModalYesButton.click()
    }

    def clickNoButtonInConfirmPointsModalWindow() {
        waitFor { confirmModalNoButton.isDisplayed() }; confirmModalNoButton.click()
    }

}