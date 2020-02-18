package com.iat.steps;

import com.iat.Config;
import com.iat.actions.HealthCheckActions;
import com.iat.actions.TransactionSearchActions;
import com.iat.utils.JdbcDatabaseConnector;
import com.iat.utils.ResponseContainer;
import cucumber.api.java.en.*;

import java.text.SimpleDateFormat;
import java.util.*;

import static com.iat.utils.DateTimeUtil.adjustDateByDays;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AffiliateManagerSteps {
    private HealthCheckActions healthCheckActions = new HealthCheckActions();
    private TransactionSearchActions transactionSearchActions = new TransactionSearchActions();
    private ResponseContainer response;
    private JdbcDatabaseConnector mySQLConnector = new JdbcDatabaseConnector(Config.mysqlConnectionPoolAffiliateManagerLIVE);
    private HashMap<Integer,List> sqlResults;

    @Given("^Affiliate manager is responding to healthcheck$")
    public void healthCheck() throws Throwable {
        healthCheckActions.doHealthCheck();
    }

    @When("^User searches for clickouts between (\\d+) days old and (\\d+) days old$")
    public void transactionSearchBetween(int startDaysOld, int endDaysOld) throws Throwable {
        Date from = new Date();
        Date to = from;

        from = adjustDateByDays(from, -startDaysOld);
        to = adjustDateByDays(to, -endDaysOld);

        response = transactionSearchActions.searchClickoutsBetweenDates(from, to, 200);
    }

    @Then("^No ([^\"]*) clickouts are found$")
    public void makeSureNoClickoutsWithStatus(String clickoutStatus) throws Throwable {
        //retrieving the ids of the clickouts which are having unwanted status
        List<Long> clickoutsIdsWithStatus =
                response.getList("transactions.findAll {it.clickoutStatus == '" + clickoutStatus + "'}.id");

        assertThat("There should be no clickouts with status '" + clickoutStatus,
                clickoutsIdsWithStatus, is(empty()));
    }


    @When("^User searches for '(.+?)' clickouts with daysToConfirm '(.+?)' between (\\d+) days old and (\\d+) days old in database$")
    public void selectTransactionsFromRangeDB(String leads, String daysToConfirm, int startDaysOld, int endDaysOld) throws Throwable {
        Date from = new Date();
        Date to = from;

        from = adjustDateByDays(from, -startDaysOld);
        to = adjustDateByDays(to, -endDaysOld);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        String query = "Select * from Clickout " +
                "where networkTransactionDate BETWEEN '" + format.format(from) + "' " +
                "AND '" + format.format(to) + "' " +
                "AND leads = " + (leads.contains("not") ? "0" : "1") + " ";

//        if (!daysToConfirm.equals("null"))
//            query  += " AND daysToConfirm <= " + daysToConfirm;
//        else
//            query +=  " AND daysToConfirm = " + daysToConfirm;
        query += daysToConfirm;
        sqlResults = mySQLConnector.getResult(query, Arrays.asList("id", "clickoutStatus", "createdDate", "networkTransactionDate", "updateStatusDate", "leads", "daysToConfirm"));

    }

    @Then("^No ([^\"]*) clickouts are found in database$")
    public void validateNoClickoutWithSpecificStatus(String clickoutStatus) throws Throwable {
//        System.out.println(sqlResults.values());

        for (List<String> transactionRow : sqlResults.values()) {
            System.out.print("TESTED Transaction: " + transactionRow);
            assertThat("Improper status! ", transactionRow.get(1), not(is(clickoutStatus)));
            System.out.println(" >> Passed!");
        }
    }

}
