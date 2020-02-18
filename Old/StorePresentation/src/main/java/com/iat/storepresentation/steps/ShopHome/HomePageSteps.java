package com.iat.storepresentation.Steps.ShopHome;

import com.iat.storepresentation.Executors.IExecutor;
import com.iat.storepresentation.Executors.SeleniumExecutor;
import com.iat.storepresentation.Navigations.ShopHome.HomePageNavigation;
import com.iat.storepresentation.Navigations.ShopHome.IndividualProductPage.IndividualProductPageNavigation;
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
 * Date: 06.03.14
 * Time: 14:36
 * To change this template use File | Settings | File Templates.
 */

public class HomePageSteps {

        private IExecutor executor;
        private HomePageNavigation homeNavi;
        private TransitionPageNavigation transitionNavi;
        private IndividualProductPageNavigation indProductPageNavi;

        public HomePageSteps(SeleniumExecutor executor) {
            this.executor = executor;
        }

        public void go_sleep(int milisec) throws Throwable {
            Thread.sleep(milisec);
        }

        @Before
        public void set_up() {
            executor.start();
            homeNavi = new HomePageNavigation(executor);
            transitionNavi = new TransitionPageNavigation(executor);
            indProductPageNavi = new IndividualProductPageNavigation(executor);
        }

        @After
        public void tear_down() {
            executor.stop();
        }

    // FRONT END REFACTOR - add epoints search solution to WLS Home pages (Rd-2112) - content of product cards
    @Given("^Not registered user open WLS page$")
    public void Not_registered_user_open_WLS_page() throws Throwable {
        homeNavi.open();
    }

    @When("^User look at home page$")
    public void User_look_at_home_page() throws Throwable {
        // Leave empty
    }

    @When("^Viewing products he can see that product cards have all needed elements$")
    public void Viewing_products_he_can_see_that_product_cards_have_all_needed_elements() throws Throwable {
      homeNavi.viewAllProductOnCurrentPageAndCheckTheirContent();
    }

    // FRONT END REFACTOR - add epoints search solution to WLS Home pages (Rd-2112) - split view
    @When("^User want to see split view of each product$")
    public void User_want_to_see_split_view_of_each_product() throws Throwable {
        // Leave empty, all navigaton Steps will be implemented in next step
    }

    @Then("^He can see that split view is visible and has proper content$")
    public void He_can_see_that_split_view_is_visible_and_has_proper_content() throws Throwable {
        homeNavi.checkContentOfEachProductSplitView();
    }

    // FRONT END REFACTOR - add epoints search solution to WLS Home pages (Rd-2112) - clickout
    @When("^User decide to buy product$")
    public void User_decide_to_buy_product() throws Throwable {
        // leave empty
    }

    @Then("^He Should click Buy button$")
    public void He_Should_click_Buy_button() throws Throwable {
        homeNavi.clickBuyButtonOfChosenMerchant(false);
    }

    @When("^Transition page will be opened$")
    public void Transition_page_will_be_opened() throws Throwable {
       transitionNavi.checkIfTransitionPageWasOpened();
    }

    @Then("^Click in continue anyway option$")
    public void Click_in_continue_anyway_option() throws Throwable {
        transitionNavi.clickContinueAnywayButton();
    }

    @Then("^Transition page should be replaced with retailer page$")
    public void Transition_page_should_be_replaced_with_retailer_page() throws Throwable {
        transitionNavi.checkIfTransitionPageIsInvisible();
    }

    @Then("^New clickout should be reported '(.+)' '(.+)'$")
    public void New_clickout_should_be_reported_from_home_page(String ifSigned, String Email) throws Throwable {
        homeNavi.checkIfClickoutWasReported(ifSigned, Email, "null");
    }

    // FRONT END REFACTOR - add epoints search solution to WLS Home pages (Rd-2112) - clickout from split view
    @When("^User decide to buy product from split view$")
    public void User_decide_to_buy_product_from_split_view() throws Throwable {
        // leave empty
    }

    @Then("^He Should click Buy button on split view$")
    public void He_Should_click_Buy_button_on_split_view() throws Throwable {
        homeNavi.clickBuyButtonOfChosenMerchant(true);
    }

    // FRONT END REFACTOR - add epoints search solution to WLS Home pages (Rd-2112) - product individual page compare with product card
    @When("^User open product page of chosen product$")
    public void User_open_product_page_of_chosen_product() throws Throwable {
        indProductPageNavi.saveNamePriceEpointsOfChosenProduct();
        indProductPageNavi.openProductPageOfChosenProduct();
    }

    @Then("^He can see that all product page elements are available$")
    public void He_can_see_that_all_product_page_elements_are_available() throws Throwable {
        indProductPageNavi.checkVisibilityOfAllProductPageElements();
    }

    @Then("^Values like name, prise, epoints are the same$")
    public void Values_like_name_prise_epoints_are_the_same() throws Throwable {
        indProductPageNavi.compareIndividualProductValuesFromProductCard();
    }

    @When("^User decide to back to previous page and use back to previous page button$")
    public void User_decide_to_back_to_previous_page_and_use_back_to_previous_page_button() throws Throwable {
        indProductPageNavi.clickBackToPreviousPageButton();
    }

    @Then("^He will be redirect to home page$")
    public void He_will_be_redirect_to_price_home_page() throws Throwable {
        homeNavi.checkIfUserWasCorrectlyRedirectedToHomePage();
    }

    // FRONT END REFACTOR - add epoints search solution to WLS Home pages (Rd-2112) - clickout from individual product page
    @And("^Click buy product on individual product page$")
    public void Click_buy_product_on_individual_product_page() throws Throwable{
        indProductPageNavi.clickBuyButtonOnIndividualproductPage();
    }

    @Then("^New clickout should be reported '(.+)' '(.+)' from individual product page$")
    public void New_clickout_should_be_reported_user_sign_out_user_sign_out_from_shop_page(String ifSigned, String Email) throws Throwable {
        indProductPageNavi.checkIfClickoutWasReported(ifSigned, Email);
    }

}

