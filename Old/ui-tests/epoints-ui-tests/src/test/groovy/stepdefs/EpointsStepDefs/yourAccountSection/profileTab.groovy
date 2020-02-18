package stepdefs.EpointsStepDefs.yourAccountSection
import geb.Browser
import mysqlConnection.jdbc
import pages.Epoints_pages.epointsPage
import stepdefs.Functions
import stepdefs.envVariables

import static cucumber.api.groovy.EN.*
import static cucumber.api.groovy.EN.Then
import cucumber.api.java.After

/**
 * Created by kbaranowski on 2015-02-25.
 */

def epoints = new epointsPage()
def func = new Functions()
def browser = new Browser()
def envVar = new envVariables()

    // 1.1 //  -------------------------------------------------------------------------- Your account - profile section
    // ----------------------------------------------------------------------------------------------- email row content
    Given(~/^Profile page is opened$/) { ->
        to epointsPage
        epoints = page
        epoints.clickSignInButton()
        waitFor{ epoints.signModule.authenticationPanel.isDisplayed() }
        epoints.signModule.enterLoginData(envVar.testUserEmail, envVar.testUserPassword)
        epoints.signModule.clickSignInButton()
        epoints.clickYourAccountButton()
        Thread.sleep(2000)
        epoints.clickProfileButton()
    }
    Given(~/^Email address is properly displayed$/) { ->
        waitFor{ epoints.userProfileTab.emailAddressLabel.isDisplayed() }
        waitFor{ epoints.userProfileTab.currentEmail.isDisplayed() }
        waitFor{ epoints.userProfileTab.emailEditCancelButton.isDisplayed() }
        assert epoints.userProfileTab.emailAddressLabel.text() == envVar.profileTabCurrentEmailLabel
        assert epoints.userProfileTab.currentEmail.text() == envVar.testUserEmail
        assert epoints.userProfileTab.emailEditCancelButton.isDisplayed()
    }
    Given(~/^Changing email fields are hidden$/) { ->
        waitFor{ !epoints.userProfileTab.newEmailLabel.isDisplayed() }
        assert !epoints.userProfileTab.newEmailLabel.isDisplayed()
        assert !epoints.userProfileTab.newEmailInputField.isDisplayed()
        assert !epoints.userProfileTab.newEmailRetypeLabel.isDisplayed()
        assert !epoints.userProfileTab.newEmailRetypeInputField.isDisplayed()
        assert !epoints.userProfileTab.emailChangeCancelButton.isDisplayed()
        assert !epoints.userProfileTab.emailChangeSaveButton.isDisplayed()
    }
    When(~/^User click edit email button$/) { ->
        epoints.userProfileTab.clickEmailChangeEditCancelButton()
    }
    Then(~/^Changing email fields will be displayed$/) { ->
        waitFor{ epoints.userProfileTab.emailChangeSaveButton.isDisplayed() }
        assert epoints.userProfileTab.newEmailLabel.text() == envVar.profileTabNewEmailLabel
        assert epoints.userProfileTab.newEmailInputField.isDisplayed()
        assert epoints.userProfileTab.newEmailRetypeLabel.text() == envVar.profileTabRetypeNewEmailLabel
        assert epoints.userProfileTab.newEmailRetypeInputField.isDisplayed()
        assert epoints.userProfileTab.emailChangeCancelButton.isDisplayed()
        assert epoints.userProfileTab.emailChangeSaveButton.isDisplayed()
    }

    // 1.2 //  -------------------------------------------------------------------------- Your account - profile section
    // ------------------------------------------------------------------------------------------ fields required alerts
    Given(~/^Email edit form is opened$/) { ->
        epoints.userProfileTab.clickEmailChangeEditCancelButton()
        waitFor{ epoints.userProfileTab.emailChangeSaveButton.isDisplayed() }
    }
    When(~/^User click save new email button without filling and data$/) { ->
        epoints.userProfileTab.clickEmailChangeSaveButton()
    }
    Then(~/^Two fields are required alerts will be displayed$/) { ->
        waitFor{ epoints.userProfileTab.emailChangeInputFieldAlertBasic.isDisplayed() }
        assert epoints.userProfileTab.emailChangeInputFieldAlertBasic[0].text() == envVar.profileTabEmailIsInvalidAlert
        assert epoints.userProfileTab.emailChangeInputFieldAlertBasic[1].text() == envVar.profileTabEmailIsNotMatchAlert
    }

    // 1.3 //  -------------------------------------------------------------------------- Your account - profile section
    // --------------------------------------------------------------------------------------- emails do not match alert
    When(~/^User fill email change form with two different emails$/) { ->
        epoints.userProfileTab.fillNewEmailForm(envVar.testUserEmail, envVar.testUserEmail+'2')
    }
    When(~/^Click save new email button$/) { ->
        epoints.userProfileTab.clickEmailChangeSaveButton()
    }
    Then(~/^Emails do not match alert will be displayed$/) { ->
        waitFor{ epoints.userProfileTab.emailChangeInputFieldAlertBasic.isDisplayed() }
        assert epoints.userProfileTab.emailChangeInputFieldAlertBasic[0].text() == envVar.profileTabEmailIsNotMatchAlert
    }

    // 1.4 //  -------------------------------------------------------------------------- Your account - profile section
    // --------------------------------------------------------------------------------------------------- cancel button
    When(~/^User click cancel setting new email button$/) { ->
        epoints.userProfileTab.clickEmailChangeCancelButton()
    }

    // 1.5 //  -------------------------------------------------------------------------- Your account - profile section
    // ------------------------------------------------------------------------------------------ email properly changed
    Given(~/^User is logged and profile page is opened with data '(.+?)' '(.+?)'$/) { String email, String password ->
        to epointsPage
        epoints = page
        epoints.clickSignInButton()
        waitFor{ epoints.signModule.authenticationPanel.isDisplayed() }
        epoints.signModule.enterLoginData(email, password)
        epoints.signModule.clickSignInButton()
        epoints.clickYourAccountButton()
        Thread.sleep(2000)
        epoints.clickProfileButton()
    }
    When(~/^User fill change email form with proper data '(.+?)'$/) { String newEmail ->
        epoints.userProfileTab.fillNewEmailForm(newEmail, newEmail)
        waitFor{ epoints.userProfileTab.emailsMatchAlert.isDisplayed() }
        assert epoints.userProfileTab.emailsMatchAlert.text() == envVar.profileTabEmailsMatchAlert
    }
    Then(~/^Email properly changed alert will be displayed$/) { ->
        waitFor{ epoints.userProfileTab.emailChangeAlertSuccess.isDisplayed() }
        assert epoints.userProfileTab.emailChangeAlertSuccess.text().replace("\n","").replace(" ","") ==  envVar.profileTabSuccessAlert.replace(" ","")
    }
    String tokenValue
    When(~/^User will follow new email confirmation link$/) { ->
        def mysql = new jdbc('points')
            tokenValue = mysql.get("SELECT tokenValue FROM points_manager.UserToken WHERE tokenType = 'EMAIL_CHANGE' ORDER BY id DESC",1)
        mysql.close()
        browser.go(envVar.epointsLink + envVar.emailChangeConfirmationPageURL + tokenValue)
    }
    Then(~/^Email will be properly changed$/) { ->
        //leave empty second test proper run will be enough confirmation
    }
    Then(~/^Email confirmation page has proper content '(.+?)'$/) { String newEmail->
        waitFor{ browser.currentUrl == envVar.epointsLink + envVar.emailChangeConfirmationPageURL + tokenValue }
        waitFor{ browser.getTitle() == envVar.emailChangeConfirmationPageTitle }
        assert browser.currentUrl == envVar.epointsLink + envVar.emailChangeConfirmationPageURL + tokenValue
        assert browser.getTitle() == envVar.emailChangeConfirmationPageTitle
        assert epoints.emailChangeConfirmationPage.accountDashboardButton.isDisplayed()
        assert epoints.emailChangeConfirmationPage.earnMoreEpointsButton.isDisplayed()
        assert epoints.emailChangeConfirmationPage.pageContent.text().replace("\n","").replace(" ","") == envVar.returnEmailAddressChangeConfirmationPageText(newEmail).replace(" ","")
        //TODO add validation of navbar and footer content
    }

    // 2.1 //  -------------------------------------------------------------------------- Your account - profile section
    // -------------------------------------------------------------------------------------------- password row content
    Given(~/^Current password set data is properly displayed$/) { ->
        waitFor{ epoints.userProfileTab.currentPasswordSetDateLabel.isDisplayed() }
        waitFor{ epoints.userProfileTab.passwordWasSetInfo.isDisplayed() }
        waitFor{ epoints.userProfileTab.passwordEditCancelButton.isDisplayed() }
        assert epoints.userProfileTab.currentPasswordSetDateLabel.text() == envVar.profileTabPasswordSetDateLabel
        assert epoints.userProfileTab.passwordWasSetInfo.text().contains(envVar.profileTabPasswordSetDateText)
        assert epoints.userProfileTab.passwordEditCancelButton.isDisplayed()
    }
    Given(~/^Changing password fields are hidden$/) { ->
        waitFor{ !epoints.userProfileTab.currentPassworLabel.isDisplayed() }
        assert !epoints.userProfileTab.currentPassworLabel.isDisplayed()
        assert !epoints.userProfileTab.currentPasswordInputField.isDisplayed()
        assert !epoints.userProfileTab.newPasswordLabel.isDisplayed()
        assert !epoints.userProfileTab.newPasswordInputField.isDisplayed()
        assert !epoints.userProfileTab.confirmNewPasswordLabel.isDisplayed()
        assert !epoints.userProfileTab.confirmNewPasswordInputField.isDisplayed()
        assert !epoints.userProfileTab.passwordChangeCancelButton.isDisplayed()
        assert !epoints.userProfileTab.passwordChangeSaveButton.isDisplayed()
    }
    When(~/^User click edit password button$/) { ->
        epoints.userProfileTab.clickPasswordChangeEditCancelButton()
    }
    Then(~/^Changing password fields will be displayed$/) { ->
        waitFor{ epoints.userProfileTab.currentPassworLabel.isDisplayed() }
        assert epoints.userProfileTab.currentPassworLabel.text() == envVar.profileTabCurrentPasswordLabel
        assert epoints.userProfileTab.currentPasswordInputField.isDisplayed()
        assert epoints.userProfileTab.newPasswordLabel.text() == envVar.profileTabNewPasswordLabel
        assert epoints.userProfileTab.newPasswordInputField.isDisplayed()
        assert epoints.userProfileTab.confirmNewPasswordLabel.text() == envVar.profileTabConfirmPasswordLabel
        assert epoints.userProfileTab.confirmNewPasswordInputField.isDisplayed()
        assert epoints.userProfileTab.passwordChangeCancelButton.isDisplayed()
        assert epoints.userProfileTab.passwordChangeSaveButton.isDisplayed()
    }

    // 2.2 //  -------------------------------------------------------------------------- Your account - profile section
    // ------------------------------------------------------------------------------------------ fields required alerts
    Given(~/^Password edit form is opened$/) { ->
        epoints.userProfileTab.clickPasswordChangeEditCancelButton()
        waitFor{ epoints.userProfileTab.passwordChangeSaveButton.isDisplayed() }
    }
    When(~/^User click save new password button without filling and data$/) { ->
        epoints.userProfileTab.clickPasswordChangeSaveButton()
    }
    Then(~/^Three fields are required alerts will be displayed$/) { ->
        waitFor{ epoints.userProfileTab.passwordChangeInputFieldAlertBasic.isDisplayed() }
        assert epoints.userProfileTab.passwordChangeInputFieldAlertBasic[0].text() == envVar.profileTabCurrentPasswordIsRequiredAlert
        assert epoints.userProfileTab.passwordChangeInputFieldAlertBasic[1].text() == envVar.profileTabNewPasswordIsRequiredAlert
        assert epoints.userProfileTab.passwordChangeInputFieldAlertBasic[2].text() == envVar.profileTabPasswordsDoNotMatchAlert
    }
    // 2.3 //  -------------------------------------------------------------------------- Your account - profile section
    // ------------------------------------------------------------------------------------ passwords do not match alert
    When(~/^User fill password change form with two different password$/) { ->
        epoints.userProfileTab.fillNewPasswordForm(envVar.testUserPassword, envVar.testUserPassword, envVar.testUserPassword+'2')
    }
    When(~/^Click save new password button$/) { ->
        epoints.userProfileTab.clickPasswordChangeSaveButton()
    }
    Then(~/^passwords do not match alert will be displayed$/) { ->
        waitFor{ epoints.userProfileTab.passwordChangeInputFieldAlertBasic.isDisplayed() }
        assert epoints.userProfileTab.passwordChangeInputFieldAlertBasic[0].text() == envVar.profileTabPasswordsDoNotMatchAlert
    }

    // 2.4 //  -------------------------------------------------------------------------- Your account - profile section
    // ------------------------------------------------------------------------------------ wrong current password alert
    When(~/^User fill password change form with wrong current password$/) { ->
        epoints.userProfileTab.fillNewPasswordForm(envVar.testUserPassword+'2', envVar.testUserPassword, envVar.testUserPassword)

    }
    Then(~/^Wrong current password alert will be displayed$/) { ->
        waitFor{ epoints.userProfileTab.passwordAlertError.isDisplayed() }
        epoints.userProfileTab.passwordAlertError.text() == envVar.profileTabWrongCurrentPasswordMessage
    }

    // 2.5 //  -------------------------------------------------------------------------- Your account - profile section
    // --------------------------------------------------------------------------------------------------- cancel button
    When(~/^User click cancel setting new password button$/) { ->
        epoints.userProfileTab.clickPasswordChangeCancelButton()
    }

    // 2.6 //  -------------------------------------------------------------------------- Your account - profile section
    // --------------------------------------------------------------------------------------- password properly changed
    When(~/^User fill change password form with proper data '(.+?)' '(.+?)'$/) { String oldPassword, String newPassword ->
        epoints.userProfileTab.fillNewPasswordForm(oldPassword, newPassword, newPassword)
        waitFor{ epoints.userProfileTab.passwordsMatchAlert.isDisplayed() }
        assert epoints.userProfileTab.passwordsMatchAlert.text() == envVar.profileTabPasswordsMatchAlert
    }
    Then(~/^Password properly changed alert will be displayed$/) { ->
        waitFor{ epoints.userProfileTab.passwordChangeAlertSuccess.isDisplayed() }
        assert epoints.userProfileTab.passwordChangeAlertSuccess.text().replace("\n","").replace(" ","") == envVar.profileTabSuccessPasswordAlert.replace(" ","")
    }

    // 3.1 //  -------------------------------------------------------------------------- Your account - profile section
    // ------------------------------------------------------------------------------------ personal details row content
    Given(~/^User personal data are properly displayed$/) { ->
        waitFor{ epoints.userProfileTab.changePersonalDetailsBlock.isDisplayed() }
        assert epoints.userProfileTab.firstNameLabel.text() == envVar.personalDetailsTabFirstNameLabel
        assert epoints.userProfileTab.lastNameLabel.text() == envVar.personalDetailsTabLastNameLabel
        assert epoints.userProfileTab.titleLabel.text() == envVar.personalDetailsTabTitleLabel
        assert epoints.userProfileTab.genderLabel.text() == envVar.personalDetailsTabGenderLabel
        assert epoints.userProfileTab.birthDateLabel.text() == envVar.personalDetailsTabDateOfBirthLabel
        def mysql = new jdbc('points')
            assert epoints.userProfileTab.firstNameInputField.attr('value') == mysql.get("SELECT firstName FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1)
            assert epoints.userProfileTab.lastNameInputField.attr('value') == mysql.get("SELECT lastName FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1)
            assert epoints.userProfileTab.titleDDL.attr('value') == mysql.get("SELECT title FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1)
            assert epoints.userProfileTab.genderDDL.attr('value') == mysql.get("SELECT gender FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1)
            assert func.convertDateToEpochFormat(epoints.userProfileTab.birthDateInputField.attr('value'), "dd/mm/YYYY") == func.convertDateToEpochFormat(mysql.get("SELECT birthDate FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1),"YYYY-mm-dd")
        mysql.close()
    }
    When(~/^User click edit personal details button$/) { ->
        waitFor{ (epoints.userProfileTab.personalDetailsEditCancelButton.isDisplayed() || epoints.userProfileTab.personalDetailsEditCancelButtonYellow.isDisplayed()) }
        if(epoints.userProfileTab.personalDetailsEditCancelButton.isDisplayed()){
            epoints.userProfileTab.clickPersonalDetailsChangeEditCancelButton()
        }else if(epoints.userProfileTab.personalDetailsEditCancelButtonYellow.isDisplayed()){
            epoints.userProfileTab.clickPersonalDetailsChangeEditCancelButtonYellow()
        }
        Thread.sleep(1000)
    }
    Then(~/^Cancel button will be displayed in personal details module$/) { ->
        waitFor{ epoints.userProfileTab.personalDetilsCancelButton.isDisplayed() }
        assert epoints.userProfileTab.personalDetilsCancelButton.isDisplayed()
    }
    Then(~/^Save button will be displayed in personal details module$/) { ->
        waitFor{ epoints.userProfileTab.personalDetilsSaveButton.isDisplayed() }
        assert epoints.userProfileTab.personalDetilsSaveButton.isDisplayed()
    }

    // 3.2 //  -------------------------------------------------------------------------- Your account - profile section
    // ------------------------------------------------------------------------- personal details fields required alerts
    When(~/^User provide date of birth in wrong format$/) { ->
        epoints.userProfileTab.clickDayOfBirthInput()
        epoints.userProfileTab.enterDayOfBirth('1234')
        epoints.userProfileTab.clickSomeNeutralSpaceOnPersonalDetails()
        Thread.sleep(1000)
    }
    Then(~/^Click save personal details button$/) { ->
        epoints.userProfileTab.clickPersonalDataChangeSaveButton()
    }
    Then(~/^Invalid date format alert will be displayed$/) { ->
        waitFor{ epoints.userProfileTab.personalDetilsAlertError.isDisplayed() }
        epoints.userProfileTab.personalDetilsAlertError.text() == envVar.personalDetailsInvalidDateFormatAlert
    }

    // 3.3 //  -------------------------------------------------------------------------- Your account - profile section
    // ---------------------------------------------------------------------------------- personal details cancel button
    When(~/^User click cancel setting new personal details button$/) { ->
        epoints.userProfileTab.clickPersonalDetailsChangeCancelButton()
    }
    Then(~/^Edit view of personal details will be closed$/) { ->
        waitFor{ !epoints.userProfileTab.personalDetilsCancelButton.isDisplayed() }
        waitFor{ !epoints.userProfileTab.personalDetilsSaveButton.isDisplayed() }
        assert !epoints.userProfileTab.personalDetilsCancelButton.isDisplayed()
        assert !epoints.userProfileTab.personalDetilsSaveButton.isDisplayed()
    }

    // 3.4 //  -------------------------------------------------------------------------- Your account - profile section
    // ------------------------------------------------------------------------------- personal details properly changed
    String firstName = 'name' + func.returnRandomValue(1000)
    String lastName = 'lastName' + func.returnRandomValue(1000)
    String title
    String gender
    String dateOfBirth = (func.returnRandomValue(17)+10)+'/0'+(func.returnRandomValue(8)+1)+'/'+(func.returnRandomValue(20)+2000)
    When(~/^User provide new personal details data$/) { ->
        epoints.userProfileTab.enterFirstName(firstName)
        epoints.userProfileTab.enterLastName(lastName)
        epoints.userProfileTab.expandTitleDDL()
        waitFor{ epoints.userProfileTab.titleDDLOption.size() == 4 }
        random = func.returnRandomValue(epoints.userProfileTab.titleDDLOption.size()-1)+1
        title = epoints.userProfileTab.titleDDLOption[random].text()
        epoints.userProfileTab.clickChosenTitleOption(random)
        epoints.userProfileTab.expandGenderDDL()
        waitFor{ epoints.userProfileTab.genderDDLOption.size() == 3 }
        random = func.returnRandomValue(epoints.userProfileTab.genderDDLOption.size()-1)+1
        gender = epoints.userProfileTab.genderDDLOption[random].text()
        epoints.userProfileTab.clickChosenGenderOption(random)
        epoints.userProfileTab.clickDayOfBirthInput()
        epoints.userProfileTab.enterDayOfBirth(dateOfBirth)
        epoints.userProfileTab.clickSomeNeutralSpaceOnPersonalDetails()
        Thread.sleep(1000)
    }
    Then(~/^New personal details data will be properly saved$/) { ->
        waitFor(10){ !epoints.userProfileTab.personalDetilsSaveButton.isDisplayed() }
        assert epoints.userProfileTab.firstNameInputField.attr('value') == firstName
        assert epoints.userProfileTab.lastNameInputField.attr('value') == lastName
        assert epoints.userProfileTab.titleDDL.attr('value') == title
        assert epoints.userProfileTab.genderDDL.attr('value').toLowerCase() == gender.toLowerCase()
        assert epoints.userProfileTab.birthDateInputField.attr('value') == dateOfBirth

        def mysql = new jdbc('points')
            assert firstName == mysql.get("SELECT firstName FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1)
            assert lastName == mysql.get("SELECT lastName FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1)
            assert title == mysql.get("SELECT title FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1)
            assert gender.toLowerCase() == mysql.get("SELECT gender FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1).toLowerCase()
            assert func.convertDateToEpochFormat(dateOfBirth, "dd/mm/YYYY") == func.convertDateToEpochFormat(mysql.get("SELECT birthDate FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1),"YYYY-mm-dd")
        mysql.close()
    }
    @After('@setDefaultPersonalAndContactDataAfter')
    public void setDefaultPersonalAndContactDataAfter(){
        def envVar = new envVariables()
        def mysql = new jdbc('points')
            //personal details
            mysql.update("UPDATE points_manager.User SET firstName = 'Krzysztof' WHERE email = '"+envVar.testUserEmail+"'")
            mysql.update("UPDATE points_manager.User SET lastName = 'Baranowski' WHERE email = '"+envVar.testUserEmail+"'")
            mysql.update("UPDATE points_manager.User SET title = 'Mrs' WHERE email = '"+envVar.testUserEmail+"'")
            mysql.update("UPDATE points_manager.User SET gender = 'MALE' WHERE email = '"+envVar.testUserEmail+"'")
            mysql.update("UPDATE points_manager.User SET birthDate = '2989-06-08 12:20:12' WHERE email = '"+envVar.testUserEmail+"'")
            //conctact details
            mysql.update("UPDATE points_manager.User SET mobileNumber = '695805680' WHERE email = '"+envVar.testUserEmail+"'")
            mysql.update("UPDATE points_manager.User SET houseNumberOrName = '10' WHERE email = '"+envVar.testUserEmail+"'")
            mysql.update("UPDATE points_manager.User SET street = 'Głowna' WHERE email = '"+envVar.testUserEmail+"'")
            mysql.update("UPDATE points_manager.User SET townOrCity = 'Krasowice' WHERE email = '"+envVar.testUserEmail+"'")
            mysql.update("UPDATE points_manager.User SET county = 'Opolskie' WHERE email = '"+envVar.testUserEmail+"'")
            mysql.update("UPDATE points_manager.User SET country = 'UK' WHERE email = '"+envVar.testUserEmail+"'")
            mysql.update("UPDATE points_manager.User SET postcode = '46-100' WHERE email = '"+envVar.testUserEmail+"'")
        mysql.close()
    }

    // 4.1 //  -------------------------------------------------------------------------- Your account - profile section
    // ------------------------------------------------------------------------------------ contact details  row content
    Given(~/^User contact data are properly displayed$/) { ->
        waitFor{ epoints.userProfileTab.changePersonalDetailsBlock.isDisplayed() }
        assert epoints.userProfileTab.contactNumberLabel.text() == envVar.contactDetailsContactNumberLabel
        assert epoints.userProfileTab.addresSectionLabel.text() == envVar.contactDetailsAddressLabel
        def mysql = new jdbc('points')
            assert epoints.userProfileTab.contactNumberInputField.attr('value') == mysql.get("SELECT mobileNumber FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1)
            assert epoints.userProfileTab.houseNumberInputField.attr('value') == mysql.get("SELECT houseNumberOrName FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1)
            assert epoints.userProfileTab.streetInputField.attr('value') == mysql.get("SELECT street FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1)
            assert epoints.userProfileTab.townInputField.attr('value') == mysql.get("SELECT townOrCity FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1)
            assert epoints.userProfileTab.countyInputField.attr('value') == mysql.get("SELECT county FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1)
            assert epoints.userProfileTab.countryInputField.attr('value') == mysql.get("SELECT country FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1)
            assert epoints.userProfileTab.postcodeInputField.attr('value') == mysql.get("SELECT postcode FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1)
        mysql.close()
    }
    When(~/^User click edit contact details button$/) { ->
        if( epoints.userProfileTab.contactDetailsEditCancelButton.isDisplayed() ){
            epoints.userProfileTab.clickContactDetailsChangeEditCancelButton()
        }else{
            epoints.userProfileTab.clickContactDetailsChangeEditCancelButtonYellow()
        }
        Thread.sleep(1000)
    }
    Then(~/^User contact data are properly displayed in edit mode$/) { ->
        waitFor{ epoints.userProfileTab.changePersonalDetailsBlock.isDisplayed() }
        assert epoints.userProfileTab.contactNumberLabel.text() == envVar.contactDetailsContactNumberLabel
        assert epoints.userProfileTab.addresSectionLabel.text() == envVar.contactDetailsAddressLabel
        assert epoints.userProfileTab.houseNumberLabel.text() == envVar.contactDetailsHouseLabel
        assert epoints.userProfileTab.streetLabel.text() == envVar.contactDetailsStreetLabel
        assert epoints.userProfileTab.townLabel.text() == envVar.contactDetailsTownLabel
        assert epoints.userProfileTab.countyLabel.text() == envVar.contactDetailsCountyLabel
        assert epoints.userProfileTab.countryLabel.text() == envVar.contactDetailsCountryLabel
        assert epoints.userProfileTab.postcodeLabel.text() == envVar.contactDetailsPostcodeLabel
        def mysql = new jdbc('points')
            assert epoints.userProfileTab.contactNumberInputField.attr('value') == mysql.get("SELECT mobileNumber FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1)
            assert epoints.userProfileTab.houseNumberInputField.attr('value') == mysql.get("SELECT houseNumberOrName FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1)
            assert epoints.userProfileTab.streetInputField.attr('value') == mysql.get("SELECT street FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1)
            assert epoints.userProfileTab.townInputField.attr('value') == mysql.get("SELECT townOrCity FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1)
            assert epoints.userProfileTab.countyInputField.attr('value') == mysql.get("SELECT county FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1)
            assert epoints.userProfileTab.countryInputField.attr('value') == mysql.get("SELECT country FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1)
            assert epoints.userProfileTab.postcodeInputField.attr('value') == mysql.get("SELECT postcode FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1)
        mysql.close()
    }
    Then(~/^Cancel button will be displayed in contact details module$/) { ->
        epoints.userProfileTab.contactDetailsCancelButton.isDisplayed()
    }
    Then(~/^Save button will be displayed in contact details module$/) { ->
        epoints.userProfileTab.contactDetailsSaveButton.isDisplayed()
    }
    Then(~/^Find address button will be displayed in contact details module$/) { ->
        epoints.userProfileTab.findAddressButton.isDisplayed()
    }