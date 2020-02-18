package modules.Epoints_modules.yourAccountSection

import geb.Module

/**
 * Created by kbaranowski on 2015-02-25.
 */
class profileTab extends Module{
    static content = {
        //email block
        changeEmailBlock{ $('.changeEmailBlock') }
        emailAddressLabel{ changeEmailBlock.find('label',0) }
        currentEmail{ changeEmailBlock.find('#userEmailVal') }
        emailEditCancelButton{ changeEmailBlock.find('.btn-grey') }
        newEmailLabel{ changeEmailBlock.find('label', for: 'newEmailAddress') }
        newEmailInputField{ changeEmailBlock.find('#newEmailAddress') }
        newEmailRetypeLabel{ changeEmailBlock.find('label', for: 'reEnterNewEmailAddress') }
        newEmailRetypeInputField{ changeEmailBlock.find('#reEnterNewEmailAddress') }
        emailChangeCancelButton{ changeEmailBlock.find('.cancelChangeEmailForm') }
        emailChangeSaveButton{ changeEmailBlock.find('.saveEmail') }
        emailChangeInputFieldAlertBasic{ changeEmailBlock.find('.error') }
        emailChangeAlertSuccess{ changeEmailBlock.find('.alert-success') }
        emailsMatchAlert{ changeEmailBlock.find('.match') }
        //password block
        changePasswordBlock{ $('.changePassBlock') }
        currentPasswordSetDateLabel{ changePasswordBlock.find('label') }
        passwordWasSetInfo{ changePasswordBlock.find('#userPass') }
        passwordEditCancelButton{ changePasswordBlock.find('.btn-grey') }
        currentPassworLabel{ changePasswordBlock.find('label', for: 'currentPassword') }
        currentPasswordInputField{ changePasswordBlock.find('#currentPassword') }
        newPasswordLabel{ changePasswordBlock.find('label', for: 'newPassword') }
        newPasswordInputField{ changePasswordBlock.find('#newPassword') }
        confirmNewPasswordLabel{ changePasswordBlock.find('label', for: 'confirmNewPassword') }
        confirmNewPasswordInputField{ changePasswordBlock.find('#confirmNewPassword') }
        passwordChangeCancelButton{ changePasswordBlock.find('.cancelChangePasswordForm') }
        passwordChangeSaveButton{ changePasswordBlock.find('.savePassword') }
        passwordChangeInputFieldAlertBasic{ changePasswordBlock.find('.error') }
        passwordChangeAlertSuccess{ changePasswordBlock.find('.alert-success') }
        passwordAlertError{ changePasswordBlock.find('.alert-danger') }
        passwordsMatchAlert{ changePasswordBlock.find('.match') }
        //personal details block
        changePersonalDetailsBlock{ $('#personalDetails') }
        personalDetailsEditCancelButton(required: false){ changePersonalDetailsBlock.find('.btn-grey') }
        personalDetailsEditCancelButtonYellow(required: false){ changePersonalDetailsBlock.find('.btn-yellow') }
        firstNameLabel{ changePersonalDetailsBlock.find('label',0) }
        firstNameInputField{ changePersonalDetailsBlock.find('#firstName') }
        lastNameLabel{ changePersonalDetailsBlock.find('label',1) }
        lastNameInputField{ changePersonalDetailsBlock.find('#lastName') }
        titleLabel{ changePersonalDetailsBlock.find('label',2) }
        titleDDL{ changePersonalDetailsBlock.find('select', name: 'personalDetails.title') }
        titleDDLOption{ titleDDL.find('option') }
        genderLabel{ changePersonalDetailsBlock.find('label',3) }
        genderDDL{ changePersonalDetailsBlock.find('select', name: 'personalDetails.gender') }
        genderDDLOption{ genderDDL.find('option') }
        birthDateLabel{ changePersonalDetailsBlock.find('label',4) }
        birthDateInputField{ changePersonalDetailsBlock.find('#birthDate') }
        personalDetilsCancelButton{ changePersonalDetailsBlock.find('.cancel.link') }
        personalDetilsSaveButton{ changePersonalDetailsBlock.find('.saveChanges') }
        personalDetilsAlertSuccess{ changePersonalDetailsBlock.find('.alert-success') }
        personalDetilsAlertError{ changePersonalDetailsBlock.find('.alert-danger') }
        //contact details block
        changeContactDetailsBlock{ $('#contactDetails') }
        contactDetailsEditCancelButton(required: false){ changeContactDetailsBlock.find('.btn-grey') }
        contactDetailsEditCancelButtonYellow(required: false){ changeContactDetailsBlock.find('.btn-yellow') }
        contactNumberLabel{ changeContactDetailsBlock.find('label',0) }
        contactNumberInputField{ changeContactDetailsBlock.find('#phoneNumber') }
        addresSectionLabel{ changeContactDetailsBlock.find('label',1) }
        houseNumberLabel(required: false){ changeContactDetailsBlock.find('label',2) }
        houseNumberInputField{ changeContactDetailsBlock.find('input', name: 'contactDetails.address.house') }
        streetLabel{ changeContactDetailsBlock.find('label',3) }
        streetInputField{ changeContactDetailsBlock.find('input', name: 'contactDetails.address.street') }
        townLabel{ changeContactDetailsBlock.find('label',4) }
        townInputField{ changeContactDetailsBlock.find('input', name: 'contactDetails.address.city') }
        countyLabel{ changeContactDetailsBlock.find('label',5) }
        countyInputField{ changeContactDetailsBlock.find('input', name: 'contactDetails.address.county') }
        countryLabel{ changeContactDetailsBlock.find('label',6) }
        countryInputField{ changeContactDetailsBlock.find('input', name: 'contactDetails.address.country') }
        postcodeLabel{ changeContactDetailsBlock.find('label',7) }
        postcodeInputField{ changeContactDetailsBlock.find('input', name: 'contactDetails.address.postCode') }
        findAddressButton{ changeContactDetailsBlock.find('.field-postcode').find('.btn-success') }
        contactDetailsCancelButton{ changeContactDetailsBlock.find('.cancel.link') }
        contactDetailsSaveButton{ changeContactDetailsBlock.find('.saveChanges') }
        contactDetailsAlertSuccess{ changeContactDetailsBlock.find('.alert-success') }
        contactDetailsAlertError{ changeContactDetailsBlock.find('.alert-danger') }

    }
    //email block
    def clickEmailChangeEditCancelButton(){ waitFor{ emailEditCancelButton.isDisplayed() }; emailEditCancelButton.click() }
    def clickEmailChangeSaveButton(){ waitFor{ emailChangeSaveButton.isDisplayed() }; emailChangeSaveButton.click() }
    def enterNewEmail(email){ waitFor{ newEmailInputField.isDisplayed() }; newEmailInputField.value(email) }
    def retypeNewEmail(email){ waitFor{ newEmailRetypeInputField.isDisplayed() }; newEmailRetypeInputField.value(email) }
    def fillNewEmailForm(email, emailRetype){ enterNewEmail(email); retypeNewEmail(emailRetype) }
    def clickEmailChangeCancelButton(){ waitFor{ emailChangeCancelButton.isDisplayed() }; emailChangeCancelButton.click() }
    //password block
    def clickPasswordChangeEditCancelButton(){ waitFor{ passwordEditCancelButton.isDisplayed() }; passwordEditCancelButton.click() }
    def clickPasswordChangeSaveButton(){ waitFor{ passwordChangeSaveButton.isDisplayed() }; passwordChangeSaveButton.click() }
    def enterCurrentPassword(oldPassword){ waitFor{ currentPasswordInputField.isDisplayed() }; currentPasswordInputField.value(oldPassword) }
    def enterNewPassword(newPassword){ waitFor{ newPasswordInputField.isDisplayed() }; newPasswordInputField.value(newPassword) }
    def retypeNewPassword(newPassword){ waitFor{ confirmNewPasswordInputField.isDisplayed() }; confirmNewPasswordInputField.value(newPassword) }
    def fillNewPasswordForm(oldPassword, newPassword, newPasswordRetype){ enterCurrentPassword(oldPassword); enterNewPassword(newPassword); retypeNewPassword(newPasswordRetype) }
    def clickPasswordChangeCancelButton(){ waitFor{ passwordChangeCancelButton.isDisplayed() }; passwordChangeCancelButton.click() }
    //personal details block
    def clickPersonalDetailsChangeEditCancelButton(){ waitFor{ personalDetailsEditCancelButton.isDisplayed() }; personalDetailsEditCancelButton.click() }
    def clickPersonalDetailsChangeEditCancelButtonYellow(){ waitFor{ personalDetailsEditCancelButtonYellow.isDisplayed() }; personalDetailsEditCancelButtonYellow.click() }
    def enterDayOfBirth(date){ waitFor{ birthDateInputField.isDisplayed() }; birthDateInputField.value(date) }
    def clickPersonalDataChangeSaveButton(){ waitFor{ personalDetilsSaveButton.isDisplayed() }; personalDetilsSaveButton.click() }
    def clickDayOfBirthInput(){ waitFor{ birthDateInputField.isDisplayed() }; birthDateInputField.click() }
    def clickSomeNeutralSpaceOnPersonalDetails(){waitFor{ changePersonalDetailsBlock.find('.ico-block').isDisplayed() }; changePersonalDetailsBlock.find('.ico-block').click() }
    def clickPersonalDetailsChangeCancelButton(){ waitFor{ personalDetilsCancelButton.isDisplayed() }; personalDetilsCancelButton.click() }
    def enterFirstName(name){ waitFor{ firstNameInputField.isDisplayed() };  firstNameInputField.value(name) }
    def enterLastName(lastName){ waitFor{ lastNameInputField.isDisplayed() };  lastNameInputField.value(lastName) }
    def expandTitleDDL(){ waitFor{ titleDDL.isDisplayed() }; titleDDL.click() }
    def clickChosenTitleOption(number){ waitFor{ titleDDLOption[number].isDisplayed() }; titleDDLOption[number].click() }
    def expandGenderDDL(){ waitFor{ genderDDL.isDisplayed() }; genderDDL.click() }
    def clickChosenGenderOption(number){ waitFor{ genderDDLOption[number].isDisplayed() }; genderDDLOption[number].click() }
    //contact details block
    def clickContactDetailsChangeEditCancelButton(){ waitFor{ contactDetailsEditCancelButton.isDisplayed() }; contactDetailsEditCancelButton.click() }
    def clickContactDetailsChangeEditCancelButtonYellow(){ waitFor{ contactDetailsEditCancelButtonYellow.isDisplayed() }; contactDetailsEditCancelButtonYellow.click() }
    def enterContactNumber(contactNumber){ waitFor{ contactNumberInputField.isDisplayed() }; contactNumberInputField.value(contactNumber) }
    def enterHouseNumber(houseNumber){ waitFor{ houseNumberInputField.isDisplayed() }; houseNumberInputField.value(houseNumber) }
    def enterStreet(street){ waitFor{ streetInputField.isDisplayed() }; streetInputField.value(street) }
    def enterTown(town){ waitFor{ townInputField.isDisplayed() }; townInputField.value(town) }
    def enterCounty(county){ waitFor{ countyInputField.isDisplayed() }; countyInputField.value(county) }
    def enterCountry(country){ waitFor{ countryInputField.isDisplayed() }; countryInputField.value(country) }
    def enterPostcode(postcode){ waitFor{ postcodeInputField.isDisplayed() }; postcodeInputField.value(postcode) }
    def fillContactDetailsForm(contactNumber,houseNumber,street,town,county,country,postcode){
        enterContactNumber(contactNumber)
        enterHouseNumber(houseNumber)
        enterStreet(street)
        enterTown(town)
        enterCounty(county)
        enterCountry(country)
        enterPostcode(postcode)
    }
    def clickFindAddressButton(){ waitFor{ findAddressButton.isDisplayed() }; findAddressButton.click() }
    def clickContactDetailsChangeCancelButton(){ waitFor{ contactDetailsCancelButton.isDisplayed() }; contactDetailsEditCancelButton.click() }
    def clickContactDetailsChangeSaveButton(){ waitFor{ contactDetailsSaveButton.isDisplayed() }; contactDetailsSaveButton.click() }
}