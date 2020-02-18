package com.iat.ePoints.Steps.Get;
/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 17.01.14
 * Time: 13:31
 * To change this template use File | Settings | File Templates.
 */


import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Executors.SeleniumExecutor;
import com.iat.ePoints.Navigations.Get.GetRetailerPageNavigation;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GetRetailerPageSteps {

    private IExecutor executor;
    private GetRetailerPageNavigation retailerNavi;

    public GetRetailerPageSteps(SeleniumExecutor executor) {
        this.executor = executor;
    }

    public void go_sleep(int milisec) throws Throwable {
        Thread.sleep(milisec);
    }

    @Before
    public void set_up() {
        executor.start();
        retailerNavi = new GetRetailerPageNavigation(executor);
    }

    @After
    public void tear_down() {
        executor.stop();
    }

    // Check content of retailer page //////////////////////////////////////////////////////////////////////////////////
    @Then("^Top section module has proper content$")
    public void Top_section_module_has_proper_content() throws Throwable {
        retailerNavi.checkContentOfTopSection();
    }

    @Then("^Delivery details section has proper content$")
    public void Delivery_details_section_has_proper_content() throws Throwable {
        retailerNavi.checkContentOfDeliveryDetails();
    }

    // RETAILER PAGE ON EPOINTS - Trust Pilot integration on new retailer page(RD-1158) ////////////////////////////////
    @Then("^User choose a retailer$")
    public void User_choose_a_retailer_from_main_page() throws Throwable {
        retailerNavi.openRetailerCardWithSpecialOffersAndCategoriesBlock();
    }

    @Then("^Can see that trust pilot is available$")
    public void Can_see_that_trust_pilot_is_available() throws Throwable {
        retailerNavi.checkIfTrustPilotIsAvailable();
    }

    @Then("^Trust pilot information are available in modal window$")
    public void Trust_pilot_information_are_available_in_modal_window() throws Throwable {
        retailerNavi.checkIfModalTrustPilotIsAvailable();
    }

    // RETAILER PAGE ON EPOINTS - VOUCHER block logic(RD-1160) voucher exists //////////////////////////////////////////
    @Given("^Retailer page with vouchers is opened$")
    public void Retailer_page_with_vouchers_is_opened() throws Throwable {
        retailerNavi.openRetailerPageWithPromotedVoucher();
    }

    @Then("^User can see voucher box with proper content$")
    public void User_can_see_voucher_box_with_proper_content() throws Throwable {
        retailerNavi.checkIfVoucherHasProperElementsAndContent();
    }

    @Then("^User can be redirected to retailer page$")
    public void User_can_be_redirected_to_retailer_page_to_receive_voucher_code() throws Throwable {
        retailerNavi.clickGetVoucherCodeButton();
        retailerNavi.checkIfUserWasRedirectedToRetailerPage();
    }

    @Then("^Proper voucher code is displayed on ePints page$")
    public void And_proper_voucher_Code_is_displayed_on_ePints_page() throws Throwable {
        retailerNavi.checkIfVoucherCodeIsDisplayedAndIsCorrect();
    }

    // RETAILER PAGE ON EPOINTS - VOUCHER block logic(RD-1160) voucher not exists //////////////////////////////////////
    @Given("^Retailer page without vouchers specials and categories is opened$")
    public void Retailer_page_without_vouchers_is_opened() throws Throwable {
        retailerNavi.openRetailerPageWithoutAnyVoucherSpecialsCategories();
    }

    @Then("^Voucher block should be not visible$")
    public void Voucher_block_should_be_not_visible() throws Throwable {
        retailerNavi.checkIfVoucherBlockIsInvisible();
    }

    // RETAILER PAGE ON EPOINTS - VOUCHER block logic( RD-1160) see all button
    @When("^User click see all vouchers from specific retailer$")
    public void User_click_see_all_vouchers_from_specific_retailer() throws Throwable {
        retailerNavi.clickSeeAllVouchersButton();
    }

    @Then("^He will be redirected to voucher page$")
    public void He_will_be_redirected_to_voucher_page() throws Throwable {
        retailerNavi.checkIfUserWasRedirectedToVoucherPage();
    }

    @Then("^All vouchers will belongs to specific retailer$")
    public void All_vouchers_will_belongs_to_specific_retailer() throws Throwable {
        retailerNavi.checkAllVouchersRetailerName();
    }

    // RETAILER PAGE ON EPOINTS - SPECIAL OFFER block logic (RD-1161)///////////////////////////////////////////////////
    @Then("^User choose a retailer with special offers block$")
    public void User_choose_a_retailer_with_special_offers_block() throws Throwable {
        retailerNavi.openRetailerCardWithSpecialOffersAndCategoriesBlock();
    }

    @And("^Special offers block is visible$")
    public void Special_offers_block_is_visible() throws Throwable {
        retailerNavi.checkSpecialOfferBlockVisibility();
    }

    @And("^Special offers block has proper content$")
    public void Special_offers_block_has_proper_content() throws Throwable {
        retailerNavi.checkContentOfSpecialffersBlock();
    }

    @And("^Offers are displayed in decreasing order$")
    public void Offers_are_displayed_in_decreasing_order() throws Throwable {
        retailerNavi.checkOrderOfOffer();
        retailerNavi.changeCategory();
        retailerNavi.checkOrderOfOffer();
        retailerNavi.useNavigationComponent("right");
        retailerNavi.checkOrderOfOffer();
    }

    @When("^User want to see another offer$")
    public void User_want_to_see_another_offer() throws Throwable {
        //Leave empty
    }

    @Then("^He use navigation component$")
    public void He_use_navigation_component() throws Throwable {
        retailerNavi.useNavigationComponent("right");
    }

    @And("^Different offer are displayed$")
    public void Different_offer_are_displayed() throws Throwable {
        retailerNavi.checkIfOfferWereChanged();
    }

    // RETAILER PAGE ON EPOINTS - PRODUCT CATEGORIES logic block (RD-1162) (RD-1360) ///////////////////////////////////
    @And("^Categories filter block is visible$")
    public void Department_filter_block_is_visible() throws Throwable {
        retailerNavi.checkVisibilityOfCategoriesBlock(true);
    }

    @And("^Categories filter block has proper content$")
    public void Department_filter_block_has_proper_content() throws Throwable {
        retailerNavi.checkContentOfCategoriesBlock();
    }

    // RETAILER PAGE ON EPOINTS - SIMILIAR RETAILERS block logic (Rd-1163) - special offer block available /////////////
    @Then("^User choose a retailer with similar retailers block$")
    public void User_choose_a_retailer_with_similar_retailers_block() throws Throwable {
        retailerNavi.openRetailerCardWithSpecialOffersAndCategoriesBlock();
    }

    @Then("^Similar retailers block is visible$")
    public void Similar_retailers_block_is_visible() throws Throwable {
        go_sleep(2000);
        retailerNavi.checkSimilarRetailersBlockVisibility(true);
    }

    @Then("^Similar retailers block has proper content$")
    public void Similar_retailers_block_has_proper_content() throws Throwable {
        retailerNavi.checkContentOfSimilarRetailersBlock();
    }

    @When("^User decide to see another retailer card$")
    public void User_decide_to_see_another_retailer_card() throws Throwable {
        retailerNavi.goToAnotherRetailerPage();
    }

    @Then("^He is able to be redirected to retailer page using similar retailer block$")
    public void He_is_able_to_be_redirected_to_retailer_page_using_similar_retailer_block() throws Throwable {
        retailerNavi.checkIfuserWereProperlyRedirectedToAnotherRetailerPage();
    }

    @When("^User com back to previous retailer$")
    public void User_com_back_to_previous_retailer() throws Throwable {
        executor.return_driver().navigate().back();
    }

    @When("^Decide to visit external retailer page$")
    public void Decide_to_visit_external_retailer_page() throws Throwable {
        retailerNavi.goToExternalRetailerPage();
    }

    @Then("^He is able to be redirected to retailer external page using similar retailer block$")
    public void He_is_able_to_be_redirected_to_retailer_external_page_using_similar_retailer_block() throws Throwable {
        retailerNavi.checkIfUserWereProperlyRedirectedToExternalRetailerPage();
    }

    // RETAILER PAGE ON EPOINTS - SIMILIAR RETAILERS block logic (Rd-1163) - special offer block not available /////////
    @Then("^Similar retailers block should be not visible$")
    public void Similar_retailers_block_should_be_not_visible() throws Throwable {
        retailerNavi.checkSimilarRetailersBlockVisibility(false);
    }

    // Check if show all retailer button works correctly ///////////////////////////////////////////////////////////////
    @Then("^He can see that show all retailers button is available$")
    public void He_can_see_that_show_all_retailers_button_is_available() throws Throwable {
        retailerNavi.checkVisibilityOfAllRetailersButton();
    }

    @Then("^User can use show all retailers button and be redirected to stores AZ page$")
    public void User_can_use_show_all_retailers_button_and_be_redirected_to_stores_AZ_page() throws Throwable {
        retailerNavi.clickShowAllRetailersButton();
        retailerNavi.checkIfUserWasRedirectedToAZPage();
    }
    // RETAILER PAGE ON EPOINTS - required update to special offer block (RD-1520) /////////////////////////////////////
    @Then("^User See that one hundred offers is available$")
    public void User_See_that_one_hundred_offers_is_available() throws Throwable {
        retailerNavi.checkIfProperNumberOfTabsIsAvaliable();
    }

    @Then("^All savings are below fifty percent$")
    public void All_savings_are_below_fifty_percent() throws Throwable {
        retailerNavi.goThroughAllTabsAndCheckSaving();
}

    @When("^User use see all button$")
    public void User_use_see_all_button() throws Throwable {
        retailerNavi.clickSeeAllSpecialOffersButton();
    }

    @Then("^He is redirected to offers page contains all offers$")
    public void He_is_redirected_to_offers_page_contains_all_offers() throws Throwable {
        retailerNavi.checkIfUserIsRedirectedToOffersPage();
    }

    // RETAILER PAGE ON EPOINTS - add see more category functionality to category block (RD-1360) no categories ////////
    @Then("^User can not see category block when retailer has not popular categories$")
    public void User_can_not_see_category_block_when_retailer_has_not_popular_categories() throws Throwable {
        retailerNavi.checkVisibilityOfCategoriesBlock(false);
    }

    //  Retailer page -  all products from retailer block - block should be visible case ///////////////////////////////
    @Then("^He can see all product from retailer block with products number$")
    public void He_can_see_all_product_from_retailer_block_with_products_number() throws Throwable {
        retailerNavi.checkVisibilityOfAllProductsBlock(true);
        retailerNavi.rememberProductsNumberDisplayedInAllProductsBlock();
    }

    @Then("^He can use link to see all product from retailer$")
    public void He_can_use_link_to_see_all_product_from_retailer() throws Throwable {
        retailerNavi.clickAllProductLink();
    }

    @Then("^He is redirected to proper page$")
    public void He_is_redirected_to_proper_page() throws Throwable {
        retailerNavi.checkIfUserWasRedirectedToShopPage();
    }

    @Then("^product number is the same as on retailer page block$")
    public void product_number_is_the_same_as_on_retailer_page_block() throws Throwable {
        retailerNavi.compareProductsNumberFromAllProductsBlockAndShopPage();
    }

    // Retailer page -  all products from retailer block - block should not be visible case ////////////////////////////
    @Then("^User can not see all product from retailer block on retailer page$")
    public void User_can_not_see_all_product_from_retailer_block_on_retailer_page() throws Throwable {
        retailerNavi.checkVisibilityOfAllProductsBlock(false);
    }
}
