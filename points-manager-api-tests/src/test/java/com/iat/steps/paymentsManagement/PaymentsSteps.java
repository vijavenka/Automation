package com.iat.steps.paymentsManagement;

import com.iat.actions.paymentsManagement.PaymentsActions;
import com.iat.contracts.paymentsManagement.PaymentsContract;
import com.iat.utils.ResponseContainer;
import com.iat.validators.ErrorsValidator;
import com.iat.validators.paymentsManagement.PaymentsValidator;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;

public class PaymentsSteps {

    private PaymentsActions paymentsActions = new PaymentsActions();
    private PaymentsContract paymentsContract = new PaymentsContract();
    private PaymentsValidator paymentsValidator = new PaymentsValidator();
    private ErrorsValidator errorsValidator = new ErrorsValidator();
    private ResponseContainer response;
    private String jsonParameters;
    private String guid;


    //Scenario Outline: Payments - check if new payment can be properly created
    @Given("^New payment is created in the system '(.+?)'$")
    public void createNewPayment(String apiKey) throws Throwable {
        jsonParameters = paymentsContract.returnRandomCreateUserRequestBody();
        response = paymentsActions.createNewPaymentInSystem(apiKey, jsonParameters, 201);
        guid = jsonParameters.split(",")[0];
        assertThat("Payment id was not returned", response.toString(), not(isEmptyOrNullString()));
    }

    @When("^Created payment details will be pulled '(.+?)'$")
    public void pullSelectedPaymentDetails(String apiKey) throws Throwable {
        response = paymentsActions.getDetailsOfSelectedPayment(apiKey, guid, 200);
        response.validateContract("payments-response-schema.json", 200);
    }

    @Then("^Pulled payment details data are the same as those used in payment creation$")
    public void checkIfPaymentDetailsAreAsExpected() throws Throwable {
        paymentsValidator.checkIfReturnedPaymentDetailsAreAsExpected(response, jsonParameters);
    }

    //Scenario Outline: Payments - check if new payment can be properly updated
    @When("^Created payment details will be updated '(.+?)'$")
    public void updateSelectedPayment(String apiKey) throws Throwable {
        jsonParameters = "null,null,newStatus,null,null,null,null,null,null,null,newFirstName,newLastName,newEmail@gmail.pl,newCurrency";
        response = paymentsActions.updatePaymentInSystem(apiKey, guid, jsonParameters, 200);
    }

    //Scenario Outline: Payments - check behaviour of system when invalid parameters used during payment creation
    @When("^When new payment will be created with invalid parameters values '(.+?)', '(.+?)', '(\\d+)', '(.+?)', '(.+?)'$")
    public void createNewPaymentUsingInvalidParameters(String apiKey, String jsonParameters, int status, String expErrorCode, String expErrorMsg) throws Throwable {
        response = paymentsActions.createNewPaymentInSystem(apiKey, jsonParameters, status);
        errorsValidator.validateHeaderErrorResponse(response, expErrorCode, expErrorMsg);
    }

    @Then("^New payment should not be created$")
    public void checkInNewPaymentNotCreated() throws Throwable {
        assertThat("Response is not empty", response.toString(), isEmptyOrNullString());
    }

    //Scenario Outline: Payments - check behaviour of system when invalid parameters used during payment details pull
    @When("^When selected payment details will be pulled with invalid parameters values '(.+?)', '(.+?)', '(\\d+)', '(.+?)', '(.+?)'$")
    public void pullNewPaymentDetailsUsingInvalidParameters(String apiKey, String guid, int status, String expErrorCode, String expErrorMsg) throws Throwable {
        response = paymentsActions.getDetailsOfSelectedPayment(apiKey, guid, status);
        errorsValidator.validateHeaderErrorResponse(response, expErrorCode, expErrorMsg);
    }

    @Then("^Selected payment details should not be returned$")
    public void CheckIfPaymentDetailsNotReturned() throws Throwable {
        assertThat("Response is not empty", response.toString(), isEmptyOrNullString());
    }

    //Scenario Outline: Payments - check behaviour of system when invalid parameters used during payment details update
    @When("^When payment will be updated with invalid parameters values '(.+?)', '(.+?)', '(\\d+)', '(.+?)', '(.+?)'$")
    public void updateSelectedPaymentDetailsUsingInvalidParameters(String apiKey, String guid, int status, String expErrorCode, String expErrorMsg) throws Throwable {
        response = paymentsActions.updateSelectedPaymentDetailsUsingInvalidParameters(apiKey, guid, status, expErrorCode, expErrorMsg);
    }

    @Then("^New payment should not be updated$")
    public void checkIfPaymentWasNotUpdated() throws Throwable {
        assertThat("Response is not empty", response.toString(), isEmptyOrNullString());
    }

}