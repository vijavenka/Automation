package com.iat.ePoints.Steps.SignIn;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Executors.SeleniumExecutor;
import com.iat.ePoints.Navigations.SignIn.LogInNavigation;

import static org.junit.Assert.*;


public class LogInSteps {

    private IExecutor executor;
    private LogInNavigation logNavi;

    public LogInSteps(SeleniumExecutor executor) {
        this.executor = executor;

    }

    public void go_sleep(int milisec) throws Throwable {
        Thread.sleep(milisec);
    }

    @Before
    public void set_up() {
        executor.start();
        logNavi = new LogInNavigation(executor);
    }

    @After
    public void tear_down() {
        executor.stop();
    }

    /**
     * ******************************************************************************************************************
     * Steps for scenarios given in file LogIn.feature
     * ******************************************************************************************************************
     */

    //scenario 1 ///////////////////////////////////////////////////////////////////////////////////////////////////////
    @Given("^Not registered user open ePoints.com$")
    public void Not_registered_user_open_ePoints_com() throws Throwable {
        logNavi.open();
        //optional if there are products in basket delete them();
    }


    @When("^Click in Sign In option$")
    public void Click_in_sign_In_option() throws Throwable {
        assertTrue("Sign In option is not available", logNavi.checkSignIn_option());
        logNavi.clickSignIn_option();
    }

    @Then("^Authentication panel is shown$")
    public void Authentication_panel_is_shown() throws Throwable {
        logNavi.checkAuthPanel_isDisplay();
    }

    @Then("^Email label is available$")
    public void Email_label_is_available() throws Throwable {
        logNavi.checkEmail_lbl_isDisplay();
    }

    @Then("^Email field is available$")
    public void Email_field_is_available() throws Throwable {
        logNavi.checkEmail_fld_isDisplay();
    }

    @Then("^Password label is available$")
    public void Password_label_is_available() throws Throwable {
        logNavi.checkPassword_lbl_isDisplay();
    }

    @Then("^Password field is available$")
    public void Password_field_is_available() throws Throwable {
        logNavi.checkPassword_fld_isDisplay();
    }

    @Then("^Forgot password link is available$")
    public void Forgot_password_link_is_available() throws Throwable {
        logNavi.checkForgotPassword_opt_isDisplay();
    }

    @Then("^Sign In button is available$")
    public void Sign_In_button_is_available() throws Throwable {
        logNavi.checkSignIn_btn_isDisplay();
    }

    @Then("^Join here Link is available$")
    public void Join_here_Link_is_available() throws Throwable {
        logNavi.checkJoinHere_opt_isDisplayed();
    }

    @Then("^Resend confirmation link is available$")
    public void Resend_confirmation_link_is_available() throws Throwable {
        logNavi.checkResendConfirmationEmailVisibility();
    }

    @Then("^Sign in using facebook button is available$")
    public void Sign_in_using_facebook_button_is_available() throws Throwable {
        logNavi.checkLoginUsinFacebookButtonVisibility();
    }


    @Then("^Close Link is available$")
    public void Close_Link_is_available() throws Throwable {
        logNavi.checkCloseLinkVisibility();
    }

    //scenario 2 ///////////////////////////////////////////////////////////////////////////////////////////////////////

    @Given("^Leave fields empty$")
    public void Leave_fields_empty() throws Throwable {

    }

    @When("^Click in Sign In button$")
    public void Click_in_Sign_In_button() throws Throwable {
        logNavi.clickSignIn_btn();
    }

    @Then("^Fields should be marked$")
    public void Fields_should_be_marked() throws Throwable {
        logNavi.checkEmailWarning_isDisplay();
        logNavi.checkPasswordWarning_isDisplay();
    }

    @Then("^Warning system information should be visible$")
    public void Warning_system_information_should_be_visible() throws Throwable {
        logNavi.checkEmailWarning_msg();
        logNavi.checkPasswordWarning_msg();
    }

    //scenario 3 ///////////////////////////////////////////////////////////////////////////////////////////////////////

    @When("^Fill email address using email which is not in the database$")
    public void Fill_email_address_using_email_which_is_not_in_the_database() throws Throwable {
        logNavi.fillEmail_fld("testEmail@not.in.db");
    }

    @When("^Fill Password field with random data$")
    public void Fill_Password_field_with_random_data() throws Throwable {
        logNavi.fillPassword_fld("RandomPassw0rd");
    }

    @Then("^Warning authentication system error should shown$")
    public void Warning_authentication_system_error_should_shown() throws Throwable {
        logNavi.checkAuthPanelSystemWarning_isDisplay();
        logNavi.checkAuthPanelSystemWarning_msg();
    }

    //scenario 4 ///////////////////////////////////////////////////////////////////////////////////////////////////////

    @When("^Fill email address using inactive account email which is in the database$")
    public void Fill_email_address_using_inactive_account_email_which_is_in_the_database() throws Throwable {
        logNavi.fillEmail_fld("krzysztofbaranowski@gmail.com");
    }

    //scenario 5 ///////////////////////////////////////////////////////////////////////////////////////////////////////

    @When("^Fill email address using active account email which is in the database$")
    public void Fill_email_address_using_active_account_email_which_is_in_the_database() throws Throwable {
        logNavi.fillEmail_fld("krzysztofbaranowski2@gmail.com");
    }

    //scenario 6 ///////////////////////////////////////////////////////////////////////////////////////////////////////

    @When("^User use '(.+)' and '(.+)' in Log in panel$")
    public void User_use_and(String login, String password) throws Throwable {
        logNavi.clickSignIn_option();
        go_sleep(500);
        logNavi.fillEmail_fld(login);
        go_sleep(500);
        logNavi.fillPassword_fld(password);
    }

    @Then("^User should be correctly logged in$")
    public void User_should_be_correctly_logged_in() throws Throwable {
        go_sleep(4000);
        assertTrue("User is not Sign In properly", logNavi.checkIsUserLoggIn());
    }

    @Then("^On Account should be (\\d+) points$")
    public void On_Account_should_be_points(int arg1) throws Throwable {
        logNavi.checkIfPointsWereAdedAfterAccountCreation(arg1);
    }
    //scenario 7 ///////////////////////////////////////////////////////////////////////////////////////////////////////

    @Given("^User is logged in$")
    public void User_is_logged_in() throws Throwable {
        logNavi.open();
        logNavi.clickSignIn_option();
        logNavi.fillEmail_fld("emailwybitnietestowy@gmail.com");
        logNavi.fillPassword_fld("Delete777");
        logNavi.clickSignIn_btn();
        assertTrue("User is not logged in", logNavi.checkIsUserLoggIn());
    }

    @When("^Click in Sign out link localised in header$")
    public void Click_in_Sign_out_link_localised_in_header() throws Throwable {
        logNavi.clickSignOut_opt();
    }

    @Then("^Homepage with Sign in / register box is opened$")
    public void Homepage_with_Sign_in_register_box_is_opened() throws Throwable {
        logNavi.checkSignOutSuccessful();
    }


    //scenario 8 ///////////////////////////////////////////////////////////////////////////////////////////////////////

    @Given("^Home page opened$")
    public void Home_page_opened() throws Throwable {
        logNavi.open();
    }

    @When("^Registered user is on page$")
    public void Registered_user_is_on_page() throws Throwable {

    }

    @When("^User provide by cookie$")
    public void User_provide_by_cookie() throws Throwable {

    }

    @When("^User is not authenticated$")
    public void User_is_not_authenticated() throws Throwable {

    }

    @When("^Click in \"Not <username>? / Not Your account?$")
    public void Click_in_Not_username_Not_Your_account() throws Throwable {

    }

    @Then("^Authentication modal panel should shown$")
    public void Authentication_modal_panel_should_shown() throws Throwable {
        logNavi.checkIsLoginModalShown();
    }

    @Then("^Email field should be filled out automatically$")
    public void Email_field_should_be_filled_out_automatically() throws Throwable {

    }

    @Then("^Password field should be available$")
    public void Password_field_should_be_available() throws Throwable {

    }

    @Then("^Not You? link should be available$")
    public void Not_You_link_should_be_available() throws Throwable {

    }

    //scenario 10
    @Given("^User is identified by cookies$")
    public void User_is_identified_by_cookies() throws Throwable {
        //need to create cookie
        //ePointsConfig

//        basket
//            Object { items=[0], config={...}, userPoints=200}
//
//            config
//                Object { userId="4a1e034f-6ea9-4594-a1db-cb19246e36c5", creationDate=1369401772075}
//
//            creationDate
//                1369401772075
//
//            userId
//                "4a1e034f-6ea9-4594-a1db-cb19246e36c5"
//
//            items
//                []
//
//            userPoints
//                200
    }

    //scenario 10
    @Given("^User is not logged in$")
    public void User_is_not_logged_in() throws Throwable {
        logNavi.open();
        assertTrue("User is logged in!! (Sign in option is not available)", logNavi.checkSignIn_option());
    }

    @Given("^Basket is opened$")
    public void Basket_is_opened() throws Throwable {
        assertTrue("Basket is not opened correctly", logNavi.checkIsBaskedOpened());
    }

    @Given("^Order these rewards option is available$")
    public void Order_these_rewards_option_is_available() throws Throwable {
        assertTrue("Order these rewards option is not available", logNavi.checkIsBaskedOpened());
    }

    @When("^Click in Order these rewards option$")
    public void Click_in_Order_these_rewards_option() throws Throwable {
        logNavi.clickInOrderTheseRewardsStep2_btn();
        Thread.sleep(4000);
    }

    //scenario 11
    @When("^Open My account page \\(dashboard, etc.\\)$")
    public void Open_My_account_page_dashboard_etc_() throws Throwable {
        logNavi.openMyAccountMainPage();
        assertTrue("After open My Account main page authorization pop-up not shown", logNavi.checkIsLoginModalShown());
        logNavi.open();
        logNavi.openMyAccountRewardsHistory();
        assertTrue("My account - rewards history authorization pop-up not shown", logNavi.checkIsLoginModalShown());
        logNavi.open();
        logNavi.openMyAccount_ePointsStatement();
        assertTrue("My account - ePoints Statement authorization pop-up not shown", logNavi.checkIsLoginModalShown());
        logNavi.open();
        logNavi.openMyAccountMyProfile();
        assertTrue("My account - My profile authorization pop-up not shown", logNavi.checkIsLoginModalShown());
        logNavi.open();
        logNavi.openMyAccountDashboard();
        assertTrue("My account - Dashboard authorization pop-up not shown", logNavi.checkIsLoginModalShown());
    }
}
