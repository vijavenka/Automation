package com.iat.steps.ecardsSection;

import com.iat.actions.ecardsSection.EcardsHistoryActions;
import com.iat.actions.ecardsSection.EcardsIndividualEcardActions;
import com.iat.utils.ResponseContainer;
import com.iat.validators.EcardsIndividualEcardValidator;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class EcardsIndividualEcardSteps {

    private EcardsIndividualEcardActions ecardsIndividualEcardActions = new EcardsIndividualEcardActions();
    private EcardsHistoryActions ecardsHistoryActions = new EcardsHistoryActions();
    private EcardsIndividualEcardValidator ecardsIndividualEcardValidator = new EcardsIndividualEcardValidator();
    private ResponseContainer response, responseTemp;

    //assertion variable
    private String ecardId;

    //TODO ecard id need to be provided here
    private String invalidEcardId = "243234234";

    //Scenario Outline: Check if it's possible to get single ecard details
    @Given("^User have list of his historical ecards '(.+?)'$")
    public void getListOfHistoricalEcards(String direction) throws Throwable {
        responseTemp = ecardsHistoryActions.getEcardsHistory(direction, 200);
        ecardId = responseTemp.getString("ecards[0].id");
    }

    @When("^User call for individual ecard details$")
    public void getDetailsOfIndividualEcard() throws Throwable {
        response = ecardsIndividualEcardActions.getIndividualEcardDetails(ecardId, 200);

    }

    @Then("^Get ecard details call returns details of selected ecard$")
    public void compareDataFromEcardListHistoryAndEcardDetails() throws Throwable {
        ecardsIndividualEcardValidator.checkIndividualEcardDataCorectness(responseTemp, response);
    }

    //Scenario Outline: Check if it's possible to get single other user personal ecard details
    @When("^User call for individual ecard details which belongs to other user '(.*)'$")
    public void user_call_for_individual_ecard_details_which_belongs_to_other_user(int status) throws Throwable {
        response = ecardsIndividualEcardActions.getIndividualEcardDetails(ecardId, status);
    }

    @Then("^Get ecard details should not be returned '(\\d+)', '(.+?)', '(.+?)'$")
    public void checkResponseAndIfDetailsWereReturned(int status, String error, String message) throws Throwable {
        assertThat("Incorrect status!", response.getInt("status"), is(status));
        assertThat("Incorrect error!", response.getString("error"), is(error));
        assertThat("Incorrect message!", response.getString("message"), is(String.format(message, invalidEcardId)));
    }

    //Scenario Outline: Check if it's possible to get single ecard details which not exists
    @When("^User call for individual ecard details which not exists '(.*)'$")
    public void user_call_for_individual_ecard_details_which_not_exists(int status) throws Throwable {
        response = ecardsIndividualEcardActions.getIndividualEcardDetails(invalidEcardId, status);
    }
}