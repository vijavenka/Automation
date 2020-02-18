package com.iat.adminportal.steps.voucher_manager;

import com.iat.adminportal.page_navigations.voucher_manager.VoucherManagerSearchScreenNavigation;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class VoucherManagerSearchPageSteps {

    private VoucherManagerSearchScreenNavigation voucherNavi = new VoucherManagerSearchScreenNavigation();
    private String recordID;
    private String recordStatus;

    public VoucherManagerSearchPageSteps() {

    }

    @Then("^Voucher manager Search interface should be opened$")
    public void Voucher_manager_Search_interface_should_be_opened() throws Throwable {
        voucherNavi.check_if_voucherSearchPage_opened();
    }

    // Voucher Edit screens in the Admin Portal should show Merchant + Network (RD-3872).
    @Given("^Voucher manager Tab is opened$")
    public void Voucher_manager_Tab_is_opened() throws Throwable {
        voucherNavi.openVoucherManagerTab();
    }

    @When("^User open voucher list view$")
    public void User_open_voucher_list_view() throws Throwable {
        voucherNavi.openVoucherManagerListView();
    }

    @When("^Edit chosen voucher$")
    public void Edit_chosen_voucher() throws Throwable {
        voucherNavi.clickEditButtonOfFirstVoucher();
    }

    @Then("^User will be able to select merchant name$")
    public void User_will_be_able_to_select_merchant_name() throws Throwable {
        voucherNavi.expandMerchantsList();
    }

    @Then("^He can see that next to merchant name is proper affiliate network name$")
    public void He_can_see_that_next_to_merchant_name_is_proper_affiliate_network_name() throws Throwable {
        voucherNavi.checkAffiliateNetworkAssignment();
    }

    // Voucher Manager - search functionality for merchants (RD-3926)
    @When("^Use search with merchant name which was assigned to some voucher$")
    public void Use_search_with_merchant_name_which_was_assigned_to_some_voucher() throws Throwable {
        voucherNavi.findMerchantAssignedToSomeVoucherInDB();
        voucherNavi.searchVouchersWithSpecifiedMerchantName();
    }

    @Then("^Results will be displayed$")
    public void Results_will_be_displayed() throws Throwable {
        voucherNavi.checkIfSomeResultsWereFound();
    }

    @Then("^Returned vouchers will have proper merchant name$")
    public void Returned_vouchers_will_have_proper_merchant_name() throws Throwable {
        voucherNavi.forEachFoundVoucherCheckIfHasAssignedProperMerchant();
    }

    //EPOINTS - VOUCHER MAN. IMPR. - allow decimal point in £ off (NBO-17)
    @When("^User enter unique voucher title$")
    public void user_enter_unique_voucher_title() throws Throwable {
        voucherNavi.enterUniqueVoucherTitle();
    }

    @When("^User enter decimal value in 'Pounds Off' field$")
    public void user_enter_decimal_value_in_Pounds_Off_field() throws Throwable {
        voucherNavi.enterOffValue("4.99");
        voucherNavi.selectOffType("pound");
    }

    @When("^User save voucher configuration$")
    public void user_save_voucher_configuration() throws Throwable {
        voucherNavi.saveEditedVoucher();
    }

    @Then("^No alert will be displayed$")
    public void no_alert_will_be_displayed() throws Throwable {
        voucherNavi.checkIfNoAlertsAreDisplayed();
    }

    @Then("^'Pounds Off' decimal value will be properly saved$")
    public void pounds_Off_decimal_value_will_be_properly_saved() throws Throwable {
        voucherNavi.checkIfDecimalOffValueWasProperlyStored("4.99");
    }

}