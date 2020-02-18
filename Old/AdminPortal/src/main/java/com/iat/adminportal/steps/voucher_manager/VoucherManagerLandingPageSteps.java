package com.iat.adminportal.steps.voucher_manager;

import com.iat.adminportal.page_navigations.voucher_manager.VoucherManagerFeedConfigurationPageNavigation;
import com.iat.adminportal.page_navigations.voucher_manager.VoucherManagerLandingPageNavigation;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class VoucherManagerLandingPageSteps {

    private VoucherManagerLandingPageNavigation voucherLandingNavi = new VoucherManagerLandingPageNavigation();
    private VoucherManagerFeedConfigurationPageNavigation voucherConfNavi = new VoucherManagerFeedConfigurationPageNavigation();
    private String recordID;
    private String recordStatus;

    public VoucherManagerLandingPageSteps() {

    }

    @Then("^Voucher Manager List screen is opened$")
    public void Voucher_manager_List_screen_is_opened_properly() throws Throwable {
        voucherLandingNavi.check_if_voucherPage_opened();
    }

    @Given("^He clicks on 'Add New Feed Configuration' button$")
    public void Voucher_Feed_Configuration_screen_opened() throws Throwable {
        voucherLandingNavi.clickOnAddNewFeedConfiguration();
    }

    @Then("^Voucher manager 'Create new' form is opened$")
    public void Voucher_Feed_Configuration_screen_is_opened() throws Throwable {
//        voucherLandingNavi.checkIfCreateViewOpened();
        voucherConfNavi.checkIfCreateViewOpened();
    }

    @When("^Check elements availability$")
    public void Check_elements_availability() throws Throwable {

    }

    @Then("^Raw Voucher statistics section should be available$")
    public void Raw_Voucher_statistics_section_should_be_available() throws Throwable {
        voucherLandingNavi.checkIfRawVoucherStatisticsAvailable();
    }

    @Then("^Raw product statistics section should include NEW vouchers count and link to search screen$")
    public void Raw_product_statistics_section_should_include_NEW_vouchers_count_and_link_to_search_screen() throws Throwable {
        voucherLandingNavi.checkIfRawVoucherStatisticsIncludeCreated();
    }

    @Then("^Raw product statistics section should include UPDATED vouchers count and link to search screen$")
    public void Raw_product_statistics_section_should_include_UPDATED_vouchers_count_and_link_to_search_screen() throws Throwable {
        voucherLandingNavi.checkIfRawVoucherStatisticsIncludeUpdated();

    }

    @Then("^Raw product statistics section should include DELETED vouchers count and link to search screen$")
    public void Raw_product_statistics_section_should_include_DELETED_vouchers_count_and_link_to_search_screen() throws Throwable {
        voucherLandingNavi.checkIfRawVoucherStatisticsIncludeDeleted();

    }

    @Then("^Updated vouchers statistics section should be available$")
    public void Updated_vouchers_statistics_section_should_be_available() throws Throwable {
        voucherLandingNavi.checkIfEditedVoucherStatisticsAvailable();

    }

    @Then("^Updated Vouchers statistics section should include EDITED vouchers count and link to search screen$")
    public void Updated_Vouchers_statistics_section_should_include_EDITED_vouchers_count_and_link_to_search_screen() throws Throwable {
        voucherLandingNavi.checkIfEditedVoucherStatisticsIncludeEdited();

    }

    @Then("^Updated Vouchers statistics section should include PARTIALLY EDITED vouchers count and link to search screen$")
    public void Updated_Vouchers_statistics_section_should_include_PARTIALLY_EDITED_vouchers_count_and_link_to_search_screen() throws Throwable {
        voucherLandingNavi.checkIfRawVoucherStatisticsIncludePartiallyEdited();

    }

    @Then("^Table with Voucher Feed Configuration for each network should be available$")
    public void Table_with_Voucher_Feed_Configuration_for_each_network_should_be_available() throws Throwable {
        voucherLandingNavi.checkVoucherFeedConfigurationTableAvailability();

    }

    @Then("^Voucher Feed Configuration table columns should have proper values$")
    public void Voucher_Feed_Configuration_table_columns_should_have_proper_values(DataTable arg1) throws Throwable {
        voucherLandingNavi.checkVoucherFeedConfigurationTableHeaders(arg1);
    }

    @Then("^Counters in Status, New, Updated, Total, Deleted, Edited columns should be links into Vouchers search screen$")
    public void Counters_in_Status_New_Updated_Total_Deleted_Edited_columns_should_be_links_into_Vouchers_search_screen() throws Throwable {
        voucherLandingNavi.checkIfcountersAreLinks();

    }

    @Then("^Promote Voucher Feed Configuration button should be available$")
    public void Promote_Voucher_Feed_Configuration_button_should_be_available() throws Throwable {
        voucherLandingNavi.checkIfPromoteButtonsAvailable();

    }

    @Then("^Deactivate/Activate Voucher Feed Configuration button should be available$")
    public void Deactivate_Activate_Voucher_Feed_Configuration_button_should_be_available() throws Throwable {
        voucherLandingNavi.checkIfActive_deactive_ButtonsAvailable();

    }

    @Then("^Promote all Voucher Feed Configurations button should be available$")
    public void Promote_all_Voucher_Feed_Configurations_button_should_be_available() throws Throwable {
        voucherLandingNavi.checkIfPromoteAllButtonAvailable();
    }

    @Then("^Add new Voucher Feed Configuration button should be available$")
    public void Add_new_Voucher_Feed_Configuration_button_should_be_available() throws Throwable {
        voucherLandingNavi.checkIfAddNewFeedConfigurationButtonAvailable();
    }

    @When("^Click in CREATED links in Raw store statistics section$")
    public void Click_in_CREATED_links_in_Raw_store_statistics_section() throws Throwable {
        voucherLandingNavi.clickInRawStatisticsCreatedLink();
    }

    @When("^Click in CREATED links in table column for network$")
    public void Click_in_CREATED_links_in_table_column_for_network() throws Throwable {

        //TODO
    }

    @When("^Click in CREATED links in table column for summary$")
    public void Click_in_CREATED_links_in_table_column_for_summary() throws Throwable {
        //TODO
    }

}