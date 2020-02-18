package com.iat.ePoints.Steps.Checkout;

import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Executors.SeleniumExecutor;
import com.iat.ePoints.Navigations.Checkout.AddToTheBasketNavigation;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 03.12.13
 * Time: 14:30
 * To change this template use File | Settings | File Templates.
 */
public class AddToTheBasketSteps {

    private IExecutor executor;
    private AddToTheBasketNavigation addNavi;

    public AddToTheBasketSteps(SeleniumExecutor executor) {
        this.executor = executor;

    }

    public void go_sleep(int milisec) throws Throwable {
        Thread.sleep(milisec);
    }

    @Before
    public void set_up() {
        executor.start();
        addNavi = new AddToTheBasketNavigation(executor);
    }

    @After
    public void tear_down() {
        executor.stop();
    }

    @Given("^Opened Spend Tab$")
    public void Opened_Spend_Tab() throws Throwable {
        addNavi.openSpendTab();
    }

    @Then("^User can choose one of top reward picks$")
    public void User_can_choose_one_of_top_reward_picks() throws Throwable {
        addNavi.openSelectedTopRewardCategory();
    }

    @Then("^Add to basket selected product$")
    public void Add_to_basket_selected_product() throws Throwable {
        addNavi.addToBasketSelectedProduct();
    }

    @Then("^User is able to return to spend landing page$")
    public void User_is_able_to_return_to_spend_landing_page() throws Throwable {
        addNavi.clickBackToAllRewardsLink();
    }

    @Then("^Choose Rewards by epoints total category$")
    public void Choose_Rewards_by_epoints_total_category() throws Throwable {
        addNavi.openSelectedRewardsByEpointsTotalCategory();
    }

    @When("^Searching for product is finished$")
    public void Searching_for_product_is_finished() throws Throwable {
        //Leave empty
    }

    @Then("^User is able to see all products in basket$")
    public void User_is_able_to_see_all_products_i_basket() throws Throwable {
        addNavi.goToBasket();
        addNavi.checkIfInBasketAreAddedBeforeProducts();
    }
}
