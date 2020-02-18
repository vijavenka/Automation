package com.iat.ePoints.Steps.Spend;

/**
 * Created with IntelliJ IDEA.
 * User: miwanczyk
 * Date: 19.06.13
 * Time: 20:45
 * To change this template use File | Settings | File Templates.
 */


import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Executors.SeleniumExecutor;
import com.iat.ePoints.Navigations.Spend.BrowseRewardsNavigation;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GetRewardsLandingPageSteps {

    private IExecutor executor;
    private BrowseRewardsNavigation redemNavi;

    public GetRewardsLandingPageSteps(SeleniumExecutor executor) {
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

    @Given("^He is on the Get rewards main tab$")
    public void he_is_on_the_main_tab() throws Throwable {
        redemNavi.goToRedemptionsLandingPage();
        redemNavi.checkIsRedemptionsLandingPageOpened();
    }

    @When("^He looks at the page$")
    public void he_looks_at_the_page() throws Throwable {
        ///Empty
    }

    @Then("^The first page is adapted for the person who is new to the ePoints$")
    public void the_first_page_is_adapted_for_the_person_who_is_new_to_the_ePoints() throws Throwable {
        //Empty
    }


    @Then("^There is information on how many items User can buy$")
    public void There_is_no_information_on_how_many_items_User_can_buy() throws Throwable {
        redemNavi.checkIsItemsCounterAvailable();
    }


    @Then("^Searcher is displayed$")
    public void Searcher_is_displayed() throws Throwable {
        redemNavi.checkIfSearcherIsDisplayed();
    }

    @Then("^User can choose a range and search the items$")
    public void User_can_choose_a_range_and_browse_the_items() throws Throwable {
        redemNavi.checkIsRangesButtonsAvailable();
    }

    @And("^Chose product from proposed by system$")
    public void Chose_product_from_proposed_by_system() throws Throwable {
        redemNavi.checkIfProperProductsAreasAreVisible();
    }

    // Spend page refresh - restructure page (RD-3575)
    @Then("^Te will be able to see three tabs$")
    public void Te_will_be_able_to_see_three_tabs() throws Throwable {
        redemNavi.checkVisibilityOfThreeTabs();
    }

    @When("^Departments Tab will be chosen$")
    public void Departments_Tab_will_be_chosen() throws Throwable {
        redemNavi.chooseDepartmentTab();
    }

    @Then("^Proper set of department tab will be displayed$")
    public void Proper_set_of_department_tab_will_be_displayed() throws Throwable {
        redemNavi.checkIfAllDepartmentAreVisible();
    }

    @When("^Rewards by epoints total tab will be chosen$")
    public void Rewards_by_epoints_total_tab_will_be_chosen() throws Throwable {
        redemNavi.chooseRewardsByEpointsTotalTab();
    }

    @Then("^Proper points value card will be displayed$")
    public void Proper_points_value_card_will_be_displayed() throws Throwable {
        redemNavi.checkIfPointRangeCardsAreVisible();
    }

    @When("^Our top reward picks tab will be chosen$")
    public void Our_top_reward_picks_tab_will_be_chosen() throws Throwable {
        redemNavi.chooseTopRewardsPickTab();
    }

    @Then("^Product buckets cards will be displayed$")
    public void Product_buckets_cards_will_be_displayed() throws Throwable {
        redemNavi.checkIfTopRewardPicksCardsAreVisible();
    }

    // Spend page refresh - add logic for department tab (RD-3595)
    @Then("^User will chose some of department$")
    public void User_can_chose_some_of_department() throws Throwable {
        redemNavi.selectSomeDepartment();
    }

    @Then("^He will be redirected to redemption page$")
    public void He_will_be_redirected_to_redemption_page() throws Throwable {
        redemNavi.checkIfRedemptionPageWasOpened();
    }

    @Then("^Only category filter will be visible from facets set$")
    public void Only_category_filter_will_be_visible_from_facets_set() throws Throwable {
        redemNavi.checkVisibilityOfCategoryFilter();
    }
}


