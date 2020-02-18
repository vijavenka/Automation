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
 * Date: 29.01.14
 * Time: 09:45
 * To change this template use File | Settings | File Templates.
 */
public class GetPriceComparisonPageSteps {

    private IExecutor executor;
    private GetPriceComparisonPageNavigation priceComparisonNavi;
    private GetShopPageNavigation shopNavi;
    private GetLandingPageNavigation getNavi;



    public GetPriceComparisonPageSteps(SeleniumExecutor executor) {
        this.executor = executor;
    }

    public void go_sleep(int milisec) throws Throwable {
        Thread.sleep(milisec);
    }

    @Before
    public void set_up() {
        executor.start();
        priceComparisonNavi = new GetPriceComparisonPageNavigation(executor);
        shopNavi = new GetShopPageNavigation(executor);
        getNavi = new GetLandingPageNavigation(executor);

    }

    @After
    public void tear_down() {
        executor.stop();
    }

    // EPOINTS - extend top level navigation to include price comparison link (RD-1366) ////////////////////////////////
    @Then("^Price comparison link is visible$")
    public void Price_comparison_link_is_visible() throws Throwable {
        priceComparisonNavi.checkIfPriceComparisonLinkIsAvailable();
    }

    @When("^User use price comparison link$")
    public void User_use_price_comparison_link() throws Throwable {
        priceComparisonNavi.clickPriceComparisonLink();
    }

    @Then("^He is redirected to correct comparison page$")
    public void He_is_redirected_to_correct_comparison_page() throws Throwable {
        priceComparisonNavi.checkPriceComparisonPageTitle();
        priceComparisonNavi.checkCorrectnessOfURL();
    }

    //EPOINTS - add department level navigation and search box (including department filter) to all shop pages (RD-1144) check if /product does not contains navigation module
    @When("^Look at comparison page$")
    public void Look_at_comparison_page() throws Throwable {
        //Leave empty
    }

    @Then("^User can not see department level navigation filter$")
    public void User_can_not_see_department_level_navigation_filter() throws Throwable {
        priceComparisonNavi.checkIfDepartmentComponentIsInvisible();
    }

    // EPOINTS - create new landing page for price comparison area (products) (Rd-1143) - check content of comparison page
    @Given("^Products comparison  page is opened$")
    public void Products_comparison_page_is_opened() throws Throwable {
        shopNavi.open();
        go_sleep(4000);
        getNavi.goToEarnEPointsPage();
        priceComparisonNavi.clickPriceComparisonLink();
    }

    @When("^User look at comparison page$")
    public void user_look_at_comparison_page() throws Throwable {
        // Leave empty
    }

    @Then("^He can see that browser is available$")
    public void He_can_see_that_browser_is_available() throws Throwable {
        priceComparisonNavi.checkVisibilityOfBrowser();
    }

    @Then("^Proper Departments links are available$")
    public void Proper_Departments_links_are_available() throws Throwable {
        priceComparisonNavi.checkVisibilityOfDepartmentsLinks();
    }

    // EPOINTS - create new landing page for price comparison area (products) (Rd-1143) - check working of search
    @When("^User use search by typing some word '(.+)' and pressing search button$")
    public void User_use_search_by_typing_some_word_Batman_and_pressing_search_button(String word) throws Throwable {
        priceComparisonNavi.typeGivenWordintoSearch(word);
        priceComparisonNavi.clicSearchButton();
    }

    @Then("^He can see results on shop page contains given word '(.+)'$")
    public void He_can_see_results_on_shop_page_contains_given_word(String word) throws Throwable {
        priceComparisonNavi.checkIfUserWasRedirectedToShopPage();
        shopNavi.checkIfProperProductWereFound(word);
    }

    // EPOINTS - create new landing page for price comparison area (products) (Rd-1143) - check working of search
    @Then("^User choosing department is able to see category card with set of links$")
    public void User_choosing_department_is_able_to_see_category_card_with_set_of_links() throws Throwable {
        priceComparisonNavi.forAllDepartmentsCheckVisibilityOfCategoryCardAndLinks();
    }

}
