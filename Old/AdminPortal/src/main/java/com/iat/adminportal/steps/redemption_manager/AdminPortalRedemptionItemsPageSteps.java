package com.iat.adminportal.steps.redemption_manager;

import com.iat.adminportal.page_navigations.redemption_manager.AdminPortalRedemptionItemsPageNavigation;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 19.03.14
 * Time: 13:25
 * To change this template use File | Settings | File Templates.
 */
public class AdminPortalRedemptionItemsPageSteps {

    private AdminPortalRedemptionItemsPageNavigation redemptionItemsNavi = new AdminPortalRedemptionItemsPageNavigation();

    public AdminPortalRedemptionItemsPageSteps() {

    }

    // REDEMPTION MANAGER - add additional search input boxes to allow more granular searches (RD-2567).
    @Given("^Redemption items page is opened$")
    public void Redemption_items_page_is_opened() throws Throwable {
        redemptionItemsNavi.openRedemptionItemsPage();
    }

    @When("^User look at redemption item page$")
    public void User_look_at_redemption_item_page() throws Throwable {
        // leave empty
    }

    @Then("^He can see set of searches$")
    public void He_can_see_set_of_searches() throws Throwable {
        redemptionItemsNavi.checkVisibilityOfSearchElements();
    }

    @Then("^Redemption Items table$")
    public void Redemption_Items_table() throws Throwable {
        redemptionItemsNavi.checkContentOfredemptionItemsTable();
    }

    // REDEMPTION MANAGER - add additional search input boxes to allow more granular searches (RD-2567) - searches.
    @When("^User use title search with '(.+)'$")
    public void User_use_title_search(String title) throws Throwable {
        redemptionItemsNavi.clearSearchResults();
        redemptionItemsNavi.useTitleSearch(title);
        redemptionItemsNavi.clickSearchButton();
    }

    @Then("^Proper results will be displayed for given title '(.+)'$")
    public void Proper_results_will_be_displayed_for_given_title(String title) throws Throwable {
        redemptionItemsNavi.checkTitleSearchResults(title);
    }

    @When("^User use category search with '(.+)'$")
    public void User_use_category_search(String category) throws Throwable {
        redemptionItemsNavi.clearSearchResults();
        redemptionItemsNavi.useCategorySearch(category);
        redemptionItemsNavi.clickSearchButton();
    }

    @Then("^Proper results will be displayed for given category '(.+)'$")
    public void Proper_results_will_be_displayed_for_given_category(String category) throws Throwable {
        redemptionItemsNavi.checkCategorySearchResults(category);
    }

    @When("^User use description search with '(.+)'$")
    public void User_use_description_search(String description) throws Throwable {
        redemptionItemsNavi.clearSearchResults();
        redemptionItemsNavi.useDescriptionSearch(description);
        redemptionItemsNavi.clickSearchButton();
    }

    @Then("^Proper results will be displayed for given description '(.+)'$")
    public void Proper_results_will_be_displayed_for_given_description(String description) throws Throwable {
        redemptionItemsNavi.checkDescriptionSearchResults(description);
    }

    @When("^User use type search with '(.+)'$")
    public void User_use_type_search(String type) throws Throwable {
        redemptionItemsNavi.clearSearchResults();
        redemptionItemsNavi.useTypeSearch(type);
        redemptionItemsNavi.clickSearchButton();
    }

    @Then("^Proper results will be displayed for given type '(.+)'$")
    public void Proper_results_will_be_displayed_for_given_type(String type) throws Throwable {
        redemptionItemsNavi.checkTypeSearchResults(type);
    }

    @When("^User use merchant search with '(.+)'$")
    public void User_use_brand_search(String merchant) throws Throwable {
        redemptionItemsNavi.clearSearchResults();
        redemptionItemsNavi.useMerchantSearch(merchant);
        redemptionItemsNavi.clickSearchButton();
    }

    @Then("^Proper results will be displayed for given merchant '(.+)'$")
    public void Proper_results_will_be_displayed_for_given_brand(String merchant) throws Throwable {
        redemptionItemsNavi.checkMerchantSearchResults(merchant);
    }

    @When("^User use points search with '(.+)' '(.+)'$")
    public void User_use_points_search(String pointsLow, String pointsHigh) throws Throwable {
        redemptionItemsNavi.clearSearchResults();
        redemptionItemsNavi.usePointsSearch(pointsLow, pointsHigh);
        redemptionItemsNavi.clickSearchButton();
    }

    @Then("^Proper results will be displayed for given points '(.+)' '(.+)'$")
    public void Proper_results_will_be_displayed_for_given_points(String pointsLow, String pointsHigh) throws Throwable {
        redemptionItemsNavi.checkPointsSearchResults(pointsLow, pointsHigh);
    }

    // REDEMPTION MANAGER - add additional search input boxes to allow more granular searches (RD-2567) - searches all.
    @When("^User user all searches with '(.+)' '(.+)' '(.+)' '(.+)' '(.+)' '(.+)'$")
    public void user_user_all_searches_with_Batman_DVD_Pushchairs_Samsung_(String title, String category, String type, String merchant, String pointsLow, String pointsHigh) throws Throwable {
        redemptionItemsNavi.clearSearchResults();
        redemptionItemsNavi.useTitleSearch(title);
        redemptionItemsNavi.useCategorySearch(category);
        //redemptionItemsNavi.useDescriptionSearch(description);
        redemptionItemsNavi.useTypeSearch(type);
        redemptionItemsNavi.useMerchantSearch(merchant);
//        redemptionItemsNavi.usePointsSearch(pointsLow, pointsHigh);
        redemptionItemsNavi.clickSearchButton();
    }

    @Then("^Proper results should be shown according to '(.+)' '(.+)' '(.+)' '(.+)' '(.+)' '(.+)'$")
    public void proper_results_should_be_shown_according_to_Batman_DVD_Pushchairs_Samsung_(String title, String category, String type, String merchant, String pointsLow, String pointsHigh) throws Throwable {
        redemptionItemsNavi.checkTitleSearchResults(title);
        redemptionItemsNavi.checkCategorySearchResults(category);
        //redemptionItemsNavi.checkDescriptionSearchResults(description);
        redemptionItemsNavi.checkTypeSearchResults(type);
        redemptionItemsNavi.checkMerchantSearchResults(merchant);
//        redemptionItemsNavi.checkPointsSearchResults(pointsLow, pointsHigh);
    }
}
