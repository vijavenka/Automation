package com.iat.pages.usersManagement

import com.iat.Config
import com.iat.pages.modules.LeftSidePanelNavigationModule
import com.iat.pages.modules.TopNavigationModule
import com.iat.stepdefs.utils.HelpFunctions
import geb.Browser
import geb.Page
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

class CreateUserPage extends Page {

    def helpFunctions = new HelpFunctions()

    static url = '/hr/users-management/create'
    static atCheckWaiting = true
    static at = { waitFor { browser.currentUrl.contains("users-management/create") } }

    static content = {
        preloader(required: false) { $('.preloaderbar') }

        topNavigation(wait: true) { module TopNavigationModule }
        leftSidePanelNavigation(wait: true) { module LeftSidePanelNavigationModule }

        pageHeader(wait: true) { $('h2') }

        bulkUploadTab(wait: true) { $('.nav-tabs').find('li', 1) }
        bulkUploadDropbox(wait: true) { $('.drop-box') }
        bulkUploadButton(wait: true) { $('.AddUser-add') }
        bulkUploadFileName(wait: true) { $('.bulk-upload-filename') }
        bulkUploadInvalidFileExtensionWarning(wait: true) { $('div[ng-if*="invalidFileExtension"]') }
        bulkUploadAlertInfo(wait: true) { $('.AddUser-success') }
        bulkUploadErrorInfo(wait: true) { $('.AddUser-errors') }

        addUserManuallyTab(wait: true) { $('.nav-tabs').find('li', 0) }
        employeenumberLabel(wait: true) { $('label[for="vm.formlyForm.form_input_employeeNumber_0"]') }
        employeeNumberInputField(wait: true) { $('input[id="vm.formlyForm.form_input_employeeNumber_0"]') }
        nameLabel(wait: true) { $('label[for="vm.formlyForm.form_input_name_1') }
        nameInputField(wait: true) { $('input[id="vm.formlyForm.form_input_name_1"]') }
        dateOfBirthLabel(wait: true) { $('label[for="vm.formlyForm.form_datepickerInput_birthDateObject_2"') }
        dateOfBirthInputField(wait: true) { $('input[id="vm.formlyForm.form_datepickerInput_birthDateObject_2"]') }
        dateOfBirthDatepicker(wait: true) { $('.input-group-addon', 0) }
        emailLabel(wait: true) { $('label[for="vm.formlyForm.form_input_email_3"]') }
        emailInputField(wait: true) { $('input[id="vm.formlyForm.form_input_email_3"]') }
        genderLabel(wait: true) { $('label[for="vm.formlyForm.form_mdRadio_gender_4"]') }
        genderMaleCheckbox(wait: true) {
            $('md-radio-group[id="vm.formlyForm.form_mdRadio_gender_4"]').find('md-radio-button', 0)
        }
        genderFemaleCheckbox(wait: true) {
            $('md-radio-group[id="vm.formlyForm.form_mdRadio_gender_4"]').find('md-radio-button', 1)
        }
        companyStartDateLabel(wait: true) {
            $('label[for="vm.formlyForm.form_datepickerInput_companyStartDateObject_5"')
        }
        companyStartDateInputField(wait: true) {
            $('input[id="vm.formlyForm.form_datepickerInput_companyStartDateObject_5"]')
        }
        companyStartDateDatepicker(wait: true) { $('.input-group-addon', 1) }
        userRoleLabel(wait: true) { $('label[for="vm.formlyForm.form_mdRadio_role_6"]') }
        userRoleUserCheckbox(wait: true) {
            $('md-radio-group[id="vm.formlyForm.form_mdRadio_role_6"]').find('md-radio-button', 0)
        }
        userRoleManagerCheckbox(wait: true) {
            $('md-radio-group[id="vm.formlyForm.form_mdRadio_role_6"]').find('md-radio-button', 1)
        }
        adminPrivilagesLabel(wait: true) { $('label[for="vm.formlyForm.form_mdRadio_adminRole_7"]') }
        adminPrivilagesNoneOption(wait: true) {
            $('md-radio-group[id="vm.formlyForm.form_mdRadio_adminRole_7"]').find('md-radio-button', 0)
        }
        adminPrivilagesAdminOption(wait: true) {
            $('md-radio-group[id="vm.formlyForm.form_mdRadio_adminRole_7"]').find('md-radio-button', 1)
        }
        adminPrivilagesSuperAdminOption(wait: true) {
            $('md-radio-group[id="vm.formlyForm.form_mdRadio_adminRole_7"]').find('md-radio-button', 2)
        }
        departmentsTree(wait: true) { $('.formly-field-departmentsTree') }
        departmentsLabel(wait: true) { departmentsTree.find('label') }
        departmentsTreeElementBasic(wait: true) { departmentsTree.find('.FormlyTree-node') }
        departmentsTreeElementBasicSelected(wait: true) { departmentsTree.find('.FormlyTree-node--selected') }

        alertBasic(required: false) { $('.message') }
        canceButton(wait: true) { $('button[ng-click="vm.formlyForm.actions.cancelForm()"]') }
        confirmationModal(wait: true) { $('md-dialog') }
        confirmationModalText(wait: true) { confirmationModal.find('h2') }
        confirmationModalNoButton(wait: true) { confirmationModal.find('.md-button', 0) }
        confirmationModalYesButton(wait: true) { confirmationModal.find('.md-button', 1) }
        saveButton(required: true, wait: true) { $('.md-primary.md-ink-ripple') }
    }

    def clickAddUserManuallTab() {
        waitFor { addUserManuallyTab.isDisplayed() }; addUserManuallyTab.click();
        waitFor { addUserManuallyTab.hasClass('active') }
    }

    def clickAddUserByBulkUploadTab() {
        waitFor { bulkUploadTab.isDisplayed() }; bulkUploadTab.click()
        waitFor { bulkUploadTab.hasClass('active') }
    }

    def uploadFileWithUserAndDepartmentsStructure(String fileName, String path) {
        Browser browser = new Browser()
        waitFor { $('input[type="file"]') }
        WebElement elem = browser.getDriver().findElement(By.xpath("//input[@type='file']"))
        String filePath = (new File(path + fileName)).getAbsolutePath().replace('\\', '\\\\')
        elem.sendKeys(filePath)
        waitFor { bulkUploadFileName != null }
    }

    def clickUploadFileButton() {
        waitFor { bulkUploadButton.isDisplayed() }; bulkUploadButton.click()
    }

    def enterName(firstName) {
        waitFor { nameInputField.isDisplayed() }; nameInputField.value(firstName)
    }

    def enterDOB(dob) {
        waitFor { dateOfBirthInputField.isDisplayed() }; dateOfBirthInputField.value(dob)
    }

    def enterEmployeeNumber(employeeNumber) {
        waitFor { employeeNumberInputField.isDisplayed() }; employeeNumberInputField.value(employeeNumber)
    }

    def enterEmail(email) {
        waitFor { emailInputField.isDisplayed() }; emailInputField.value(email)
    }

    def selectGender(gender) {
        if (gender == 'MALE') {
            waitFor { genderMaleCheckbox.isDisplayed() }; genderMaleCheckbox.click()
        } else if (gender == 'FEMALE') {
            waitFor { genderFemaleCheckbox.isDisplayed() }; genderFemaleCheckbox.click()
        }
    }

    def enterCompanyStartDate(startDate) {
        waitFor { companyStartDateInputField.isDisplayed() }; companyStartDateInputField.value(startDate)
    }

    def selectUserRole(role) {
        if (role == "USER") {
            waitFor { userRoleUserCheckbox.isDisplayed() }; userRoleUserCheckbox.click()
        } else if (role == "MANAGER") {
            waitFor { userRoleManagerCheckbox.isDisplayed() }; userRoleManagerCheckbox.click()
        }
    }

    def selectManagerRole(role) {
        if (role == "NONE") {
            waitFor { adminPrivilagesNoneOption.isDisplayed() }; adminPrivilagesNoneOption.click()
        } else if (role == "ADMIN") {
            waitFor { adminPrivilagesAdminOption.isDisplayed() }; adminPrivilagesAdminOption.click()
        } else if (role == "SUPER ADMIN") {
            waitFor { adminPrivilagesSuperAdminOption.isDisplayed() }; adminPrivilagesSuperAdminOption.click()
        }
    }

    def selectDepartment(departmentNumber) {
        waitFor { departmentsTreeElementBasic[departmentNumber].isDisplayed() };
        departmentsTreeElementBasic[departmentNumber].click()
    }

    def clickCancelButton() {
        waitFor { canceButton.isDisplayed() }; canceButton.click()
    }

    def clickCancelConfirmationModalYesButton() {
        helpFunctions.waitSomeTime(Config.waitShort)
        waitFor { confirmationModalYesButton.isDisplayed() }; confirmationModalYesButton.click()
    }

    def clickCancelConfirmationModalNoButton() {
        helpFunctions.waitSomeTime(Config.waitShort)
        waitFor { confirmationModalNoButton.isDisplayed() }; confirmationModalNoButton.click()
    }

    def clickSaveButton() {
//        helpFunctions.scrolPageUpDown("down")
        waitFor { saveButton.isDisplayed() }; saveButton.click()
        sleep(500)
        waitFor { !preloader.isDisplayed() }
    }

}