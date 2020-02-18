package com.iat.steps.couponUsage;

import com.iat.Config;
import com.iat.actions.couponUsage.CouponUsageActions;
import com.iat.actions.users.UsersActions;
import com.iat.domain.UserBalance;
import com.iat.domain.couponUsage.CouponUsageCashIn;
import com.iat.repository.impl.UserRepositoryImpl;
import com.iat.utils.DataExchanger;
import com.iat.utils.HelpFunctions;
import com.iat.utils.JdbcDatabaseConnector;
import com.iat.utils.ResponseContainer;
import com.iat.validators.CouponUsageValidator;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CouponUsageSteps {

    private CouponUsageActions couponUsageActions = new CouponUsageActions();
    private CouponUsageValidator couponUsageValidator = new CouponUsageValidator();
    private UsersActions usersActions = new UsersActions();
    private JdbcDatabaseConnector mySQLConnector = new JdbcDatabaseConnector(Config.mysqlConnectionPoolPointsManager);
    private HelpFunctions helpFunctions = new HelpFunctions();

    private DataExchanger dataExchanger = DataExchanger.getInstance();

    private ResponseContainer response;

    @Before("@PointForCouponRedeemAreAdded")
    public void PointForCouponRedeemAreAdded() {
        System.out.println("\nADDING Points for coupons usage");
        String userId = mySQLConnector.getSingleResult("SELECT id FROM points_manager.User WHERE userKey = '" + new UserRepositoryImpl().findByEmail(Config.getNewsUserDefault_1.split(",")[0]).getUuid() + "'");
        mySQLConnector.execute("UPDATE points_manager.Points SET delta = '20000' WHERE userId = '" + userId + "' AND tagId = '" + mySQLConnector.getSingleResult("SELECT id FROM points_manager.Tag WHERE tagKey = 'couponRedemptionEpointsEarned'") + "'");
        mySQLConnector.execute("UPDATE points_manager.Points SET delta = '20000' WHERE userId = '" + userId + "' AND tagId = '" + mySQLConnector.getSingleResult("SELECT id FROM points_manager.Tag WHERE tagKey = 'couponCashInEpointsEarned'") + "'");
        mySQLConnector.execute("UPDATE points_manager.User SET confirmed = '100000' WHERE id = '" + userId + "'");
    }

    @When("^News branch manager pull 'coupon usage' list$")
    public void getCouponUsageList() throws Throwable {
        response = couponUsageActions.getCouponUsageList("0", "99999");
    }

    @Then("^All coupons redemption of manager branch will be returned$")
    public void checkCouponUsageListResponseCorrectness() throws Throwable {
        response.validateContract("/couponUsage/GET-coupon-usage.json");
    }

    @Then("^Coupon usage number is correct$")
    public void checkCouponUsageNumberCorrectness() throws Throwable {
        couponUsageValidator.checkNumberCorrectnessOfCouponUsageListElements(response);
    }

    @Then("^Data will be returned in redeemed date desc order$")
    public void checkCouponUsageListOrder() throws Throwable {
        couponUsageValidator.checkOrderCorrectnessOfCouponUsageList(response);
    }

    @Then("^All coupon usages belongs to managers store$")
    public void checkCouponUsageListStore() throws Throwable {
        couponUsageValidator.checkStoreCorrectnessOfCouponUsageList(response);
    }

    @Then("^Zero results will be returned$")
    public void checkIfNoResultsWereReturnedForNotNewsUser() throws Throwable {
        couponUsageValidator.checkIfZeroResultsWereReturnedForNotNewsUser(response);
    }

    @Given("^User coupon usage points summary is known$")
    public void storeUserCouponUsagePointsSummary() throws Throwable {
        response = couponUsageActions.getCouponUsagePointsSummary();
        response.validateContract("/couponUsage/GET-coupon-usage-summary.json");
        dataExchanger.setUserCouponUsagePointsSummary(response);
    }

    @Given("^User balance is known$")
    public void storageUserBalance() throws Throwable {
        ResponseContainer response = usersActions.getUserBalance("null", 200);
        dataExchanger.setUserBalance(response.getAsObject(UserBalance.class));
    }

    @When("^User cash-in some epoints$")
    public void cashInEpoints() throws Throwable {
        String epontsValue = Integer.toString(2000 + helpFunctions.returnRandomValue(5000));
        String sortCode = Integer.toString(20 + helpFunctions.returnRandomValue(20)) + "-" + Integer.toString(20 + helpFunctions.returnRandomValue(20)) + "-" + Integer.toString(20 + helpFunctions.returnRandomValue(20));
        String accountNumber = Integer.toString(123456789 + helpFunctions.returnRandomValue(123456789));
        String accountHoldersName = "accountHoldersName_" + new Date().getTime();
        String usersReference = "userReference_" + new Date().getTime();

        CouponUsageCashIn couponUsageCashIn = new CouponUsageCashIn(epontsValue, sortCode, accountNumber, accountHoldersName, usersReference);
        dataExchanger.setCouponUsageCashIn(couponUsageCashIn);
        couponUsageCashIn.toJson();
        response = couponUsageActions.cashIn(couponUsageCashIn.toJsonRequest(), 200);
    }

    @Then("^Balance confirmed points will be constantly decreased of cashed points$")
    public void checkIfPointsWereDeductedFromUserBalance() throws Throwable {
        couponUsageValidator.checkIfPointsWereDeductedFromUserBalance();
    }

    @Then("^User coupon usage points summary is updated of cashed points$")
    public void checkIfUserCouponUsagePointsSummaryWasUpdated() throws Throwable {
        couponUsageValidator.checkIfUserCouponUsagePointsSummaryWasUpdated();
    }

    @Then("^User try to cash epoints with following data '(.+?)'$")
    public void tryToCahInPointsWithIncorrectData(String jsonBody) throws Throwable {
        String epontsValue = jsonBody.split(",")[0];
        String sortCode = jsonBody.split(",")[1];
        String accountNumber = jsonBody.split(",")[2];
        String accountHoldersName = jsonBody.split(",")[3];
        String usersReference = jsonBody.split(",")[4];

        CouponUsageCashIn couponUsageCashIn = new CouponUsageCashIn(epontsValue, sortCode, accountNumber, accountHoldersName, usersReference);
        dataExchanger.setCouponUsageCashIn(couponUsageCashIn);
        couponUsageCashIn.toJson();
        response = couponUsageActions.cashIn(couponUsageCashIn.toJsonRequest(), 400);
    }

    @Then("^Cash-in returns proper error message '(.+?)'$")
    public void validateCashInErrorMessage(String message) throws Throwable {
        String messageExtracted = response.getString("message");
        assertThat("Incorrect message!", messageExtracted, is(message));
    }

}