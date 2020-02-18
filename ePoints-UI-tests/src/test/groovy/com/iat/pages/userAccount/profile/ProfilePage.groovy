package com.iat.pages.userAccount.profile

import com.iat.pages.AbstractPage

class ProfilePage extends AbstractPage {

    static url = '/my-account/profile'
    static at = { waitFor { browser.currentUrl.contains("/profile") } }

    static content = {
        //email block
        changeEmailBlock(wait: true) { $('.changeEmailBlock') }
        emailAddressLabel(wait: true) { changeEmailBlock.find('label', 0) }
        currentEmail(wait: true) { changeEmailBlock.find('#userEmail').find('strong') }
        emailEditCancelButton(required: false) { changeEmailBlock.find('.btn-grey') }
        newEmailLabel(wait: true) { changeEmailBlock.find('label', for: 'email') }
        newEmailInputField(wait: true) { changeEmailBlock.find('.new-email') }
        newEmailRetypeLabel(wait: true) { changeEmailBlock.find('label', for: 'confirmEmail') }
        newEmailRetypeInputField(wait: true) { changeEmailBlock.find('.confirm-email') }
        emailChangeCancelButton(wait: true) { changeEmailBlock.find('.cancel') }
        emailChangeSaveButton(wait: true) { changeEmailBlock.find('.saveEmail') }
        emailAddressIsInvalidAlert(wait: true) { changeEmailBlock.find('.error', 0) }
        emailAddressIsRequiredAlert(wait: true) { changeEmailBlock.find('.error', 1) }
        emailAddressConfirmationIsRequiredAlert(wait: true) { changeEmailBlock.find('.error', 2) }
        emailEmailsDoNotMatchAlert(wait: true) { changeEmailBlock.find('.error', 3) }
        emailChangeAlertSuccess(wait: true) { changeEmailBlock.find('.alert-success') }
        emailChangeAlertDanger(wait: true) { changeEmailBlock.find('.alert-danger') }
        emailsMatchAlert(wait: true) { changeEmailBlock.find('.match') }
        //password block
        changePasswordBlock(wait: true) { $('.changePassBlock') }
        currentPasswordSetDateLabel(wait: true) { changePasswordBlock.find('label', 0) }
        passwordWasSetInfo(wait: true) { changePasswordBlock.find('#userPass') }
        passwordEditCancelButton(wait: true) { changePasswordBlock.find('.btn-grey') }
        currentPassworLabel(wait: true) { changePasswordBlock.find('label', for: 'currentPassword') }
        currentPasswordInputField(wait: true) { changePasswordBlock.find('#currentPassword') }
        newPasswordLabel(wait: true) { changePasswordBlock.find('label', for: 'newPassword') }
        newPasswordInputField(wait: true) { changePasswordBlock.find('#newPassword') }
        confirmNewPasswordLabel(wait: true) { changePasswordBlock.find('label', for: 'confirmNewPassword') }
        confirmNewPasswordInputField(wait: true) { changePasswordBlock.find('#confirmNewPassword') }
        passwordChangeCancelButton(wait: true) { changePasswordBlock.find('.cancelChangePasswordForm') }
        passwordChangeSaveButton(wait: true) { changePasswordBlock.find('.savePassword') }
        passwordChangeInputFieldAlertBasic(wait: true) { changePasswordBlock.find('.error') }
        passwordChangeAlertSuccess(wait: true) { changePasswordBlock.find('.alert-success') }
        passwordAlertError(wait: true) { changePasswordBlock.find('.alert-danger') }
        passwordsMatchAlert(wait: true) { changePasswordBlock.find('.match') }
        //personal details block
        changePersonalDetailsBlock(required: false) { $('#personalDetails') }
        personalDetailsEditCancelButton(required: false) { changePersonalDetailsBlock.find('.btn-grey') }
        personalDetailsEditCancelButtonYellow(required: false) {
            changePersonalDetailsBlock.find('.btn-yellow')
        }
        firstNameLabel(wait: true) { changePersonalDetailsBlock.find('label', 0) }
        firstNameInputField(wait: true) { changePersonalDetailsBlock.find('#firstName') }
        lastNameLabel(wait: true) { changePersonalDetailsBlock.find('label', 1) }
        lastNameInputField(wait: true) { changePersonalDetailsBlock.find('#lastName') }
        titleLabel(wait: true) { changePersonalDetailsBlock.find('label', 2) }
        titleDDL(wait: true) { changePersonalDetailsBlock.find('select', name: 'personalDetails.title') }
        titleDDLOption(wait: true) { titleDDL.find('option') }
        genderLabel(wait: true) { changePersonalDetailsBlock.find('label', 3) }
        genderDDL(wait: true) { changePersonalDetailsBlock.find('select', name: 'personalDetails.gender') }
        genderDDLOption(wait: true) { genderDDL.find('option') }
        birthDateLabel(wait: true) { changePersonalDetailsBlock.find('label', 4) }
        birthDateInputField(wait: true) { changePersonalDetailsBlock.find('.datepicker-input') }
        personalDetilsCancelButton(required: false) { changePersonalDetailsBlock.find('.cancel.link') }
        personalDetilsSaveButton(required: false) { changePersonalDetailsBlock.find('.saveChanges') }
        personalDetilsAlertSuccess(wait: true) { changePersonalDetailsBlock.find('.alert-success') }
        personalDetilsAlertError(wait: true) { changePersonalDetailsBlock.find('.error') }
        //contact details block
        changeContactDetailsBlock(required: false) { $('#contactDetails') }
        contactDetailsEditCancelButton(required: false) { changeContactDetailsBlock.find('.btn-grey') }
        contactDetailsEditCancelButtonYellow(required: false) {
            changeContactDetailsBlock.find('.btn-yellow')
        }
        contactNumberLabel(wait: true) { changeContactDetailsBlock.find('label', 0) }
        contactNumberInputField(wait: true) { changeContactDetailsBlock.find('input', name: 'phoneNo') }
        addresSectionLabel(wait: true) { changeContactDetailsBlock.find('label', 1) }
        houseNumberLabel(required: false) { changeContactDetailsBlock.find('label', 2) }
        houseNumberInputField(wait: true) { changeContactDetailsBlock.find('input', name: 'house') }
        streetLabel(wait: true) { changeContactDetailsBlock.find('label', 3) }
        streetInputField(wait: true) { changeContactDetailsBlock.find('input', name: 'street') }
        townLabel(wait: true) { changeContactDetailsBlock.find('label', 4) }
        townInputField(wait: true) { changeContactDetailsBlock.find('input', name: 'city') }
        countyLabel(wait: true) { changeContactDetailsBlock.find('label', 5) }
        countyInputField(wait: true) { changeContactDetailsBlock.find('input', name: 'county') }
        countryLabel(wait: true) { changeContactDetailsBlock.find('label', 6) }
        countryInputField(wait: true) { changeContactDetailsBlock.find('input', name: 'country') }
        postcodeLabel(wait: true) { changeContactDetailsBlock.find('label', 7) }
        postcodeInputField(wait: true) { changeContactDetailsBlock.find('input', name: 'postCode') }
        findAddressButton(wait: true) { changeContactDetailsBlock.find('.field-postcode').find('.btn-success') }
        addressesDDL(wait: true) { changeContactDetailsBlock.find('.CheckoutDelivery__suggested') }
        addressesDDLOption(wait: true) { addressesDDL.find('option') }
        contactDetailsCancelButton(required: false) { changeContactDetailsBlock.find('.cancel.link') }
        contactDetailsSaveButton(required: false) { changeContactDetailsBlock.find('.saveChanges') }
        contactDetailsAlertSuccess(wait: true) { changeContactDetailsBlock.find('.alert-success') }
        contactDetailsAlertDanger(wait: true) { changeContactDetailsBlock.find('.alert-danger') }
        contactDetilsAlertError(wait: true) { changeContactDetailsBlock.find('.error') }

        preferencessSection(wait: true) { $('#preferencesDetails') }
        optInOutReceivingEmailsCheckbox(wait: true) { $(".PreferencesDetails-formBlock input") }
        optInOutReceivingEmailsCheckboxLabel(wait: true) { preferencessSection.$('.PreferencesDetails-description') }
    }

//email block
    def clickEmailChangeEditCancelButton() {
        waitFor { emailEditCancelButton.isDisplayed() }; emailEditCancelButton.click()
    }

    def clickEmailChangeSaveButton() { waitFor { emailChangeSaveButton.isDisplayed() }; emailChangeSaveButton.click() }

    def enterNewEmail(email) { waitFor { newEmailInputField.isDisplayed() }; newEmailInputField.value(email) }

    def retypeNewEmail(email) {
        waitFor { newEmailRetypeInputField.isDisplayed() }; newEmailRetypeInputField.value(email)
    }

    def fillNewEmailForm(email, emailRetype) { enterNewEmail(email); retypeNewEmail(emailRetype) }

    def clickEmailChangeCancelButton() {
        waitFor { emailChangeCancelButton.isDisplayed() }; emailChangeCancelButton.click()
    }
    //password block
    def clickPasswordChangeEditCancelButton() {
        waitFor { passwordEditCancelButton.isDisplayed() }; passwordEditCancelButton.click()
    }

    def clickPasswordChangeSaveButton() {
        sleep(500)
        waitFor { passwordChangeSaveButton.isDisplayed() }; passwordChangeSaveButton.click()
    }

    def enterCurrentPassword(oldPassword) {
        waitFor { currentPasswordInputField.isDisplayed() }; currentPasswordInputField.value(oldPassword)
    }

    def enterNewPassword(newPassword) {
        waitFor { newPasswordInputField.isDisplayed() }; newPasswordInputField.value(newPassword)
    }

    def retypeNewPassword(newPassword) {
        waitFor { confirmNewPasswordInputField.isDisplayed() }; confirmNewPasswordInputField.value(newPassword)
    }

    def fillNewPasswordForm(oldPassword, newPassword, newPasswordRetype) {
        enterCurrentPassword(oldPassword); enterNewPassword(newPassword); retypeNewPassword(newPasswordRetype)
    }

    def clickPasswordChangeCancelButton() {
        waitFor { passwordChangeCancelButton.isDisplayed() }; passwordChangeCancelButton.click()
    }
    //personal details block
    def clickPersonalDetailsChangeEditCancelButton() {
        waitFor { personalDetailsEditCancelButton.isDisplayed() }; personalDetailsEditCancelButton.click()
    }

    def clickPersonalDetailsChangeEditCancelButtonYellow() {
        waitFor { personalDetailsEditCancelButtonYellow.isDisplayed() }; personalDetailsEditCancelButtonYellow.click()
    }

    def enterDayOfBirth(date) { waitFor { birthDateInputField.isDisplayed() }; birthDateInputField.value(date) }

    def clickPersonalDataChangeSaveButton() {
        waitFor { personalDetilsSaveButton.isDisplayed() }; personalDetilsSaveButton.click()
    }

    def clickDayOfBirthInput() { waitFor { birthDateInputField.isDisplayed() }; birthDateInputField.click() }

    def clickSomeNeutralSpaceOnPersonalDetails() {
        waitFor { changePersonalDetailsBlock.find('.ico-block').isDisplayed() }
        changePersonalDetailsBlock.find('.ico-block').click()
    }

    def clickPersonalDetailsChangeCancelButton() {
        waitFor { personalDetilsCancelButton.isDisplayed() }; personalDetilsCancelButton.click()
    }

    def enterFirstName(name) { waitFor { firstNameInputField.isDisplayed() }; firstNameInputField.value(name) }

    def enterLastName(lastName) { waitFor { lastNameInputField.isDisplayed() }; lastNameInputField.value(lastName) }

    def expandTitleDDL() { waitFor { titleDDL.isDisplayed() }; titleDDL.click() }

    def clickChosenTitleOption(number) {
        waitFor { titleDDLOption[number].isDisplayed() }; titleDDLOption[number].click()
    }

    def expandGenderDDL() { waitFor { genderDDL.isDisplayed() }; genderDDL.click() }

    def clickChosenGenderOption(number) {
        waitFor { genderDDLOption[number].isDisplayed() }; genderDDLOption[number].click()
    }
    //contact details block
    def clickContactDetailsChangeEditCancelButton() {
        waitFor { contactDetailsEditCancelButton.isDisplayed() }; contactDetailsEditCancelButton.click()
    }

    def clickContactDetailsChangeEditCancelButtonYellow() {
        waitFor { contactDetailsEditCancelButtonYellow.isDisplayed() }; contactDetailsEditCancelButtonYellow.click()
    }

    def enterContactNumber(contactNumber) {
        waitFor { contactNumberInputField.isDisplayed() }; contactNumberInputField.value(contactNumber)
    }

    def enterHouseNumber(houseNumber) {
        waitFor { houseNumberInputField.isDisplayed() }; houseNumberInputField.value(houseNumber)
    }

    def enterStreet(street) { waitFor { streetInputField.isDisplayed() }; streetInputField.value(street) }

    def enterTown(town) { waitFor { townInputField.isDisplayed() }; townInputField.value(town) }

    def enterCounty(county) { waitFor { countyInputField.isDisplayed() }; countyInputField.value(county) }

    def enterCountry(country) { waitFor { countryInputField.isDisplayed() }; countryInputField.value(country) }

    def enterPostcode(postcode) { waitFor { postcodeInputField.isDisplayed() }; postcodeInputField.value(postcode) }

    def fillContactDetailsForm(contactNumber, houseNumber, street, town, county, country, postcode) {
        enterContactNumber(contactNumber)
        enterHouseNumber(houseNumber)
        enterStreet(street)
        enterTown(town)
        enterCounty(county)
        enterCountry(country)
        enterPostcode(postcode)
    }

    def clickFindAddressButton() { waitFor { findAddressButton.isDisplayed() }; findAddressButton.click() }

    def clickContactDetailsChangeCancelButton() {
        waitFor { contactDetailsCancelButton.isDisplayed() }; contactDetailsEditCancelButton.click()
    }

    def clickContactDetailsChangeSaveButton() {
        waitFor { contactDetailsSaveButton.isDisplayed() }; contactDetailsSaveButton.click()
    }

    def selectAddressDDLOption(optionNumber) {
        waitFor { addressesDDL.isDisplayed() }; addressesDDL.click()
        waitFor { addressesDDLOption[optionNumber].isDisplayed() }; addressesDDLOption[optionNumber].click()
    }

    def clickOptInOutReceivingEmailsCheckbox() {
        waitFor { optInOutReceivingEmailsCheckboxLabel.isDisplayed() }
        optInOutReceivingEmailsCheckboxLabel.click()
    }

    def isOptInOutReceivingEmailsCheckboxChecked() {
        return optInOutReceivingEmailsCheckbox.value()
    }
}