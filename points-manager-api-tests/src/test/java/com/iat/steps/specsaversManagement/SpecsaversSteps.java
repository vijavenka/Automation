package com.iat.steps.specsaversManagement;

import com.iat.actions.specsaversManagement.SpecsaversActions;
import com.iat.utils.ResponseContainer;
import com.iat.validators.ErrorsValidator;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static java.lang.String.format;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;

public class SpecsaversSteps {

    private SpecsaversActions specsaversActions = new SpecsaversActions();
    private ErrorsValidator errorsValidator = new ErrorsValidator();
    private ResponseContainer response;

    //Get reasons list for group - check contract
    @When("^Reasons list call is made for group data '(.+?)'$")
    public void getReasonsForGroup(String params) throws Throwable {
        response = specsaversActions.getReasonsForGroup(params, 200);
    }

    @Then("^Reasons list for group response match contract$")
    public void getReasonsForGroupMatchContract() throws Throwable {
        response.validateContract("specsaversGetReasons-response-schema.json", 200);
    }

    //Get reasons list for group - system message validation
    @When("^Reasons list call for group is made with incorrect data '(.+?)', '(\\d+)', '(.+?)', '(.+?)'$")
    public void getReasonsForGroupErrorMessage(String params, int status, String expErrorCode, String expErrorMsg) throws Throwable {
        response = specsaversActions.getReasonsForGroup(params, status);
        errorsValidator.validateHeaderErrorResponse(response, expErrorCode, specsaversActions.createErrorMessage(params, expErrorCode, expErrorMsg));
    }

    @Then("^Reasons list for group response is empty$")
    public void getReasonsForGroupIsEmpty() throws Throwable {
        assertThat("Response is not null", response.toString(), isEmptyOrNullString());
    }

    //Get reasons list for partner - check contract
    @When("^Reasons list call is made for partner data '(.+?)'$")
    public void getReasonsForPartner(String apiKey) throws Throwable {
        response = specsaversActions.getReasonsForPartner(apiKey, 200);
    }

    @Then("^Reasons list for partner response match contract$")
    public void getReasonsForPartnerMatchContract() throws Throwable {
        response.validateContract("specsaversGetReasons-response-schema.json", 200);
    }

    //Get reasons list for partner - system message validation
    @When("^Reasons list call for partner is made with incorrect data '(.+?)', '(\\d+)', '(.+?)', '(.+?)'$")
    public void getReasonsForPartnerErrorMessage(String apiKey, int status, String expErrorCode, String expErrorMsg) throws Throwable {
        response = specsaversActions.getReasonsForPartner(apiKey, status);
        errorsValidator.validateHeaderErrorResponse(response, expErrorCode, format(expErrorMsg, apiKey));
    }

    @Then("^Reasons list for partner response is empty$")
    public void getReasonsForPartnerIsEmpty() throws Throwable {
        assertThat("Response is not null", response.toString(), isEmptyOrNullString());
    }

    //Get users info for partner - check contract
    @When("^User info for partner call is made for partner data '(.+?)'$")
    public void usersInfoForPartner(String params) throws Throwable {
        response = specsaversActions.getUsersInfoForPartner(params, 200);

    }

    @Then("^User info for partner response match contract$")
    public void usersInfoForPartnerMatchContract() throws Throwable {
        //TODO: huge amount of results to compare
        response.validateContract("specsaversGetUsersInfoForPartner-response-schema.json", 200);
    }

    //Get users info for partner - system message validation
    @When("^User info for partner call is made with incorrect data '(.+?)', '(\\d+)', '(.+?)', '(.+?)'$")
    public void usersInfoForPartnerErrorMessage(String params, int status, String expErrorCode, String expErrorMsg) throws Throwable {
        response = specsaversActions.getUsersInfoForPartner(params, status);
        errorsValidator.validateHeaderErrorResponse(response, expErrorCode, format(expErrorMsg, expErrorMsg.contains("shortName") ? params.split(",")[0] : params.split(",")[1]));
    }

    @Then("^User info for partner response is empty$")
    public void usersInfoForPartnerResponseIsEmpty() throws Throwable {
        assertThat("Response is not null", response.toString(), isEmptyOrNullString());
    }

    //Get partner report overview - check contract
    @When("^Partner report overview call is made for partner data '(.+?)'$")
    public void getPartnerReportOverview(String params) throws Throwable {
        response = specsaversActions.getPartnerReportOverview(params, 200);

    }

    @Then("^Partner report overview response match contract$")
    public void pgetPartnerReportOverviewMatchContract() throws Throwable {
        response.validateContract("specsaversGetReportOverviewForPartner-response-schema.json", 200);
    }

    //Get partner report overview - system message validation
    @When("^Partner report overview call is made with incorrect data '(.+?)', '(\\d+)', '(.+?)', '(.+?)'$")
    public void getPartnerReportOverviewErrorMessage(String params, int status, String expErrorCode, String expErrorMsg) throws Throwable {
        response = specsaversActions.getPartnerReportOverview(params, status);
        errorsValidator.validateHeaderErrorResponse(response, expErrorCode, format(expErrorMsg, expErrorMsg.contains("shortName") ? params.split(",")[0] : params.split(",")[1]));
    }

    @Then("^Partner report overview response is empty$")
    public void getPartnerReportOverviewResponseIsEmpty() throws Throwable {
        assertThat("Response is not null", response.toString(), isEmptyOrNullString());
    }

    //Get partner points awarded - check contract
    @Given("^Partner points awarded call is made for partner data '(.+?)'$")
    public void getAwardedPointsForPartner(String params) throws Throwable {
        response = specsaversActions.getAwardedPointsForPartner(params, 200);
    }

    @When("^Partner points awarded call is returned with proper pages size '(.+?)'$")
    public void getAwardedPointsForPartnerResponseProperSize(String params) throws Throwable {
        String[] params2 = params.split(",");
        String size = params2[3];
        if (size.equals("null")) size = "50";
        assertThat("Page size is incorrect!", response.getInt("pageSize"), is(Integer.parseInt(size)));
    }

    @Then("^Partner points awarded response match contract$")
    public void getAwardedPointsForPartnerMatchContract() throws Throwable {
        response.validateContract("specsaversGetAwardedPointsForPartner-response-schema.json", 200);
    }

    //Get partner points awarded - system message validation
    @When("^Partner points awarded call is made with incorrect data '(.+?)', '(\\d+)', '(.+?)', '(.+?)'$")
    public void getAwardedPointsForPartnerErrorMessage(String params, int status, String expErrorCode, String expErrorMsg) throws Throwable {
        response = specsaversActions.getAwardedPointsForPartner(params, status);
        errorsValidator.validateHeaderErrorResponse(response, expErrorCode, format(expErrorMsg, expErrorMsg.contains("shortName") ? params.split(",")[0] : params.split(",")[1]));

    }

    @Then("^Partner points awarded response is empty$")
    public void getAwardedPointsForPartnerResponseIsEmpty() throws Throwable {
        assertThat("Response is not null", response.toString(), isEmptyOrNullString());
    }

    //Get partner points redeemed - check contract
    @Given("^Partner points redeemed call is made for partner data '(.+?)'$")
    public void getRedeemedPointsForPartner(String params) throws Throwable {
        response = specsaversActions.getRedeemedPointsForPartner(params, 200);
    }

    @When("^Partner points redeemed call is returned with proper pages size '(.+?)'$")
    public void getRedeemedPointsForPartnerResponseProperSize(String params) throws Throwable {
        String[] params2 = params.split(",");
        String size = params2[3];
        if (size.equals("null")) size = "50";
        assertThat("Page size is incorrect!", response.getInt("pageSize"), is(Integer.parseInt(size)));
    }

    @Then("^Partner points redeemed response match contract$")
    public void getRedeemedPointsForPartnerMatchContract() throws Throwable {
        response.validateContract("specsaversGetRedeemedPointsForPartner-response-schema.json", 200);
    }

    //Get partner points redeemed - system message validation
    @When("^Partner points redeemed call is made with incorrect data '(.+?)', '(\\d+)', '(.+?)', '(.+?)'$")
    public void getRedeemedPointsForPartnerErrorMessage(String params, int stats, String expErrorCode, String expErrorMsg) throws Throwable {
        response = specsaversActions.getRedeemedPointsForPartner(params, stats);
        errorsValidator.validateHeaderErrorResponse(response, expErrorCode, format(expErrorMsg, expErrorMsg.contains("shortName") ? params.split(",")[0] : params.split(",")[1]));
    }

    @Then("^Partner points redeemed response is empty$")
    public void getRedeemedPointsForPartnerResponseIsEmpty() throws Throwable {
        assertThat("Response is not null", response.toString(), isEmptyOrNullString());
    }

    //Get partner points nets - check contract
    @When("^Partner points nets call is made for partner data '(.+?)'$")
    public void getNetsPointsForPartner(String params) throws Throwable {
        response = specsaversActions.getNetsPointsForPartner(params, 200);

    }

    @Then("^Partner points nets response match contract$")
    public void getNetsPointsForPartnerMatchContract() throws Throwable {
        response.validateContract("specsaversGetNetsValuesForPartner-response-schema.json", 200);
    }

    //Get partner points nets - system message validation
    @When("^Partner points nets call is made with incorrect data '(.+?)', '(\\d+)', '(.+?)', '(.+?)'$")
    public void getNetsPointsForPartnerErrorMessage(String params, int status, String expErrorCode, String expErrorMsg) throws Throwable {
        response = specsaversActions.getNetsPointsForPartner(params, status);
        errorsValidator.validateHeaderErrorResponse(response, expErrorCode, format(expErrorMsg, expErrorMsg.contains("shortName") ? params.split(",")[0] : params.split(",")[1]));
        System.out.print(response);
    }

    @Then("^Partner points nets response is empty$")
    public void getNetsPointsForPartnerResponseIsEmpty() throws Throwable {
        assertThat("Response is not null", response.toString(), isEmptyOrNullString());
    }
}
