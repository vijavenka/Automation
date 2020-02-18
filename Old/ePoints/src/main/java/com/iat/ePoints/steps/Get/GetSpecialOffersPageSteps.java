package com.iat.ePoints.Steps.Get;

import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Executors.SeleniumExecutor;
import com.iat.ePoints.Navigations.Get.GetSpecialOffersPageNavigation;
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
public class GetSpecialOffersPageSteps {

    private IExecutor executor;
    private GetSpecialOffersPageNavigation specialOffersNavi;

    public GetSpecialOffersPageSteps(SeleniumExecutor executor) {
        this.executor = executor;
    }

    public void go_sleep(int milisec) throws Throwable {
        Thread.sleep(milisec);
    }

    @Before
    public void set_up() {
        executor.start();
        specialOffersNavi = new GetSpecialOffersPageNavigation(executor);
    }

    @After
    public void tear_down() {
        executor.stop();
    }

    // EPOINTS - add SPECIAL OFFERS interfaces to epoints (RD-1142) - check content of special offers page
    @Given("^Special Offers Page is Opened$")
    public void Special_Offers_Page_is_Opened() throws Throwable {
        specialOffersNavi.openSpecialOffersPage();
    }

    @When("^User look at special offers page$")
    public void User_look_at_special_offers_page() throws Throwable {
        specialOffersNavi.checkVisibilityOfPageHeader();
    }

    @Then("^He can see filter module$")
    public void He_can_see_filter_module() throws Throwable {
        specialOffersNavi.checkVisibilityOfSpecialOffersFilterModule();
    }

    @Then("^Few proposed special offers cards$")
    public void Few_proposed_special_offers_cards() throws Throwable {
        specialOffersNavi.checkVisibilityOfSpecialOffersCardAndContent();
    }

    // EPOINTS - add SPECIAL OFFERS interfaces to epoints (RD-1142) - check working of view special offer button
    @When("^User decide to see offer and click view button$")
    public void User_decide_to_see_offer_and_click_view_button() throws Throwable {
        specialOffersNavi.clickOneChosenViewSpecialOfferButton();
    }

    @Then("^Special products offer should be opened$")
    public void Special_products_offer_should_be_opened() throws Throwable {
        specialOffersNavi.checkIfOfferSearchPageWasOpened();
    }

    // EPOINTS - add SPECIAL OFFERS interfaces to epoints (RD-1142) - check working of percentage saving filter and department filter
    @When("^User chose some percentage saving range$")
    public void User_chose_some_percentage_saving_range() throws Throwable {
        specialOffersNavi.chosePercentageSavingRange();
    }

    @When("^User chose department and category$")
    public void User_chose_department_and_category() throws Throwable {
        specialOffersNavi.choseDepartmentAndCategory();
    }

    @Then("^After click show me button proper product should be displayed$")
    public void After_click_show_me_button_proper_product_should_be_displayed() throws Throwable {
        specialOffersNavi.clickShowButtonAndCheckResults();
    }

    @Then("^User can use price filter$")
    public void User_can_use_price_filter() throws Throwable {
        // All implemented in next step
    }

    @Then("^Be sure that it works correctly$")
    public void Be_sure_that_it_works_correctly() throws Throwable {
        specialOffersNavi.checkWorkingOfPriceFilter();
    }
}
