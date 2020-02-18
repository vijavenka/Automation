package com.iat.ePoints.Steps.Get;

import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Executors.SeleniumExecutor;
import com.iat.ePoints.Navigations.Get.GetVouchersPageNavigation;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
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
public class GetVouchersPageSteps {

    private IExecutor executor;
    private GetVouchersPageNavigation voucherNavi;

    public GetVouchersPageSteps(SeleniumExecutor executor) {
        this.executor = executor;
    }

    public void go_sleep(int milisec) throws Throwable {
        Thread.sleep(milisec);
    }

    @Before
    public void set_up() {
        executor.start();
        voucherNavi = new GetVouchersPageNavigation(executor);
    }

    @After
    public void tear_down() {
        executor.stop();
    }
    // EPOINTS - add VOUCHERS interfaces to epoints.com (RD-1140) - check content of Voucher Page
    @Given("^Voucher page is opened$")
    public void Voucher_page_is_opened() throws Throwable {
        voucherNavi.openVoucherPage();
    }

    @When("^User look at voucher Page$")
    public void User_look_at_voucher_Page() throws Throwable {
        // Leave empty
        go_sleep(10000);
    }

    @Then("^He Can see standard pagination component and facets$")
    public void He_Can_see_standard_pagination_component_and_facets() throws Throwable {
        voucherNavi.checkVisibilityOfPaginationCOmponentAndFacets();
    }

    @Then("^All vouchers has all needed elements$")
    public void All_vouchers_has_all_needed_elements() throws Throwable {
        voucherNavi.forAllVouchersCheckVisibilityOfElementsLikeExpiryDateNameETC();
    }

    // EPOINTS - add VOUCHERS interfaces to epoints.com (RD-1140) - check possibility of using voucher, db validate, voucher code and clickout
    @When("^User choose some voucher for him$")
    public void User_choose_some_voucher_for_him() throws Throwable {
        voucherNavi.chooseVoucher();
    }

    @Then("^He Can be sure that voucher is valid$")
    public void He_Can_be_sure_that_voucher_is_valid() throws Throwable {
        voucherNavi.compareBasicInformationWithDB();
    }

    @Then("^He can reach external merchant page with voucher code$")
    public void He_can_reach_external_merchant_page_wit_voucher_code() throws Throwable {
        voucherNavi.clickGetVoucherCodeAndCheckIfTransitionPagePageWasOpened();
        voucherNavi.returnToVoucherPageForVoucherCodeAndCompareItWithDB();
    }

    @And("^Clickout should be reported '(.+)' '(.+)' from voucher page$")
    public void New_clickout_should_be_reported_from_voucher_page(String ifSigned, String Email) throws Throwable {
        voucherNavi.checkIfClickoutWasReported(ifSigned, Email);
    }

    // EPOINTS - add VOUCHER individual product interface to epoints (RD-1805) - check displaying chosen voucher on individual voucher page
    @When("^Individual voucher page will be opened$")
    public void Individual_voucher_page_will_be_opened() throws Throwable {
        voucherNavi.openIndividualVoucherPageUsingShareOption();
    }

    @Then("^User can see that highlighted voucher has the same content as chosen one$")
    public void User_can_see_that_highlighted_voucher_has_the_same_content_as_chosen_one() throws Throwable {
        voucherNavi.checkContentOfChosenVoucherAndItsValues();
    }

    @Then("^Clicking get voucher code user can receive voucher code$")
    public void Clicking_get_voucher_code_user_can_receive_voucher_code() throws Throwable {
        voucherNavi.checkIfCodeIsAvailableWhenUserCLickGetVoucherCode();
    }

    // EPOINTS - add VOUCHER individual product interface to epoints (RD-1805) - check reporting of clickout from individual voucher page
    @Then("^User can click get voucher code$")
    public void User_can_click_get_voucher_code() throws Throwable {
        voucherNavi.clickGetVoucherCodeButton();
    }
}
