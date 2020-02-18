package com.iat.steps.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iat.actions.users.UsersActions;
import com.iat.domain.LoginSuccess;
import com.iat.domain.rewardsHistory.Reward;
import com.iat.domain.rewardsHistory.RewardsHistory;
import com.iat.domain.transactions.Transactions;
import com.iat.domain.userProfile.Address;
import com.iat.repository.impl.UserRepositoryImpl;
import com.iat.utils.DataExchanger;
import com.iat.utils.ResponseContainer;
import com.iat.validators.users.UsersValidator;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class UsersSteps {

    private ResponseContainer response;
    private UsersActions usersActions = new UsersActions();
    private UsersValidator userValidator = new UsersValidator();
    private Transactions transactions;
    private RewardsHistory rewardsHistory;

    private DataExchanger dataExchanger = DataExchanger.getInstance();
    private ObjectMapper mapper = new ObjectMapper();


    @When("^User balance will be requested for '(.+?)'$")
    public void getUserBalance(String businessId) throws Throwable {
        response = usersActions.getUserBalance(businessId, 200);
    }

    @Then("^Correct balance value will be returned for '(.+?)'$")
    public void checkCorrectnessOfUserBalance(String businessId) throws Throwable {
        userValidator.validateUserBalanceCorrectness(businessId, response);
    }

    @When("^User transactions will be requested for parameters '(.+?)', '(\\d+)', '(\\d+)', '(.+?)', '(.+?)', '(.+?)', '(\\d+)'$")
    public void getUserTransactionsForParameters(String uuid, int page, int size, String sort, String type, String businessId, int status) throws Throwable {
        if (uuid.equals("previous_call")) uuid = dataExchanger.getUuid();
        response = usersActions.getUserTransactions(uuid, page, size, sort, type, businessId, status);
        if (status == 200)
            transactions = response.getAsObject(Transactions.class);
    }

    @Then("^Correct transactions will be returned for parameters '(.+?)', '(\\d+)', '(\\d+)', '(.+?)', '(.+?)', '(.+?)'$")
    public void validateReturnedTransactionsDataCorrectness(String uuid, int page, int size, String sort, String type, String businessId) throws Throwable {
        if (uuid.equals("previous_call")) uuid = dataExchanger.getUuid();
        userValidator.validateUserTransactionsCorrectness(uuid, page, size, sort, type, businessId, transactions);
    }

    @Then("^Correct transactions error message will be returned for parameters '(.+?)', '(.+?)'$")
    public void validateTransactionsErrorResponse(String error, String message) throws Throwable {
        userValidator.validateErrorResponse(response, error, message);
    }

    @When("^User rewards history will be requested for parameters '(.+?)', '(\\d+)', '(.+?)', '(\\d+)'$")
    public void getUserRewardsHistoryForParameters(String email, int size, String businessId, int status) throws Throwable {
        if (email.equals("previous_call"))
            email = dataExchanger.getEmail();
        response = usersActions.getUserRewardsHistory(email, size, businessId, status);
        if (status == 200)
            rewardsHistory = new RewardsHistory(response.getList("", Reward.class));
    }

    @Then("^Correct rewards history will be returned for parameters '(.+?)', '(\\d+)', '(.+?)'$")
    public void validateReturnedRewardsHistoryDataCorrectness(String email, int size, String businessId) throws Throwable {
        String uuid;
        if (email.equals("previous_call"))
            uuid = dataExchanger.getUuid();
        else
            uuid = new UserRepositoryImpl().findByEmail(email).getUuid();
        userValidator.validateUserRewardHistoryCorrectness(uuid, size, businessId, rewardsHistory);
    }

    @Then("^Correct rewards history error message will be returned for parameters '(.+?)', '(.+?)'$")
    public void validateRewardsHistoryErrorResponse(String error, String message) throws Throwable {
        userValidator.validateErrorResponse(response, error, message);
    }


    @Then("^User delivery address is set '(.+?)'$")
    public void addNewDeliveryAddress(String jsonData) throws Throwable {
        Address address;

        switch (jsonData.toUpperCase()) {
            case "DEFAULT":
                address = new Address("firstName", "lastName", "123e", "asdasd", "sdas", "dasdas", "UK", "EN92NY", "348299228");
                break;
            default:
                address = mapper.readValue(jsonData, Address.class);
        }
        dataExchanger.setDeliveryAddress(address);
    }

    @And("^Account dashboard profile completion is set to '(.+?)'$")
    public void checkIfProfileCompletedFlagChangedAfterCotactPersonalDetailsSet(String profileCompleted) throws Throwable {
        response = usersActions.getAccountDashboard(dataExchanger.getUuid(), 0, 10, "latest,desc", "CURRENT", 200);
        userValidator.checkProfileCompletedState(response, profileCompleted);
    }

    @And("^Fetching membershipType$")
    public void fetchingMembershipType() {
        response = usersActions.getMembershipType(200);
        dataExchanger.setLoginSuccess(response.getAsObject(LoginSuccess.class));
    }
}