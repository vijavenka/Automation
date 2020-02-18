package com.iat.ePoints.Steps.Registration;

import com.iat.ePoints.Navigations.SignIn.LogInNavigation;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en.And;


import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Executors.SeleniumExecutor;
import com.iat.ePoints.Navigations.Registration.RegistrationNavigation;

import static org.junit.Assert.*;


public class RegistrationSteps {

    private IExecutor executor;
    private RegistrationNavigation regNavi;
    private LogInNavigation loginNavi;

    public RegistrationSteps(SeleniumExecutor executor) {
        this.executor = executor;

    }

    public void go_sleep(int milisec) throws Throwable {
        Thread.sleep(milisec);
    }

    @Before
    public void set_up() {
        executor.start();
        regNavi = new RegistrationNavigation(executor);
        loginNavi = new LogInNavigation(executor);
    }

    @After
    public void tear_down() {
        executor.stop();
    }

    @Given("^Not registered user open Home page$")
    public void Not_registered_user_open_Home_page() throws Throwable {
        regNavi.open();
        go_sleep(4000);

    }

    @Given("^Click in Register option$")
    public void Click_in_Register_option() throws Throwable {
        regNavi.clickInRegister();

    }

    /**
     * ******************************************************************************************************************
     * Steps for scenarios given in file RegistrationProcess.feature
     * ******************************************************************************************************************
     */

    // Scenario 1 //////////////////////////////////////////////////////////////////////////////////////////////////////
    @When("^Leave form fields empty$")
    public void Leave_form_fields_empty() throws Throwable {
        assertTrue("Tell me more link available = ", regNavi.checkTellMeMore());
        assertTrue("Email field not available ", regNavi.checkEmailAddress());
    }

    @When("^Click in Sign up now and get (\\d+) ePoints$")
    public void Click_in_Sign_up_now_and_get_ePoints(int arg1) throws Throwable {
        regNavi.clickSignUp();
    }

    @Then("^Warning 'required' system massage should be shown$")
    public void Warning_required_system_required_massage_should_be_shown() throws Throwable {
        go_sleep(2000);
        assertTrue("Email warning system message not shown ", regNavi.checkEmailSystemMessage());
        assertEquals("Email warning system message is not correct: " + regNavi.returnEmailSystemMessage(), "Email address is required", regNavi.returnEmailSystemMessage());

    }

    @Then("^New account cannot be created$")
    public void New_account_cannot_be_created() throws Throwable {

    }

    // scenario 2 //////////////////////////////////////////////////////////////////////////////////////////////////////
    @When("^Start entering chars into fields$")
    public void Start_entering_chars_into_fields() throws Throwable {
        assertTrue("Email field not available ", regNavi.checkEmailAddress());
        regNavi.enterStringToEmailField(Integer.toString(executor.return_random_value(1000)) + "q1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e3255@wp.pl");
        regNavi.clickSignUp();

    }

    @Then("^Only (\\d+) chars length is allowed$")
    public void Only_chars_length_is_allowed(int arg1) throws Throwable {
        //assertEquals("Email field length is incorrect", arg1, regNavi.getEmailFieldLength());

    }

    @And("^Warning 'required' system length massage should be shown$")
    public void Warning_lenght_system_massage_should_be_shown() throws Throwable {
        assertTrue("Length warning system message not shown ", regNavi.checkEmailLenghtSystemMessage());

    }

    //scenario 3 ///////////////////////////////////////////////////////////////////////////////////////////////////////
    @When("^Fill out fields - enter words which include chars from table below$")
    public void Fill_out_fields_enter_words_which_include_chars_from_table_below() throws Throwable {

    }

    @Then("^Fields should be marked as incorrectly filled out$")
    public void Fields_should_be_marked_as_incorrectly_filled_out() throws Throwable {

    }

    @Then("^System message should be shown with information about incorrect chars entered into each field$")
    public void System_message_should_be_shown_with_information_about_incorrect_chars_entered_into_each_field() throws Throwable {

    }

    //scenario 4 ///////////////////////////////////////////////////////////////////////////////////////////////////////
    @Given("^Fill out email address field with using same email address which is currently in database$")
    public void Fill_out_email_address_field_with_using_same_email_address_which_is_currently_in_database() throws Throwable {
        regNavi.enterStringToEmailField("krzysztofbaranowski2@gmail.com");
    }

    @Then("^Email address field should be marked as incorrectly filled$")
    public void Email_address_field_should_be_marked_as_incorrectly_filled() throws Throwable {
        assertTrue("Duplicate email warning message not shown ", regNavi.checkEmailLenghtSystemMessage());
        go_sleep(2000);
    }

    @Then("^System duplicate warning message should be shown$")
    public void System_warning_message_should_be_shown() throws Throwable {
        assertEquals("Email warning system message is not correct: " + regNavi.returnEmailLenghtDuplicateSystemMessage(), "Username already taken", regNavi.returnEmailLenghtDuplicateSystemMessage());
    }

    //scenario 5 ///////////////////////////////////////////////////////////////////////////////////////////////////////
    @When("^Fill out email address field with using improper email address$")
    public void Fill_out_email_address_field_with_using_inproper_email_address() throws Throwable {
        //String without "@" char.
        regNavi.enterStringToEmailField("krzysztofbaranowski2gmail.com");
    }

    @Then("^Warning 'invalid' system massage should be shown$")
    public void Warning_system_invailid_massage_should_be_shown() throws Throwable {
        go_sleep(2000);
        assertTrue("Email warning system message not shown ", regNavi.checkEmailSystemMessage());
        assertEquals("Email warning system message is not correct: " + regNavi.returnEmailSystemMessage(), "Email address is invalid", regNavi.returnEmailSystemMessage());

    }

    //scenario 6 ///////////////////////////////////////////////////////////////////////////////////////////////////////
    @Given("^Fill the registration form$")
    public void Fill_the_registration_form() throws Throwable {
        regNavi.enterStringToEmailField(Integer.toString(executor.return_random_value(1000000)) + "testemail@wp.pl");

    }

    @Then("^New account should be created$")
    public void New_account_should_be_created() throws Throwable {
        go_sleep(2000);
        regNavi.checkVisibilityOfHiUserNameWelcome();
    }

    @Then("^Account should have status inactive$")
    public void Account_should_have_status_inactive() throws Throwable {
        // Express the Regexp above with the code you wish you had
    }

    @Then("^Email submitted page should be opened$")
    public void Email_submitted_page_should_be_opened() throws Throwable {
        regNavi.checkVisibilityOfCheckYourInboxInformation();
    }

    @Then("^On Account should be (\\d+) points;$")
    public void On_Account_should_be_points(int arg1) throws Throwable {
        assertTrue("Check if created user has 50 points on his account", regNavi.checkcorrectnessOfPointsAmount(arg1));
    }

    //scenario 7 ///////////////////////////////////////////////////////////////////////////////////////////////////////
    @Given("^Tell me more option available$")
    public void Tell_me_more_option_available() throws Throwable {
        assertTrue("Tell me more option is not available", regNavi.checkTellMeMore());

    }

    @When("^Click in Tell me more option$")
    public void Click_in_Tell_me_more_option() throws Throwable {
        regNavi.clickTelMeMore();
        go_sleep(1000);
    }

    @Then("^Information window is opened$")
    public void Information_window_is_opened() throws Throwable {
        assertTrue("The \"Why register\" window is not available", regNavi.checkWhyRegisterWindow());
    }

    @Then("^Close option is available$")
    public void Close_opption_is_available() throws Throwable {
        assertTrue("Close option is not available", regNavi.checkWhyRegisterCloseOption());
    }

    @Then("^Close button is available$")
    public void Close_button_is_available() throws Throwable {
        assertTrue("Close button is not available", regNavi.checkWhyRegisterCloseButton());
    }

    @Then("^Close button behaviour is correct$")
    public void Close_button_behaviour_is_correct() throws Throwable {
        regNavi.clickWhyRegisterCloseButton();
        assertFalse("The \"Why Register\" window is opened after use close button", regNavi.checkWhyRegisterWindow());
    }

    @Then("^Close option behaviour is correct$")
    public void Close_option_behaviour_is_correct() throws Throwable {
        //open Why Register window again
        regNavi.clickTelMeMore();
        go_sleep(1000);
        regNavi.clickWhyRegisterCloseOption();
        assertFalse("The \"Why Register\" window is opened after use close option", regNavi.checkWhyRegisterWindow());
    }

    @Then("^Esc key behaviour is correct$")
    public void Esc_key_behaviour_is_correct() throws Throwable {
        //open Why Register window again
        regNavi.clickTelMeMore();
        go_sleep(1000);
        regNavi.clickWhyRegisterESC_key();
        assertFalse("The \"Why Register\" window is opened after use close option", regNavi.checkWhyRegisterWindow());
    }

    /**
     * ******************************************************************************************************************
     * Steps for scenarios given in file RegistrationEmail.feature
     * ******************************************************************************************************************
     */

    //scenario 1 ///////////////////////////////////////////////////////////////////////////////////////////////////////
    @Then("^Confirmation email should be sent on user email box$")
    public void Confirmation_email_should_be_sent_on_user_email_box() throws Throwable {
        regNavi.checkVisibilityOfcheckYourEmailMessage();
    }

    // Resend confirmation email for verified User, Resend confirmation email for not verified email ///////////////////
    @When("^User try to resend confirmation email$")
    public void User_try_to_resend_confirmation_email() throws Throwable {
        loginNavi.clickSignIn_option();
        regNavi.clickResentConfirmationEmailLink();
    }

    @When("^Enter verified email '(.+)'$")
    public void Enter_verified_email_emailwybitnietestowy_gmail_com(String email) throws Throwable {
        loginNavi.fillEmail_fld(email);
    }

    @And("^Confirm sending verification email$")
    public void Confirm_sending_verification_email() throws Throwable{
        regNavi.clickResendEmailButton();
    }

    @Then("^Proper warning alert should be displayed '(.+)'$")
    public void Proper_warning_alert_should_be_displayed(String isVerified) throws Throwable {
        regNavi.checkIfResendErrorAlertIsDisplayed(isVerified);
    }

    @When("^Enter not verified email '(.+)'$")
    public void Enter_not_verified_email_krzysztofbaranowski_gmail_com(String email) throws Throwable {
        loginNavi.fillEmail_fld(email);
    }

}
