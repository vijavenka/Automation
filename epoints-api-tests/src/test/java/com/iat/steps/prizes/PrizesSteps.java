package com.iat.steps.prizes;

import com.iat.Config;
import com.iat.actions.prizes.PrizesActions;
import com.iat.actions.transactions.TransactionsActions;
import com.iat.actions.users.UsersActions;
import com.iat.domain.UserBalance;
import com.iat.domain.transactions.PointsDetails;
import com.iat.repository.impl.UserRepositoryImpl;
import com.iat.steps.TransitionSteps;
import com.iat.utils.DataExchanger;
import com.iat.utils.JdbcDatabaseConnector;
import com.iat.utils.ResponseContainer;
import com.iat.validators.prizes.PrizesValidator;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.Date;
import java.util.List;

import static com.iat.utils.DateTimeUtil.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PrizesSteps {

    private DataExchanger dataExchanger = DataExchanger.getInstance();
    private PrizesActions prizesActions = new PrizesActions();
    private TransitionSteps transitionSteps = new TransitionSteps();
    private PrizesValidator prizesValidator = new PrizesValidator();
    private JdbcDatabaseConnector mySQLConnectorPointsManager = new JdbcDatabaseConnector(Config.mysqlConnectionPoolPointsManager);
    private UserBalance userBalance;
    private TransactionsActions transactionsActions = new TransactionsActions();
    private UsersActions usersActions = new UsersActions();
    private ResponseContainer response;
    private List spin;

    @Given("^User spins number is set to '(\\d+)', '(.+?)'$")
    public void setSpinNumberForUser(int spinsNumber, String spinsStatus) throws Throwable {
        String merchantId = "70324625-314d-4fe9-99e9-0eacde8b3d5a";
        String affiliatNetworkId = "d8fcae82-e536-4721-8d4d-7eec532eedc3";

        transitionSteps.generateClickoutStoreAndValidate(merchantId);
        transitionSteps.updateClickoutReferenceAndValidate(affiliatNetworkId, "%", "PENDING");

        if (spinsNumber > 0) {
            if (spinsStatus.equals("DECLINED"))
                transitionSteps.updateClickoutReferenceAndValidate(affiliatNetworkId, "%", "DECLINED");
            else if (spinsStatus.equals("USED"))
                transactionsActions.postTransactions(preparePointsDetails(1, "PENDING"), dataExchanger.getUserProfile().getAccountDetails().getId(), 201);
        } else {
//            create clickout for different type to be sure that @deleteClickoutAndPointsAfterTest hook will not fail and has something to be deleted
//            on the other hand it will ensure that count returns only spins for requested status
            if (spinsStatus.equals("AVAILABLE"))
                transitionSteps.updateClickoutReferenceAndValidate(affiliatNetworkId, "%", "DECLINED");
        }
    }

    @When("^Spins number request will be done for '(.+?)', and user '(.+?)', (\\d+)$")
    public void getUserSpinNumber(String spinsStatus, String userUuid, int status) throws Throwable {
        if (userUuid.equals("epointsUserDefault_1")) {
            String userEmail = Config.getEpointsUserDefault_1.split(",")[0];
            userUuid = new UserRepositoryImpl().findByEmail(userEmail).getUuid();
        }
        response = prizesActions.getSpinsNumber(userUuid, spinsStatus, status);
    }

    @Then("^Correct number of spins '(\\d+)', '(.+?)' will be returned in response$")
    public void validateSpinsNumberCorrectness(int spinsNumber, String spinsStatus) throws Throwable {
        assertThat("Wrong number of spins", response.getInt("count"), is(spinsNumber));
    }

    @Then("^Spins count error message will be thrown with '(\\d+)', '(.+?)', '(.+?)'$")
    public void checkSpinsCountErrorMessageCorrectness(int status, String error, String message) throws Throwable {
        assertThat("Wrong response status", response.getInt("status"), is(status));
        assertThat("Wrong response error", response.getString("error"), is(error));
        assertThat("Wrong response message", response.getString("message"), is(message));
    }

    @When("^Spin is used to win (\\d+) points \\(pointsStatus: (.+?)\\), (\\d+)$")
    public void spinIsUsedToWinPoints(int howManyPoints, String pointsType, int status) throws Throwable {
        userBalance = usersActions.getUserBalance("null", 200).getAsObject(UserBalance.class);
        response = transactionsActions.postTransactions(preparePointsDetails(howManyPoints, pointsType), dataExchanger.getUserProfile().getAccountDetails().getId(), status);
        if (status == 201) dataExchanger.setSpinUsed(true);
    }

    @Then("^Spin's status is ([^\"]*)$")
    public void spinSStatusIs(String spinStatus) throws Throwable {
        spin = prizesValidator.validateSpinStatus(spinStatus);
    }

    @Then("^Spin points record is created with points$")
    public void spinPointsRecordIsCreated() throws Throwable {
        int howManyPoints = dataExchanger.getPointsDetails().getNumPoints();
        prizesValidator.validateSpinPointsCorrectness(howManyPoints, spin.get(0).toString(), (long) spin.get(1));
        prizesValidator.validateUserBalanceAfterAwarding(howManyPoints, userBalance);
    }

    @Then("^Spin awarding results with error ([^\"]*), ([^\"]*)$")
    public void spinAwardingResultsWithError(String errorCode, String errorMsg) throws Throwable {
        if (errorMsg.contains("$SPIN-UUID"))
            errorMsg = errorMsg.replace("$SPIN-UUID", dataExchanger.getPointsDetails().getExternalTransactionId());
        if (errorMsg.contains("$USER-UUID"))
            errorMsg = errorMsg.replace("$USER-UUID", dataExchanger.getUserProfile().getAccountDetails().getId());

        response.getValidatableResponse().assertThat()
                .header("X-Error-Code", is(errorCode))
                .and()
                .header("X-Error-Message", is(errorMsg));
    }

    private PointsDetails preparePointsDetails(int howManyPoints, String pointsType) {
        String spinUuid;
        if (dataExchanger.getClickoutDb() == null)
            spinUuid = null;
        else {
            String pointsIdOfClickout = dataExchanger.getClickoutDb().getPointsId();
            spinUuid = mySQLConnectorPointsManager.getSingleResult("SELECT spinUuid from points_manager.Spin WHERE originalPointsId = " + pointsIdOfClickout);
        }
        PointsDetails pointsDetails = new PointsDetails(howManyPoints, pointsType, spinUuid);
        dataExchanger.setPointsDetails(pointsDetails);
        return pointsDetails;
    }

    @Then("^Number of new spin Points record is (\\d+)$")
    public void numberOfNewPointsRecordIs(int expectedAmountOfPointsRecords) throws Throwable {
        String date = convertToString(adjustDateBySeconds(new Date(), -90), Format.yyyyMMddHHmmss, true);

        String count = mySQLConnectorPointsManager.getSingleResult("SELECT count(*) from points_manager.Points pts" +
                " left join points_manager.Tag t on pts.tagId = t.id" +
                " where t.tagKey = 'roulette' and" +
                " pts.createdAt > '" + date + "'");

        assertThat("Wrong amount of expected new spin Points records!", Integer.parseInt(count), is(expectedAmountOfPointsRecords));
    }
}