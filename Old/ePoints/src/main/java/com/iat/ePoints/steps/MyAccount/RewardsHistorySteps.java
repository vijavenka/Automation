package com.iat.ePoints.Steps.MyAccount;

import com.iat.ePoints.Navigations.Checkout.CompleteNavigation;
import com.iat.ePoints.Navigations.MyAccount.RewardsHistoryNavigation;
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


public class RewardsHistorySteps {

    private IExecutor executor;
    private MyAccountNavigation myAccountNavi;
    private LogInNavigation loginNavi;
    private RewardsHistoryNavigation historyNavi;
    private CompleteNavigation completeNavi;

    public RewardsHistorySteps(SeleniumExecutor executor) {
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
        historyNavi = new RewardsHistoryNavigation(executor);
        completeNavi = new CompleteNavigation(executor);
    }

    @After
    public void tear_down() {
        executor.stop();
    }

    // scenario: Checking empty rewards history list////////////////////////////////////////////////////////////////////

    @Given("^Registered user with no rewards history open ePoints.com$")
    public void Registered_user_with_no_rewards_history_open_ePoints_com() throws Throwable {
        myAccountNavi.open();
        loginNavi.clickSignIn_option();
        loginNavi.fillEmail_fld("iat.epoints.test@gmail.com");
        loginNavi.fillPassword_fld("everest01");
        loginNavi.clickSignIn_btn();
        assertTrue("User is not logged in", loginNavi.checkIsUserLoggIn());
    }

    @Given("^Open rewards history page$")
    public void Open_rewards_history_page() throws Throwable {
        myAccountNavi.openMyAccount();
        myAccountNavi.clickInHistory();
    }

    @When("^Rewards history page is correctly opened$")
    public void Rewards_history_page_is_correctly_opened() throws Throwable {
        myAccountNavi.checkingHistory_isOpened();
    }

    @When("^Rewards history page is empty$")
    public void Rewards_history_page_is_empty() throws Throwable {
        historyNavi.checkIfRewardHistoryPageIsEmpty();
    }

    // scenario: Checking empty rewards history list////////////////////////////////////////////////////////////////////


    @Given("^Registered user with sme rewards history open ePoints.com$")
    public void Registered_user_with_sme_rewards_history_open_ePoints_com() throws Throwable {
        myAccountNavi.open();
        loginNavi.clickSignIn_option();
        loginNavi.fillEmail_fld("emailwybitnietestowy@gmail.com");
        loginNavi.fillPassword_fld("Delete777");
        loginNavi.clickSignIn_btn();
        assertTrue("User is not logged in", loginNavi.checkIsUserLoggIn());
    }

    @When("^Rewards history page have one record minimum$")
    public void Rewards_history_page_have_one_record_minimum() throws Throwable {
        historyNavi.checkVisibilityOfTableWithBoughtProducts();
    }

    @Then("^Reward date is displayed$")
    public void Reward_date_is_displayed() throws Throwable {
        historyNavi.checkVisibilityOfRewardDate();
    }

    @Then("^Reward image is displayed$")
    public void Reward_image_is_displayed() throws Throwable {
        historyNavi.checkVisibilityOfProductImage();
    }

    @Then("^Reward title is displayed$")
    public void Reward_title_is_displayed() throws Throwable {
        historyNavi.checkVisibilityOfProductTitle();
    }

    @Then("^ePoints used is displayed$")
    public void ePoints_used_is_displayed() throws Throwable {
        historyNavi.checkVisibilityOfePointsUsed();
    }

    @Then("^Delivery details are displayed$")
    public void Delivery_details_are_displayed() throws Throwable {
        historyNavi.checkVisibilityOfDeliveryDetails();
    }

    @Then("^Contact Us link is displayed$")
    public void Contact_Us_link_is_displayed() throws Throwable {
        historyNavi.checkVisibilityOfContactUsLink();
    }

    // scenario: Checking if in reward history module data are properly written (product name, delivery details)////////

    @Given("^User try to remember chosen product name$")
    public void User_try_to_remember_chosen_product_name() throws Throwable {
        historyNavi.rememberProductName();
    }

    @Then("^User try to remember Delivery details$")
    public void User_try_to_remember_Delivery_details() throws Throwable {
        historyNavi.rememberDeliveryDetails();
    }

    @Then("^User goes to user account page$")
    public void user_goes_to_user_account_page() throws Throwable {
        completeNavi.clickMyAccountButton();
    }

    @Then("^He can see that all data are properly saved in reward history module$")
    public void He_can_see_that_all_data_are_properly_saved_in_reward_history_module() throws Throwable {
        go_sleep(5000);
        historyNavi.compareAllDataInRewardsHistory();
    }

    // scenario: Checking if detailed page of bought product can be reached correctly///////////////////////////////////

    @Then("^User can see details of chosen product from list$")
    public void User_can_see_details_of_chose_product_from_list() throws Throwable {
        historyNavi.clickChosenProductNameAndRememberItsName();
    }

    @Then("^Get correct information about ordered product$")
    public void Get_correct_information_about_ordered_product() throws Throwable {
        historyNavi.checkPageTitleAndProductName();
    }

    // Checking rewards history list content
    @Then("^Rewards history page can bee seen with proper content comparing to DB for email '(.+)'$")
    public void Rewards_history_page_can_bee_seen_with_proper_content_comparing_to_DB(String userEmail) throws Throwable {
        historyNavi.compareHistryListContentWithDB(userEmail);
    }
}
