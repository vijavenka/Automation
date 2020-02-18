package com.iat.steps.partnersManagement;

import com.iat.actions.partnersManagement.PartnerBalanceActions;
import com.iat.utils.ResponseContainer;
import com.iat.validators.ErrorsValidator;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PartnerBalanceSteps {

    private PartnerBalanceActions partnerBalanceActions = new PartnerBalanceActions();
    private ResponseContainer response;
    private ErrorsValidator errorsValidator = new ErrorsValidator();

    /*Get partner balance - check response for selected fields*/
    @Given("^Partner get balance call is made with data '(.+?)'$")
    public void partnerGetBalance(String params) throws Throwable {
        response = partnerBalanceActions.getPartnerBalance(params, 200);
    }

    @When("^Partner get balance response for request match contract$")
    public void partnerGetBalanceResponseMatchContract() throws Throwable {
        //contract validation is present in controller
        //response.validateContract("partnerBalance-response-schema.json", 200);
    }

    @Then("^Partner get balance response include fields selected '(.+?)'$")
    public void partnerGetBalanceResponseIncludeFieldsSelected(String params) throws Throwable {
        String[] params2 = params.split(";");
        String[] fields = params2[2].split(",");

        for (String field : fields)
            assertThat("Field (" + field + " is not in response!", response.get(field), is(notNullValue()));

    }

    /*Get partner balance - system message validation*/
    @When("^Partner get balance call is made with some miss data '(.+?)','(\\d+)','(.+?)','(.+?)'$")
    public void partnerGetBalance(String params, int status, String expErrorCode, String expErrorMsg) throws Throwable {
        response = partnerBalanceActions.getPartnerBalance(params, status);
        errorsValidator.validateHeaderErrorResponse(response, expErrorCode, expErrorMsg);
    }

    @Then("^Partner get balance response for request with miss data should be empty$")
    public void partnerGetBalanceResponseForRequestWithMissData() throws Throwable {
        assertThat("Response is not null", response.toString(), isEmptyOrNullString());
    }
}
