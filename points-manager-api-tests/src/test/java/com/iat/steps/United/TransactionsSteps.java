package com.iat.steps.United;

import com.iat.Config;
import com.iat.actions.United.TransactionsActions;
import com.iat.domain.Account;
import com.iat.domain.Points;
import com.iat.repository.AccountRepository;
import com.iat.repository.PointsRepository;
import com.iat.repository.impl.AccountRepositoryImpl;
import com.iat.repository.impl.PointsRepositoryImpl;
import com.iat.repository.impl.TagRepositoryImpl;
import com.iat.utils.DataExchanger;
import com.iat.utils.JdbcDatabaseConnector;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.HashMap;
import java.util.List;

import static java.lang.Thread.sleep;
import static java.util.Arrays.asList;
import static org.exparity.hamcrest.date.DateMatchers.sameDay;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TransactionsSteps {

    private AccountRepository accountRepository = new AccountRepositoryImpl();
    private PointsRepository pointsRepository = new PointsRepositoryImpl();
    private TransactionsActions transactionsActions = new TransactionsActions();
    private DataExchanger dataExchanger = DataExchanger.getInstance();
    private JdbcDatabaseConnector mySQLConnector = new JdbcDatabaseConnector(Config.mysqlConnectionPool);

    @When("^File '(.+?)' for client '(.+?)' with transactions will be processed '(.+?)' for date '(.+?)'")
    public void processingTransactionsFile(String dataUrl, String externalId, int code, String transactionDate) throws Throwable {
        transactionsActions.processTransactions(dataUrl, externalId, transactionDate, code);
        System.out.println("Wait for transactions be processed 7 sec");
        sleep(7000);
    }

    @Then("^Points record is created:$")
    public void pointsRecordIsCreated(List<Points> expectedPointsList) throws Throwable {

        List<Points> actualPointsList = pointsRepository.getPointsExternalListWithinMinutes(Config.UNITED_TEST_AUTO_POINTS_USER_ID, 2);
        dataExchanger.setPointsList(actualPointsList);

        if (!expectedPointsList.isEmpty()) {

            for (Points actualPoints : actualPointsList) {
                //this is wrong but no idea how to do it in different way
                String criteriaId = String.valueOf(actualPoints.getRewardCriteriaId());
                boolean pointsFound = false;
                int iterator = 0;
                System.out.println();
                for (Points expectedPoints : expectedPointsList) {
                    System.out.println("externalTransactionId: (1) " + expectedPoints.getExternalTransactionId() + " activityInfo: " + expectedPoints.getActivityInfo() + " status: " + expectedPoints.getStatus());
                    pointsFound = expectedPoints.getActivityInfo().equals(actualPoints.getActivityInfo()) && expectedPoints.getExternalTransactionId().substring(expectedPoints.getExternalTransactionId().length() - 5, expectedPoints.getExternalTransactionId().length()).equals(actualPoints.getExternalTransactionId().substring(actualPoints.getExternalTransactionId().length() - 5, actualPoints.getExternalTransactionId().length())) && expectedPoints.getStatus().equals(actualPoints.getStatus());
                    if (!pointsFound) continue;

                    System.out.println(++iterator + " -----------------------------");
                    System.out.println(expectedPoints.getStatus());
                    System.out.println(actualPoints.getStatus());

                    assertThat(actualPoints.getStatus(), is(expectedPoints.getStatus()));
                    assertThat(actualPoints.getActivityInfo(), is(expectedPoints.getActivityInfo()));
                    assertThat(actualPoints.getOnBehalfOfId(), is(expectedPoints.getOnBehalfOfId()));
                    assertThat(actualPoints.getDelta(), is(expectedPoints.getDelta()));
                    assertThat(actualPoints.getBalance(), is(expectedPoints.getBalance()));
                    assertThat(actualPoints.getPartnerExternalId(), is(expectedPoints.getPartnerExternalId()));
                    assertThat(String.valueOf(actualPoints.getRewardCriteriaDescription()), is(expectedPoints.getRewardCriteriaDescription()));

                    if (!expectedPoints.getRewardCriteriaDescription().equals("null"))
                        assertThat(actualPoints.getRewardCriteriaDescription(), startsWith(expectedPoints.getRewardCriteriaDescription()));
                    assertThat("Auto-confirm date should be 14 days from now!", actualPoints.getAutoConfirmDate(), is(sameDay(expectedPoints.getAutoConfirmDate())));

                    if (!actualPoints.getStatus().equals("DECLINED")) {
//                            assertThat("Wrong tag id for DECLINED transaction", actualPoints.getTagId(), is(Long.parseLong(new TagRepositoryImpl().findByTagKey(Config.refundTransactionTagKey).getId())));
                        assertThat("Field externalTransactionId", actualPoints.getExternalTransactionId(), is(expectedPoints.getExternalTransactionId().replace("$CRITERIA_ID", criteriaId)));
                    } else {
                        assertThat("Wrong tag id for DECLINED transaction", actualPoints.getTagId(), is(Long.parseLong(new TagRepositoryImpl().findByTagKey(Config.refundTransactionTagKey).getId())));
                        assertThat("Field externalTransactionId", actualPoints.getExternalTransactionId(), not("null"));
                    }
                    break;
                }
                assertThat("Points record not found for externalTransactionId: " + actualPoints.getExternalTransactionId() + " activityInfo: " + actualPoints.getActivityInfo() + " status: " + actualPoints.getStatus(), pointsFound);
            }
        } else
            assertThat("Points records should not be created because transactions file have improper data ", actualPointsList, is(empty()));

        assertThat("There are less Points records than expected!", actualPointsList, hasSize(expectedPointsList.size()));
    }


    @Then("^Reports table was properly filled for UNITED retailer$")
    public void reportsTableValidationForUnited() throws Throwable {
        //points awarded
        List<Points> pointsList = dataExchanger.getPointsList();

        //reporting records for awarded transactions
        HashMap<Integer, List> reportingRecords = mySQLConnector.getResult("SELECT * FROM Reporting where retailerId ='" + Config.UNITED_TEST_AUTO_EXTERNAL_ID + "'", asList("transactionDate", "skuId", "skuName", "location", "quantity", "criteriaName", "epointsPerUnit", "epointsAwarded", "pointsId"));

        for (Points pointsRecord : pointsList) {

            boolean reportingRecordForPointsAwardedFound = false;

            String apiActivityInfo = pointsRecord.getActivityInfo();
            int apiQuantity = Integer.parseInt(apiActivityInfo.substring(0, apiActivityInfo.indexOf("X")).trim());
            String apiProductName = apiActivityInfo.substring(apiActivityInfo.indexOf("X") + 2);
            String apiTransactionDate = pointsRecord.getExternalTransactionId().substring(0, pointsRecord.getExternalTransactionId().indexOf(" ")) + " 00:00:00.0";
            String apiCriteriaDescription = pointsRecord.getRewardCriteriaDescription();
            int apiDelta = pointsRecord.getDelta();

            //no way to extract this value so have to be hardcoded (transaction file - branch)
            String apiBranch = "13";

            HashMap<Integer, List> rewardCriteriaRecord = mySQLConnector.getResult("SELECT productId, epointsAmount FROM RewardCriteria where id ='" + pointsRecord.getRewardCriteriaId() + "'", asList("productId", "epointsAmount"));
            String apiProductSKU = rewardCriteriaRecord.get(0).get(0).toString();
            String apiEpointsAmount = rewardCriteriaRecord.get(0).get(1).toString();

            System.out.println("Points awarded for transaction: " + apiActivityInfo + " " + pointsRecord.getExternalTransactionId());


            for (List reporting : reportingRecords.values()) {

                String transactionDate = reporting.get(0).toString();
                String skuId = reporting.get(1).toString();
                String skuName = reporting.get(2).toString();
                String location = reporting.get(3).toString();
                int quantity = (int) reporting.get(4);
                String criteriaName = reporting.get(5).toString();
                String ePointsPerUnit = reporting.get(6).toString();
                int ePointsAwarded = (int) reporting.get(7);
                long pointsId = (long) reporting.get(8);

                reportingRecordForPointsAwardedFound = pointsId == pointsRecord.getId();
                if (!reportingRecordForPointsAwardedFound) continue;

                System.out.println("Reporting record found!: transactionDate: " + transactionDate +
                        " skuId: " + skuId +
                        " skuName: " + skuName +
                        " location: " + location +
                        " quantity: " + quantity +
                        " criteriaName: " + criteriaName +
                        " epointsPerUnit: " + ePointsPerUnit +
                        " epointsAwarded: " + ePointsAwarded +
                        " pointsId: " + pointsId);

                assertThat("Transaction date is incorrect", transactionDate, is(apiTransactionDate));
                assertThat("skuId is incorrect", skuId, is(apiProductSKU));
                assertThat("skuName is incorrect", skuName, is(apiProductName));
                assertThat("location is incorrect", location, is(apiBranch));
                assertThat("quantity is incorrect", quantity, is(apiQuantity));
                assertThat("criteriaName is incorrect", criteriaName, is(apiCriteriaDescription));
                assertThat("epointsPerUnit is incorrect", ePointsPerUnit, is(apiEpointsAmount));
                assertThat("epointsAwarded is incorrect", ePointsAwarded, is(apiDelta));

                break;
            }

            assertThat("Reporting record not found for points awarded (transaction)", reportingRecordForPointsAwardedFound);
        }
    }


    @Then("^Account for ([^\"]*) is updated:$")
    public void accountForIsUpdated(String externalIdType, List<Account> accounts) throws Throwable {
        Account expectedAccount = accounts.get(0);
        Account actualAccount = accountRepository.getAccountByUserAndBusinessType(Config.UNITED_TEST_AUTO_POINTS_USER_ID, externalIdType);

        assertThat(actualAccount, samePropertyValuesAs(expectedAccount));
    }

    @Then("^Points record is not created for user with united externalId '(.+?)'$")
    public void points_record_is_not_created_for_user_with_eunited_externalId(String externalId) throws Throwable {

    }

    @Then("^Points records are not created$")
    public void pointsNotCreated() throws Throwable {
        List<Points> actualPointsList = pointsRepository.getPointsExternalListWithinMinutes(Config.UNITED_TEST_AUTO_POINTS_USER_ID, 2);
        assertThat("Points should not be created!", actualPointsList, is(empty()));
    }
}