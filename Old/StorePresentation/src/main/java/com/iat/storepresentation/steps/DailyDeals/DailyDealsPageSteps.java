package com.iat.storepresentation.Steps.DailyDeals;

import com.iat.storepresentation.Executors.IExecutor;
import com.iat.storepresentation.Executors.SeleniumExecutor;
import com.iat.storepresentation.Navigations.DailyDeals.DailyDealsPageNavigation;
import com.iat.storepresentation.Navigations.TransitionPage.TransitionPageNavigation;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 07.03.14
 * Time: 16:06
 * To change this template use File | Settings | File Templates.
 */
public class DailyDealsPageSteps {

    private IExecutor executor;
    private TransitionPageNavigation transitionNavi;
    private DailyDealsPageNavigation dealsNavi;


    public DailyDealsPageSteps(SeleniumExecutor executor) {
        this.executor = executor;
    }

    public void go_sleep(int milisec) throws Throwable {
        Thread.sleep(milisec);
    }

    @Before
    public void set_up() {
        executor.start();
        transitionNavi = new TransitionPageNavigation(executor);
        dealsNavi = new DailyDealsPageNavigation(executor);
    }

    @After
    public void tear_down() {
        executor.stop();
    }

    // FRONT END REFACTOR - add epoints search solution to WLS Daily Deals pages (RD-2098) - check content of all daily deals
    @Given("^Daily deals page is opened$")
    public void Daily_deals_page_is_opened() throws Throwable {
        dealsNavi.openDailyDealsPage(true);
    }

    @When("^User look at daily deals Page$")
    public void User_look_at_daily_deals_Page() throws Throwable {
        // Leave empty
    }

    @Then("^All daily deals has all needed elements$")
    public void All_daily_deals_has_all_needed_elements() throws Throwable {
        dealsNavi.forAllDailyDealsCheckElementsLikeExpiryDatePriceETC();
    }

    // FRONT END REFACTOR - add epoints search solution to WLS Daily Deals pages (RD-2098) - check possibility of buying daily deals and check reported clickout
    @When("^User want to buy some daily deal$")
    public void User_want_to_buy_some_daily_deal() throws Throwable {
        // Leave empty
    }

    @Then("^He Click get this deal button$")
    public void He_Click_get_this_deal_button() throws Throwable {
        dealsNavi.choseSomeDailyDealsAndClickGetThisDealButton();
    }

    @When("^Click continue anyway option$")
    public void Click_continue_anyway_option() throws Throwable {
        dealsNavi.rememberMerchantName();
        dealsNavi.clickContinueAnywayInTransitionPage();
    }

    @Then("^New clickout should be reported '(.+)' '(.+)' from daily deals page$")
    public void New_clickout_should_be_reported_from_daily_deals_page(String ifSigned, String Email) throws Throwable {
        dealsNavi.checkIfClickoutWasReported(ifSigned, "emailwybitnietestowy@gmail.com");
    }

    // FRONT END REFACTOR - add epoints search solution to WLS Daily Deals pages (RD-2098) - location from home page
    @When("^User chose daily deals location from DDL$")
    public void User_chose_daily_deals_location_from_DDL() throws Throwable {
        dealsNavi.selectSpecifiedLocation();
    }

    @Then("^He Will be redirected to daily deal page$")
    public void He_Will_be_redirected_to_daily_deal_page() throws Throwable {
        dealsNavi.checkIfDailyDealsPageWasOpened();
    }

    @Then("^Proper location will be applied for search results$")
    public void Proper_location_will_be_applied_for_search_results() throws Throwable {
        dealsNavi.checkIfLocationWasAppliedToResults();
    }

    // FRONT END REFACTOR - add epoints search solution to WLS Daily Deals pages (RD-2098) - location from popup window
    @Given("^Facets popup window is opened$")
    public void Facets_popup_window_is_opened() throws Throwable {
        dealsNavi.openFacetsPopupWindow();
    }

    @When("^User chose daily deals location from popup window$")
    public void User_chose_daily_deals_location_from_popup_window() throws Throwable {
        dealsNavi.selectSpecifiedLocationFromPopupWindow();
    }

    @When("^Confirmed settings using done button$")
    public void Confirmed_settings_using_done_button() throws Throwable {
        dealsNavi.clickDoneButton();
    }

    // FRONT END REFACTOR - add epoints search solution to WLS Daily Deals pages (RD-2098) - check individual daily deals page content
    @When("^User chose some of daily deals and click on it$")
    public void User_chose_some_of_daily_deals_and_click_on_it() throws Throwable {
        dealsNavi.chooseOneOfDailyDeals();
    }

    @Then("^Individual daily deal page should be opened$")
    public void Individual_daily_deal_page_should_be_opened() throws Throwable {
        dealsNavi.checkIfIndividualDealPageWasOpened();
    }

    @Then("^User can see that it has proper content and proper values$")
    public void User_can_see_that_it_has_proper_content_and_proper_values() throws Throwable {
        dealsNavi.checkVisibilityOfDealsIndividualPageAndItsValues();
    }

    @When("^User decide to back to previous page$")
    public void User_decide_to_back_to_previous_page() throws Throwable {
        // Leave empty
    }

    @Then("^He can use back to previous page button and he will be redirected to it$")
    public void He_can_use_back_to_previous_page_button_and_he_will_be_redirected_to_it() throws Throwable {
        dealsNavi.clickBackToPreviousPageButton();
        dealsNavi.checkIfUserWasCorrectlyRedirectedToPreviousPage();
    }

    // FRONT END REFACTOR - add epoints search solution to WLS Daily Deals pages (RD-2098) - check working of clickout from individual daily deal page
    @When("^Decide to get deal, then he should click get button$")
    public void Decide_to_get_deal_then_he_should_click_get_button() throws Throwable {
        dealsNavi.clickGetDealButton();
    }

    // FRONT END REFACTOR - add epoints search solution to WLS Daily Deals pages (RD-2098) - check link to retailer from individual daily deal page
    @When("^Used decide to go to deal retailer page$")
    public void Used_decide_to_go_to_deal_retailer_page() throws Throwable {
        // Leave empty
    }

    @Then("^He should click retailer link$")
    public void He_should_click_retailer_link() throws Throwable {
        dealsNavi.clickRetailerLink();
    }

    @Then("^Proper retailer page should be opened$")
    public void Proper_retailer_page_should_be_opened() throws Throwable {
        dealsNavi.checkIfProperRetailerPageWasOpened();
    }
    
    // FRONT END REFACTOR - add epoints search solution to WLS Daily Deals pages (RD-2098) - - epoints range, price, saving slider
    @When("^User use slider to set epoints earned range value$")
    public void User_use_slider_to_set_epoints_earned_range_value() throws Throwable {
        dealsNavi.setEpointsEarnedSliderRange();
    }

    @When("^User use slider to set price range value$")
    public void User_use_slider_to_set_price_range_value() throws Throwable {
        dealsNavi.setPriceSliderRange();
    }

    @When("^User use slider to set saving range value$")
    public void User_user_slider_to_set_saving_range_value() throws Throwable {
        dealsNavi.setSavingSliderRange();
    }

    @When("^User admit filter changes$")
    public void User_admit_filter_changes() throws Throwable {
        dealsNavi.clickDoneButtonOnFAcetsPopupWindow();
    }

    @Then("^He can see that presented product match those criteria$")
    public void He_can_see_that_presented_product_match_those_criteria() throws Throwable {
        dealsNavi.checkCorrectnesOfallFeaturesValue();
    }
}
