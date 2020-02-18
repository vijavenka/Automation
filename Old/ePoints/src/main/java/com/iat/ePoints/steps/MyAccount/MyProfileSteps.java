package com.iat.ePoints.Steps.MyAccount;

/**
 * Created with IntelliJ IDEA.
 * User: miwanczyk
 * Date: 20.06.13
 * Time: 22:30
 * To change this template use File | Settings | File Templates.
 */


import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Executors.SeleniumExecutor;
import com.iat.ePoints.Navigations.MyAccount.MyProfileNavigation;
import com.iat.ePoints.Navigations.SignIn.LogInNavigation;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertTrue;

public class MyProfileSteps {

    private IExecutor executor;
    private MyProfileNavigation myProfNavi;
    private LogInNavigation logNavi;


    public MyProfileSteps(SeleniumExecutor executor) {
        this.executor = executor;
    }

    public void go_sleep(int milisec) throws Throwable {
        Thread.sleep(milisec);
    }

    @Before
    public void set_up() {
        executor.start();
        myProfNavi = new MyProfileNavigation(executor);
        logNavi = new LogInNavigation(executor);

    }

    @After
    public void tear_down() {
        executor.stop();
    }

    // scenario 1 //////////////////////////////////////////////////////////////////////////////////////////////////////

    @Then("^My profile page should be opened properly$")
    public void My_profile_page_should_be_opened_properly() throws Throwable {
        myProfNavi.goToMyProfileSite();
    }

    @Then("^Go to my profile page$")
    public void Go_to_my_profile_page() throws Throwable {
        myProfNavi.clickProfileLinkInDashboard();
    }

    @Then("^Account details section should be available$")
    public void Account_details_section_should_be_available() throws Throwable {
        myProfNavi.checkVisibilityOfAccountDetailsSection();

    }

    @Then("^Account details section should have required elements$")
    public void Account_details_section_should_have_required_elements() throws Throwable {
        myProfNavi.checkContentOfAccountDetailsSection();
    }

    @Then("^Personal details section should be available$")
    public void Personal_details_section_should_be_available() throws Throwable {
        myProfNavi.checkVisibilityOfPersonalDetailsSection();
    }

    @Then("^Personal detail section should be properly displayed for user$")
    public void Personal_detail_section_should_be_properly_displayed_for_user_with_empty_details() throws Throwable {
        myProfNavi.checkContentOfPersonalDetailsSection();
    }

    @Then("^Contact details section should be available$")
    public void Contact_details_section_should_be_available() throws Throwable {
        myProfNavi.checkVisibilityOfContactDetailsSection();
    }

    @Then("^Contact details section should be properly displayed for user$")
    public void Contact_details_section_should_be_properly_displayed_for_user_with_empty_interests() throws Throwable {
        myProfNavi.checkContentOfContactDetailsSection();
    }

    // scenario 2 //////////////////////////////////////////////////////////////////////////////////////////////////////

    @Then("^Edit email address$")
    public void Edit_email_address() throws Throwable {
        myProfNavi.clickEmailEditButton();
    }

    @Then("^Enter new email address data '(.+)'$")
    public void Enter_new_email_address_data(String newEmail) throws Throwable {
        myProfNavi.enterNewEmail(newEmail);
        myProfNavi.repeatNewEmail(newEmail);
    }

    @Then("^Confirm email changes$")
    public void Confirm_email_changes() throws Throwable {
        myProfNavi.clickSaveNewEmailButton();
    }

    @Then("^Check page behaviour after email changing$")
    public void Check_page_behaviour_after_email_changing() throws Throwable {
        myProfNavi.checkIfSuccesAlertIsVisibleAfterEmailChanging();
    }

    @Then("^Confirm new email using Link and check changes in DB '(.+)' '(.+)'$")
    public void Confirm_new_email_using_Link_and_check_changes_in_DB(String oldEmail, String newEmail) throws Throwable {
        myProfNavi.followConfirmationLinkCreatedUsingToken(oldEmail);
        myProfNavi.checkNewEmailInDB(oldEmail, newEmail);
    }
    // scenario 3 //////////////////////////////////////////////////////////////////////////////////////////////////////

    @When("^User use '(.+)' and '(.+)' to Log into existing account$")
    public void User_use_and(String email, String password) throws Throwable {
        logNavi.clickSignIn_option();
        go_sleep(500);
        logNavi.fillEmail_fld(email);
        go_sleep(500);
        logNavi.fillPassword_fld(password);
        go_sleep(500);
    }

    @Then("^Edit my password$")
    public void Edit_my_password() throws Throwable {
        myProfNavi.clickPasswordEditButton();
    }

    @Then("^Enter new password data with '(.+)' '(.+)' '(.+)'$")
    public void Enter_new_password_data(String oldPassword, String newPassword, String repeatNewPassword) throws Throwable {
        myProfNavi.enterOldPassword(oldPassword);
        myProfNavi.enterNewPassword(newPassword);
        myProfNavi.repeatNewPassword(repeatNewPassword);
    }

    @Then("^Confirm password changes$")
    public void Confirm_password_changes() throws Throwable {
        myProfNavi.clickSaveNewPasswordButton();
    }

    @Then("^Check page behaviour after password changing$")
    public void Check_page_behaviour_after_password_changing() throws Throwable {
        myProfNavi.checkIfSuccesAlertIsVisibleAfterPasswordChanging();
    }

    //scenario 4 ///////////////////////////////////////////////////////////////////////////////////////////////////////


    @Then("^Edit my personal data$")
    public void Edit_my_personal_data() throws Throwable {
        myProfNavi.clickPersonalDataEditButton();

    }

    @Then("^Enter new personal data using '(.+)' '(.+)' '(.+)' '(.+)' '(.+)'$")
    public void Enter_new_personal_data(String Name, String LastName, String Title, String Gender, String DateOfBirth) throws Throwable {
        myProfNavi.clearAllPersonalDataTextfields();
        myProfNavi.enterNewDateOfBirth(DateOfBirth);
        myProfNavi.enterNewFirstName(Name);
        myProfNavi.enterNewLastName(LastName);
        myProfNavi.chooseNewTitle(Title);
        myProfNavi.chooseNewGender(Gender);
    }

    @Then("^Confirm personal data changes$")
    public void Confirm_personal_data_changes() throws Throwable {
        myProfNavi.clickSaveNewPersonalDetailsDataButton();
    }

    @Then("^Check page behaviour after personal data changing$")
    public void Check_page_behaviour_after_personal_data_changing() throws Throwable {
        myProfNavi.checkIfSuccesAlertIsVisibleAfterPersonalDataChanging();
    }

    @Then("^Logout form ePoints page$")
    public void Logout_form_ePoints_page() throws Throwable {
        myProfNavi.logoutFromUserAccount();
        Thread.sleep(4000);
    }

    @Then("^Compare all personal user data with data entered before '(.+)' '(.+)' '(.+)' '(.+)' '(.+)' '(.+)'$")
    public void Compare_all_personal_user_data_with_entered_before(String Name, String LastName, String Title, String Gender, String DateOfBirth, String Email) throws Throwable {
        myProfNavi.validateCorrectnessOfAllDataInUserPersonalDetails(Name, LastName, Title, Gender, DateOfBirth);
        myProfNavi.validateCorrectnessOfAllPersonalDataInDB(Name, LastName, Title, Gender, DateOfBirth, Email);
    }

    // scenario 5 //////////////////////////////////////////////////////////////////////////////////////////////////////

    @Then("^Edit my contact details$")
    public void edit_my_contact_details() throws Throwable {
        myProfNavi.clickContactDetailsEditButton();
        Thread.sleep(2000);
    }

    @Then("^Enter new contact details '(.+)' '(.+)' '(.+)' '(.+)' '(.+)' '(.+)' '(.+)'$")
    public void enter_new_contact_details_Poboczna_Wrocław_Dolnośląskie_Poland_(String Phone, String HouseNumber, String Street, String City, String County, String Country, String Postcode) throws Throwable {
        myProfNavi.clearAllContactsDetailsfields();
        myProfNavi.enterNewPhoneNumber(Phone);
        myProfNavi.enterNewHouseNumber(HouseNumber);
        myProfNavi.enterNewStreetName(Street);
        myProfNavi.enterNewCityName(City);
        myProfNavi.enterNewCountyName(County);
        myProfNavi.enterNewCountryName(Country);
        myProfNavi.enterNewPostcode(Postcode);
    }

    @Then("^Confirm contact details changes$")
    public void Confirm_contact_details_changes() throws Throwable {
        myProfNavi.clickSaveNewContactDetailsDataButton();
    }

    @Then("^Check page behaviour after contact details changing$")
    public void Check_page_behaviour_after_contact_details_changing() throws Throwable {
        myProfNavi.checkIfSuccesAlertIsVisibleAfterContactDetailsChanging();
    }

    @Then("^Compare all contact details with data entered before '(.+)' '(.+)' '(.+)' '(.+)' '(.+)' '(.+)' '(.+)' '(.+)'$")
    public void Compare_all_contact_details_with_data_entered_before_Poboczna_Wrocław_Dolnośląskie_Poland_(String Phone, String HouseNumber, String Street, String City, String County, String Country, String Postcode, String Email) throws Throwable {
        myProfNavi.validateCorrectnessOfAllDataInUsercontactDetails(Phone, HouseNumber, Street, City, County, Country, Postcode);
        myProfNavi.validateCorrectnessOfAllContactDataInDB(Phone, HouseNumber, Street, City, County, Country, Postcode, Email);
    }

    // scenario 6 //////////////////////////////////////////////////////////////////////////////////////////////////////

    @Then("^Cancel email data edition$")
    public void Cancel_email_data_edition() throws Throwable {
        myProfNavi.clikCancelEmailEditionButton();
    }

    @Then("^Check visibility of save email button$")
    public void Check_visibility_of_save_email_button() throws Throwable {
        myProfNavi.checkVisibilityOfSaveEmailButton();
    }

    @Then("^Cancel password edition$")
    public void Cancel_password_edition() throws Throwable {
        myProfNavi.clikCancelPasswordEditionButton();
    }

    @Then("^Check visibility of save password button$")
    public void Check_visibility_of_save_password_button() throws Throwable {
        myProfNavi.checkVisibilityOfSavePasswordButton();
    }

    @Then("^Cancel personal details edition$")
    public void Cancel_personal_details_edition() throws Throwable {
        myProfNavi.clikCancelPersonalDetailsEditionButton();
    }

    @Then("^Check visibility of save personal details button$")
    public void Check_visibility_of_save_personal_details_button() throws Throwable {
        myProfNavi.checkVisibilityOfSavePersonalDetailsButton();
    }

    @Then("^Cancel contact details edition$")
    public void Cancel_contact_details_edition() throws Throwable {
        myProfNavi.clikCancelContactDetailsEditionButton();
    }

    @Then("^Check visibility of save contact details button$")
    public void Check_visibility_of_save_contact_details_button() throws Throwable {
        myProfNavi.checkVisibilityOfSaveContactDetailsButton();
    }

    // 7 allow new FACEBOOK users to create epoints password (RD-1110) /////////////////////////////////////////////////
    @Then("^Edit my password for first time$")
    public void Edit_my_password_for_first_time() throws Throwable {
        myProfNavi.clickPasswordEditButtonNotSpecified();
    }

    @Then("^Create new password data with '(.+)' '(.+)'$")
    public void Create_new_password_data_with_Delete_Delete_(String newPassword, String repeatNewPassword) throws Throwable {
        go_sleep(4000);
        myProfNavi.enterNewPassword(newPassword);
        myProfNavi.repeatNewPassword(repeatNewPassword);
    }

    @Then("^Log out and try to login using new ePoints password or email '(.+)' '(.+)'$")
    public void Log_out_and_try_to_login_using_new_ePoints_password_or_email(String fbEmail, String password) throws Throwable {
        logNavi.clickSignOut_opt();
        go_sleep(4000);
        logNavi.clickSignIn_option();
        go_sleep(500);
        logNavi.fillEmail_fld(fbEmail);
        go_sleep(500);
        logNavi.fillPassword_fld(password);
        go_sleep(500);
        logNavi.clickSignIn_btn();
        assertTrue("User is not Sign In properly", logNavi.checkIsUserLoggIn());
    }

    // 8 EPOINTS - facebook registered user changes email address (RD-1111) ////////////////////////////////////////////
    @Then("^Follow confirmation link sent on user email '(.+)'$")
    public void Follow_confirmation_link_sent_on_user_email(String userEmail) throws Throwable {
        myProfNavi.followConfirmationLinkCreatedUsingToken(userEmail);
    }
}

