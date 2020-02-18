package com.iat.steps.redemptionsManagement;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iat.actions.redemptionsManagement.RedemptionsActions;
import com.iat.domain.orderRedemption.OrderRefund;
import com.iat.utils.HelpFunctions;
import com.iat.utils.ResponseContainer;
import com.iat.validators.ErrorsValidator;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static com.iat.utils.DateTimeUtil.*;
import static org.exparity.hamcrest.date.DateMatchers.sameOrAfter;
import static org.exparity.hamcrest.date.DateMatchers.sameOrBefore;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RedemptionsSteps {

    private RedemptionsActions redemptionsActions = new RedemptionsActions();
    private HelpFunctions helpFunctions = new HelpFunctions();
    private ObjectMapper mapper = new ObjectMapper();
    private ResponseContainer response;
    private String redemptionId;
    private ErrorsValidator errorsValidator = new ErrorsValidator();

    //Get redemptions list and check response contract
    @When("^Get redemptions list call is made with data '(.+?)'$")
    public void getRedemptionsList(String params) throws Throwable {
        response = redemptionsActions.getRedemptions(params, 200);
    }

    @Then("^Get redemptions response have proper results count for provided data '(.+?)'$")
    public void getRedemptionsListMatchContract(String params) throws Throwable {

        //TODO move validation to separate class
        String[] params2 = params.split(",");
        String apiKey = params2[0];
        String ascending = params2[1];
        String orderField = params2[2];
        String offset = params2[3];
        String limit = params2[4];
        String startDate = params2[5];
        String endDate = params2[6];
        String searchField = params2[7];
        String keyword = params2[8];
        String withAddictivityInfo = params2[9];

        if (limit.equals("null")) limit = "25";
        assertThat("Results size is not matching searchResultsCount value!", response.getList("results.redemptionId"), hasSize(response.getInt("searchResultsCount")));
        assertThat("Incorrect results count - more than provided limit", response.getInt("searchResultsCount"), is(lessThanOrEqualTo(Integer.parseInt(limit))));

        //startDate endDate validation
        List<Date> extractedCreatedAt = new ArrayList<>();
        response.getList("results.createdDate", Long.class).forEach(it -> extractedCreatedAt.add(new Date(it)));

        if (!startDate.equals("null")) {
            Date providedStartDate;
            if (startDate.contains("-"))
                providedStartDate = convertToDate(startDate, Format.dd_MM_yyyy, true);
            else
                providedStartDate = new Date(Long.parseLong(startDate));
            System.out.print("\n START DATE: " + providedStartDate);

            assertThat("All dates should not be after provided start date!", extractedCreatedAt, everyItem(is(sameOrAfter(providedStartDate))));
        }
        if (!endDate.equals("null")) {
            Date providedEndDate;

            if (endDate.contains("-")) {
                providedEndDate = convertToDate(endDate, Format.dd_MM_yyyy, true);
                providedEndDate = adjustDateBySeconds(adjustDateByDays(providedEndDate, 1), -1); // added time 23:59:59
            } else
                providedEndDate = new Date(Long.parseLong(endDate));
            System.out.print("\n END DATE: " + providedEndDate);

            assertThat("All dates should not be before provided end date!", extractedCreatedAt, everyItem(is(sameOrBefore(providedEndDate))));
        }

        //ascending validation
        if (orderField.equals("null")) {
            if (ascending.equals("null") || ascending.equals("false")) {
                for (int i = 0; i < extractedCreatedAt.size() - 1; i++)
                    assertThat("Results are not sorted by desc!", extractedCreatedAt.get(i), is(sameOrAfter(extractedCreatedAt.get(i + 1))));
            } else {
                for (int i = 0; i < extractedCreatedAt.size() - 1; i++)
                    assertThat("Results are not sorted by asc!", extractedCreatedAt.get(i), is(sameOrBefore(extractedCreatedAt.get(i + 1))));
            }
        }

        if (orderField.equals("redemptionId")) {
            List<Long> extractedRedemptionId = response.getList("results.redemptionId", Long.class);
            if (ascending.equals("null") || ascending.equals("false")) {
                for (int i = 0; i < extractedRedemptionId.size() - 1; i++)
                    assertThat("Results are not sorted by desc!", extractedRedemptionId.get(i), is(greaterThanOrEqualTo(extractedRedemptionId.get(i + 1))));
            } else {
                for (int i = 0; i < extractedRedemptionId.size() - 1; i++)
                    assertThat("Results are not sorted by asc!", extractedRedemptionId.get(i), is(lessThanOrEqualTo(extractedRedemptionId.get(i + 1))));
            }
        }

        if (orderField.equals("orderId")) {
            List<Long> extractedOrderId = response.getList("results.orderId", Long.class);
            if (ascending.equals("null") || ascending.equals("false")) {
                for (int i = 0; i < extractedOrderId.size() - 1; i++)
                    assertThat("Results are not sorted by desc!", extractedOrderId.get(i), is(greaterThanOrEqualTo(extractedOrderId.get(i + 1))));
            } else {
                for (int i = 0; i < extractedOrderId.size() - 1; i++)
                    assertThat("Results are not sorted by asc!", extractedOrderId.get(i), is(lessThanOrEqualTo(extractedOrderId.get(i + 1))));
            }
        }

        if (!searchField.equals("null") && !keyword.equals("null")) {
            List<String> extractedSearchFields = response.getList("results." + searchField, String.class);
            assertThat("Every search results should contain keyword!", extractedSearchFields, everyItem(containsString(keyword)));
        }

        //contract
        response.validateContract("redemptionsList-response-schema.json", 200);
    }

    //Get redemptions list - system message validation
    @When("^Get redemptions list call is made with following data '(.+?)', '(\\d+)', '(.+?)', '(.+?)'$")
    public void getRedemptionsListErrorResponse(String params, int status, String expErrorCode, String expErrorMsg) throws Throwable {
        response = redemptionsActions.getRedemptions(params, status);
        errorsValidator.validateHeaderErrorResponse(response, expErrorCode, redemptionsActions.createErrorMessage(params, expErrorCode, expErrorMsg));
    }

    @Then("^Get redemptions list response for request without proper data should be empty$")
    public void getRedemptionsListIsEmpty() throws Throwable {
        assertThat("Response is not null", response.toString(), isEmptyOrNullString());
    }


    //Get redemptions by id and check response contract
    @Given("^Get redemptions list default call is made$")
    public void getRedemptionsListDefault() throws Throwable {
        response = redemptionsActions.getRedemptions("accessKey,null,null,null,null,null,null,null,null,null", 200);
    }

    @Given("^Random redemption id is selected from Get redemptions list default call response$")
    public void getRedemptionsListDefaultResponseAndSelectRandomRedemptionId() throws Throwable {
        List<String> redemptionIds = response.getList("results.redemptionId", String.class);
        redemptionId = redemptionIds.get(new Random().nextInt(redemptionIds.size()));
        assertThat("Missing redemptionId", redemptionId, not(isEmptyOrNullString()));
    }

    @When("^Get redemptions by id call is made with data '(.+?)'$")
    public void getRedemptionsById(String params) throws Throwable {
        response = redemptionsActions.getRedemptionsById(params + "," + redemptionId, 200);
    }

    @Then("^Get redemptions by id response match contract$")
    public void getRedemptionsByIdMatchContract() throws Throwable {
        assertThat("Response redemption Id is not same as in request", response.getString("id"), is(redemptionId));
        response.validateContract("redemptionsById-response-schema.json", 200);
    }

    //Get redemptions by id - system message validation
    @When("^Get redemptions by id call is made with following data '(.+?)', '(\\d+)', '(.+?)', '(.+?)'$")
    public void getRedemptionsByIdErrorResponse(String params, int status, String expErrorCode, String expErrorMsg) throws Throwable {
        response = redemptionsActions.getRedemptionsById(params, status);
        errorsValidator.validateHeaderErrorResponse(response, expErrorCode, redemptionsActions.createErrorMessage(params, expErrorCode, expErrorMsg));
    }

    @Then("^Get redemptions by id response for request without proper data should be empty$")
    public void getRedemptionsByIdIsEmpty() throws Throwable {
        assertThat("Response is not null", response.toString(), isEmptyOrNullString());
    }

    //Create new redemptions fulfill update and check response
    @Given("^Get redemptions list call is made with default epoints partner data$")
    public void getRedemptionsListWithStatusFulFill() throws Throwable {
        response = redemptionsActions.getRedemptions("accessKey,null,null,null,null,null,null,status,FULFILL,null", 200);
        redemptionId = response.getString("results[0].redemptionId");
        assertThat("Missing redemptionId", redemptionId, not(isEmptyOrNullString()));
    }

    @When("^Create new redemptions fulfill call is made with data '(.+?)', '(.+?)'$")
    public void createNewRedemptionsFulfillUpdate(String apiKey, String fulfill) throws Throwable {
        response = redemptionsActions.createRedemptionsUpdate(apiKey + "," + redemptionId, fulfill + "," + new Date().getTime() + ",REDEEMED", 200);
    }

    @Then("^Create redemptions fulfill response data is true$")
    public void createNewRedemptionsFulfillUpdateResponseMatchContract() throws Throwable {
        assertThat("Redemptions fulfill update failed", response.toString(), is("true"));
    }

    //Create new redemptions fulfill update - system message validation
    @When("^Create new redemptions fulfill call is made with following data '(.+?)', '(.+?)', '(\\d+)', '(.+?)', '(.+?)'$")
    public void createNewRedemptionsFulfillUpdateError(String params, String jsonBody, int status, String expErrorCode, String expErrorMsg) throws Throwable {
        response = redemptionsActions.createRedemptionsUpdate(params, jsonBody, status);
        errorsValidator.validateHeaderErrorResponse(response, expErrorCode, redemptionsActions.createErrorMessage(params, expErrorCode, expErrorMsg));
    }

    @Then("^Create new redemptions fulfill response for request without proper data should be empty$")
    public void createNewRedemptionsFulfillUpdateIsEmpty() throws Throwable {
        assertThat("Response is not null", response.toString(), isEmptyOrNullString());
    }


    //Create new redemptions refund and check response
    @When("^Create new redemptions refund call is made with data '(.+?)', '(.+?)'$")
    public void createNewRedemptionsRefund(String apiKey, String activityInfo) throws Throwable {
        mapper.enable(JsonParser.Feature.IGNORE_UNDEFINED, JsonParser.Feature.ALLOW_MISSING_VALUES);
        OrderRefund orderRefund = mapper.readValue("{\"id\":\"" + redemptionId + "\",\"activityInfo\":\"" + activityInfo + "\",\"refundDate\":\"" + helpFunctions.returnEpochOfCurrentDay() + "\"}", OrderRefund.class);

        response = redemptionsActions.createRedemptionsRefund(apiKey, mapper.writeValueAsString(orderRefund), 200);
    }

    @Then("^Create redemptions refund response match contract and data '(.+?)'$")
    public void createNewRedemptionsRefundMatchContract(String activityInfo) throws Throwable {
        //moved to controller class after refactoring
    }


    //Create new redemptions refund - system message validation
    @When("^Create new redemptions refund call is made with following data '(.+?)', '(.+?)', '(\\d+)', '(.+?)', '(.+?)'$")
    public void createNewRedemptionsRefundError(String params, String jsonBody, int status, String expErrorCode, String expErrorMsg) throws Throwable {
        response = redemptionsActions.createRedemptionsRefund(params, jsonBody, status);
        System.out.print(response);
    }

    @Then("^Create new redemptions refund response should include proper message for following data '(.+?)', '(.+?)', '(.+?)', '(.+?)', '(.+?)'$")
    public void create_new_redemptions_refund_response_for_request_without_proper_data_should_be_empty(String params, String jsonBody, String expResponseCode, String expErrorCode, String expErrorMsg) throws Throwable {


        //TODO Validation for response missing

    }
}