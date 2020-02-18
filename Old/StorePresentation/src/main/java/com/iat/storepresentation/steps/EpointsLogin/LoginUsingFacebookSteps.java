package com.iat.storepresentation.Steps.EpointsLogin;

import com.iat.storepresentation.Executors.IExecutor;
import com.iat.storepresentation.Executors.SeleniumExecutor;
import com.iat.storepresentation.Navigations.EpointsLogin.LoginUsingFacebookNavigation;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 08.01.14
 * Time: 12:26
 * To change this template use File | Settings | File Templates.
 */
public class LoginUsingFacebookSteps {

    private IExecutor executor;
    private LoginUsingFacebookNavigation loginFbNavi;


    public LoginUsingFacebookSteps(SeleniumExecutor executor) {
        this.executor = executor;

    }

    public void go_sleep(int milisec) throws Throwable {
        Thread.sleep(milisec);
    }

    @Before
    public void set_up() {
        executor.start();
        loginFbNavi = new LoginUsingFacebookNavigation(executor);
    }

    @After
    public void tear_down() {
        executor.stop();
    }
    // USER SIGN IN - add Facebook option to sign in interface for WLS (RD-657) ////////////////////////////////////////
    @Given("^Not registered user open Home page$")
    public void Not_registered_user_open_Home_page() throws Throwable {
        loginFbNavi.open();
    }

    @When("^User open login panel and facebook option should be available$")
    public void User_open_login_panel_facebook_and_option_should_be_available() throws Throwable {
        loginFbNavi.openLoginPanel();
        loginFbNavi.checkIfFacebookLoginOptionIsAvaliable();
    }

    @Then("^User is able to close login panel$")
    public void User_is_able_to_close_login_panel() throws Throwable {
        loginFbNavi.closeLoginPanel();
    }

    @Then("^Go to transition page using product buy button$")
    public void Go_to_transition_page_using_product_buy_button() throws Throwable {
        loginFbNavi.clickBuyButton();
    }

    @Then("^He can see that facebook login option is also available in transition page$")
    public void He_can_see_that_facebook_login_option_is_also_available_in_transition_page() throws Throwable {
        loginFbNavi.checkIfFacebookLoginIsAvailableonTransitionPage();
    }

    @When("^User use '(.+)' and '(.+)' in Log in panel$")
    public void User_use_emailwybitnietestowy_gmail_com_and_Delete_in_Log_in_panel(String email, String password) throws Throwable {
        loginFbNavi.openLoginPanel();
        loginFbNavi.enterLoginCrenentials(email, password);
    }

    @When("^Click in Sign In button$")
    public void Click_in_Sign_In_button() throws Throwable {
        loginFbNavi.clickSignInButtonInLoginPanel();
    }

    @Then("^User should be correctly logged in$")
    public void User_should_be_correctly_logged_in() throws Throwable {
        loginFbNavi.checkIfUserIsCorrectlyLoggedIn();
    }

    @Then("^Click in Sign out link localised in header$")
    public void Click_in_Sign_out_link_localised_in_header() throws Throwable {
        loginFbNavi.clickSignOutButton();
    }

    @Then("^Click in loozenge sign in option$")
    public void Click_in_loozenge_sign_in_option() throws Throwable {
        loginFbNavi.openLoginPanel();
    }

    @Then("^Sign in with facebook button should works$")
    public void Sign_in_with_facebook_button_should_works() throws Throwable {
        loginFbNavi.clickSignInWithFacebookButton();
    }

    @When("^User enter facebook credentials '(.+)' '(.+)' and confirm login details$")
    public void User_enter_facebook_credentials_and_confirm_login_details(String email, String password) throws Throwable {
        loginFbNavi.enterFacebokCredentials(email, password);
    }

}
