package com.iat.storepresentation.Steps.SearchComponent;

import com.iat.storepresentation.Executors.IExecutor;
import com.iat.storepresentation.Executors.SeleniumExecutor;
import com.iat.storepresentation.Navigations.SearchComponent.SearchPageNavigation;
import com.iat.storepresentation.Navigations.ShopHome.HomePageNavigation;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 07.03.14
 * Time: 10:27
 * To change this template use File | Settings | File Templates.
 */
public class SearchPageSteps {

    private IExecutor executor;
    private HomePageNavigation homeNavi;
    private SearchPageNavigation searchNavi;


    public SearchPageSteps(SeleniumExecutor executor) {
        this.executor = executor;
    }

    public void go_sleep(int milisec) throws Throwable {
        Thread.sleep(milisec);
    }

    @Before
    public void set_up() {
        executor.start();
        homeNavi = new HomePageNavigation(executor);
        searchNavi = new SearchPageNavigation(executor);
    }

    @After
    public void tear_down() {
        executor.stop();
    }

    // FRONT END REFACTOR - add epoints search solution to WLS Search results pages (RD-2084) - working of home page search
    @When("^User use search with chose phrase '(.+)'$")
    public void User_use_search_with_chose_phrase(String phrase) throws Throwable {
        searchNavi.typePhraseToSearcher(phrase);
        searchNavi.clickSearchButton();
    }

    @Then("^Search page should be opened$")
    public void Search_page_should_be_opened() throws Throwable {
        searchNavi.checkIfSearchPageWasOpened();
    }

    @Then("^Proper product connected with chosen phrase should be displayed '(.+)'$")
    public void proper_product_connected_with_chosen_phrase_should_be_displayed(String phrase) throws Throwable {
        searchNavi.checkIfProperProductWasFound(phrase);
    }

    // FRONT END REFACTOR - add epoints search solution to WLS Search results pages (RD-2084) - working of pagination component
    @Given("^Search page is opened$")
    public void Search_page_is_opened() throws Throwable {
        searchNavi.typePhraseToSearcher("Batman");
        searchNavi.clickSearchButton();
        go_sleep(6000);
    }

    @Then("^User can see that pagination component is available$")
    public void User_can_see_that_pagination_component_is_available() throws Throwable {
        searchNavi.checkIfAllGirderElementsAreAvailable();
        searchNavi.checkIfAllBottomPaginationElementsAreAvailable();
    }

    @Then("^Showing module is working correctly$")
    public void Showing_module_is_working_correctly() throws Throwable {
        searchNavi.changeItemPerPageNumber("20");
        searchNavi.clickRightArrow();
        searchNavi.checkShowingBehaviour("Showing 21 - 40", "2");
        searchNavi.clickLeftArrow();
        searchNavi.checkShowingBehaviour("Showing 1 - 20", "1");
    }

    @Then("^Items per page module is working correctly$")
    public void Items_per_page_module_is_working_correctly() throws Throwable {
        searchNavi.changeItemPerPageNumber("20");
        searchNavi.checkShowingBehaviour("Showing 1 - 20", "1");
        searchNavi.checkPaginationBehaviour(20);
        searchNavi.changeItemPerPageNumber("40");
        searchNavi.checkShowingBehaviour("Showing 1 - 40", "1");
        searchNavi.checkPaginationBehaviour(40);
        searchNavi.changeItemPerPageNumber("20");
        searchNavi.checkShowingBehaviour("Showing 1 - 20", "1");
        searchNavi.checkPaginationBehaviour(20);
    }

    @Then("^Bottom pagination module is working correctly$")
    public void Bottom_pagination_module_is_working_correctly() throws Throwable {
        searchNavi.clickNextButton();
        searchNavi.checkShowingBehaviour("Showing 21 - 40", "2");
        searchNavi.clickPrevButton();
        searchNavi.checkShowingBehaviour("Showing 1 - 20", "1");
        searchNavi.clickDirectlyPageNumber();
        searchNavi.checkShowingBehaviour("Showing 21 - 40", "2");
    }

    // FRONT END REFACTOR - add epoints search solution to WLS Search results pages (RD-2084) - working of price filter
    @Then("^User can use price filter$")
    public void User_can_use_price_filter() throws Throwable {
        // All implemented in next step
    }

    @Then("^Be sure that it works correctly$")
    public void Be_sure_that_it_works_correctly() throws Throwable {
        searchNavi.checkWorkingOfPriceFilter();
    }

    // FRONT END REFACTOR - add epoints search solution to WLS Search results pages (RD-2084) - content of product cards
    @When("^User look at search page$")
    public void User_look_at_search_page_page() throws Throwable {
        // leave empty
    }

    // FRONT END REFACTOR - add epoints search solution to WLS Search results pages (RD-2084) - facet component
    @Then("^He can see facet component$")
    public void He_can_see_facet_component() throws Throwable {
        searchNavi.checkVisibilityOfFacetComponent();
    }

    @Then("^Facet component can be used$")
    public void Facet_component_can_be_used() throws Throwable {
        searchNavi.useFacetComponent();
    }

    @When("^User use clear all filters button$")
    public void User_use_clear_all_filters_button() throws Throwable {
        searchNavi.clickClearAllFilterButton();
    }

    @Then("^All filters should be cleared$")
    public void All_filters_should_be_cleared() throws Throwable {
        searchNavi.checkIfFiltersWereDisabled();
    }

    // FRONT END REFACTOR - add epoints search solution to WLS Search results pages (RD-2084) - facet popup window
    @When("^User use see all facets option$")
    public void User_use_see_all_facets_option() throws Throwable {
        searchNavi.clickSeeeAllFacetsButton();
    }

    @Then("^Popup with proper filters will be showed$")
    public void Popup_with_proper_filters_will_be_showed() throws Throwable {
        searchNavi.checkVisibilityOfFacetsPopup();
        searchNavi.checkCorrectnessOfFacetCategories();
    }

    @Then("^User can choose some filter$")
    public void User_can_choose_some_filter() throws Throwable {
        searchNavi.choseFilterOption();
    }

    @Then("^Filter should works$")
    public void Filter_should_works() throws Throwable {
        searchNavi.checkIfFilterIsEnabled();
    }
}
