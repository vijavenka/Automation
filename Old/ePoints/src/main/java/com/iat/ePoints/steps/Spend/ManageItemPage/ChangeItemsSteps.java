package com.iat.ePoints.Steps.Spend.ManageItemPage;

import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Executors.SeleniumExecutor;
import com.iat.ePoints.Navigations.Spend.ManageItemPageNavigation;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertTrue;

public class ChangeItemsSteps {

    private IExecutor executor;
    private ManageItemPageNavigation itemPage;
    private int startQuant = 0;

    public ChangeItemsSteps(SeleniumExecutor executor) {
        this.executor = executor;
    }

    @Before
    public void set_up() {
        executor.start();
        itemPage = new ManageItemPageNavigation(executor);
    }

    @After
    public void tear_down() {
        executor.stop();
    }

    // scenario 2 from Manage the item page/////////////////////////////////////////////////////////////////////////////

    @Given("^'(.+)' user is on the '(.+)' item page$")
    public void userIsOnItemPage(String user, String item) throws Throwable {
        itemPage = new ManageItemPageNavigation(executor);
        itemPage.open();
        executor.open(item);
        Thread.sleep(5000);
    }

    // scenario 2 from Manage the item page/////////////////////////////////////////////////////////////////////////////

    @When("^User clicks Back to rewards link$")
    public void User_clicks_Back_to_rewards_link() throws InterruptedException {
        itemPage.clickBackToRewardsReference();
    }

    @Then("^User is redirected back to list of all redemption items$")
    public void User_is_redirected_back_to_list_of_all_redemption_items() throws Throwable {
        itemPage.checkIfUserIsOnBrowseRewardsPage();
    }

    // scenario 3 Change number of items////////////////////////////////////////////////////////////////////////////////

    @When("^User clicks on '(.+)' icon$")
    public void userClicksOnPlusIcon(String symbol) throws Throwable {
        startQuant = itemPage.getQuant();
        if (symbol.equals("+")) itemPage.moreItems();
        else if (symbol.equals("-")) itemPage.lessItems();
        int endQuant = itemPage.getQuant();
    }


    @Then("^If '(.+)' clicked the number of items on the counter is changed by one$")
    public void theNumberOfItemsChangedByOne(String symbol) throws Throwable {
        int endQuant = itemPage.getQuant();
        boolean checkIfOne;
        if (symbol.equals("+")) {
            if ((endQuant - startQuant) == 1) checkIfOne = true;
            else checkIfOne = false;
        } else if (symbol.equals("-")) {
            if ((startQuant - 1) == endQuant) checkIfOne = true;
            else checkIfOne = true;
        } else checkIfOne = false;

        assertTrue(startQuant >= 1);
        assertTrue(endQuant >= 1);
        assertTrue(checkIfOne);
    }

}