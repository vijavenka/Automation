package com.iat.ePoints.Steps.Get;

/**
 * Created with IntelliJ IDEA.
 * User: miwanczyk
 * Date: 19.06.13
 * Time: 14:41
 * To change this template use File | Settings | File Templates.
 */

import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Executors.SeleniumExecutor;
import com.iat.ePoints.Navigations.Get.GetLandingPageNavigation;
import com.iat.ePoints.Navigations.Get.GetStoresAZPageNavigation;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GetLandingPageSteps {

    private IExecutor executor;
    private GetLandingPageNavigation earnNavi;
    private GetStoresAZPageNavigation earnAZNavi;

    public GetLandingPageSteps(SeleniumExecutor executor) {
        this.executor = executor;
    }

    public void go_sleep(int milisec) throws Throwable {
        Thread.sleep(milisec);
    }

    @Before
    public void set_up() {
        executor.start();
        earnNavi = new GetLandingPageNavigation(executor);
        earnAZNavi = new GetStoresAZPageNavigation(executor);
    }

    @After
    public void tear_down() {
        executor.stop();
    }

    // scenario 1 //////////////////////////////////////////////////////////////////////////////////////////////////////

    @When("^Open Get ePoints page$")
    public void Open_Earn_ePoints_page() throws Throwable {
        earnNavi.goToEarnEPointsPage();
    }

    @Then("^Get ePoints page should be opened properly$")
    public void Earn_ePoints_page_should_be_opened_properly() throws Throwable {
        earnNavi.checkIsEarnLandingPageOpend();
    }

    @Then("^Get ePoints page have proper sections$")
    public void Earn_ePoints_page_have_proper_sections() throws Throwable {
        earnNavi.checkIsEarnLandingPageHaveProperSections();
    }

    @Then("^Get ePoints sections have proper content$")
    public void Earn_ePoints_sections_have_proper_content() throws Throwable {
        earnNavi.checkIfEarnLandingPageSectionsHaveProperContent();
    }

    // scenario 1 //////////////////////////////////////////////////////////////////////////////////////////////////////

    @Then("^All links and buttons should works properly$")
    public void All_links_and_buttons_should_works_properly() throws Throwable {
        earnNavi.checkSearcherLink();
        earnNavi.checkAZOfAllStoresButtonLink();
        earnNavi.checkContactUSButtonLink();
    }

    // RETAILER PAGE ON EPOINTS - change retailer links on home and get pages (RD-1156 release 10) /////////////////////
    @Then("^Following any retailer link from main page user can see that retailer url is correct$")
    public void Following_any_retailer_link_from_main_page_user_can_see_that_retailer_url_is_correct() throws Throwable {
        earnNavi.checkCorrectnessOfRetailersLinksOnMainPage();
    }

    @Then("^User can check that url's are correct also on get page$")
    public void User_can_check_that_url_s_are_correct_also_on_get_page() throws Throwable {
         earnNavi.checkCorrectnessOfRetailersLinksOnGetPage();
    }

    @Then("^User can check that url's are correct also on stores a-z page$")
    public void User_can_check_that_url_s_are_correct_also_on_stores_a_z_page() throws Throwable {
        earnNavi.checkCorrectnessOfRetailersLinksOnStoresAZPage();
    }


    @Then("^User can check that url's are correct also on about us page$")
    public void User_can_check_that_url_s_are_correct_also_on_about_us_page() throws Throwable {
        earnNavi.checkCorrectnessOfRetailersLinksOnAboutUsPage();
    }

    // scenario 4 //////////////////////////////////////////////////////////////////////////////////////////////////////

    @Then("^All needed elements should be visible in AZ page$")
    public void all_needed_elements_should_be_visible() throws Throwable {
        earnAZNavi.checkContentOfAZpage();
    }

    // scenario 5 //////////////////////////////////////////////////////////////////////////////////////////////////////

    @Then("^Searcher is available$")
    public void Searcher_is_available() throws Throwable {
        earnAZNavi.checkSearcherVisibility();
    }


    @When("^'(.+)' phrase will be entered and search button used$")
    public void Some_phrase_will_be_entered_and_search_button_used(String retailer) throws Throwable {
        earnAZNavi.enterRetailerNameIntoSearcher(retailer);
        earnAZNavi.clickSearchButton();
    }

    @Then("^Proper retailer '(.+)' should be found$")
    public void Proper_retailer_should_be_found(String retailer) throws Throwable {
        earnAZNavi.checkRetailerResult(retailer);
    }

    // scenario 6 //////////////////////////////////////////////////////////////////////////////////////////////////////
    @Then("^User can use alphabetic search and it works fine$")
    public void User_can_use_alphabetic_search_and_it_works_fine() throws Throwable {
        earnAZNavi.checkAllRetailersForGivenLetters();
    }

    // RETAILER PAGE ON EPOINTS - add pagination to A-Z interface(RD-1168) /////////////////////////////////////////////
    @Then("^User can see that pagination component is available$")
    public void User_can_see_that_pagination_component_is_available() throws Throwable {
        earnAZNavi.checkIfAllGirderElementsAreAvailable();
        earnAZNavi.checkIfAllBottomPaginationElementsAreAvailable();
        earnAZNavi.navigateToLetterWithLargeNumberOfRetailers();
    }

    @Then("^Showing module is working correctly$")
    public void Showing_module_is_working_correctly() throws Throwable {
        earnAZNavi.changeItemPerPageNumber("20");
        earnAZNavi.clickRightArrow();
        go_sleep(2000);
        earnAZNavi.checkShowingBehaviour("Showing 21 - 40", "2");
        earnAZNavi.clickLeftArrow();
        go_sleep(2000);
        earnAZNavi.checkShowingBehaviour("Showing 1 - 20", "1");
    }

    @Then("^Items per page module is working correctly$")
    public void Items_per_page_module_is_working_correctly() throws Throwable {
        earnAZNavi.changeItemPerPageNumber("20");
        go_sleep(2000);
        earnAZNavi.checkShowingBehaviour("Showing 1 - 20", "1");
        earnAZNavi.checkPaginationBehaviour(20);
        earnAZNavi.changeItemPerPageNumber("40");
        go_sleep(2000);
        earnAZNavi.checkShowingBehaviour("Showing 1 - 40", "1");
        earnAZNavi.checkPaginationBehaviour(40);
        earnAZNavi.changeItemPerPageNumber("20");
        go_sleep(2000);
        earnAZNavi.checkShowingBehaviour("Showing 1 - 20", "1");
        earnAZNavi.checkPaginationBehaviour(20);
    }

    @Then("^Bottom pagination module is working correctly$")
    public void Bottom_pagination_module_is_working_correctly() throws Throwable {
        earnAZNavi.clickNextButton();
        go_sleep(2000);
        earnAZNavi.checkShowingBehaviour("Showing 21 - 40", "2");
        earnAZNavi.clickPrevButton();
        go_sleep(2000);
        earnAZNavi.checkShowingBehaviour("Showing 1 - 20", "1");
        earnAZNavi.clickDirectlyPageNumber();
        go_sleep(2000);
        earnAZNavi.checkShowingBehaviour("Showing 21 - 40", "2");
    }

    // RETAILER PAGE ON EPOINTS - update A-Z search results interface //////////////////////////////////////////////////
    @Then("^User pointing chosen retailer card can see two buttons$")
    public void User_pointing_chosen_retailer_card_can_see_two_buttons() throws Throwable {
        earnAZNavi.pointChosenRetailer();
    }

    @Then("^User click Info and offers button and he is redirect to retailer page$")
    public void User_click_Info_and_offers_button_and_he_is_redirect_to_retailer_page() throws Throwable {
        earnAZNavi.goToRetailerPage();
    }

    @Then("^Using Visit retailer button he is redirected to transition page$")
    public void Using_Visit_retailer_button_he_is_redirected_to_transition_page() throws Throwable {
        earnAZNavi.goToTransitionPage();
    }

    // RETAILER PAGE ON EPOINTS - make retailer favourite logic and A-Z page change(RD-1159) ///////////////////////////
    @Given("^User can not see Favourites block on Stores-AZ when he has no any favourites stores selected$")
    public void User_can_not_see_Favourites_block_on_Stores_AZ_when_he_has_no_any_favourites_stores_selected() throws Throwable {
        earnAZNavi.goToShopsAZpage();
        earnAZNavi.checkFavouriteStoreSectionVisibility(false);
    }

    @Then("^He is able to go to retailer page and select one store as favourite$")
    public void He_is_able_to_go_to_retailer_page_and_select_one_store_as_favourite() throws Throwable {
        earnAZNavi.goToChosenRetailerPageAndStoreCardElementsValues();
        earnAZNavi.clickAddOrRemoveToFavouritesButton(true);
    }

    @When("^User came back to AZ page he can see Your favourite stores block and selected stores$")
    public void User_came_back_to_AZ_page_he_can_see_Your_favourite_stores_block_and_selected_stores() throws Throwable {
        earnAZNavi.checkFavouriteStoreSectionVisibility(true);
        earnAZNavi.checkFavouriteBlockContent();
    }

    @When("^Information about selected store should be written in Database$")
    public void Information_about_selected_store_should_be_written_in_database() throws Throwable {
        earnAZNavi.checkUserInterestsTableForChanges();
    }

    @Then("^User can decide to remove all stores from favourites$")
    public void User_can_decide_to_remove_all_stores_from_favourites() throws Throwable {
        earnAZNavi.removeStoreFromFavourites();
    }

    @Then("^Stores and favourite section will not be displayed$")
    public void Stores_and_favourite_section_will_not_be_displayed() throws Throwable {
        earnAZNavi.checkFavouriteStoreSectionVisibility(false);
    }

    // RETAILER PAGE ON EPOINTS - make retailer favourite logic and A-Z page change(RD-1159) - more product test ///////
    @Then("^User can add several retailers to his favourites$")
    public void User_can_add_several_retailers_to_his_favourites() throws Throwable {
        earnAZNavi.addSeveralRetailersToFavourites();
    }

    @Then("^Favourites stores carousel behave properly$")
    public void Favourites_stores_carousel_behave_properly() throws Throwable {
        earnAZNavi.checkContentAndBehaviourOfCarousel();
    }

    // RETAILER PAGE ON EPOINTS - add department filter to A-Z results (RD-1167) ///////////////////////////////////////
    @Then("^User can see properly displayed department filter$")
    public void User_can_see_properly_displayed_department_filter() throws Throwable {
        earnAZNavi.checkVisibilityOfAllDepartments();
    }

    @When("^One of department will be selected$")
    public void One_of_department_will_be_selected() throws Throwable {
        earnAZNavi.selectOneDepartment();
    }

    @Then("^Retailers from others departments should not be displayed$")
    public void Retailers_from_others_departments_should_not_be_displayed() throws Throwable {
        earnAZNavi.checkIfNumberOfRetailersWasDecreased();
    }

    @And("^Letters without any content should be hidden$")
    public void Letters_without_any_content_should_be_hidden() throws Throwable {
        earnAZNavi.checkIfThereAreHiddenLetters();
    }

    @Then("^User want to see results for all categories again$")
    public void User_want_to_see_results_for_all_categories_again() throws Throwable {
        earnAZNavi.clickSeeAllButton();
    }

    @Then("^The same categories as on the beginning should be displayed$")
    public void The_same_categories_as_on_the_beginning_should_be_displayed() throws Throwable {
        earnAZNavi.compareNumberOfDisplayedRetailerWithNumberOnBeggining();
    }

    // Ensure that Recently visited stores is disabled for not logged user /////////////////////////////////////////////
    @Then("^Not Logged user can not see recently visited stores component$")
    public void Not_Logged_user_can_not_see_recently_visited_stores_component() throws Throwable {
        earnAZNavi.checkIfRecentlyVisitedComponentIsEnabled(false);
    }

    // Ensure that Recently visited stores is enabled for logged user and works fine ///////////////////////////////////
    @Then("^Logged user can see recently visited component$")
    public void Logged_user_can_see_recently_visited_component() throws Throwable {
        earnAZNavi.checkIfRecentlyVisitedComponentIsEnabled(true);
    }

    @Then("^User can use recently visited stores component$")
    public void User_can_use_recently_visited_stores_component() throws Throwable {
        earnAZNavi.goToRecentlyVisitedComponent();
        earnAZNavi.checkIfInsideRecentlyVisitedComponentIsAnyRetailer();
        earnAZNavi.openRetailerPageUsingRecentlyVisitedComponentContent();
    }
}
