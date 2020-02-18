package com.iat.steps.pointsAllocation;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iat.Config;
import com.iat.actions.accountManagement.AccountActivationActions;
import com.iat.actions.accountManagement.AccountBalanceActions;
import com.iat.actions.partnersManagement.PartnersListingActions;
import com.iat.actions.pointsAllocation.AddPointsActions;
import com.iat.actions.usersManagement.UserTransactionHistoryActions;
import com.iat.domain.pointsAllocation.MultiplePointsTransaction;
import com.iat.domain.pointsAllocation.PointsTransaction;
import com.iat.repository.UserRepository;
import com.iat.repository.impl.UserRepositoryImpl;
import com.iat.utils.HelpFunctions;
import com.iat.utils.ResponseContainer;
import com.iat.utils.ResponseHolder;
import com.iat.validators.ErrorsValidator;
import com.iat.validators.pointsAllocation.PointsAllocationValidator;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AddPointsSteps {

    private ResponseHolder responseHolder = ResponseHolder.getInstance();
    private AccountActivationActions accountActivationActions = new AccountActivationActions();
    private AccountBalanceActions accountBalanceActions = new AccountBalanceActions();
    private ObjectMapper mapper = new ObjectMapper();
    private HelpFunctions helpFunctions = new HelpFunctions();
    private ErrorsValidator errorsValidator = new ErrorsValidator();

    private UserRepository userRepository = new UserRepositoryImpl();
    private PartnersListingActions partnersListingActions = new PartnersListingActions();
    private AddPointsActions addPointsActions = new AddPointsActions();
    private PointsAllocationValidator pointsAllocationValidator = new PointsAllocationValidator();
    private UserTransactionHistoryActions userTransactionHistoryActions = new UserTransactionHistoryActions();
    private ResponseContainer userBalance;
    private List<ResponseContainer> usersBalances;
    private List<ResponseContainer> usersBalancesAfterBulkUpload;
    private ResponseContainer response;

    //Scenario Outline: Points allocation to the user - default positive parameters
    @Given("^Active '(.+?)' wants to award '(.+?)' with points using '(.+?)'$")
    public void getStatusOfPartnerAndUser(String partner, String id, String idType) {
        responseHolder.setUserIdType(idType);
        if (idType.equals("UUID"))
            responseHolder.setUserId(userRepository.findByEmail(id).getUuid());
        else if (idType.equals("EMAIL"))
            responseHolder.setUserId(id);
        assertThat("Account is not active!", accountActivationActions.getAccountActivationStatus(responseHolder.getUserId(), idType));
        assertThat("Partner doesn't exists: " + partner, partnersListingActions.checkPartnerAvailabilityStatus(partner));

        userBalance = accountBalanceActions.getAccountBalance(responseHolder.getUserId(), idType, 200);
    }

    @When("^He makes points allocation call with '(.+?)'$")
    public void addPointsToUser(String pointsParameters) throws IOException {
        mapper.enable(JsonParser.Feature.IGNORE_UNDEFINED, JsonParser.Feature.ALLOW_MISSING_VALUES);
        PointsTransaction pointsTransaction = mapper.readValue(pointsParameters, PointsTransaction.class);
        responseHolder.setPointsTransaction(pointsTransaction);
        if (responseHolder.getPointsTransaction().getApiAccessKey().equals("envDepends"))
            responseHolder.getPointsTransaction().setApiAccessKey(Config.getPartnerAccessKey());

        response = addPointsActions.addPointsToUserByIdProperly(mapper.writeValueAsString(pointsTransaction), responseHolder.getUserId(), responseHolder.getUserIdType(), 201);
    }

    @Then("^Points should be added to the user balance$")
    public void verifyUserBalance() {
        pointsAllocationValidator.assertPointsAfterTransaction(userBalance,
                accountBalanceActions.getAccountBalance(responseHolder.getUserId(), responseHolder.getUserIdType(), 200),
                responseHolder.getPointsTransaction());
    }

    @And("^Transaction ID should be returned$")
    public void validateAddPointsResponse() {
        assertThat("Transaction ID is not returned", response.toString().split(",")[0], not(isEmptyOrNullString()));
    }

    @Then("^Transaction data were correctly saved$")
    public void transaction_data_were_correctly_saved() throws Throwable {
        response = userTransactionHistoryActions.getUserTransactionHistory(responseHolder.getUserId() + "," + Config.getPartnerAccessKey() + "," + responseHolder.getUserIdType() + ",createdAt,null,null,null,false,null,null,null,null", 200);
        pointsAllocationValidator.compareRequestParameterWithTransactionsResponseData(responseHolder.getPointsTransaction(), response);
    }

    //Scenario Outline: Points allocation to the user - default negative parameters
    @When("^User makes points allocation call with invalid parameter '(.+?)', '(.+?)', '(.+?)', '(\\d+)', '(.+?)', '(.+?)'$")
    public void addPointsToUserWithInvalidParameters(String id, String idType, String pointsParameters, int status, String expErrorCode, String expErrorMsg) throws Throwable {
        mapper.enable(JsonParser.Feature.IGNORE_UNDEFINED, JsonParser.Feature.ALLOW_MISSING_VALUES);
        PointsTransaction pointsTransaction = mapper.readValue(pointsParameters, PointsTransaction.class);
        responseHolder.setPointsTransaction(pointsTransaction);
        if (responseHolder.getPointsTransaction().getApiAccessKey().equals("envDepends"))
            responseHolder.getPointsTransaction().setApiAccessKey(Config.getPartnerAccessKey());

        if (idType.contains("UUID") && !id.equals("notExistingEmail@gmail.com"))
            id = userRepository.findByEmail(id).getUuid();

        response = addPointsActions.addPointsToUserByIdProperly(id, idType, mapper.writeValueAsString(pointsTransaction), status);
        errorsValidator.validateHeaderErrorResponse(response, expErrorCode, expErrorMsg.replace("envDepends", Config.getPartnerAccessKey()));
    }

    @Then("^Points should not be added to the user account$")
    public void checkIfPointsWereNotAdded() throws Throwable {
//        Here is difference between ews and points manager call, PM does not provide any response, ews returns complete response
        assertThat("Points were added to account", response.toString().length(), anyOf(is(0), is(greaterThan(20))));
    }

    //Scenario Outline: Points allocation to the user - check if user can acquire points from specific tag
    @When("^User checks if he can acquire points from specific tag '(.+?)', '(.+?)', '(.+?)'$")
    public void checkIfUserCanAcquirePointsFromSpecificTag(String id, String idType, String pointsParameters) throws Throwable {
        mapper.enable(JsonParser.Feature.IGNORE_UNDEFINED, JsonParser.Feature.ALLOW_MISSING_VALUES);
        PointsTransaction pointsTransaction = mapper.readValue(pointsParameters, PointsTransaction.class);
        responseHolder.setPointsTransaction(pointsTransaction);
        if (responseHolder.getPointsTransaction().getApiAccessKey().equals("envDepends"))
            responseHolder.getPointsTransaction().setApiAccessKey(Config.getPartnerAccessKey());

        response = addPointsActions.checkPointsAcquirePossibility(id, idType, mapper.writeValueAsString(pointsTransaction), 200);
    }

    @Then("^He will receive response about points adding possibility '(.+?)'$")
    public void checkIfPointsAcquireConfirmationWasReturned(String pointsAddingPossibility) throws Throwable {
        assertThat("Points acquire confirmation was not returned", response.getString("availability"), is(pointsAddingPossibility));
    }

    //Scenario Outline: Points allocation to the user - check if user can acquire points from specific tag using invalid parameters
    @When("^User checks if he can acquire points from specific tag using invalid parameters '(.+?)', '(.+?)', '(.+?)', '(\\d+)', '(.+?)', '(.+?)'$")
    public void checkIfUserCanAcquirePointsFromSpecificTagUsingInvalidParameters(String id, String idType, String pointsParameters, int status, String expErrorCode, String expErrorMsg) throws Throwable {
        mapper.enable(JsonParser.Feature.IGNORE_UNDEFINED, JsonParser.Feature.ALLOW_MISSING_VALUES);
        PointsTransaction pointsTransaction = mapper.readValue(pointsParameters, PointsTransaction.class);
        responseHolder.setPointsTransaction(pointsTransaction);
        if (responseHolder.getPointsTransaction().getApiAccessKey().equals("envDepends"))
            responseHolder.getPointsTransaction().setApiAccessKey(Config.getPartnerAccessKey());

        response = addPointsActions.checkPointsAcquirePossibility(id, idType, mapper.writeValueAsString(pointsTransaction), status);
        errorsValidator.validateHeaderErrorResponse(response, expErrorCode, expErrorMsg.replace("envDepends", Config.getPartnerAccessKey()));
    }

    @Then("^He will not receive response about points adding possibility$")
    public void checkIfPointsAcquireConfirmationWasNotReturned() throws Throwable {
        assertThat("Points acquire confirmation was returned", response.toString(), isEmptyOrNullString());
    }

    //Scenario Outline: Points allocation to the user - award few users at once using bulk upload
    @When("^Partner awards few users at once using bulk upload '(.+?)'$")
    public void awardUserByBulkUplad(String pointsParameters) throws Throwable {
        mapper.enable(JsonParser.Feature.IGNORE_UNDEFINED, JsonParser.Feature.ALLOW_MISSING_VALUES);
        PointsTransaction pointsTransaction = mapper.readValue(pointsParameters, PointsTransaction.class);
        responseHolder.setPointsTransaction(pointsTransaction);
        if (responseHolder.getPointsTransaction().getApiAccessKey().equals("envDepends"))
            responseHolder.getPointsTransaction().setApiAccessKey(Config.getPartnerAccessKey());
        responseHolder.getPointsTransaction().getUsers().set(0, userRepository.findByEmail(responseHolder.getPointsTransaction().getUsers().get(0)).getUuid());
        responseHolder.getPointsTransaction().getUsers().set(1, userRepository.findByEmail(responseHolder.getPointsTransaction().getUsers().get(1)).getUuid());

        usersBalances = new ArrayList<>();
        for (String user : responseHolder.getPointsTransaction().getUsers())
            usersBalances.add(accountBalanceActions.getAccountBalance(user, "UUID", 200));

        response = addPointsActions.bulkUploadPoints(mapper.writeValueAsString(pointsTransaction), 201);
    }

    @Then("^Points should be properly added to awarded user accounts$")
    public void checkIfPointsWereAddedToUsersAccounts() throws Throwable {

        usersBalancesAfterBulkUpload = new ArrayList<>();
        for (String user : responseHolder.getPointsTransaction().getUsers())
            usersBalancesAfterBulkUpload.add(accountBalanceActions.getAccountBalance(user, "UUID", 200));

        for (int i = 0; i < usersBalances.size(); i++)
            pointsAllocationValidator.assertPointsAfterTransaction(usersBalances.get(i), usersBalancesAfterBulkUpload.get(i), responseHolder.getPointsTransaction());
    }

    //Scenario Outline: Points allocation to the user - award few users at once using bulk upload with invalid parameters
    @When("^Partner awards few users at once using bulk upload with invalid parameters '(.+)', '(\\d+)', '(.+?)', '(.+?)'$")
    public void awardUserByBulkUpladWithInvalidParameters(String pointsParameters, int status, String expErrorCode, String expErrorMsg) throws Throwable {
        mapper.enable(JsonParser.Feature.IGNORE_UNDEFINED, JsonParser.Feature.ALLOW_MISSING_VALUES);
        PointsTransaction pointsTransaction = mapper.readValue(pointsParameters, PointsTransaction.class);
        responseHolder.setPointsTransaction(pointsTransaction);
        if (responseHolder.getPointsTransaction().getApiAccessKey().equals("envDepends"))
            responseHolder.getPointsTransaction().setApiAccessKey(Config.getPartnerAccessKey());
        if (!responseHolder.getPointsTransaction().getUsers().get(0).contains("wrong"))
            responseHolder.getPointsTransaction().getUsers().set(0, userRepository.findByEmail(responseHolder.getPointsTransaction().getUsers().get(0)).getUuid());
        if (!responseHolder.getPointsTransaction().getUsers().get(1).contains("wrong"))
            responseHolder.getPointsTransaction().getUsers().set(1, userRepository.findByEmail(responseHolder.getPointsTransaction().getUsers().get(1)).getUuid());

        response = addPointsActions.bulkUploadPoints(mapper.writeValueAsString(pointsTransaction), status);
        errorsValidator.validateHeaderErrorResponse(response, expErrorCode, expErrorMsg.replace("envDepends", Config.getPartnerAccessKey()));
    }

    @Then("^Points should not be properly added to awarded user accounts$")
    public void checkIfPointsWereNotAddedToUsersAccounts() throws Throwable {
    }

    //Scenario Outline: Points allocation to the user - award few users at once using multiple-awards
    @When("^Partner awards few users at once using multiple-awards '(.+?)'$")
    public void awardUserByMultipleAwards(String pointsParameters) throws Throwable {
        mapper.enable(JsonParser.Feature.IGNORE_UNDEFINED, JsonParser.Feature.ALLOW_MISSING_VALUES);
        PointsTransaction pointsTransaction1 = mapper.readValue(pointsParameters.split(";")[1], PointsTransaction.class);
        PointsTransaction pointsTransaction2 = mapper.readValue(pointsParameters.split(";")[2], PointsTransaction.class);
        List<PointsTransaction> transactions = asList(pointsTransaction1, pointsTransaction2);

        mapper.enable(JsonParser.Feature.IGNORE_UNDEFINED, JsonParser.Feature.ALLOW_MISSING_VALUES);
        MultiplePointsTransaction multiplePointsTransaction = new MultiplePointsTransaction();

        String createdAt;
        if (pointsParameters.split(";")[0].equals("current"))
            createdAt = Long.toString(helpFunctions.returnEpochOfCurrentDay());
        else
            createdAt = pointsParameters.split(";")[0];
        multiplePointsTransaction.setCreatedAt(createdAt);
        multiplePointsTransaction.setDetails(transactions);

        for (int i = 0; i < multiplePointsTransaction.getDetails().size(); i++) {
            if (multiplePointsTransaction.getDetails().get(i).getApiAccessKey().equals("envDepends"))
                multiplePointsTransaction.getDetails().get(i).setApiAccessKey(Config.getPartnerAccessKey());
            multiplePointsTransaction.getDetails().get(i).setUserKey(userRepository.findByEmail(multiplePointsTransaction.getDetails().get(i).getUserKey()).getUuid());
        }

        responseHolder.setMultiplepointsTransaction(multiplePointsTransaction);

        usersBalances = new ArrayList<>();

        for (PointsTransaction detail : responseHolder.getMultiplepointsTransaction().getDetails())
            usersBalances.add(accountBalanceActions.getAccountBalance(detail.getUserKey(), "UUID", 200));

        response = addPointsActions.awardPointsToMultipleTagsAndClientsProperly(multiplePointsTransaction, 201);
    }

    @Then("^Points should be properly added to awarded by multiple awards user accounts$")
    public void checkIfPointsWereAddedToUsersAccountsByMultipleAwards() throws Throwable {
        usersBalancesAfterBulkUpload = new ArrayList<>();

        for (PointsTransaction detail : responseHolder.getMultiplepointsTransaction().getDetails())
            usersBalancesAfterBulkUpload.add(accountBalanceActions.getAccountBalance(detail.getUserKey(), "UUID", 200));

        for (int i = 0; i < usersBalances.size(); i++)
            pointsAllocationValidator.assertPointsAfterTransaction(usersBalances.get(i), usersBalancesAfterBulkUpload.get(i), responseHolder.getMultiplepointsTransaction().getDetails().get(i));
    }

    //Scenario Outline: Points allocation to the user - award few users at once using multiple-awards with invalid parameters
    @When("^Partner awards few users at once using multiple-awards with ivalid parameters values '(.+?)', '(\\d+)', '(.+?)', '(.+?)'$")
    public void awardUserByMultipleAwardsWithInvalidParametersValues(String pointsParameters, int status, String expErrorCode, String expErrorMsg) throws Throwable {
        mapper.enable(JsonParser.Feature.IGNORE_UNDEFINED, JsonParser.Feature.ALLOW_MISSING_VALUES);
        PointsTransaction pointsTransaction1 = mapper.readValue(pointsParameters.split(";")[1], PointsTransaction.class);
        PointsTransaction pointsTransaction2 = mapper.readValue(pointsParameters.split(";")[2], PointsTransaction.class);
        List<PointsTransaction> transactions = asList(pointsTransaction1, pointsTransaction2);

        MultiplePointsTransaction multiplePointsTransaction = new MultiplePointsTransaction();

        String createdAt;
        if (pointsParameters.split(";")[0].equals("current"))
            createdAt = Long.toString(helpFunctions.returnEpochOfCurrentDay());
        else
            createdAt = pointsParameters.split(";")[0];
        multiplePointsTransaction.setCreatedAt(createdAt);
        multiplePointsTransaction.setDetails(transactions);

        for (PointsTransaction detail : multiplePointsTransaction.getDetails()) {
            if (detail.getApiAccessKey().equals("envDepends"))
                detail.setApiAccessKey(Config.getPartnerAccessKey());
            if (!detail.getUserKey().contains("wrong"))
                detail.setUserKey(userRepository.findByEmail(detail.getUserKey()).getUuid());
        }

        responseHolder.setMultiplepointsTransaction(multiplePointsTransaction);

        response = addPointsActions.awardPointsToMultipleTagsAndClientsProperly(multiplePointsTransaction, status);
        errorsValidator.validateHeaderErrorResponse(response, expErrorCode, expErrorMsg.replace("envDepends", Config.getPartnerAccessKey()));
    }

}