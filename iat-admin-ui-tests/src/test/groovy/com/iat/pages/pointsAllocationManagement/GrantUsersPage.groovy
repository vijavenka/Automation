package com.iat.pages.pointsAllocationManagement

import com.iat.Config
import com.iat.pages.modules.LeftSidePanelNavigationModule
import com.iat.pages.modules.TopNavigationModule
import com.iat.stepdefs.utils.HelpFunctions
import geb.Page
import org.openqa.selenium.Keys

import static org.hamcrest.MatcherAssert.assertThat

class GrantUsersPage extends Page {

    def helpFunctions = new HelpFunctions()

    static url = '/hr/points-allocation/grant-users'
    static atCheckWaiting = true
    static at = { waitFor { browser.currentUrl.contains("grant-users") } }

    static content = {
        topNavigation(wait: true) { module TopNavigationModule }
        leftSidePanelNavigation(wait: true) { module LeftSidePanelNavigationModule }

        stepNavigatorSelectCardDetails(wait: true) { $('.wizard').find('li', 0) }
        wizardForm(wait: true) { $('.ui-wizard-form') }
        availablePointsToAlocate(wait: true) { wizardForm.find('.row', 0).find('strong') }
        selectReasonLabel(wait: true) { wizardForm.find('label', for: 'vm.selectTemplatesForm.form_select_reason_0') }
        selectReasonDDL(wait: true) { wizardForm.find('select') }
        selectReasonDDLOption(wait: true) { selectReasonDDL.find('option') }
        messageLabel(wait: true) { wizardForm.find('label', for: 'vm.selectTemplatesForm.form_textarea_message_1') }
        messageInputField(wait: true) { wizardForm.find('textarea') }
        ccLabel(wait: true) { wizardForm.find('label', for: 'vm.selectTemplatesForm.form_selectize_cc_2') }
        ccInputField(wait: true) { wizardForm.find('.selectize-input').find('input') }
        ccElementBasic(wait: true, required: false) { wizardForm.find('.selectize-input').find('.item') }
        ccElementBasicCloseCross(wait: true, required: false) { wizardForm.find('.selectize-input').find('.remove') }
        imagePreviewLabel(wait: true) { wizardForm.find('label', 4) }
        imagePreviewBigImage(wait: true) { wizardForm.find('.GrantUsers-imagePreview') }
        imagePreviewImageName(wait: true) { wizardForm.find('.GrantUsers-imagePreviewWrapper').find('p') }
        selectImageLabel(wait: true) { wizardForm.find('label', 5) }
        selectImageTemplateBasic(wait: true) { wizardForm.find('.GrantUsers-imageItem') }
        nextButtonStep1(wait: true) { wizardForm.find('.md-raised.btn-w-md.md-primary.md-button.md-ink-ripple', 0) }

        stepNavigatorSelectUsers(wait: true) { $('.wizard').find('li', 1) }
        pointsPerUserLabel(wait: true) { wizardForm.find('label', 0) }
        pointsPerUserInputField(wait: true) { wizardForm.find('input', 0) }

        search(required: false) { $('.selectize-control.formly-field-selectize') }
        searchLabel(required: false) { wizardForm.find('label', 1) }
        searchInputField(required: false) { search.$('.selectize-input').$('input') }
        searchDdlElement(required: false) { search.$('.row') }
        searchDdlUserName(required: false) { searchDdlElement.$('.Selectize-item', 0) }
        searchDdlEmail(required: false) { searchDdlElement.$('.Selectize-item', 1) }
        searchDdlDepartmentName(required: false) { searchDdlElement.$('.Selectize-item', 2) }
        searchDdlManagersName(required: false) { searchDdlElement.$('.Selectize-item', 3) }
        searchSelectedUserElement(required: false) { search.$('.selectize-input').$('.item') }
        searchSelectedUserEmail(required: false) { search.$('.selectize-input').$('.selectize-email') }
        searchSelectedUserName(required: false) { search.$('.selectize-input').$('.selectize-name') }
        searchSelectedUserCrossButton(required: false) { search.$('.selectize-input').$('a') }

        selectUsersLabel(wait: true) { wizardForm.find('label', 2) }
        selectUsersNode(wait: true) { wizardForm.find('.angular-ui-tree-handle') }
        selectUsersNodeUsersNumber(wait: true) { selectUsersNode.find('.row').find('.zmdi-account').parent() }
        selectUsersNodeUsersNumberSelected(wait: true, required: false) { selectUsersNode.find('.row').find('.badge') }
        selectUsersNodeAllButton(wait: true) { selectUsersNode.find('.row').find('.md-primary') }
        selectUsersNodeClearButton(wait: true) { selectUsersNode.find('.row').find('button', 1) }
        selectUsersNodeChooseButton(wait: true) { selectUsersNode.find('.row').find('button', 2) }
        selectUsersNodeHideButton(wait: true) { selectUsersNode.find('.row').find('button', 3) }
        selectUsersChooseDDLInput(wait: true) { selectUsersNode.find('.selectize-input').find('input') }
        selectUserChooseDDLOptionEmail(wait: true) {
            selectUsersNode.find('.selectize-dropdown-content').find('.option')
        }
        selectUserChooseDDLOptionSelected(wait: true, required: false) {
            selectUsersNode.find('.selectize-input.items').find('div')
        }
        selectUserChooseDDLOptionSelectedEmail(wait: true) {
            selectUsersNode.find('.selectize-input.items').find('.selectize-email')
        }
        selectUserChooseDDLOptionSelectedName(wait: true) {
            selectUsersNode.find('.selectize-input.items').find('.selectize-name')
        }
        selectUserChooseDDLOptionSelectedCrossButton(wait: true) {
            selectUsersNode.find('.selectize-input.items').find('.remove')
        }
        summaryInformation(wait: true) { $('.GrantUsers-summary') }
        summaryInformationPointsPerUser(wait: true) { summaryInformation.find('strong', 0) }
        summaryInformationPointsInTotal(wait: true) { summaryInformation.find('strong', 1) }
        backButtonStep2(wait: true) { wizardForm.find('.md-raised.btn-w-md.md-default.md-button.md-ink-ripple', 0) }
        nextButtonStep2(wait: true) { wizardForm.find('.md-raised.btn-w-md.md-primary.md-button.md-ink-ripple', 0) }
        alert(wait: true) { $('.callout') }

        stepNavigatorConfirmation(wait: true) { $('.wizard').find('li', 2) }
        usersTable(wait: true, required: false) { $('.table') }
        usersTableHeaderRowElementBasic(wait: true) { usersTable.find('thead').find('th') }
        usersTableDataRowBasic(wait: true, required: false) { usersTable.find('tbody').find('tr') }
        usersTableDataTotalPointsValue(wait: true) { usersTable.find('tfoot').find('tr').last().find('th', 3) }
        deleteUserConfirmationPopup(wait: true) { $('md-dialog') }
        deleteUserConfirmationPopupInfo(wait: true) { deleteUserConfirmationPopup.find('h2') }
        deleteUserConfirmationPopupRemoveButton(wait: true) { deleteUserConfirmationPopup.find('button', 1) }
        deleteUserConfirmationPopupCancelButton(wait: true) { deleteUserConfirmationPopup.find('button', 0) }
        previewHeader(wait: true) { wizardForm.find('.GrantUsers-preview').find('h2') }
        previewImg(wait: true) { wizardForm.find('.GrantUsers-preview').find('img') }
        previewMessage(wait: true) { wizardForm.find('.GrantUsers-preview').find('.lead').next('div') }
        backButtonStep3(wait: true) { wizardForm.find('.md-raised.btn-w-md.md-default.md-button.md-ink-ripple', 0) }
        saveButton(wait: true) { wizardForm.find('.md-raised.btn-w-md.md-primary.md-button.md-ink-ripple', 0) }

        grantUsersLoader(required: false) { $('.GrantUsers-loader') }
    }

    def clickNavigationStepOne() {
        waitFor { stepNavigatorSelectUsers.isDisplayed() }; stepNavigatorSelectUsers.click()
    }

    def clickNavigationStepTwo() {
        waitFor { stepNavigatorSelectCardDetails.isDisplayed() }; stepNavigatorSelectCardDetails.click()
    }

    def clickNavigationStepThree() {
        waitFor { stepNavigatorConfirmation.isDisplayed() }; stepNavigatorConfirmation.click()
    }

    //first step functions
    def expandReasonDDL() {
        waitFor { selectReasonDDL.isDisplayed() }; selectReasonDDL.click()
    }

    def selectAwardReasonDDLOption(reasontoBeSelected) {
        waitFor { selectReasonDDLOption[reasontoBeSelected].isDisplayed() };
        selectReasonDDLOption[reasontoBeSelected].click()
    }

    def enterMessage(message) {
        waitFor { messageInputField.isDisplayed() }; messageInputField.value(message)
    }

    def enterCC(cc) {
        waitFor { ccInputField.isDisplayed() }; ccInputField.value(cc)
        ccInputField << Keys.chord(Keys.ENTER)
        ccInputField << Keys.chord(Keys.ESCAPE)
    }

    def clickCcElementBasicCloseCross(number) {
        waitFor { ccElementBasicCloseCross[number].isDisplayed() }; ccElementBasicCloseCross[number].click()
    }

    def selectImage(number) {
        helpFunctions.waitSomeTime(Config.waitMedium)
        waitFor { selectImageTemplateBasic[number].isDisplayed() }; selectImageTemplateBasic[number].click()
    }

    def clickNextButtonOnStepOne() {
        waitFor { nextButtonStep1.isDisplayed() }; nextButtonStep1.click()
    }

    def enterPhraseToGlobalSearch(phrase) {
        waitFor { searchInputField.isDisplayed() }
        searchInputField.value(phrase)
    }

    def selectUserFromGlobalSearchResultsList(String field, String fieldValue) {
        waitFor { searchDdlElement.size() > 0 }

        def fieldValueFromDDL;
        def numberOfAlreadySelectedUsers = searchSelectedUserElement.size()

        for (int i = 0; i < searchDdlElement.size(); i++) {
            switch (field) {
                case "userName":
                    fieldValueFromDDL = searchDdlElement[i].$('.Selectize-item', 0).text()
                    break
                case "userEmail":
                    fieldValueFromDDL = searchDdlElement[i].$('.Selectize-item', 1).text()
                    break
            }

            if (fieldValueFromDDL.contains(fieldValue)) {
                searchDdlElement[i].click()
                waitFor { searchSelectedUserElement.size() == (numberOfAlreadySelectedUsers + 1) }
                switch (field) {
                    case "userName":
                        assertThat("Selected user slab does not contain searched user name", searchSelectedUserName[numberOfAlreadySelectedUsers].text().contains(fieldValue))
                        break
                    case "userEmail":
                        assertThat("Selected user slab does not contain searched user email", searchSelectedUserEmail[numberOfAlreadySelectedUsers].text().contains(fieldValue))
                        break
                }
                break
            }
        }
    }

    def getSearchResultData(String field, int row) {
        waitFor { searchDdlElement[row].isDisplayed() }
        switch (field) {
            case "userName":
                return searchDdlElement[row].$('.Selectize-item', 0).text()
                break
            case "userEmail":
                return searchDdlElement[row].$('.Selectize-item', 1).text()
                break
            case "department":
                return searchDdlElement[row].$('.Selectize-item', 2).text()
                break
            case "managerName":
                return searchDdlElement[row].$('.Selectize-item', 3).text()
                break
        }
    }

    def getSelectedElemebntData(String field, int element) {
        switch (field) {
            case "userName":
                return searchSelectedUserElement[element].$('.selectize-name').text()
                break
            case "userEmai;":
                return searchSelectedUserElement[element].$('.selectize-email').text()
                break
        }
    }

//second step functions
    def enterPointsValue(pointsValue) {
        waitFor { pointsPerUserInputField.isDisplayed() }; pointsPerUserInputField.value(pointsValue)
    }

    def clickAllUsersInChosenNode(userToBeSelectedDepartmentNode) {
        waitFor { selectUsersNodeAllButton[userToBeSelectedDepartmentNode].isDisplayed() };
        selectUsersNodeAllButton[userToBeSelectedDepartmentNode].click()
    }

    def clickClearInChosenNode(nodeNumber) {
        waitFor { selectUsersNodeClearButton[nodeNumber].isDisplayed() }; selectUsersNodeClearButton[nodeNumber].click()
    }

    def clickChooseInChosenNode(nodeNumber) {
        waitFor { selectUsersNodeChooseButton[nodeNumber].isDisplayed() };
        selectUsersNodeChooseButton[nodeNumber].click()
    }

    def clickHideInChoosenNode(nodeNumber) {
        waitFor { selectUsersNodeHideButton[nodeNumber].isDisplayed() }; selectUsersNodeHideButton[nodeNumber].click()
    }

    def enterEmailIntoChoosenNodeSearch(email) {
        waitFor { selectUsersChooseDDLInput.isDisplayed() }; selectUsersChooseDDLInput.value(email)
    }

    def selectUserFromChosenNodeDDL(number) {
        waitFor { selectUserChooseDDLOptionEmail[number].isDisplayed() }; selectUserChooseDDLOptionEmail[number].click()
    }

    def clickSelectUserChooseDDLOptionSelectedCrossButton(number) {
        waitFor { selectUserChooseDDLOptionSelectedCrossButton[number].isDisplayed() };
        selectUserChooseDDLOptionSelectedCrossButton[number].click()
    }

    def clickBackButtonOnStepTwo() {
        helpFunctions.waitSomeTime(Config.waitShort)
        waitFor { backButtonStep2.isDisplayed() }; backButtonStep2.click()
    }

    def clickNextButtonOnStepTwo() {
        waitFor { nextButtonStep2.isDisplayed() }
        helpFunctions.waitSomeTime(Config.waitShort)
        nextButtonStep2.click()
    }

    //third step
    def clickBackButtonOnStepThird() {
        waitFor { backButtonStep3.isDisplayed() }; backButtonStep3.click()
    }

    def clickSaveButtonOnStepThree() {
        waitFor { saveButton.isDisplayed() }; saveButton.click()
    }

    def clickRemoveButtonOfChosenUser(number) {
        waitFor { returnAllocationTableLocator('Action', number).isDisplayed() };
        returnAllocationTableLocator('Action', number).click()
        sleep(500) //popup have to be steady
    }

    def clickDeleteUserConfirmationPopupRemoveButton() {
        waitFor { deleteUserConfirmationPopupRemoveButton.isDisplayed() };
        deleteUserConfirmationPopupRemoveButton.click()
    }

    def returnAllocationTableLocator(String column, int position) {
        switch (column) {
            case "Email":
                waitFor { usersTableDataRowBasic[position].find('td', 0).isDisplayed() }
                return usersTableDataRowBasic[position].find('td', 0)
                break
            case "Name":
                waitFor { usersTableDataRowBasic[position].find('td', 1).isDisplayed() }
                return usersTableDataRowBasic[position].find('td', 1)
                break
            case "Department":
                waitFor { usersTableDataRowBasic[position].find('td', 2).isDisplayed() }
                return usersTableDataRowBasic[position].find('td', 2)
                break
            case "Points":
                waitFor { usersTableDataRowBasic[position].find('td', 3).isDisplayed() }
                return usersTableDataRowBasic[position].find('td', 3)
                break
            case "Action":
                waitFor { usersTableDataRowBasic[position].find('td', 4).isDisplayed() }
                return usersTableDataRowBasic[position].find('td', 4).find('a')
                break
        }
    }

}