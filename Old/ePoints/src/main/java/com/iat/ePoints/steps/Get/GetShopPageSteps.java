package com.iat.ePoints.Steps.Get;

import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Executors.SeleniumExecutor;
import com.iat.ePoints.Navigations.Get.GetLandingPageNavigation;
import com.iat.ePoints.Navigations.Get.GetPriceComparisonPageNavigation;
import com.iat.ePoints.Navigations.Get.GetShopPageNavigation;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 31.01.14
 * Time: 14:51
 * To change this template use File | Settings | File Templates.
 */
public class GetShopPageSteps {

    private IExecutor executor;
    private GetShopPageNavigation shopNavi;
    private GetPriceComparisonPageNavigation comparisionNavi;
    private GetLandingPageNavigation getNavi;

    public GetShopPageSteps(SeleniumExecutor executor) {
        this.executor = executor;
    }

    public void go_sleep(int milisec) throws Throwable {
        Thread.sleep(milisec);
    }

    @Before
    public void set_up() {
        executor.start();
        shopNavi = new GetShopPageNavigation(executor);
        comparisionNavi = new GetPriceComparisonPageNavigation(executor);
        getNavi = new GetLandingPageNavigation(executor);

    }

    @After
    public void tear_down() {
        executor.stop();
    }

    //EPOINTS - add department level navigation and search box (including department filter) to all shop pages (RD-1144) - check department navigation visibility
    @When("^User choose some department$")
    public void User_choose_some_department() throws Throwable {
        shopNavi.chooseSomeDepartmenIcon();
    }

    @Then("^Department navigation component is available$")
    public void Department_navigation_component_is_available() throws Throwable {
        shopNavi.checkIfDepartmentNavigationIsAvaliable();
    }

    //EPOINTS - add department level navigation and search box (including department filter) to all shop pages (RD-1144) - check if department navigation has proper content
    @Given("^Shop page is opened$")
    public void Shop_page_is_opened() throws Throwable {
        shopNavi.open();
        go_sleep(4000);
        getNavi.goToEarnEPointsPage();
        comparisionNavi.clickPriceComparisonLink();
        shopNavi.chooseSomeDepartmenIcon();
    }

    @Then("^Department left drop down is available and has proper content$")
    public void Department_left_drop_down_is_available_and_has_proper_content() throws Throwable {
        shopNavi.checkContentOfLeftDDL();
    }

    @Then("^Search department component is available$")
    public void Search_department_component_is_available() throws Throwable {
        shopNavi.checkContenOfSearch();
    }

    @Then("^Department right drop dow list is available$")
    public void Department_right_drop_dow_list_is_available() throws Throwable {
        shopNavi.checkContentOfRightDDL();
    }

    // EPOINTS - add department level navigation and search box (including department filter) to all shop pages (RD-1144) - check if search works correctly
    @When("^User type some phrase '(.+)' into the searcher$")
    public void User_type_some_phrase_Batman_into_the_searcher(String phrase) throws Throwable {
        shopNavi.searchNeededPhrase(phrase);
    }

    @Then("^He can see that results are correct according to given word '(.+)'$")
    public void He_can_see_that_results_are_correct_according_to_given_word(String phrase) throws Throwable {
        shopNavi.checkIfProperProductWereFound(phrase);
    }

    // EPOINTS - add department level navigation and search box (including department filter) to all shop pages (RD-1144) - check if department filter works
    @When("^Change department and try to search once again$")
    public void Change_department_and_try_to_search_once_again() throws Throwable {
        shopNavi.changeSearchDepartment();
    }

    @Then("^Different product should be presented according to department$")
    public void Different_product_should_be_presented_according_to_department() throws Throwable {
        shopNavi.checkIfDisplayedProductWereChanged();
    }

    // EPOINTS - add points to redeem display to product interfaces (RD-1152) - check visibility and working of links #we search to find some product with redeeem display
    @Then("^He can see products with redeem price$")
    public void He_can_see_products_with_redeem_price() throws Throwable {
        shopNavi.checkIfSomeRedeemProductIsVisible();
    }

    @Then("^Link on product tab works correct$")
    public void Link_on_product_tab_works_correct() throws Throwable {
        shopNavi.useRedeemLinkOnProductCard();
        shopNavi.checkCorrectnessOfOpenedProductPage();

    }

    @Then("^Link on split view works correct$")
    public void Link_on_split_view_works_correct() throws Throwable {
        shopNavi.clickOnProductToShowSplitView();
        shopNavi.useRedeemLinkOnProductSplitView();
        shopNavi.checkCorrectnessOfOpenedProductPage();
    }

    @When("^User open individual product shop page$")
    public void User_open_individual_product_shop_page() throws Throwable {
        shopNavi.clickOnProductToShowSplitView();
        shopNavi.goToIndividualShopProductPage();
    }

    @Then("^He can see two redeem links$")
    public void He_can_see_two_redeem_links() throws Throwable {
        shopNavi.checkVisibilityOfRedeemLinksOnShopIndyvidualProductPage();
    }

    @Then("^Both redeem links works correctly$")
    public void Both_redeem_links_works_correctly() throws Throwable {
        shopNavi.checkWorkingOfTwoRedeemLinksOnShopProductPage();
    }

    // EPOINTS - add DEPARTMENT interfaces to epoints.com (RD-1154) - Department page availability from comparison page
    @Then("^He is able to choose some department$")
    public void He_is_able_to_choose_some_department() throws Throwable {
        shopNavi.chooseSomeDepartmentAndRememberItsData();
    }

    @Then("^Open department page using link in popup menu$")
    public void Open_department_page_using_link_in_popup_menu() throws Throwable {
        shopNavi.checkIfCorrectDepartmentPageWasOpened(true);
    }

    @Then("^User can see categories list and categories circles$")
    public void User_can_see_categories_list_and_categories_circles() throws Throwable {
        shopNavi.checkVisibilityOSearchComponentOnDepartmentCategoryView();
        shopNavi.checIfCategoriesOnDepartmentPageAreDisplayedCorrectly();
    }

    // EPOINTS - add DEPARTMENT interfaces to epoints.com (RD-1154) - Department page availability from shop page
    @Then("^He is able to choose some department from DDL$")
    public void He_is_able_to_choose_some_department_from_DDL() throws Throwable {
        shopNavi.chooseCategoryFromDepartmentsDDL();
    }

    @Then("^Open department page using link from DDL$")
    public void Open_department_page_using_link_from_DDL() throws Throwable {
        shopNavi.checkIfCorrectDepartmentPageWasOpened(false);
    }

    // EPOINTS - add DEPARTMENT interfaces to epoints.com (RD-1154) - second level of department
    @When("^User chose one of categories$")
    public void User_chose_one_of_categories() throws Throwable {
        shopNavi.choseOneOfSubDepartment();
    }

    @Then("^He will be redirected to second level department page or shop page$")
    public void He_will_be_redirected_to_second_level_department_page_or_shop_page() throws Throwable {
        shopNavi.checkWhichPathWasSetAndCheckContentOfPageAndTheirName();
    }

    // EPOINTS - add PRODUCT PAGE interface to epoints.com (RD-1146) - product page availability from comparison page
    @When("^User open product page of chosen product$")
    public void User_open_product_page_of_chosen_product() throws Throwable {
        shopNavi.saveNamePriceEpointsOfChosenProduct();
        shopNavi.openProductPageOfChosenProduct();
    }

    @Then("^He can see that all product page elements are available$")
    public void He_can_see_that_all_product_page_elements_are_available() throws Throwable {
        shopNavi.checkVisibilityOfAllProductPageElements();
    }

    @Then("^Values like name, prise, epoints are the same$")
    public void Values_like_name_prise_epoints_are_the_same() throws Throwable {
        shopNavi.compareIndividualProductValuesFromProductCard();
    }

    @When("^User decide to back to previous page and use back to previous page button$")
    public void User_decide_to_back_to_previous_page_and_use_back_to_previous_page_button() throws Throwable {
        shopNavi.clickBackToPreviousPageButton();
    }

    @Then("^He will be redirect to price comparison page$")
    public void He_will_be_redirect_to_price_comparison_page() throws Throwable {
        shopNavi.checkIfUserWasCorrectlyRedirectedToComparisonPage();
    }

    // EPOINTS - add PRODUCT PAGE interface to epoints.com (RD-1146) - product page availability from retailer page
    @When("^User open product page of chosen product from special offers block$")
    public void User_open_product_page_of_chosen_product_from_special_offers_block() throws Throwable {
        shopNavi.saveNamePriceEpointsOfChosenProductFromSpecialOfferBlock();
        shopNavi.openProductPageOfChosenProductFomSpecialOfferBlock();
    }

    @Then("^He will be redirect to retailer page of the same retailer$")
    public void He_will_be_redirect_to_retailer_page_of_the_same_retailer() throws Throwable {
        shopNavi.checkIfUserWasCorrectlyRedirectedToRetailerPage();
    }

    // EPOINTS - add PRODUCT PAGE interface to epoints.com (RD-1146) - check working of retailer link and buy button
    @Then("^He Can use link to retailer page$")
    public void He_Can_use_link_to_retailer_page() throws Throwable {
        shopNavi.clickRetailerLinkonProductPage();
    }

    @Then("^Retailer link works correctly$")
    public void Retailer_link_works_correctly() throws Throwable {
        shopNavi.checkIfUserWasCorrectlyRedirectedToRetailerPage();
    }

    @When("^He decide to go back to product page and buy product$")
    public void He_decide_to_go_back_to_product_page_and_buy_product() throws Throwable {
        shopNavi.returnToProductPageAndClickBuyButton();
    }

    // EPOINTS - add CATEGORY interfaces to epoints.com (RD-1145 )- category reached form retailer page category block
    @When("^User choose some category in category block$")
    public void User_choose_some_category_in_category_block() throws Throwable {
        shopNavi.openCategoryPageWithProductInIt();
    }

    @Then("^He will be redirected to correct category page$")
    public void He_will_be_redirected_to_correct_category_page() throws Throwable {
        shopNavi.checkIfCorrectCategoryPageWasOpened();
    }

    @Then("^At the end category page has all needed search, products and  pagination elements$")
    public void At_the_end_category_page_has_all_needed_search_products_and_pagination_elements() throws Throwable {
        shopNavi.checkContentOfCategoryPage();
    }

    // EPOINTS - add SEARCH RESULTS interfaces to epoints.com (RD-1153) - content of product cards
    @When("^User look at shop page$")
    public void User_look_at_shop_page() throws Throwable {
        // Leave empty
    }

    @When("^Viewing products he can see that product cards have all needed elements$")
    public void Viewing_products_he_can_see_that_product_cards_have_all_needed_elements() throws Throwable {
        shopNavi.viewAllProductOnCurrentPageAndCheckTheirContent();
    }

    // EPOINTS - add SEARCH RESULTS interfaces to epoints.com (RD-1153) - split view
    @When("^User want to see split view of each product$")
    public void User_want_to_see_split_view_of_each_product() throws Throwable {
        // Leave empty, all navigaton Steps will be implemented in next step
    }

    @Then("^He can see that split view is visible and has proper content$")
    public void He_can_see_that_split_view_is_visible_and_has_proper_content() throws Throwable {
        shopNavi.checkContentOfEachProductSplitView();
    }

    // EPOINTS - add SEARCH RESULTS interfaces to epoints.com (RD-1153) - facet component
    @Then("^He can see facet component$")
    public void He_can_see_facet_component() throws Throwable {
        shopNavi.checkVisibilityOfFacetComponent();
    }

    @Then("^Facet component can be used$")
    public void Facet_component_can_be_used() throws Throwable {
        shopNavi.useFacetComponent();
    }

    @When("^User use clear all filters button$")
    public void User_use_clear_all_filters_button() throws Throwable {
        shopNavi.clickClearAllFilterButton();
    }

    @Then("^All filters should be cleared$")
    public void All_filters_should_be_cleared() throws Throwable {
        shopNavi.checkIfFiltersWereDisabled();
    }

    // EPOINTS - add SEARCH RESULTS interfaces to epoints.com (RD-1153) - facet popup
    @When("^User use see all facets option$")
    public void User_use_see_all_facets_option() throws Throwable {
        shopNavi.clickSeeeAllFacetsButton();
    }

    @Then("^Popup with proper filters will be showed$")
    public void Popup_with_proper_filters_will_be_showed() throws Throwable {
        shopNavi.checkVisibilityOfFacetsPopup();
        shopNavi.checkCorrectnessOfFacetCategories();
    }

    @Then("^User can choose some filter$")
    public void User_can_choose_some_filter() throws Throwable {
        shopNavi.choseFilterOption();
    }

    @Then("^Filter should works$")
    public void Filter_should_works() throws Throwable {
        shopNavi.checkIfFilterIsEnabled();
    }

    // EPOINTS - add SEARCH RESULTS interfaces to epoints.com (RD-1153) - clickout from product card
    @When("^User decide to buy product and go to retailer page$")
    public void User_decide_to_buy_product_and_go_to_retailer_page() throws Throwable {
        // Leave empty
    }

    @Then("^He Should click Buy button$")
    public void He_Should_click_Buy_button() throws Throwable {
        shopNavi.clickBuyButtonOfChosenMerchant();
    }

    @When("^Transition page will be opened$")
    public void Transition_page_will_be_opened() throws Throwable {
        // Leave empty
    }

    @Then("^New clickout should be reported '(.+)' '(.+)' from shop page$")
    public void New_clickout_should_be_reported(String ifSigned, String Email) throws Throwable {
        shopNavi.checkIfClickoutWasReported(ifSigned, Email);
    }

    // EPOINTS - extend top level navigation to include shop links (RD-1139)
    @Then("^He can see vouchers daily deals and special offers additional links$")
    public void He_can_see_vouchers_daily_deals_and_special_offers_additional_links() throws Throwable {
        shopNavi.checkVisibilityOfAdditionalShopLinks();
    }

    @Then("^User can use them and see that they works properly$")
    public void User_can_use_them_and_see_that_they_works_properly() throws Throwable {
        shopNavi.useAllAdditionalLinkAndChekkIfTheyWorks();
    }

}
