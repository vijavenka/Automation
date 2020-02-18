package com.iat.ePoints.Steps.Spend;

/**
 * Created with IntelliJ IDEA.
 * User: miwanczyk
 * Date: 20.06.13
 * Time: 09:45
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

public class SearchResultsToolsSteps {

    private IExecutor executor;
    private BrowseRewardsNavigation redemNavi;

    public SearchResultsToolsSteps(SeleniumExecutor executor) {
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

    public void go_sleep(int milisec) throws Throwable {
        Thread.sleep(milisec);
    }

    @When("^Browse rewards page opened$")
    public void Browse_rewards_page_opened() throws Throwable {
        redemNavi.checkIsBrowseRewardsPageOpened();
    }

    @Then("^Page size component is available$")
    public void Page_size_component_is_available() throws Throwable {
        redemNavi.checkIsPageSizeComponentAvailable();
    }

    @Then("^Sort order component is available$")
    public void Sort_order_component_is_available() throws Throwable {
        // Express the Regexp above with the code you wish you had
        redemNavi.checkIsSortOrderComponentAvailable();
    }

    @Then("^Pagination component is available$")
    public void Pagination_component_is_available() throws Throwable {
        redemNavi.checkIsPaginationComponentAvailable();
    }

    @Then("^Ranges buttons are available$")
    public void Ranges_buttons_are_available() throws Throwable {
        redemNavi.checkIsRangesBtnsAvailable();
    }

    @Then("^Category component is available$")
    public void Category_component_is_available() throws Throwable {
        redemNavi.checkIsCategoryComponentAvailable();
    }

    // Spend page refresh - show initial department filter (RD-3605) - reach by product counter (without breadcrumb)
    @When("^redemption page will by opened using product counter$")
    public void redemption_page_will_by_opened_using_product_counter() throws Throwable {
        redemNavi.openRedemptionPageUsingProductCounter();
    }

    @Then("^Initial department filter should be available$")
    public void Initial_department_filter_should_be_available() throws Throwable {
        redemNavi.checkIfDepartmentFilterIsAvailable();
    }

    @When("^User choose some department option$")
    public void When_user_choose_some_department_option() throws Throwable {
        redemNavi.chooseSomeDepartmentOption();
    }

    @And("^Rest of filters will be available$")
    public void Rest_of_filters_will_be_available() throws Throwable {
        redemNavi.checkIfAllNeededFacetsAreDisplayed();
    }

    // Spend page refresh - show initial department filter (RD-3605) - reach by search (with breadcrumb)
    @When("^redemption page will by opened using search$")
    public void redemption_page_will_by_opened_using_search() throws Throwable {
        redemNavi.searchReward("book");
    }

    @Then("^Breadcrumb module will be properly displayed for chosen department$")
    public void Breadcrumb_module_will_be_properly_displayed() throws Throwable {
        redemNavi.checkCorrectnessOfBreadcrumb("department_level");
    }

    @When("^User click all department breadcrumb option$")
    public void User_click_all_department_breadcrumb_option() throws Throwable {
        redemNavi.clickAlldepartmentBreadcrumbOption();
    }

    // Spend page refresh - points to purchase filter (RD-3645).
    @When("^Use point range filter$")
    public void Use_point_range_filter() throws Throwable {
        redemNavi.usePointRangeFilterWithRandomValues();
    }

    @Then("^Only product in point range will be displayed$")
    public void Only_product_in_point_range_will_be_displayed() throws Throwable {
        redemNavi.checkIfProductPointsValueAreInProperRange();
    }

    @Then("^Found between information will be correctly displayed$")
    public void Found_between_information_will_be_correctly_displayed() throws Throwable {
        redemNavi.checkIfFoundBetweenInformationHasProperContent();
    }

    // Spend page refresh - breadcrumb logic (RD-3655).
    @Then("^On redemption first part of breadcrumb will be displayed$")
    public void On_redemption_first_part_of_breadcrumb_will_be_displayed() throws Throwable {
        redemNavi.checkCorrectnessOfBreadcrumb("department_level");
    }

    @When("^User select some category$")
    public void User_select_some_category() throws Throwable {
        redemNavi.selectSomeCategory();
    }

    @Then("^Breadcrumb will be extended by category name$")
    public void Breadcrumb_will_be_extended_by_category_name() throws Throwable {
        redemNavi.checkCorrectnessOfBreadcrumb("category_level");
    }

    @When("^User select some brand$")
    public void User_select_some_brand() throws Throwable {
        redemNavi.selectSomeBrand();
    }

    @Then("^Breadcrumb will be extended by brand name$")
    public void Breadcrumb_will_be_extended_by_brand_name() throws Throwable {
        redemNavi.checkCorrectnessOfBreadcrumb("brand_level");
    }

    @When("^User select some type$")
    public void User_select_some_type() throws Throwable {
        redemNavi.selectSomeType();
    }

    @Then("^Breadcrumb will be extended by type name$")
    public void Breadcrumb_will_be_extended_by_type_name() throws Throwable {
        redemNavi.checkCorrectnessOfBreadcrumb("type_level");
    }

    @Then("^Breadcrumb will be extended by points range$")
    public void Breadcrumb_will_be_extended_by_points_range() throws Throwable {
        redemNavi.checkCorrectnessOfBreadcrumb("points_level");
    }

    // Spend page refresh - category filter (RD-3615).
    @Then("^Category filter should be available$")
    public void Category_filter_should_be_available() throws Throwable {
        redemNavi.checkIfCategoryFilterIsVisible(true);
    }

    @Then("^Category filter should disappears$")
    public void Category_filter_should_disappears() throws Throwable {
        redemNavi.checkIfCategoryFilterIsVisible(false);
    }

    @When("^Used decide to comeback to category selection$")
    public void Used_decide_to_comeback_to_category_selection() throws Throwable {

    }

    @Then("^He can click bread crumb department section$")
    public void He_can_click_bread_crumb_department_section() throws Throwable {
        redemNavi.clickDepartmentBreadcrumbOption();
    }

    // Spend page refresh - brand filter (RD-3625) - functional + breadcrumb no search
    @Then("^Brand filter should be available$")
    public void Brand_filter_should_be_available() throws Throwable {
        redemNavi.checkIfBrandFilterIsAvailable();
    }

    @When("^User add second brand$")
    public void User_add_second_brand() throws Throwable {
        redemNavi.selectSomeBrand();
    }

    @Then("^Breadcrumb will also contains name of second brand$")
    public void Breadcrumb_will_also_contains_name_of_second_brand() throws Throwable {
        redemNavi.checkIfNewBrandWasAddedToBreadcrumb();
    }

    @When("^Third Brand will be added$")
    public void Third_Brand_will_be_added() throws Throwable {
        redemNavi.selectSomeBrand();
    }

    @Then("^Only information about number of items will be displayed on breadcrumb$")
    public void Only_information_about_number_of_items_will_be_displayed_on_breadcrumb() throws Throwable {
        redemNavi.checkBreadcrumbNumberInformation();
    }

    @When("^User use clear brand button$")
    public void User_use_clear_brand_button() throws Throwable {
        redemNavi.clickClearButtonInBranSection();
    }

    @Then("^Selected brands will be cleared$")
    public void Selected_brands_will_be_cleared() throws Throwable {
        redemNavi.checkIfOnlyBrandFacedWasCleared();
    }

    // Spend page refresh - brand filter (RD-3625) - functional + search
    @When("^User use brand search with some phrase$")
    public void user_use_brand_search_with_som_phrase() throws Throwable {
        redemNavi.useBrandSearch();
    }

    @Then("^Search should works correctly and gave correct results$")
    public void Search_should_works_correctly_and_gave_correct_results() throws Throwable {
        redemNavi.checkBrandResultsAfterSearching();
    }

    // Spend page refresh - type filter (RD-3635) - functional + breadcrumb no search
    @Given("^Department and category is already chosen$")
    public void Department_and_category_is_already_chosen() throws Throwable {
        redemNavi.selectDepartmentAndCategoryHighlypopulated();
    }

    @When("^User add second type$")
    public void User_add_second_type() throws Throwable {
        redemNavi.selectSomeType();
    }

    @Then("^Breadcrumb will also contains name of second type$")
    public void Breadcrumb_will_also_contains_name_of_second_type() throws Throwable {
        redemNavi.checkIfNewTypeWasAddedToBreadcrumb();
    }

    @When("^Third type will be added$")
    public void Third_type_will_be_added() throws Throwable {
        redemNavi.selectSomeType();
    }

    @Then("^Only information about number of types will be displayed on breadcrumb$")
    public void Only_information_about_number_of_types_will_be_displayed_on_breadcrumb() throws Throwable {
        redemNavi.checkBreadcrumbTypeNumberInformation();
    }

    @When("^User use clear type button$")
    public void User_use_clear_type_button() throws Throwable {
        redemNavi.clickClearButtonInTypeSection();
    }

    @Then("^Selected types will be cleared$")
    public void Selected_types_will_be_cleared() throws Throwable {
        redemNavi.checkIfOnlyTypeFacedWasCleared();
    }

    // Spend page refresh - type filter (RD-3635) - functional +  search
    @Then("^Type filter should be available$")
    public void Type_filter_should_be_available() throws Throwable {
        redemNavi.checkIfTypeFilterIsAvailable();
    }

    @When("^User use type search with some phrase$")
    public void User_use_type_search_with_som_phrase() throws Throwable {
        redemNavi.useTypeSearch();
    }

    @Then("^Type search should works correctly and gave correct results$")
    public void Type_search_should_works_correctly_and_gave_correct_results() throws Throwable {
        redemNavi.checkTypeResultsAfterSearching();
    }

    // Spend page refresh - add counts to filter values (RD-3677).
    @Then("^Proper number of product should be displayed on navigation bar$")
    public void Proper_number_of_product_should_be_displayed_on_navigation_bar() throws Throwable {
        redemNavi.checkCorrectnessOfProductNumberOnNavigationBar();
    }
}
