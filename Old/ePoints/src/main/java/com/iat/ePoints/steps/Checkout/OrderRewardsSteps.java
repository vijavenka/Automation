package com.iat.ePoints.Steps.Checkout;


import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Executors.SeleniumExecutor;
import com.iat.ePoints.Navigations.Checkout.SelectedRewardsNavigation;


import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class OrderRewardsSteps {

    private IExecutor executor;
    private SelectedRewardsNavigation rewardsNavi;

    public OrderRewardsSteps(SeleniumExecutor executor) {
        this.executor = executor;
    }

    @Before
    public void set_up() {
        executor.start();
        rewardsNavi = new SelectedRewardsNavigation(executor);
    }

    @After
    public void tear_down() {
        executor.stop();
    }

    // Sceeanrio: Order rewards- logged in User/////////////////////////////////////////////////////////////////////////

    @When("^The items in the basket are affordable$")
    public void the_items_in_the_basket_are_affordable() throws Throwable {

    }

    @When("^User clicks on Order this rewards$")
    public void User_clicks_on_Order_this_rewards() throws Throwable {

    }

    @When("^He clicks on Order rewards button$")
    public void he_clicks_on_Order_rewards_button() throws Throwable {
        rewardsNavi.goToSelectRewardView();
    }

    @When("^He is on Selected rewards page$")
    public void he_is_on_Selected_rewards_page() throws Throwable {
        rewardsNavi.checkIfCheckouPageHasproperTitle();
        rewardsNavi.checkIfSelectRewardsModuleHasProperTitle();
    }

    @Then("^User is redirected to Delivery Details screen$")
    public void User_is_redirected_to_Delivery_Details_screen() throws Throwable {

    }

    @Then("^User's products selection is displayed$")
    public void User_s_products_selection_is_displayed() throws Throwable {

    }
}