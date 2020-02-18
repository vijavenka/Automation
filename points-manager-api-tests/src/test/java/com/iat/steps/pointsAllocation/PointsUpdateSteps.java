package com.iat.steps.pointsAllocation;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iat.Config;
import com.iat.actions.accountManagement.AccountBalanceActions;
import com.iat.actions.pointsAllocation.AddPointsActions;
import com.iat.actions.pointsAllocation.PointsUpdateActions;
import com.iat.domain.pointsAllocation.PointsTransaction;
import com.iat.domain.pointsAllocation.PointsTransactionUpdate;
import com.iat.repository.UserRepository;
import com.iat.repository.impl.UserRepositoryImpl;
import com.iat.utils.ResponseContainer;
import com.iat.utils.ResponseHolder;
import com.iat.validators.ErrorsValidator;
import com.iat.validators.pointsAllocation.PointsUpdateValidator;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isEmptyOrNullString;

public class PointsUpdateSteps {

    private PointsUpdateActions pointsUpdateActions = new PointsUpdateActions();
    private AddPointsActions addPointsActions = new AddPointsActions();
    private AccountBalanceActions accountBalanceActions = new AccountBalanceActions();
    private ResponseHolder responseHolder = ResponseHolder.getInstance();
    private UserRepository userRepository = new UserRepositoryImpl();
    private ObjectMapper mapper = new ObjectMapper();
    private PointsUpdateValidator pointsUpdateValidator = new PointsUpdateValidator();
    private ResponseContainer response;
    private String transactionId;
    private ResponseContainer oldBalanceInfo;
    private ErrorsValidator errorsValidator = new ErrorsValidator();

    //Scenario Outline: Update points transaction - proper points transaction update
    @Given("^User '(.+?)' is awarded with points for his activities '(.+?)'$")
    public void createNewPointsAllocationForSpecifiedUser(String userEmail, String pointsParameters) throws Throwable {
        responseHolder.setUserId(userRepository.findByEmail(userEmail).getUuid());

        mapper.enable(JsonParser.Feature.IGNORE_UNDEFINED, JsonParser.Feature.ALLOW_MISSING_VALUES);
        PointsTransaction pointsTransaction = mapper.readValue(pointsParameters, PointsTransaction.class);
        responseHolder.setPointsTransaction(pointsTransaction);
        if (responseHolder.getPointsTransaction().getApiAccessKey().equals("envDepends"))
            responseHolder.getPointsTransaction().setApiAccessKey(Config.getPartnerAccessKey());
        response = addPointsActions.addPointsToUserByIdProperly(mapper.writeValueAsString(pointsTransaction), responseHolder.getUserId(), "UUID", 201);
        transactionId = response.toString();

        oldBalanceInfo = accountBalanceActions.getAccountBalance(responseHolder.getUserId(), "UUID", 200);

    }

    @When("^Partner update user transaction details data '(.+?)'$")
    public void updateSpecifiedPointsAllocationData(String pointsParametersUpdate) throws Throwable {
        mapper.enable(JsonParser.Feature.IGNORE_UNDEFINED, JsonParser.Feature.ALLOW_MISSING_VALUES);
        PointsTransactionUpdate pointsTransactionUpdate = mapper.readValue(pointsParametersUpdate, PointsTransactionUpdate.class);
        responseHolder.setPointsTransactionUpdate(pointsTransactionUpdate);
        if (responseHolder.getPointsTransactionUpdate().getApiAccessKey().equals("envDepends"))
            responseHolder.getPointsTransactionUpdate().setApiAccessKey(Config.getPartnerAccessKey());
        response = pointsUpdateActions.updatePointsTransactionByTransactionId(transactionId, mapper.writeValueAsString(pointsTransactionUpdate), 200);
    }

    @Then("^User transaction details data will be updated$")
    public void checkUfSelectedTransactionWasUpdated() throws Throwable {
        pointsUpdateValidator.checkCorrectnesOfUserPointsValuesAfterUpdate(oldBalanceInfo, accountBalanceActions.getAccountBalance(responseHolder.getUserId(), "UUID", 200));
    }

    //Scenario Outline: Update points transaction - check behaviour of system when points transaction will be done with invalid parameters
    @When("^Partner update user transaction details data using invalid parameters '(.+?)', '(.+?)', '(\\d+)', '(.+?)', '(.+?)'$")
    public void updateSpecifiedPointsAllocationDataUsingInvalidParameters(String pointsParametersUpdate, String isCorrect, int status, String expErrorCode, String expErrorMsg) throws Throwable {

        mapper.enable(JsonParser.Feature.IGNORE_UNDEFINED, JsonParser.Feature.ALLOW_MISSING_VALUES);
        PointsTransactionUpdate pointsTransactionUpdate = mapper.readValue(pointsParametersUpdate, PointsTransactionUpdate.class);
        responseHolder.setPointsTransactionUpdate(pointsTransactionUpdate);
        if (responseHolder.getPointsTransactionUpdate().getApiAccessKey().equals("envDepends"))
            responseHolder.getPointsTransactionUpdate().setApiAccessKey(Config.getPartnerAccessKey());

        if (isCorrect.equals("incorrect"))
            response = pointsUpdateActions.updatePointsTransactionByTransactionId("wrongTransactionId", mapper.writeValueAsString(pointsTransactionUpdate), status);
        else {
            response = pointsUpdateActions.updatePointsTransactionByTransactionId(transactionId, mapper.writeValueAsString(pointsTransactionUpdate), status);
            expErrorMsg = expErrorMsg.replace("envDepends", Config.getPartnerAccessKey());
        }
        errorsValidator.validateHeaderErrorResponse(response, expErrorCode, expErrorMsg);
    }

    @Then("^User transaction details will not be updated$")
    public void user_transaction_details_will_not_be_updated() throws Throwable {
        assertThat("transaction was updated", response.toString(), isEmptyOrNullString());
    }

}