package com.iat.steps.partnersManagement;

import com.iat.Config;
import com.iat.actions.partnersManagement.CreatePartnerActions;
import com.iat.utils.ResponseContainer;
import com.iat.validators.ErrorsValidator;
import com.iat.validators.partnersManagement.CreatePartnerValidator;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CreatePartnerSteps {

    private CreatePartnerActions createPartnerActions = new CreatePartnerActions();
    private CreatePartnerValidator createPartnerValidator = new CreatePartnerValidator();
    private ResponseContainer response;
    private ErrorsValidator errorsValidator = new ErrorsValidator();


    /*Creating new Partner - check contract and response data for positive cases*/
    @Given("^Add new partner call is made with provided data '(.+?)','(.+?)','(.+?)','(.+?)','(.+?)','(.+?)' \\(positive cases\\)$")
    public void addNewPartnerCall(String name, String siteURL, String description, String email, String logoURL, String group) throws Throwable {
        response = createPartnerActions.createNewPartner(name, siteURL, description, email, logoURL, group, 201);
    }

    @When("^Partner should be properly created for provided data '(.+?)','(.+?)','(.+?)','(.+?)','(.+?)','(.+?)'$")
    public void partnerIsProperlyCreated(String name, String siteURL, String description, String email, String logoURL, String group) throws Throwable {
        assertThat("Incorrect Partner name was returned!", response.getString("name"), is(name));
        assertThat("Empty Partner shortName was returned!", response.getString("shortName"), not(isEmptyOrNullString()));
        assertThat("Empty Partner apiKey was returned!", response.getString("apiKey"), not(isEmptyOrNullString()));
        assertThat("Empty Partner secret was returned!", response.getString("secret"), not(isEmptyOrNullString()));
    }

    @Then("^Create Partner response match contract$")
    public void createPartnerResponseMatchContract() throws Throwable {
        //contract is validated in controller
        //response.validateContract("partnerCreation-response-schema.json", 201);
    }

    /*Creating new Partner - check response header data for negative cases*/
    @Given("^Add new partner call is made with provided data '(.+?)','(.+?)','(.+?)','(.+?)','(.+?)','(.+?)','(\\d+)','(.+?)','(.+?)' \\(negative cases\\)$")
    public void addNewPartnerCall(String name, String siteURL, String description, String email, String logoURL, String group, int status, String expErrorCode, String expErrorMsg) throws Throwable {
        response = createPartnerActions.createNewPartner(name, siteURL, description, email, logoURL, group, status);
        if (!group.equals("null")) expErrorMsg = expErrorMsg.replace("$GROUP", group);
        errorsValidator.validateHeaderErrorResponse(response, expErrorCode, expErrorMsg);
    }

    @When("^Partner should not be properly created for provided data$")
    public void partnerIsNotCreated() throws Throwable {
        assertThat("Response is not null", response.toString(), isEmptyOrNullString());

    }

    @When("^File '(.+?)' with partners list will be processed for partners group '(.+?)', '(\\d+)'$")
    public void bulkUploadPartnersFromFile(String googleDocId, String groupShortName, int status) throws Throwable {
        response = createPartnerActions.bulkUploadPartners(groupShortName, googleDocId, status);
    }

    @Then("^Partners will be properly saved in points manager and assigned to correct partners group '(.+?)'$")
    public void validatePartnersBulkUploadCorrectness(String groupShortName) throws Throwable {
        createPartnerValidator.validatePartnersBulkUploadCorrectness(Config.getBulkUploadPartnersData(), groupShortName);
    }

    @Then("^Correct error message will be returned for file '(.+?)', '(.+?)', '(.+?)'$")
    public void checkPartnersBulkUploadErrorMessage(String error, String message, String items) throws Throwable {
        createPartnerValidator.validatePartnersBulkUploadErrorMessage(response, error, message, items);
    }

    @Then("^All added during bulk upload partners will be rolled back$")
    public void checkIfProcessedPartnersAreRolledBack() throws Throwable {
        createPartnerValidator.validatePartnersWereRolledBack(Config.getBulkUploadPartnersData());
    }

}