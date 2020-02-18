package com.iat.storepresentation.Steps.AZPage;

import com.iat.storepresentation.Executors.IExecutor;
import com.iat.storepresentation.Executors.SeleniumExecutor;
import com.iat.storepresentation.Navigations.AZPage.AZPageNavigations;
import com.iat.storepresentation.Navigations.TransitionPage.TransitionPageNavigation;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 08.03.14
 * Time: 13:27
 * To change this template use File | Settings | File Templates.
 */
public class AZPageSteps {

    private IExecutor executor;
    private TransitionPageNavigation transitionNavi;
    private AZPageNavigations AZNavi;

    public AZPageSteps(SeleniumExecutor executor) {
        this.executor = executor;
    }

    public void go_sleep(int milisec) throws Throwable {
        Thread.sleep(milisec);
    }

    @Before
    public void set_up() {
        executor.start();
        transitionNavi = new TransitionPageNavigation(executor);
        AZNavi = new AZPageNavigations(executor);
    }

    @After
    public void tear_down() {
        executor.stop();
    }

    // RETAILER PAGE ON WLS - add navigation and A_Z page logic (Rd-1164) - content of AZ page
    @Given("^AZ page is opened$")
    public void AZ_page_is_opened() throws Throwable {
        AZNavi.openAZPage();
    }

    @Then("^All needed elements should be visible in AZ page$")
    public void all_needed_elements_should_be_visible() throws Throwable {
        AZNavi.checkContentOfAZpage();
    }

    // RETAILER PAGE ON WLS - add navigation and A_Z page logic (Rd-1164) - working of search
    @Then("^Searcher is available$")
    public void Searcher_is_available() throws Throwable {
        AZNavi.checkSearcherVisibility();
    }


    @When("^'(.+)' phrase will be entered and search button used$")
    public void Some_phrase_will_be_entered_and_search_button_used(String retailer) throws Throwable {
        AZNavi.enterRetailerNameIntoSearcher(retailer);
        AZNavi.clickSearchButton();
    }

    @Then("^Proper retailer '(.+)' should be found$")
    public void Proper_retailer_should_be_found(String retailer) throws Throwable {
        AZNavi.checkRetailerResult(retailer);
    }

    // RETAILER PAGE ON WLS - add navigation and A_Z page logic (Rd-1164) - alphabetic search
    @Then("^User can use alphabetic search and it works fine$")
    public void User_can_use_alphabetic_search_and_it_works_fine() throws Throwable {
        AZNavi.checkAllRetailersForGivenLetters();
    }

    // RETAILER PAGE ON WLS - add navigation and A_Z page logic (Rd-1164) - merchant card
    @Then("^User pointing chosen retailer card can see two buttons$")
    public void User_pointing_chosen_retailer_card_can_see_two_buttons() throws Throwable {
        AZNavi.pointChosenRetailer();
    }

    @Then("^User click Info and offers button and he is redirect to retailer page$")
    public void User_click_Info_and_offers_button_and_he_is_redirect_to_retailer_page() throws Throwable {
        AZNavi.goToRetailerPage();
    }

    @Then("^Using Visit retailer button he is redirected to transition page$")
    public void Using_Visit_retailer_button_he_is_redirected_to_transition_page() throws Throwable {
        AZNavi.goToTransitionPage();
    }

    // RETAILER PAGE ON WLS - add navigation and A_Z page logic (Rd-1164) - favourite logic
    @Given("^User can not see Favourites block on Stores-AZ when he has no any favourites stores selected$")
    public void User_can_not_see_Favourites_block_on_Stores_AZ_when_he_has_no_any_favourites_stores_selected() throws Throwable {
        AZNavi.checkFavouriteStoreSectionVisibility(false);
    }

    @Then("^He is able to go to retailer page and select one store as favourite$")
    public void He_is_able_to_go_to_retailer_page_and_select_one_store_as_favourite() throws Throwable {
        AZNavi.goToChosenRetailerPageAndStoreCardElementsValues();
        AZNavi.clickAddOrRemoveToFavouritesButton(true);
    }

    @When("^User came back to AZ page he can see Your favourite stores block and selected stores$")
    public void User_came_back_to_AZ_page_he_can_see_Your_favourite_stores_block_and_selected_stores() throws Throwable {
        AZNavi.checkFavouriteStoreSectionVisibility(true);
        AZNavi.checkFavouriteBlockContent();
    }

    @When("^Information about selected store should be written in database$")
    public void Information_about_selected_store_should_be_written_in_database() throws Throwable {
        AZNavi.checkUserInterestsTableForChanges();
    }

    @Then("^User can decide to remove all stores from favourites$")
    public void User_can_decide_to_remove_all_stores_from_favourites() throws Throwable {
        AZNavi.removeStoreFromFavourites();
    }

    @Then("^Stores and favourite section will not be displayed$")
    public void Stores_and_favourite_section_will_not_be_displayed() throws Throwable {
        AZNavi.checkFavouriteStoreSectionVisibility(false);
    }

    // RETAILER PAGE ON WLS - add navigation and A_Z page logic (RD-1164) - favourite logic several products
    @Then("^User can add several retailers to his favourites$")
    public void User_can_add_several_retailers_to_his_favourites() throws Throwable {
        AZNavi.addSeveralRetailersToFavourites();
    }

    @Then("^Favourites stores carousel behave properly$")
    public void Favourites_stores_carousel_behave_properly() throws Throwable {
        AZNavi.checkContentAndBehaviourOfCarousel();
    }

    // RETAILER PAGE ON WLS - add navigation and A_Z page logic (RD-1164) - recently visited stores for not logged user
    @Then("^Not Logged user can not see recently visited stores component$")
    public void Not_Logged_user_can_not_see_recently_visited_stores_component() throws Throwable {
        AZNavi.checkIfRecentlyVisitedComponentIsEnabled(false);
    }
    
    // RETAILER PAGE ON WLS - add navigation and A_Z page logic (RD-1164) - recently visited stores for logged user
    @Then("^Logged user can see recently visited component$")
    public void Logged_user_can_see_recently_visited_component() throws Throwable {
        AZNavi.checkIfRecentlyVisitedComponentIsEnabled(true);
    }

    @Then("^User can use recently visited stores component$")
    public void User_can_use_recently_visited_stores_component() throws Throwable {
        AZNavi.goToRecentlyVisitedComponent();
        AZNavi.checkIfInsideRecentlyVisitedComponentIsAnyRetailer();
        AZNavi.openRetailerPageUsingRecentlyVisitedComponentContent();
    }
    
    // RETAILER PAGE ON WLS - add navigation and A_Z page logic (RD-1164) - department filter
    @Then("^User can see properly displayed department filter$")
    public void User_can_see_properly_displayed_department_filter() throws Throwable {
        AZNavi.checkVisibilityOfAllDepartments();
    }

    @When("^One of department will be selected$")
    public void One_of_department_will_be_selected() throws Throwable {
        AZNavi.selectOneDepartment();
    }

    @Then("^Retailers from others departments should not be displayed$")
    public void Retailers_from_others_departments_should_not_be_displayed() throws Throwable {
        AZNavi.checkIfNumberOfRetailersWasDecreased();
    }

    @And("^Letters without any content should be hidden$")
    public void Letters_without_any_content_should_be_hidden() throws Throwable {
        AZNavi.checkIfThereAreHiddenLetters();
    }

    @Then("^User want to see results for all categories again$")
    public void User_want_to_see_results_for_all_categories_again() throws Throwable {
        AZNavi.clickSeeAllButton();
    }

    @Then("^The same categories as on the beginning should be displayed$")
    public void The_same_categories_as_on_the_beginning_should_be_displayed() throws Throwable {
        AZNavi.compareNumberOfDisplayedRetailerWithNumberOnBeggining();
    }


}
