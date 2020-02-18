package com.iat.steps.partnersManagement;

import com.iat.actions.partnersManagement.PartnerTransactionsActions;
import com.iat.utils.ResponseContainer;
import com.iat.validators.ErrorsValidator;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static java.lang.String.format;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;

public class PartnerTransactionsSteps {

    private PartnerTransactionsActions partnerTransactionsActions = new PartnerTransactionsActions();
    private ErrorsValidator errorsValidator = new ErrorsValidator();
    private ResponseContainer response;

    /*Try get partner transactions without providing partner accessKey*/
    @When("^Partner transactions call is made with no partner data$")
    public void tryGetPartnerTransactionsWithoutAccessKey() throws Throwable {
        response = partnerTransactionsActions.getPartnersTransactions(400);
        errorsValidator.validateHeaderErrorResponse(response, "REQUIRED_PARAMETER_NOT_PRESENT", "Required String parameter 'apiKey' is not present.");
    }

    @Then("^Partner transactions response for request with no partner data should be empty$")
    public void checkResponseForGetPartnerTransactionsWithoutAccessKey() throws Throwable {
        assertThat("Response is not null", response.toString(), isEmptyOrNullString());

    }

    /*Get partner transactions for current month and check response contract*/
    @When("^Partner transactions call is made with partner data '(.+?)'$")
    public void getPartnerTransactions(String apiKey) throws Throwable {
        response = partnerTransactionsActions.getPartnersTransactions(apiKey, 200);

    }

    @Then("^Partner transactions response data is proper for partner '(.+?)'$")
    public void partnerTransactionsResponseMatchContract(String partnerName) throws Throwable {
        response.validateContract("partnerTransactions-response-schema.json", 200);
        assertThat(response.getString("name"), is(partnerName));
    }

    /*Get partner transactions for current month - check response header data for negative cases*/
    @When("^Partner transactions call is made with partner data '(.+?)','(\\d+)','(.+?)','(.+?)' \\(negative cases\\)$")
    public void getPartnerTransactionsForNegativeData(String apiKey, int status, String expErrorCode, String expErrorMsg) throws Throwable {
        response = partnerTransactionsActions.getPartnersTransactions(apiKey, status);
        errorsValidator.validateHeaderErrorResponse(response, expErrorCode, format(expErrorMsg, apiKey));

    }

    @Then("^Partner transactions response is empty$")
    public void partnerTransactionsResponseIsEmpty() throws Throwable {
        assertThat("Response is not null", response.toString(), isEmptyOrNullString());
    }

}
