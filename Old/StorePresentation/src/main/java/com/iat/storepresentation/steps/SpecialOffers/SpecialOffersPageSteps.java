package com.iat.storepresentation.Steps.SpecialOffers;

import com.iat.storepresentation.Executors.IExecutor;
import com.iat.storepresentation.Executors.SeleniumExecutor;
import com.iat.storepresentation.Navigations.SpecialOffers.SpecialOffersPageNavigation;
import com.iat.storepresentation.Navigations.TransitionPage.TransitionPageNavigation;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 08.03.14
 * Time: 09:41
 * To change this template use File | Settings | File Templates.
 */
public class SpecialOffersPageSteps {

    private IExecutor executor;
    private TransitionPageNavigation transitionNavi;
    private SpecialOffersPageNavigation specialOffersNavi;

    public SpecialOffersPageSteps(SeleniumExecutor executor) {
        this.executor = executor;
    }

    public void go_sleep(int milisec) throws Throwable {
        Thread.sleep(milisec);
    }

    @Before
    public void set_up() {
        executor.start();
        transitionNavi = new TransitionPageNavigation(executor);
        specialOffersNavi = new SpecialOffersPageNavigation(executor);
    }

    @After
    public void tear_down() {
        executor.stop();
    }

    // FRONT END REFACTOR - add epoints search solution to WLS Special Offers pages (RD-2105) - check content of special offers page
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

    // FRONT END REFACTOR - add epoints search solution to WLS Special Offers pages (RD-2105) - check working of view special offer button
    @When("^User decide to see offer and click view button$")
    public void User_decide_to_see_offer_and_click_view_button() throws Throwable {
        specialOffersNavi.clickOneChosenViewSpecialOfferButton();
    }

    @Then("^Special products offer should be opened$")
    public void Special_products_offer_should_be_opened() throws Throwable {
        specialOffersNavi.checkIfOfferSearchPageWasOpened();
    }

    // FRONT END REFACTOR - add epoints search solution to WLS Special Offers pages (RD-2105) - check working of percentage saving filter, department filter and price range
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
}
