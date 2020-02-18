package com.iat.steps.partnersManagement;

import com.iat.actions.HealthCheckActions;
import com.iat.actions.partnersManagement.PartnersListingActions;
import com.iat.utils.ResponseContainer;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PartnersListingSteps {

    private HealthCheckActions healthCheckActions = new HealthCheckActions();
    private PartnersListingActions partnersListingActions = new PartnersListingActions();
    private ResponseContainer response;

    /* Scenario: Get default list of Partners */
    @Given("^Points Manager API is responding to requests$")
    public void checkHeartbeatOfPointsManager() {
        assertThat("Points Manager API not Respondek (HTTP/1.1 200)", healthCheckActions.checkIfPointsManagerIsResponding());
    }

    @When("^Default call for list of Partners is made$")
    public void getDefaultListOfPartners() {
        response = partnersListingActions.getDefaultPartnerList(200);
    }

    @Then("^List of partners is returned regardless of the active status$")
    public void validateDefaultPartnersList() {
        //response.validateContract("partnersList-response-schema.json", 200);
        assertThat("Results count invalid!", response.getList("results"), hasSize(25));
    }

    /*Scenario Outline: Pull specific Partner info by shortName*/
    @When("^Partner details call is made with partner shortName'(.+?)'$")
    public void getPartnerDetailsByShortName(String partnerShortName) throws Throwable {
        response = partnersListingActions.getPartnersListByShortName(partnerShortName, 200);
    }

    @Then("^Partner with provided shortName '(.+?)' is in results$")
    public void checkIfPartnerIsInResultsByShortName(String partnerShortName) throws Throwable {
        //response.validateContract("partnersList-response-schema.json", 200);
        if (!partnerShortName.equals("NOT_EXIST")) {
            assertThat("Missing partner for provided shortName", partnerShortName, isIn(response.getList("results.shortName")));
            assertThat("Incorrect searchResultsCount!", response.getInt("searchResultsCount"), is(1));
        }
    }

    /*Scenario Outline: Pull specific Partner info by name*/
    @When("^Partner details call is made with partner name'(.+?)'$")
    public void getPartnerDetailsByName(String partnerName) throws Throwable {
        response = partnersListingActions.getPartnersListByName(partnerName, 200);
    }

    @Then("^Partner with provided name '(.+?)' is in results$")
    public void checkIfPartnerIsInResultsByName(String partnerName) throws Throwable {
        //response.validateContract("partnersList-response-schema.json", 200);
        if (!partnerName.equals("not_exist")) {
            assertThat("Missing partner for provided shortName", partnerName, isIn(response.getList("results.name")));
            assertThat("Incorrect searchResultsCount!", response.getInt("searchResultsCount"), is(greaterThan(0)));
        }
    }

    /*Scenario Outline: Check if partners details count in results are changed after provide page and size params*/
    @When("^Pull list of partners with page equals '(\\d+)' and size equals '(\\d+)'$")
    public void getPartnersListForProvidedPageAndSizeParams(int page, int size) throws Throwable {
        response = partnersListingActions.getPartnersListForPageSize(page, size, 200);
    }

    @Then("^Partners count should be returned according provided parameters page '(\\d+)' and size '(\\d+)'$")
    public void checkIfPartnersDetailsCountIsProperForProvidedPageAndSizeParams(int page, int size) throws Throwable {
        //response.validateContract("partnersList-response-schema.json", 200);
        assertThat("Incorrect page was returned!", response.getInt("page"), is(page));
        assertThat("Incorrect page size was returned!", response.getInt("pageSize"), is(size));
        assertThat("Results count is not matching pageSize parameter!", response.getList("results"), hasSize(size));
    }

    /*Scenario Outline: Pull specific Partner info by shortName and compare shortName with Name*/
    @Then("^Partner shortName '(.+?)' match partner Name '(.+?)'$")
    public void checkIfPartnerShortNameMatchName(String partnerShortName, String partnerName) throws Throwable {
        assertThat("Missing partner name!", partnerName, isIn(response.getList("results.name")));
    }

}
