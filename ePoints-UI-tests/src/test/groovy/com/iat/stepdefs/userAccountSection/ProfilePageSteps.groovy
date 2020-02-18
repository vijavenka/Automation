package com.iat.stepdefs.userAccountSection

import com.iat.Config
import com.iat.pages.home.EpointsHomePage
import com.iat.pages.userAccount.EmailActivationPage
import com.iat.pages.userAccount.profile.ProfilePage
import com.iat.repository.impl.UserRepositoryImpl
import com.iat.stepdefs.envVariables
import com.iat.stepdefs.utils.DataExchanger
import com.iat.stepdefs.utils.Functions
import geb.Browser

import static cucumber.api.groovy.EN.*
import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.*

def epointsHomePage = new EpointsHomePage()
def profilePage = new ProfilePage()
def emailActivationPage = new EmailActivationPage()

def func = new Functions()
def browser = new Browser()
def envVar = new envVariables()
def dataExchanger = DataExchanger.getInstance()

boolean optInOutReceivingEmailsCheckboxChecked

// 1.1 //  ------------------------------------------------------------------------------ Your account - profile section
// --------------------------------------------------------------------------------------------------- email row content
Given(~/^Profile page is opened in '(.+?)' context by '(.+?)' user$/) { String partner, String user ->
    to EpointsHomePage
    epointsHomePage = page
    if (user == "default")
        epointsHomePage.signInUserToEpoints(Config.unitedUser, Config.unitedUserPassword)
    else if (user == 'created') {
        func.clearCookiesAndStorage()
        epointsHomePage.signInUserToEpoints(dataExchanger.getEmail(), dataExchanger.getPassword())
    } else
        epointsHomePage.signInUserToEpoints(user.split(',')[0], user.split(',')[1])
    if (partner.toLowerCase() == "united") {
        epointsHomePage.accountSwitcher.unitedSwitcher.click()
        waitFor { epointsHomePage.accountSwitcher.currentAccount == "United" }
    } else if (partner.toLowerCase() == "epoints") {
        epointsHomePage.accountSwitcher.epointsSwitcher.click()
        waitFor { epointsHomePage.accountSwitcher.currentAccount == "epoints" }
    }

    epointsHomePage.cookiePanelModule.clickAcceptButtonOnCookiePanel()
    epointsHomePage.goToUserProfilePage(false)

    sleep(1000)
    at ProfilePage
    profilePage = page
}
Given(~/^Email address is properly displayed$/) { ->
    waitFor { profilePage.emailAddressLabel.isDisplayed() }
    waitFor { profilePage.currentEmail.isDisplayed() }
    waitFor { profilePage.emailEditCancelButton.isDisplayed() }
    assert profilePage.emailAddressLabel.text() == envVar.profileTabCurrentEmailLabel
    assert profilePage.currentEmail.text() == Config.unitedUser
    assert profilePage.emailEditCancelButton.isDisplayed()
}
Given(~/^Changing email fields are hidden$/) { ->
    waitFor { !profilePage.newEmailLabel.isDisplayed() }
    assert !profilePage.newEmailLabel.isDisplayed()
    assert !profilePage.newEmailInputField.isDisplayed()
    assert !profilePage.newEmailRetypeLabel.isDisplayed()
    assert !profilePage.newEmailRetypeInputField.isDisplayed()
    assert !profilePage.emailChangeCancelButton.isDisplayed()
    assert !profilePage.emailChangeSaveButton.isDisplayed()
}
When(~/^User click edit email button$/) { ->
    profilePage.clickEmailChangeEditCancelButton()
}
Then(~/^Changing email fields will be displayed$/) { ->
    waitFor { profilePage.emailChangeSaveButton.isDisplayed() }
    assert profilePage.newEmailLabel.text() == envVar.profileTabNewEmailLabel
    assert profilePage.newEmailInputField.isDisplayed()
    assert profilePage.newEmailRetypeLabel.text() == envVar.profileTabRetypeNewEmailLabel
    assert profilePage.newEmailRetypeInputField.isDisplayed()
    assert profilePage.emailChangeCancelButton.isDisplayed()
    assert profilePage.emailChangeSaveButton.isDisplayed()
}

// 1.2 //  ------------------------------------------------------------------------------ Your account - profile section
// ---------------------------------------------------------------------------------------------- fields required alerts
Given(~/^Email edit form is opened$/) { ->
    profilePage.clickEmailChangeEditCancelButton()
    waitFor { profilePage.emailChangeSaveButton.isDisplayed() }
}
When(~/^User fill change email form with improper data '(.+?)'$/) { String newEmail ->
    profilePage.fillNewEmailForm(newEmail, newEmail)
    waitFor { profilePage.emailsMatchAlert.isDisplayed() }
    assert profilePage.emailsMatchAlert.text() == envVar.profileTabEmailsMatchAlert
}
Then(~/^Email address is invalid alert will be presented$/) { ->
    waitFor { profilePage.emailAddressIsInvalidAlert.isDisplayed() }
    assert profilePage.emailAddressIsInvalidAlert.text() == envVar.profileTabEmailIsInvalidAlert
    assert profilePage.emailEmailsDoNotMatchAlert.text() == ''
}
When(~/^User remove previous entered new email data$/) { ->
    profilePage.fillNewEmailForm('', '')
}
Then(~/^Email address is required alert will be presented$/) { ->
    waitFor { profilePage.emailAddressIsRequiredAlert.isDisplayed() }
    assert profilePage.emailAddressIsRequiredAlert.text() == envVar.profileTabEmailIsRequiredAlert
    assert profilePage.emailAddressConfirmationIsRequiredAlert.text() == envVar.profileTabEmailConfirmationIsRequiredAlert
}

// 1.3 //  ------------------------------------------------------------------------------ Your account - profile section
// ------------------------------------------------------------------------------------------- emails do not match alert
When(~/^User fill email change form with two different emails$/) { ->
    profilePage.fillNewEmailForm(Config.epointsUser, Config.epointsUser + '2')
}
When(~/^Click save new email button$/) { ->
    profilePage.clickEmailChangeSaveButton()
}
Then(~/^Emails do not match alert will be displayed$/) { ->
    waitFor { profilePage.emailEmailsDoNotMatchAlert.isDisplayed() }
    assert profilePage.emailEmailsDoNotMatchAlert.text() == envVar.profileTabEmailIsNotMatchAlert
}

// 1.4 //  ------------------------------------------------------------------------------ Your account - profile section
// ------------------------------------------------------------------------------------------------------- cancel button
When(~/^User click cancel setting new email button$/) { ->
    profilePage.clickEmailChangeCancelButton()
}

// 1.5 //  ------------------------------------------------------------------------------ Your account - profile section
// ---------------------------------------------------------------------------------------- validation for current email
When(~/^User fill email change form with email which is already used$/) { ->
    profilePage.fillNewEmailForm(Config.unitedUser, Config.unitedUser)
}
Then(~/^Email should be different from existing one message will be displayed$/) { ->
    waitFor { profilePage.emailChangeAlertDanger.isDisplayed() }
    assert profilePage.emailChangeAlertDanger.text().contains(envVar.profileTabNewEmailHasBeDifferentInfo)
}

// 1.6 //  ------------------------------------------------------------------------------ Your account - profile section
// ------------------------------------------------------------------------------------------ validation for taken email
When(~/^User fill email change form with email which is already taken$/) { ->
    profilePage.fillNewEmailForm(Config.epointsUser, Config.epointsUser)
}
Then(~/^Email is in use message will be displayed$/) { ->
    waitFor { profilePage.emailChangeAlertDanger.isDisplayed() }
    assert profilePage.emailChangeAlertDanger.text().contains(envVar.profileTabEmailUserInfo)
}

// 1.7 //  ------------------------------------------------------------------------------ Your account - profile section
// ---------------------------------------------------------------------------------------------- email properly changed
Given(~/^User is logged and profile page is opened with data '(.+?)' '(.+?)' in '(.+?)' context$/) { String email, String password, String partner ->
    to EpointsHomePage
    epointsHomePage = page
    epointsHomePage.signInUserToEpoints(email, password)
    if (partner.toLowerCase() == "united") {
        epointsHomePage.accountSwitcher.unitedSwitcher.click()
        waitFor { epointsHomePage.accountSwitcher.currentAccount == "United" }
    } else if (partner.toLowerCase() == "epoints") {
        epointsHomePage.accountSwitcher.epointsSwitcher.click()
        waitFor { epointsHomePage.accountSwitcher.currentAccount == "epoints" }
    }
    epointsHomePage.cookiePanelModule.clickAcceptButtonOnCookiePanel()
    epointsHomePage.goToUserProfilePage(false)
    at ProfilePage
    profilePage = page
}
When(~/^User fill change email form with proper data '(.+?)'$/) { String newEmail ->
    profilePage.fillNewEmailForm(newEmail, newEmail)
    waitFor { profilePage.emailsMatchAlert.isDisplayed() }
    assert profilePage.emailsMatchAlert.text() == envVar.profileTabEmailsMatchAlert
}
Then(~/^Email properly changed alert will be displayed$/) { ->
    waitFor { profilePage.emailChangeAlertSuccess.isDisplayed() }
    assert profilePage.emailChangeAlertSuccess.text().replace("\n", "").replace(" ", "") == envVar.profileTabSuccessAlert.replace(" ", "")
}
String tokenValue
When(~/^User '(.+?)' will follow new email confirmation link$/) { String email ->
    tokenValue = new UserRepositoryImpl().getTokenValueByUUID(new UserRepositoryImpl().findByEmail(email).getUuid())
    browser.go(envVar.epointsLink + envVar.emailChangeConfirmationPageURL + tokenValue)
    at EmailActivationPage
    emailActivationPage = page
}
Then(~/^Email will be properly changed$/) { ->
    //leave empty second test proper run will be enough confirmation
}
Then(~/^Email confirmation page has proper content '(.+?)'$/) { String newEmail ->
    waitFor(15) { emailActivationPage.accountDashboardButton.isDisplayed() }
    waitFor(15) { emailActivationPage.earnMoreEpointsButton.isDisplayed() }
    System.out.println(emailActivationPage.pageContent.text())
    assert emailActivationPage.pageContent.text().replace("\n", "").replace(" ", "") == envVar.returnEmailAddressChangeConfirmationPageText(newEmail).replace(" ", "")
}

// 2.1 //  ----------------------------------------------------------------------------- Your account - profile section
// ------------------------------------------------------------------------------------------------ password row content
Given(~/^Current password set data is properly displayed$/) { ->
    waitFor { profilePage.currentPasswordSetDateLabel.isDisplayed() }
    waitFor { profilePage.passwordWasSetInfo.isDisplayed() }
    waitFor { profilePage.passwordEditCancelButton.isDisplayed() }
    assert profilePage.currentPasswordSetDateLabel.text() == envVar.profileTabPasswordSetDateLabel
    assert profilePage.passwordWasSetInfo.text().contains(envVar.profileTabPasswordSetDateText)
    assert profilePage.passwordEditCancelButton.isDisplayed()
}
Given(~/^Changing password fields are hidden$/) { ->
    waitFor { !profilePage.currentPassworLabel.isDisplayed() }
    assert !profilePage.currentPassworLabel.isDisplayed()
    assert !profilePage.currentPasswordInputField.isDisplayed()
    assert !profilePage.newPasswordLabel.isDisplayed()
    assert !profilePage.newPasswordInputField.isDisplayed()
    assert !profilePage.confirmNewPasswordLabel.isDisplayed()
    assert !profilePage.confirmNewPasswordInputField.isDisplayed()
    assert !profilePage.passwordChangeCancelButton.isDisplayed()
    assert !profilePage.passwordChangeSaveButton.isDisplayed()
}
When(~/^User click edit password button$/) { ->
    profilePage.clickPasswordChangeEditCancelButton()
}
Then(~/^Changing password fields will be displayed$/) { ->
    waitFor { profilePage.currentPassworLabel.isDisplayed() }
    assert profilePage.currentPassworLabel.text() == envVar.profileTabCurrentPasswordLabel
    assert profilePage.currentPasswordInputField.isDisplayed()
    assert profilePage.newPasswordLabel.text() == envVar.profileTabNewPasswordLabel
    assert profilePage.newPasswordInputField.isDisplayed()
    assert profilePage.confirmNewPasswordLabel.text() == envVar.profileTabConfirmPasswordLabel
    assert profilePage.confirmNewPasswordInputField.isDisplayed()
    assert profilePage.passwordChangeCancelButton.isDisplayed()
    assert profilePage.passwordChangeSaveButton.isDisplayed()
}

// 2.2 //  ------------------------------------------------------------------------------ Your account - profile section
// ---------------------------------------------------------------------------------------------- fields required alerts
Given(~/^Password edit form is opened$/) { ->
    profilePage.clickPasswordChangeEditCancelButton()
    waitFor { profilePage.passwordChangeSaveButton.isDisplayed() }
}
Given(~/^User remove previous entered new password data$/) { ->
    profilePage.fillNewPasswordForm('', '', '')
}
Then(~/^Three fields are required alerts will be displayed$/) { ->
    waitFor { profilePage.passwordChangeInputFieldAlertBasic[0].isDisplayed() }
    assert profilePage.passwordChangeInputFieldAlertBasic[0].text() == envVar.profileTabCurrentPasswordIsRequiredAlert
    assert profilePage.passwordChangeInputFieldAlertBasic[1].text() == envVar.profileTabNewPasswordIsRequiredAlert
    assert profilePage.passwordChangeInputFieldAlertBasic[3].text() == envVar.profileTabNewPasswordConfirmationIsRequiredAlert
}

// 2.3 //  ------------------------------------------------------------------------------ Your account - profile section
// ---------------------------------------------------------------------------------------- passwords do not match alert
When(~/^User fill password change form with two different password$/) { ->
    profilePage.fillNewPasswordForm(Config.epointsUserPassword, Config.epointsUserPassword, Config.epointsUserPassword + '2')
}
When(~/^Click save new password button$/) { ->
    profilePage.clickPasswordChangeSaveButton()
}
Then(~/^passwords do not match alert will be displayed$/) { ->
    waitFor { profilePage.passwordChangeInputFieldAlertBasic[2].isDisplayed() }
    assert profilePage.passwordChangeInputFieldAlertBasic[2].text() == envVar.profileTabPasswordsDoNotMatchAlert
}

// 2.4 //  ------------------------------------------------------------------------------ Your account - profile section
// ---------------------------------------------------------------------------------------- wrong current password alert
When(~/^User fill password change form with wrong current password$/) { ->
    profilePage.fillNewPasswordForm(Config.epointsUserPassword + '2', Config.epointsUserPassword, Config.epointsUserPassword)

}
Then(~/^Wrong current password alert will be displayed$/) { ->
    waitFor { profilePage.passwordAlertError.isDisplayed() }
    profilePage.passwordAlertError.text() == envVar.profileTabWrongCurrentPasswordMessage
}

// 2.5 //  ------------------------------------------------------------------------------ Your account - profile section
// ------------------------------------------------------------------------------------------------------- cancel button
When(~/^User click cancel setting new password button$/) { ->
    profilePage.clickPasswordChangeCancelButton()
}

// 2.6 //  ------------------------------------------------------------------------------ Your account - profile section
// ------------------------------------------------------------------------------------------- password properly changed
When(~/^User fill change password form with proper data '(.+?)' '(.+?)'$/) { String oldPassword, String newPassword ->
    profilePage.fillNewPasswordForm(oldPassword, newPassword, newPassword)
    waitFor { profilePage.passwordsMatchAlert.isDisplayed() }
    assert profilePage.passwordsMatchAlert.text() == envVar.profileTabPasswordsMatchAlert
}
Then(~/^Password properly changed alert will be displayed$/) { ->
    waitFor { profilePage.passwordChangeAlertSuccess.isDisplayed() }
    assert profilePage.passwordChangeAlertSuccess.text().replace("\n", "").replace(" ", "") == envVar.profileTabSuccessPasswordAlert.replace(" ", "")
}

// 3.1 //  ------------------------------------------------------------------------------ Your account - profile section
// ---------------------------------------------------------------------------------------- personal details row content
Given(~/^User personal data are properly displayed$/) { ->
    waitFor { profilePage.changePersonalDetailsBlock.isDisplayed() }
    assert profilePage.firstNameLabel.text() == envVar.personalDetailsTabFirstNameLabel
    assert profilePage.lastNameLabel.text() == envVar.personalDetailsTabLastNameLabel
    assert profilePage.titleLabel.text() == envVar.personalDetailsTabTitleLabel
    assert profilePage.genderLabel.text() == envVar.personalDetailsTabGenderLabel
    assert profilePage.birthDateLabel.text() == envVar.personalDetailsTabDateOfBirthLabel
    assert profilePage.firstNameInputField.isDisplayed()
    assert profilePage.lastNameInputField.isDisplayed()
    assert profilePage.titleDDL.isDisplayed()
    assert profilePage.genderDDL.isDisplayed()
    assert profilePage.birthDateInputField.isDisplayed()
}
When(~/^User click edit personal details button$/) { ->
    sleep(500)
    waitFor {
        (profilePage.personalDetailsEditCancelButton.isDisplayed() || profilePage.personalDetailsEditCancelButtonYellow.isDisplayed())
    }
    if (profilePage.personalDetailsEditCancelButton.isDisplayed())
        profilePage.clickPersonalDetailsChangeEditCancelButton()
    else if (profilePage.personalDetailsEditCancelButtonYellow.isDisplayed())
        profilePage.clickPersonalDetailsChangeEditCancelButtonYellow()
    sleep(1000)
}
Then(~/^Cancel button will be displayed in personal details module$/) { ->
    waitFor { profilePage.personalDetilsCancelButton.isDisplayed() }
    assert profilePage.personalDetilsCancelButton.isDisplayed()
}
Then(~/^Save button will be displayed in personal details module$/) { ->
    waitFor { profilePage.personalDetilsSaveButton.isDisplayed() }
    assert profilePage.personalDetilsSaveButton.isDisplayed()
}

// 3.2 //  ------------------------------------------------------------------------------ Your account - profile section
// ----------------------------------------------------------------------------- personal details fields required alerts
When(~/^User provide date of birth in wrong format$/) { ->
    profilePage.clickDayOfBirthInput()
    profilePage.enterDayOfBirth('1234')
    profilePage.clickSomeNeutralSpaceOnPersonalDetails()
    sleep(1000)
}
Then(~/^Click save personal details button$/) { ->
    profilePage.clickPersonalDataChangeSaveButton()
}
Then(~/^Invalid date format alert will be displayed$/) { ->
    waitFor { profilePage.personalDetilsAlertError.isDisplayed() }
    profilePage.personalDetilsAlertError.text() == envVar.personalDetailsInvalidDateFormatAlert
}

// 3.3 //  ------------------------------------------------------------------------------ Your account - profile section
// -------------------------------------------------------------------------------------- personal details cancel button
When(~/^User click cancel setting new personal details button$/) { ->
    profilePage.clickPersonalDetailsChangeCancelButton()
}
Then(~/^Edit view of personal details will be closed$/) { ->
    waitFor { !profilePage.personalDetilsCancelButton.isDisplayed() }
    waitFor { !profilePage.personalDetilsSaveButton.isDisplayed() }
    assert !profilePage.personalDetilsCancelButton.isDisplayed()
    assert !profilePage.personalDetilsSaveButton.isDisplayed()
}

// 3.4 //  ------------------------------------------------------------------------------ Your account - profile section
// ----------------------------------------------------------------------------------- personal details properly changed
String firstName = 'name' + func.returnRandomValue(1000)
String lastName = 'lastName' + func.returnRandomValue(1000)
String title
String gender
String dateOfBirth = (func.returnRandomValue(17) + 10) + '/0' + (func.returnRandomValue(8) + 1) + '/' + (func.returnRandomValue(20) + 2000)
When(~/^User provide new personal details data$/) { ->
    profilePage.enterFirstName(firstName)
    profilePage.enterLastName(lastName)
    profilePage.expandTitleDDL()
    waitFor { profilePage.titleDDLOption.size() == 4 }
    random = func.returnRandomValue(profilePage.titleDDLOption.size() - 1) + 1
    title = profilePage.titleDDLOption[random].text()
    profilePage.clickChosenTitleOption(random)
    profilePage.expandGenderDDL()
    waitFor { profilePage.genderDDLOption.size() == 3 }
    random = func.returnRandomValue(profilePage.genderDDLOption.size() - 1) + 1
    gender = profilePage.genderDDLOption[random].text()
    profilePage.clickChosenGenderOption(random)
    profilePage.clickDayOfBirthInput()
    profilePage.enterDayOfBirth(dateOfBirth)
    profilePage.clickSomeNeutralSpaceOnPersonalDetails()
    sleep(1000)
}
Then(~/^New personal details data will be properly saved$/) { ->
    waitFor { !profilePage.personalDetilsSaveButton.isDisplayed() }
    browser.getDriver().navigate().refresh()
    waitFor { profilePage.firstNameInputField.attr('value') == firstName }
    assert profilePage.lastNameInputField.attr('value') == lastName
    assert profilePage.titleDDL.attr('value') == title
    assert profilePage.genderDDL.attr('value').toLowerCase() == gender.toLowerCase()
    assert profilePage.birthDateInputField.attr('value') == dateOfBirth
}

// 4.1 //  ------------------------------------------------------------------------------ Your account - contact section
// ---------------------------------------------------------------------------------------- contact details  row content
Given(~/^User contact data are properly displayed$/) { ->
    waitFor { profilePage.changePersonalDetailsBlock.isDisplayed() }
    assert profilePage.contactNumberLabel.text() == envVar.contactDetailsContactNumberLabel
    assert profilePage.addresSectionLabel.text() == envVar.contactDetailsAddressLabel
    waitFor { profilePage.contactNumberInputField.isDisplayed() }
    assert profilePage.houseNumberInputField.isDisplayed()
    assert profilePage.streetInputField.isDisplayed()
    assert profilePage.townInputField.isDisplayed()
    assert profilePage.countyInputField.isDisplayed()
    assert profilePage.countryInputField.isDisplayed()
    assert profilePage.postcodeInputField.isDisplayed()
}
When(~/^User click edit contact details button$/) { ->
    sleep(500)
    waitFor {
        profilePage.contactDetailsEditCancelButton.isDisplayed() || profilePage.contactDetailsEditCancelButtonYellow.isDisplayed()
    }
    if (profilePage.contactDetailsEditCancelButton.isDisplayed())
        profilePage.clickContactDetailsChangeEditCancelButton()
    else
        profilePage.clickContactDetailsChangeEditCancelButtonYellow()
    sleep(1000)
}
Then(~/^User contact data are properly displayed in edit mode$/) { ->
    waitFor { profilePage.changePersonalDetailsBlock.isDisplayed() }
    assert profilePage.contactNumberLabel.text() == envVar.contactDetailsContactNumberLabel
    assert profilePage.addresSectionLabel.text() == envVar.contactDetailsAddressLabel
    assert profilePage.houseNumberLabel.text() == envVar.contactDetailsHouseLabel
    assert profilePage.streetLabel.text() == envVar.contactDetailsStreetLabel
    assert profilePage.townLabel.text() == envVar.contactDetailsTownLabel
    assert profilePage.countyLabel.text() == envVar.contactDetailsCountyLabel
    assert profilePage.countryLabel.text() == envVar.contactDetailsCountryLabel
    assert profilePage.postcodeLabel.text() == envVar.contactDetailsPostcodeLabel
}
Then(~/^Cancel button will be displayed in contact details module$/) { ->
    profilePage.contactDetailsCancelButton.isDisplayed()
}
Then(~/^Save button will be displayed in contact details module$/) { ->
    profilePage.contactDetailsSaveButton.isDisplayed()
}
Then(~/^Find address button will be displayed in contact details module$/) { ->
    profilePage.findAddressButton.isDisplayed()
}

// 4.2 //  ------------------------------------------------------------------------------ Your account - contact section
// ------------------------------------------------------------------------------------------------------ address finder
When(~/^User provides proper UK postcode$/) { ->
    profilePage.enterPostcode(envVar.UKPostcode)
}
When(~/^User click find address button$/) { ->
    profilePage.clickFindAddressButton()
}
Then(~/^DDL with addresses will be displayed$/) { ->
    waitFor { profilePage.addressesDDL.isDisplayed() }
}
Then(~/^One of ddl addresses can be used to complete contact details$/) { ->
    profilePage.selectAddressDDLOption(1)
    waitFor { profilePage.houseNumberInputField.value().size() > 0 }
    assert profilePage.streetInputField.value().size() > 0
    assert profilePage.townInputField.value().size() > 0
    assert profilePage.countryInputField.value().size() > 0
}

// 4.3 //  ------------------------------------------------------------------------------ Your account - contact section
// ---------------------------------------------------------------------------------------- address finder wrong address
When(~/^User provides incorrect UK postcode$/) { ->
    profilePage.enterPostcode('wrongPostCode')
}
Then(~/^Incorrect UK postcode provided alert will be displayed$/) { ->
    waitFor { profilePage.contactDetilsAlertError.isDisplayed() }
    assert profilePage.contactDetilsAlertError.text().equals('Postcode WRONGPOSTCODE not found')
}

// 4.4 //  ------------------------------------------------------------------------------ Your account - contact section
// ------------------------------------------------------------------------------------------------------- cancel button
When(~/^User click cancel setting new contact details button$/) { ->
    profilePage.clickContactDetailsChangeEditCancelButton()
}
Then(~/^Edit view of contact details will be closed$/) { ->
    waitFor { !profilePage.contactDetailsCancelButton.isDisplayed() }
    waitFor { !profilePage.contactDetailsSaveButton.isDisplayed() }
    assert !profilePage.contactDetailsCancelButton.isDisplayed()
    assert !profilePage.contactDetailsSaveButton.isDisplayed()
}

// 4.5 //  ------------------------------------------------------------------------------ Your account - contact section
// ------------------------------------------------------------------------------------ contact details properly changed
String contactNumber = 'conNum' + func.returnEpochOfCurrentDay()
String houseNumber = 'houseNumber' + func.returnEpochOfCurrentDay()
String street = 'street' + func.returnEpochOfCurrentDay()
String town = 'town' + func.returnEpochOfCurrentDay()
String county = 'county' + func.returnEpochOfCurrentDay()
String country = 'UK'
String postcode = envVar.UKPostcode
When(~/^User provide new contact details data$/) { ->
    profilePage.fillContactDetailsForm(contactNumber, houseNumber, street, town, county, country, postcode)
}
When(~/^Click save contact details button$/) { ->
    profilePage.clickContactDetailsChangeSaveButton()
}
Then(~/^New contact details data will be properly saved$/) { ->
    waitFor { !profilePage.contactDetailsSaveButton.isDisplayed() }
    browser.getDriver().navigate().refresh()
    waitFor { profilePage.contactNumberInputField.attr('value') == contactNumber }

    assert profilePage.houseNumberInputField.attr('value') == houseNumber
    assert profilePage.streetInputField.attr('value') == street
    assert profilePage.townInputField.attr('value') == town
    assert profilePage.countyInputField.attr('value') == county
    assert profilePage.countryInputField.attr('value') == country
    assert profilePage.postcodeInputField.attr('value') == postcode
}

Then(~/^Preferences section is available$/) { ->
    assertThat("Preferences section is not available", profilePage.preferencessSection.isDisplayed())
}

Then(~/^Preferences section contains opt in\/out receiving emails checkbox$/) { ->
    assertThat("Preferences opt in/out receiving emails checkbox label is not correct", profilePage.optInOutReceivingEmailsCheckboxLabel.text(), is(equalTo('Send me any news or marketing information by email from epoints.com')))
}

When(~/^Opt in\/out receiving emails checkbox state will be changed$/) { ->
    optInOutReceivingEmailsCheckboxChecked = profilePage.isOptInOutReceivingEmailsCheckboxChecked()
    profilePage.clickOptInOutReceivingEmailsCheckbox()
}

Then(~/^Opt in\/out receiving emails checkbox state will be persisted$/) { ->
    func.refreshPage()
    assertThat("Changed opt in out receiving emails checkbox state was not persisted", profilePage.isOptInOutReceivingEmailsCheckboxChecked(), is(not(optInOutReceivingEmailsCheckboxChecked)))
}

Then(~/^Opt in\/out receiving emails checkbox is checked by default for new user$/) { ->
    sleep(5000)
    assertThat("Changed opt in out receiving emails checkbox state is not true by default", profilePage.isOptInOutReceivingEmailsCheckboxChecked(), is("on"))
}