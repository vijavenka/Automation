package com.iat.ePoints.Steps.Spend.ManageItemPage;

import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Executors.SeleniumExecutor;
import com.iat.ePoints.Navigations.Spend.BrowseRewardsNavigation;
import com.iat.ePoints.Navigations.Spend.ManageItemPageNavigation;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertTrue;

public class GoToItemSteps {

    private IExecutor executor;
    private BrowseRewardsNavigation browsePage;
    private ManageItemPageNavigation itemPage;

    public GoToItemSteps(SeleniumExecutor executor) {
        this.executor = executor;
    }

    @Before
    public void set_up() {
        executor.start();
        browsePage = new BrowseRewardsNavigation(executor);
        itemPage = new ManageItemPageNavigation(executor);
    }

    @After
    public void tear_down() {
        executor.stop();
    }

    @When("^User use searcher with reward '(.+)'$")
    public void User_use_searcher_with_reward(String RewardName) throws Throwable {
        browsePage.searchReward(RewardName);
    }

    @When("^User clicks on '(.+)' to browse$")
    public void userClicksOnItem(String item) throws Throwable {
        browsePage.click_item(item);
        Thread.sleep(4000);
    }

    @Then("^User is redirected to the individual redemption item page '(.+)'$")
    public void UserIsOnItemPage(String Item) throws Throwable {
        assertTrue(browsePage.checkIfItemPage(Item));
    }

    @Then("^Image, description, points to purchase and more information are available$")
    public void Image_description_points_to_purchase_and_more_information_are_available() throws Throwable {
        assertTrue("Details section is invisible", itemPage.checkDetails());
        assertTrue("Delivery title is invisible", itemPage.checkDeliveryTitle());
        assertTrue("Product name is invisible", itemPage.checkDescriptionTitle());
        assertTrue("Delivery content is invisible", itemPage.checkDeliveryContent());
        // product description is no mandatory
        //assertTrue("Product description is invisible", itemPage.checkDescriptionContent());
        assertTrue("Product picture is invisible", itemPage.checkPictureVisibility());

    }

    @Then("^'Add to basket' option is available$")
    public void checkAddToBasketOpt() throws Throwable {
        assertTrue(itemPage.checkSelectButton());
    }
}