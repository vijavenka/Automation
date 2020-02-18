package com.iat.adminportal.steps.voucher_manager;

import com.iat.adminportal.page_navigations.voucher_manager.VoucherManagerFeedConfigurationPageNavigation;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;


public class VoucherManagerFeedConfigurationPageSteps {

    private VoucherManagerFeedConfigurationPageNavigation voucherNavi = new VoucherManagerFeedConfigurationPageNavigation();
    private String recordID;
    private String recordStatus;

    public VoucherManagerFeedConfigurationPageSteps() {

    }

    @Then("^Labels of create new form are visible$")
    public void labelsForCreateNewVisible() throws Throwable {
        voucherNavi.checkIfLabelsAreAvailable();
    }

    @Then("^Name field is available$")
    public void nameFieldIsAvailable() throws Throwable {
        voucherNavi.checkIfNameFieldIsAvailable();
    }

    @Then("^Short Name field is available$")
    public void shortNameFieldIsAvailable() throws Throwable {
        voucherNavi.checkIfShortNameFieldIfAvailable();
    }

    @Then("^Location URL field is available$")
    public void locationURLTextAreaIsAvailable() throws Throwable {
        voucherNavi.checkIfLocationURLFieldIfAvailable();
    }

    @Then("^Affiliate Network drop-down is available with values from Network Manager$")
    public void affiliateNetworkDropDownIsAvailable() throws Throwable {
        voucherNavi.checkIfAffiliateNetworkDropDownIsAvailable();
    }

    @Then("^Product Property field is available$")
    public void productPropertyNameFieldIsAvailable() throws Throwable {
        voucherNavi.checkIfProductPropertyNameFieldIsAvailable();
    }

    @Then("^Content Type drop-down is available with values$")
    public void contentTypeDropDownIsAvailable(DataTable contentTypes) throws Throwable {
        voucherNavi.checkIfContentTypeDropDownIsAvailable(contentTypes);
    }

    @Then("^Pull Method drop-down is available with values$")
    public void pullMethodDropDownIsAvailable(DataTable pullMethod) throws Throwable {
        voucherNavi.check_if_PullMethod_DropDown_available(pullMethod);
    }

    @Then("^Cron timing field is available$")
    public void cronTimingFieldIsAvailable() throws Throwable {
        voucherNavi.check_if_Cron_Timing_field_available();
    }

    @Then("^Pre-processing Stages are available with values$")
    public void preProcessingStagesAreAvailable(DataTable preProcessingStage) throws Throwable {

    }
}