package com.iat.steps.ecardsSection;

import com.iat.actions.ecardsSection.EcardsCompanyActivityActions;
import com.iat.utils.ResponseContainer;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class EcardsCompanyActivitySteps {

    private EcardsCompanyActivityActions ecardsCompanyActivityActions = new EcardsCompanyActivityActions();
    private ResponseContainer response;


    @When("^User call for ecards company activity history$")
    public void getCompanyActivityEcardsList() throws Throwable {
        response = ecardsCompanyActivityActions.getEcardsCompanyActivity(200);

    }

    @Then("^Ecards company activity history will be returned$")
    public void checkCompanyActivityEcardsListResponse() throws Throwable {
        //controller is doing that already
        //contractValidator.validateResponseWithContract("ecards/GET-ecards-companyActivity.json", response);
    }

    @Then("^Ecards company ectivity have maximum (\\d+) results$")
    public void checkCompanyActivityEcardsListResponseCount(int count) throws Throwable {
        assertThat("Company activity results count is greater than allowed max!", response.getList("ecards"), hasSize(not(greaterThan(count))));
    }

    //Scenario Outline: Check if returned ecards are from proper range
    @Then("^Ecards are returned from range '(.+?)' according to partners manager settings$")
    public void checkReturnedEcardsRange(String ecardsRange) throws Throwable {
        System.out.println("\nVALIDATION: " + response);

        String returnedEcardRange = response.getString("scope");
        switch (ecardsRange) {
            case "SAME_COMPANY":
                assertThat(returnedEcardRange, is("COMPANY"));
                break;
            case "SAME_DEPARTMENT":
                assertThat(returnedEcardRange, is("DEPARTMENT"));
                break;
            case "ALL_USERS":
                assertThat(returnedEcardRange, is("ALL"));
                break;
        }
    }

    //Scenario Outline: Check if user without ecards permission can pull company activity
    @When("^User without ecards call for ecards company activity history '(.+?)'$")
    public void getCompanyActivityEcardsListError(int status) throws Throwable {
        response = ecardsCompanyActivityActions.getEcardsCompanyActivity(status);
    }

    @Then("^Ecards activity are not returned '(\\d+)', '(.+?)', '(.+?)'$")
    public void checkIfNoEcardsWereReturned(int status, String error, String message) throws Throwable {
        System.out.println("VALIDATION: " + response.toString());

        assertThat("Incorrect status!", response.getInt("status"), is(status));
        assertThat("Incorrect error!", response.getString("error"), is(error));
        assertThat("Incorrect message!", response.getString("message"), is(message));
    }
}