package com.iat.ePoints.Steps.Spend;

/**
 * Created with IntelliJ IDEA.
 * User: miwanczyk
 * Date: 19.06.13
 * Time: 21:49
 * To change this template use File | Settings | File Templates.
 */

import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Executors.SeleniumExecutor;
import com.iat.ePoints.Navigations.Spend.BrowseRewardsNavigation;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GetRewardsGridItemCardSteps {

    private IExecutor executor;
    private BrowseRewardsNavigation redemNavi;

    public GetRewardsGridItemCardSteps(SeleniumExecutor executor) {
        this.executor = executor;
    }

    @Before
    public void set_up() {
        executor.start();
        redemNavi = new BrowseRewardsNavigation(executor);
    }

    @After
    public void tear_down() {
        executor.stop();
    }

    @When("^Open redemptions browse page$")
    public void Open_redemptions_browse_page() throws Throwable {
        redemNavi.goToRedemptionsBrowserRewards();
    }

    @Then("^Chose number of items per page '(.+)'$")
    public void Chose_number_of_items_per_page(String ItemsPerPage) throws Throwable {
        redemNavi.chooseProperNumberOfElementsPerPage(ItemsPerPage);
    }

    @Then("^Cards for items should be available '(.+)'$")
    public void Cards_for_items_should_be_available(String ItemsPerPage) throws Throwable {
        redemNavi.checkIsItamsCardsAvailable(ItemsPerPage);
    }

    @Then("^Card have image '(.+)'$")
    public void Card_have_image(String ItemsPerPage) throws Throwable {
        redemNavi.checkIsItamsCardsImagesAvailable(ItemsPerPage);
    }

    @Then("^Card have title as hyperlink '(.+)'$")
    public void Card_have_title_as_hyperlink(String ItemsPerPage) throws Throwable {
        redemNavi.checkIsItamsCardsTitlesAvailable(ItemsPerPage);
    }

    @Then("^Card have points component '(.+)'$")
    public void Card_have_points_component(String ItemsPerPage) throws Throwable {
        redemNavi.checkIsItamsCardsPointsComponentAvailable(ItemsPerPage);
    }

    @Then("^Card have claim this reward button '(.+)'$")
    public void Card_have_claim_this_reward_button(String ItemsPerPage) throws Throwable {
        redemNavi.checkIsItamsCardsAddBasketButtonsAvailable(ItemsPerPage);
    }

    // Scenario: Checking if points range filter works properly ////////////////////////////////////////////////////////

    @Then("^User can use points range filtering '(.+)'$")
    public void User_can_use_points_range_filtering_(String rangeDown) throws Throwable {
        redemNavi.clickProperRangeButton(rangeDown);
    }

    @Then("^Be sure that products points value will be inside those range '(.+)' '(.+)'$")
    public void Be_sure_that_products_points_value_will_be_inside_those_range_(String rangeDown, String rangeUp) throws Throwable {
        redemNavi.checkIfProductPointsValueAreInProperRange(rangeDown, rangeUp);
    }


}
