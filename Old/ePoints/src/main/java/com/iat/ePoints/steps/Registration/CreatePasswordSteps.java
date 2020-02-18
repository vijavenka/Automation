package com.iat.ePoints.Steps.Registration;

import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Executors.SeleniumExecutor;
import com.iat.ePoints.Navigations.Registration.CreatePasswordNavigation;
import com.iat.ePoints.Navigations.SignIn.LogInNavigation;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 29.01.14
 * Time: 12:18
 * To change this template use File | Settings | File Templates.
 */
public class CreatePasswordSteps {

    private IExecutor executor;
    private CreatePasswordNavigation passwordCreateNavi;
    private LogInNavigation loginNavi;

    public CreatePasswordSteps(SeleniumExecutor executor) {
        this.executor = executor;

    }

    public void go_sleep(int milisec) throws Throwable {
        Thread.sleep(milisec);
    }

    @Before
    public void set_up() {
        executor.start();
        passwordCreateNavi = new CreatePasswordNavigation(executor);
        loginNavi = new LogInNavigation(executor);
    }

    @After
    public void tear_down() throws SQLException {
        executor.stop();
    }

    // Checking if create password page view is correct ////////////////////////////////////////////////////////////////
    @Given("^Page for create password is opened$")
    public void Page_for_create_password_is_opened() throws Throwable {
        passwordCreateNavi.joinEpoints();
        passwordCreateNavi.followConfirmationLink();
    }

    @When("^Checking view of page$")
    public void Checking_view_of_page() throws Throwable {
        //Leave empty
    }

    @Then("^Confirmation password field is available$")
    public void Confirmation_password_field_is_available() throws Throwable {
        passwordCreateNavi.checkIfPasswordFieldIsAvaliable();
    }

    @Then("^Confirm password field is available$")
    public void Confirm_password_field_is_available() throws Throwable {
        passwordCreateNavi.checkIfPasswordConfirmationFieldIsAvaliable();
    }

    @Then("^First name field is available$")
    public void First_name_field_is_available() throws Throwable {
        passwordCreateNavi.checkIfFirstNameFieldIsAvaliable();
    }

    @Then("^Last name field is available$")
    public void Last_name_field_is_available() throws Throwable {
        passwordCreateNavi.checkIfFirstLastFieldIsAvaliable();
    }

    @Then("^Done option is available$")
    public void Done_submit_option_is_available() throws Throwable {
        passwordCreateNavi.checkIfDoneButtonIsAvaliable();
    }

    // Checking if set password link from confirmation email is working correctly //////////////////////////////////////
    @When("^Done button will be pressed before filling any data$")
    public void Done_button_will_be_pressed_before_filling_any_data() throws Throwable {
        passwordCreateNavi.pressDoneButton();
    }

    @Then("^All create password alerts will be shown$")
    public void All_create_password_alerts_will_be_shown() throws Throwable {
        passwordCreateNavi.checkPasswordAlertVisibility(true);
        passwordCreateNavi.checkConfirmPasswordAlertVisibility(true);
        passwordCreateNavi.checkNameAlertVisibility(true);
        passwordCreateNavi.checkLastNameAlertVisibility(true);
    }

    @When("^Enter only new password and name$")
    public void Enter_only_new_password_and_name() throws Throwable {
        passwordCreateNavi.enterNewPassword("password");
        passwordCreateNavi.enterName("name");
        passwordCreateNavi.enterLastName("lastname");
    }

    @When("^Confirm password which do not match first one$")
    public void Confirm_password_which_do_not_match_first_one() throws Throwable {
        passwordCreateNavi.pressDoneButton();
    }

    @Then("^Proper system message should be shown$")
    public void Proper_system_message_should_be_shown() throws Throwable {
        passwordCreateNavi.checkPasswordAlertVisibility(false);
        passwordCreateNavi.checkConfirmPasswordAlertVisibility(true);
        passwordCreateNavi.checkNameAlertVisibility(false);
        passwordCreateNavi.checkLastNameAlertVisibility(false);
    }

    // Checking if account activation working correctly ////////////////////////////////////////////////////////////////
    @Given("^All needed credentials are correctly filled by user$")
    public void All_needed_credentials_are_correctly_filled_by_user() throws Throwable {
        passwordCreateNavi.enterNewPassword("password");
        passwordCreateNavi.enterConfirmNewPassword("password");
        passwordCreateNavi.enterName("name");
        passwordCreateNavi.enterLastName("lastname");
    }

    @When("^Click in Done button$")
    public void Click_in_Done_button() throws Throwable {
        passwordCreateNavi.pressDoneButton();
    }

    @Then("^Account should be activated$")
    public void Account_should_be_activated() throws Throwable {
        passwordCreateNavi.checkIfAccountWasActivated();
    }

    @Then("^User is redirected to all done screen$")
    public void User_is_redirected_to_all_done_screen() throws Throwable {
        passwordCreateNavi.checkIfAllDoneScreenIsVisible();
    }

    @Then("^Tell me more option is available$")
    public void Tell_me_more_option_is_available() throws Throwable {
        passwordCreateNavi.checkVisibilityOfTellMeMorelink();
        passwordCreateNavi.checkIfTellMeMoreLinkWorksCorrectly();
    }

    @Then("^Get epoints now is available$")
    public void get_epoints_now_is_available() throws Throwable {
        passwordCreateNavi.checkVisibilityOfGetEpointsButton();
        passwordCreateNavi.checkIfGetEpointsButtonWorksCorrectly();
    }

    // Check If forgot your password page is available and has proper content //////////////////////////////////////////
    @Then("^User is able to reach forgot password page using sign in popup$")
    public void User_is_able_to_reach_forgot_password_page_using_sign_in_popup() throws Throwable {
        passwordCreateNavi.openForgotPasswordPage();
        passwordCreateNavi.checkForgotPasswordPageTitle();
    }

    @When("^He looks on forgot your password page$")
    public void He_looks_on_forgot_your_password_page() throws Throwable {
        //LeaveEmpty
    }

    @Then("^He can see all needed elements to recover the password to ePoints account$")
    public void He_can_see_all_needed_elements_to_recover_the_password_to_ePoints_account() throws Throwable {
        passwordCreateNavi.checkAvaliablityOfAllElements();
    }

    // Check If forgot your password page returns proper alerts ////////////////////////////////////////////////////////
    @When("^User use try to restore password without entering email$")
    public void User_use_try_to_restore_password_without_entering_email() throws Throwable {
        passwordCreateNavi.clickSendButton();
    }

    @Then("^Email is required alert should appear$")
    public void Email_is_required_alert_should_appear() throws Throwable {
        passwordCreateNavi.checkIfEmailRequiredAlertAppear();
    }

    @When("^User enter incorrect email$")
    public void User_enter_incorrect_email() throws Throwable {
        passwordCreateNavi.enterUserEmail("incorrectemail@gmail.com");
        passwordCreateNavi.clickSendButton();
    }

    @Then("^Email does not exist alert should appear$")
    public void Email_does_not_exist_alert_should_appear() throws Throwable {
        passwordCreateNavi.checkIfEmailDoesNotExistAlertAppear();
    }

    // Check If password restoration link is created and is working ////////////////////////////////////////////////////
    @When("^User enter correct email '(.+)'$")
    public void User_enter_correct_email(String userEmail) throws Throwable {
        passwordCreateNavi.enterUserEmail(userEmail);
    }

    @When("^Submit sending email$")
    public void Submit_sending_email() throws Throwable {
        passwordCreateNavi.clickSendButton();
    }

    @Then("^Success alert should be shown$")
    public void Success_alert_should_be_shown() throws Throwable {
        passwordCreateNavi.checkVisibilityOfSuccessAlert();
    }

    @Then("^Link sent on user email should works '(.+)'$")
    public void Link_sent_on_user_email_should_works(String userEmail) throws Throwable {
        passwordCreateNavi.followRestorePasswordConfirmationLink(userEmail);
        passwordCreateNavi.checkIfProperPasswordRestorationPageWasOpened();
    }

    @Then("^Allow to create new password '(.+)'$")
    public void Allow_to_create_new_password(String newPassword) throws Throwable {
        passwordCreateNavi.enterDataAndConfirmNewPassword(newPassword);
    }

    @Then("^User can check if new password works '(.+)' '(.+)'$")
    public void User_can_check_if_new_password_works(String userEmail, String newPassword) throws Throwable {
        loginNavi.clickSignIn_option();
        loginNavi.fillEmail_fld(userEmail);
        loginNavi.fillPassword_fld(newPassword);
        loginNavi.clickSignIn_btn();
        loginNavi.checkIsUserLoggIn();
    }
}
