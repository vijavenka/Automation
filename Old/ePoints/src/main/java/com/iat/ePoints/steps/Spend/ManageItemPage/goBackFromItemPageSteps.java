package com.iat.ePoints.Steps.Spend.ManageItemPage;

import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Executors.SeleniumExecutor;
import com.iat.ePoints.Navigations.Spend.BrowseRewardsNavigation;
import com.iat.ePoints.Navigations.Spend.ManageItemPageNavigation;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class goBackFromItemPageSteps {

    private IExecutor executor;
    private ManageItemPageNavigation itemPage;
    private BrowseRewardsNavigation browsePage;

    public goBackFromItemPageSteps(SeleniumExecutor executor) {
        this.executor = executor;
    }

    @Before
    public void set_up() {
        executor.start();
        itemPage = new ManageItemPageNavigation(executor);
        browsePage = new BrowseRewardsNavigation(executor);
    }

    @Given("^User is on the '([^']*)' page$")
    public void UserIsOnItemsPage(String item) throws Throwable {
        itemPage = new ManageItemPageNavigation(executor);
        itemPage.open();
        executor.open(item);
    }

    @When("^User clicks 'Back to rewards' link$")
    public void UserClicksBackToRewards() throws Throwable {
        itemPage.goBack();
    }

    @After
    public void tear_down() {
        executor.stop();
    }
}