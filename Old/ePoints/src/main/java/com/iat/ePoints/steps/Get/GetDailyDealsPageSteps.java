package com.iat.ePoints.Steps.Get;

import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Executors.SeleniumExecutor;
import com.iat.ePoints.Navigations.Get.GetDailyDealsPageNavigation;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 12.02.14
 * Time: 15:05
 * To change this template use File | Settings | File Templates.
 */
public class GetDailyDealsPageSteps {

    private IExecutor executor;
    private GetDailyDealsPageNavigation dailyDealsNavi;

    public GetDailyDealsPageSteps(SeleniumExecutor executor) {
        this.executor = executor;
    }

    public void go_sleep(int milisec) throws Throwable {
        Thread.sleep(milisec);
    }

    @Before
    public void set_up() {
        executor.start();
        dailyDealsNavi = new GetDailyDealsPageNavigation(executor);
    }

    @After
    public void tear_down() {
        executor.stop();
    }

    // EPOINTS - add DAILY DEALS list interfaces to epoints.com (RD-1141) - check content of all daily deals
    @Given("^Daily deals page is opened$")
      public void Daily_deals_page_is_opened() throws Throwable {
        dailyDealsNavi.openDailyDealsPage(true);
    }

    @When("^User look at daily deals Page$")
    public void User_look_at_daily_deals_Page() throws Throwable {
        // Leave empty
    }

    @Then("^All daily deals has all needed elements$")
    public void All_daily_deals_has_all_needed_elements() throws Throwable {
        dailyDealsNavi.forAllDailyDealsCheckElementsLikeExpiryDatePriceETC();
    }

    // EPOINTS - add DAILY DEALS list interfaces to epoints.com (RD-1141) - check possibility of buying daily deals and check reported clickout
    @When("^User want to buy some daily deal$")
    public void User_want_to_buy_some_daily_deal() throws Throwable {
        // Leave empty
    }

    @Then("^He Click get this deal button$")
    public void He_Click_get_this_deal_button() throws Throwable {
        dailyDealsNavi.choseSomeDailyDealsAndClickGetThisDealButton();
    }

    /*@When("^Click continue anyway option$")
    public void Click_continue_anyway_option() throws Throwable {
        dailyDealsNavi.rememberMerchantName();
        dailyDealsNavi.clickContinueAnywayInTransitionPage();
    }*/

    @Then("^New clickout should be reported '(.+)' '(.+)' from daily deals page$")
    public void New_clickout_should_be_reported_from_daily_deals_page(String ifSigned, String Email) throws Throwable {
        dailyDealsNavi.checkIfClickoutWasReported(ifSigned, "emailwybitnietestowy@gmail.com");
    }

    // EPOINTS - add DAILY DEALS list interfaces to epoints.com (RD-1141) - epoints range, price, saving slider
    @Given("^Facets popup window is opened$")
    public void Facets_popup_window_is_opened() throws Throwable {
        dailyDealsNavi.openPopupFacetsWindowUsingSeeAllButton();
    }

    @When("^User use slider to set epoints earned range value$")
    public void User_use_slider_to_set_epoints_earned_range_value() throws Throwable {
        dailyDealsNavi.setEpointsEarnedSliderRange();
    }

    @When("^User use slider to set price range value$")
    public void User_use_slider_to_set_price_range_value() throws Throwable {
        dailyDealsNavi.setPriceSliderRange();
    }

    @When("^User use slider to set saving range value$")
    public void User_user_slider_to_set_saving_range_value() throws Throwable {
        dailyDealsNavi.setSavingSliderRange();
    }

    @When("^User admit filter changes$")
    public void User_admit_filter_changes() throws Throwable {
        dailyDealsNavi.clickDoneButtonOnFAcetsPopupWindow();
    }

    @Then("^He can see that presented product match those criteria$")
    public void He_can_see_that_presented_product_match_those_criteria() throws Throwable {
        dailyDealsNavi.checkCorrectnesOfallFeaturesValue();
    }

    // DAILY DEALS - ensure proper use of location filter on epoints (RD-1151) - check showing of modal windows with locations
    @When("^User look at daily deals page$")
    public void User_look_at_daily_deals_page() throws Throwable {
        //Leave Empty
    }

    @Then("^He can see modal location window$")
    public void He_can_see_modal_location_window() throws Throwable {
        dailyDealsNavi.checkVisibilityOfLocationModalWindow();
    }

    @Then("^Modal location window has proper content$")
    public void Modal_location_window_has_proper_content() throws Throwable {
        dailyDealsNavi.checkContentOfLocationModalWindow();
    }

    // DAILY DEALS - ensure proper use of location filter on epoints (RD-1151) - check working of change location button
    @Given("^Daily deals page is opened without closing location window$")
    public void Daily_deals_page_is_opened_without_closing_location_window() throws Throwable {
        dailyDealsNavi.openDailyDealsPage(false);
    }

    @When("^User close modal location window$")
    public void User_close_modal_location_window() throws Throwable {
        dailyDealsNavi.closeLocationModalWindow();
    }

    @Then("^He is able to change location using change location button$")
    public void He_is_able_to_change_location_using_change_location_button() throws Throwable {
        dailyDealsNavi.changeLocationAndCheckCorrectnessOfSendCookies();
    }

    // EPOINTS - add DAILY DEALS individual product interface to epoints (RD-1795) - check page content
    @When("^User chose some of daily deals and click on it$")
    public void User_chose_some_of_daily_deals_and_click_on_it() throws Throwable {
        dailyDealsNavi.chooseOneOfDailyDeals();
    }

    @Then("^Individual daily deal page should be opened$")
    public void Individual_daily_deal_page_should_be_opened() throws Throwable {
        dailyDealsNavi.checkIfIndividualDealPageWasOpened();
    }

    @Then("^User can see that it has proper content and proper values$")
    public void User_can_see_that_it_has_proper_content_and_proper_values() throws Throwable {
        dailyDealsNavi.checkVisibilityOfDealsIndividualPageAndItsValues();
    }

    @When("^User decide to back to previous page$")
    public void User_decide_to_back_to_previous_page() throws Throwable {
        // Leave empty
    }

    @Then("^He can use back to previous page button and he will be redirected to it$")
    public void He_can_use_back_to_previous_page_button_and_he_will_be_redirected_to_it() throws Throwable {
        dailyDealsNavi.clickBackToPreviousPageButton();
        dailyDealsNavi.checkIfUserWasCorrectlyRedirectedToPreviousPage();
    }

    // EPOINTS - add DAILY DEALS individual product interface to epoints (RD-1795) - check working of clickout
    @When("^Decide to get deal, then he should click get button$")
    public void Decide_to_get_deal_then_he_should_click_get_button() throws Throwable {
        dailyDealsNavi.clickGetDealButton();
    }

    // EPOINTS - add DAILY DEALS individual product interface to epoints (RD-1795) - check link to retailer
    @When("^Used decide to go to deal retailer page$")
    public void Used_decide_to_go_to_deal_retailer_page() throws Throwable {
        // Leave empty
    }

    @Then("^He should click retailer link$")
    public void He_should_click_retailer_link() throws Throwable {
        dailyDealsNavi.clickRetailerLink();
    }

    @Then("^Proper retailer page should be opened$")
    public void Proper_retailer_page_should_be_opened() throws Throwable {
        dailyDealsNavi.checkIfProperRetailerPageWasOpened();
    }
}
