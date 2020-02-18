package com.iat.steps.partnersManagement;

import com.iat.actions.partnersManagement.PartnersSiteNotVisitedActions;
import com.iat.utils.ResponseContainer;
import com.iat.validators.ErrorsValidator;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isEmptyOrNullString;

public class PartnersSiteNotVisitedSteps {

    private PartnersSiteNotVisitedActions partnersSiteNotVisitedActions = new PartnersSiteNotVisitedActions();
    private ErrorsValidator errorsValidator = new ErrorsValidator();
    private ResponseContainer response;

    /*Get partners sites not visited yet List - system message validation*/
    @When("^Partners site not visited call is made with user data '(.+?)', '(\\d+)', '(.+?)', '(.+?)'$")
    public void partnersSiteNotVisited(String params, int status, String expErrorCode, String expErrorMsg) throws Throwable {
        response = partnersSiteNotVisitedActions.getPartnersSiteNotVisited(params, status);
        errorsValidator.validateHeaderErrorResponse(response, expErrorCode, partnersSiteNotVisitedActions.createErrorMessage(params, expErrorCode, expErrorMsg));

    }

    @Then("^Partners Site not visited response for request with no partner data should be empty$")
    public void partnersSiteNotVisitedResponseIsEmpty() throws Throwable {
        assertThat("Response is not null", response.toString(), isEmptyOrNullString());
    }

    /*Get partners sites not visited yet List and check response contract*/
    @When("^Partners site not visited call is made '(.+?)'$")
    public void partnersSiteNotVisited(String params) throws Throwable {
        response = partnersSiteNotVisitedActions.getPartnersSiteNotVisited(params, 200);
        System.out.print(response);
    }

    @Then("^Partners Site not visited response match contract$")
    public void partnersSiteNotVisitedResponseMatchContract() throws Throwable {
        //TODO: Executing validateResponseWithContract method take too much time in case when response have huge amount of nodes - about 200
        response.validateContract("partnersSiteNotVisited-response-schema.json", 200);
    }
}
