package com.iat.adminportal.steps;

import com.iat.adminportal.executors.IExecutor;
import com.iat.adminportal.page_navigations.AdminPortalFeedManagerNavigation;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AdminPortalFeedManagerSteps {

    DataExchanger dataExchanger = DataExchanger.getInstance();

    public IExecutor executor = dataExchanger.getExecutor();
    private AdminPortalFeedManagerNavigation feedPage = new AdminPortalFeedManagerNavigation();
    private String recordID;
    private String recordStatus;

    public AdminPortalFeedManagerSteps() {

    }

    @Given("^List view component is available with column names$")
    public void checkAvailableColumnHeaders(DataTable expectedHeaders) {
        List<String> actualHeaders = executor.getTableHeaders();
        expectedHeaders.diff(actualHeaders);
    }

    @And("^He clicks on a 'Delete' button next to a chosen feed$")
    public void clickDeleteOnFeed() {
        feedPage.clicksDeleteOnFeed1stRow();
        assertTrue(feedPage.checkDeleteConfirmation());
    }

    @And("^He looks for record to be deleted$")
    public void lookForRecord2BDeleted() {
        recordID = feedPage.lookForRecord4Deletion();
        feedPage.searchRecordDumb(recordID);
        assertTrue(feedPage.checkSearchSuccessful(recordID));
        recordStatus = feedPage.getFeedStatus();
    }

    @And("^He clicks on the 'Create new' button$")
    public void clickCreateNewButton() {
        feedPage.clickCreateNewFeedButton();
    }

    @And("^First page of 'Create New' Feed Manager is opened$")
    public void checkFeedDetailsStepOnePage() {
        feedPage.checkFeedDetailsStep1EditMode();
    }

    @When("^Admin chooses to ([^']*) the delete action by pressing '([^']*)' button$")
    public void clickNoOnConfirmation(String action, String button) {
        feedPage.confirmAction(button);
        assertFalse(feedPage.checkDeleteConfirmation());
    }

    @Then("^Feed is not deleted$")
    public void checkFeedNotDeleted() {
        assertTrue(feedPage.checkDeleteButton());
    }

    @Then("^Feed is deleted$")
    public void checkFeedDeleted() {
        assertFalse(feedPage.checkDeleteButton());
    }

    @And("^Feed still has an active 'Delete' button on the Feed List$")
    public void checkDeleteActive() {
        assertTrue(feedPage.checkDeleteButton());
    }

    @And("^'Delete' button disappears and the 'Deleted' text is shown next to a chosen feed on the Feed List$")
    public void checkDeleteDisabled() {
        assertFalse(feedPage.checkDeleteButton());
    }

    @And("^Feed status \\(Active/Deactivated\\) stays unchanged$")
    public void checkFeedStatus() {
        assertTrue(new String(feedPage.getFeedStatus()).equals(recordStatus));
    }

    @And("^Feed becomes 'Deactivated'$")
    public void checkFeedDeactivated() {
        assertTrue(new String(feedPage.getFeedStatus()).equals("Deactivated"));
    }

    @And("^'Activate' button is no longer available for the record$")
    public void checkActivateButton() {
        assertFalse(feedPage.checksActivateButtonExists());
    }

    @And("^'Run' option is still enabled$")
    public void checkRunButtonExists() {
        assertTrue(feedPage.checkRunButton());
    }

    @And("^'Run' option is no longed available$")
    public void checkRunButtonDoesntExists() {
        assertFalse(feedPage.checkRunButton());
    }
}