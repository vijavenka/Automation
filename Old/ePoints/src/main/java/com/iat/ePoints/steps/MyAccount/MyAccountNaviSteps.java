package com.iat.ePoints.Steps.MyAccount;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Executors.SeleniumExecutor;
import com.iat.ePoints.Navigations.MyAccount.MyAccountNavigation;
import com.iat.ePoints.Navigations.SignIn.LogInNavigation;

import static org.junit.Assert.*;

public class MyAccountNaviSteps {

    private IExecutor executor;
    private MyAccountNavigation myAccountNavi;
    private LogInNavigation loginNavi;

    public MyAccountNaviSteps(SeleniumExecutor executor) {
        this.executor = executor;
    }

    public void go_sleep(int milisec) throws Throwable {
        Thread.sleep(milisec);
    }

    @Before
    public void set_up() {
        executor.start();
        myAccountNavi = new MyAccountNavigation(executor);
        loginNavi = new LogInNavigation(executor);
    }

    @After
    public void tear_down() {
        executor.stop();
    }

    //Scenario: Checking if all links to My account tabs are available /////////////////////////////////////////////////

    @Given("^Registered user open ePoints.com$")
    public void Registered_user_open_ePoints_com() throws Throwable {
        myAccountNavi.open();
        loginNavi.clickSignIn_option();
        loginNavi.fillEmail_fld("emailwybitnietestowy@gmail.com");
        loginNavi.fillPassword_fld("Delete777");
        loginNavi.clickSignIn_btn();
        assertTrue("User is not logged in", loginNavi.checkIsUserLoggIn());

    }

    @Given("^Open My Account page$")
    public void Open_My_Account_page() throws Throwable {
        go_sleep(4000);
        assertTrue("My Account menu option is not available", myAccountNavi.checkMyAccount_menu_isAvailable());
        myAccountNavi.openMyAccount();
    }

    @Then("^My Account page opened correctly$")
    public void My_Account_page_opened_correctly() throws Throwable {

    }

    @Then("^Dashboard navigation option is available$")
    public void Dashboard_navigation_option_is_available() throws Throwable {
        assertTrue("Dashboard navigation menu is not available", myAccountNavi.checkDasboard_menu_isAvailable());
    }

    @Then("^My epoints navigation option is available$")
    public void ePoints_statement_navigation_option_is_available() throws Throwable {
        assertTrue("ePoints statement navigation menu is not available", myAccountNavi.checkMyEpoints_menu_isAvailable());
    }

    @Then("^My profile navigation option is available$")
    public void My_profile_navi_option_is_available() throws Throwable {
        assertTrue("My profile navigation menu is not available", myAccountNavi.checkProfile_menu_isAvailable());
    }

    @Then("^Activity navigation option is available$")
    public void Activity_navigation_option_is_available() throws Throwable {
        assertTrue("Rewards history navigation menu is not available", myAccountNavi.checkActivity_menu_isavailable());
    }

    @Then("^Rewards History navigation option is available$")
    public void Rewards_History_navigation_option_is_available() throws Throwable {
        assertTrue("Rewards history navigation menu is not available", myAccountNavi.checkHistory_menu_isavailable());
    }

    @Then("^Invite friends navigation option is available$")
    public void Invite_friends_navigation_option_is_available() throws Throwable {
        assertTrue("Invite friend navigation menu is not available", myAccountNavi.checkInviteFriends_menu_isavailable());
    }

    //Scenario: Checking if all My accounts links redirect to proper tabs //////////////////////////////////////////////

    @When("^Click in each of My account sub pages navigation options$")
    public void Click_in_each_of_My_account_sub_pages_navigation_options() throws Throwable {
        //Skip this
    }

    @Then("^Proper sub page should opened correctly$")
    public void Proper_sub_page_should_opened_correctly() throws Throwable {
        myAccountNavi.clickInMyepoints();
        go_sleep(1000);
        myAccountNavi.checkingMyEpoints_isOpened();
        myAccountNavi.clickInProfile();
        go_sleep(1000);
        myAccountNavi.checkingProfile_isOpened();
        myAccountNavi.clickInActivity();
        go_sleep(1000);
        myAccountNavi.checkingActivity_isOpened();
        myAccountNavi.clickInHistory();
        go_sleep(1000);
        myAccountNavi.checkingHistory_isOpened();
        myAccountNavi.clickInInviteFriends();
        go_sleep(1000);
        myAccountNavi.checkingInviteFriends_isOpened();
    }
}
