package com.iat.validators;

import com.iat.Config;
import com.iat.actions.TransitionActions;
import com.iat.actions.users.UsersActions;
import com.iat.domain.UserBalance;
import com.iat.domain.transactions.ClickoutDb;
import com.iat.utils.DataExchanger;
import com.iat.utils.JdbcDatabaseConnector;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class TransitionValidator {

    private TransitionActions transitionActions = new TransitionActions();
    private DataExchanger dataExchanger = DataExchanger.getInstance();
    private UsersActions usersActions = new UsersActions();
    private JdbcDatabaseConnector mySQLConnectorAdminPortal = new JdbcDatabaseConnector(Config.mysqlConnectionPoolAdminPortal);
    private JdbcDatabaseConnector mySQLConnectorPointsManager = new JdbcDatabaseConnector(Config.mysqlConnectionPoolPointsManager);

    public void validateClickoutProperlyCreated() throws Throwable {
        System.out.println("Clickout created - validation started!");
        ClickoutDb clickoutDb = dataExchanger.getClickoutDb();
        assertThat("Clickout record - Improper value! Leads flag", clickoutDb.getLeads(), is(dataExchanger.getTransitionTo().getLeadGenerator()));
        assertThat("Clickout record - Improper value! userId", clickoutDb.getUserId(), is(dataExchanger.getUserProfile().getAccountDetails().getId()));
        assertThat("Clickout record - Improper value! clickoutStatus", clickoutDb.getClickoutStatus(), is("UNKNOWN"));
        assertThat("Clickout record - Improper value! clickoutType", clickoutDb.getClickoutType(), is("UNKNOWN"));
        assertThat("Clickout record - Improper value! epoints", clickoutDb.getEpoints(), is(0));
        assertThat("Clickout record - Improper value! merchantName", clickoutDb.getMerchant(), is(dataExchanger.getTransitionTo().getMerchantName()));
        assertThat("Clickout record - Improper value! merchantId", clickoutDb.getMerchantId(), is(dataExchanger.getTransitionTo().getMerchantId()));
        assertThat("Clickout record - Improper value! reference", clickoutDb.getReference(), not(isEmptyOrNullString()));

        System.out.println("Clickout created - validation passed");
    }

    public void validateClickoutPendingStatus() {
        System.out.println("Clickout PENDING status - validation started!");
        //Clickout record validation
        assertThat("Clickout record - Improper value clickoutStatus", dataExchanger.getClickoutDb().getClickoutStatus(), is("PENDING"));
        assertThat("Clickout record - Improper value clickoutType", dataExchanger.getClickoutDb().getClickoutType(), is("SALE"));
        assertThat("Clickout record - Improper value saleAmount", dataExchanger.getClickoutDb().getSaleAmount(), not("null"));
        assertThat("Clickout record - Improper value networkTransactionDate", dataExchanger.getClickoutDb().getNetworkTransactionDate(), not("null"));
        assertThat("Clickout record - Improper value pointsId", dataExchanger.getClickoutDb().getPointsId(), not("null"));
        assertThat("Clickout record - Improper value transactionId", dataExchanger.getClickoutDb().getTransactionId(), not("null"));

        if (dataExchanger.getClickoutDb().getLeads().equals("true"))
            assertThat("Clickout record - Improper value daysToConfirm", dataExchanger.getClickoutDb().getDaysToConfirm(), is("null"));
        else {
            Integer merchantMultiplier = Integer.parseInt(mySQLConnectorAdminPortal.getSingleResult("SELECT epointsMultiplier FROM admin_ui.Merchant WHERE id = '" + dataExchanger.getTransitionTo().getMerchantId() + "'"));
            assertThat("Clickout record - Improper value epoints", dataExchanger.getClickoutDb().getEpoints(), is(((int) Float.parseFloat(dataExchanger.getClickoutDb().getSaleAmount()) * merchantMultiplier)));
            assertThat("Clickout record - Improper value daysToConfirm", dataExchanger.getClickoutDb().getDaysToConfirm(), is("30"));
        }

        System.out.println("Clickout PENDING status - validation passed");
    }

    public void validateClickoutOriginalPointsPendingStatus() {
        System.out.println("originalPoints PENDING status - validation started!");
        List originalPoints = mySQLConnectorPointsManager.getResult("SELECT activityInfo, delta, externalTransactionId, status, userId, autoConfirm, autoConfirmDate, merchantName" +
                " FROM points_manager.Points" +
                " WHERE id = " + dataExchanger.getClickoutDb().getPointsId(), asList("activityInfo", "delta", "externalTransactionId", "status", "userId", "autoConfirm", "autoConfirmDate", "merchantName")).get(0);

        String originalPointsActivityInfo = originalPoints.get(0).toString();
        int originalPointsDelta = Integer.parseInt(originalPoints.get(1).toString());
        String originalPointsExternalTransactionId = originalPoints.get(2).toString();
        String originalPointsStatus = originalPoints.get(3).toString();
        String originalPointsUserId = originalPoints.get(4).toString();
        String originalPointsAutoConfirm = originalPoints.get(5).toString();
        String originalPointsAutoConfirmDate = originalPoints.get(6).toString();
        String originalPointsMerchantName = originalPoints.get(7).toString();

        assertThat("originalPoints record - Improper value activityInfo", originalPointsActivityInfo, is("Purchase at " + dataExchanger.getTransitionTo().getMerchantName()));
        assertThat("originalPoints record - Improper value delta", originalPointsDelta, is(dataExchanger.getClickoutDb().getEpoints()));
        assertThat("originalPoints record - Improper value externalTransactionId", originalPointsExternalTransactionId, is(dataExchanger.getClickoutDb().getTransactionId()));
        assertThat("originalPoints record - Improper value status", originalPointsStatus, is("PENDING"));
        assertThat("originalPoints record - Improper value merchantName", originalPointsMerchantName, is(dataExchanger.getClickoutDb().getMerchant()));

        if (dataExchanger.getClickoutDb().getLeads().equals("true")) {
            System.out.println("autoConfirm validated for leads");
            String leadMerchantMaxDays = mySQLConnectorAdminPortal.getSingleResult("SELECT daysToConfirmCommission FROM admin_ui.Merchant WHERE id = '" + dataExchanger.getTransitionTo().getMerchantId() + "'");
            assertThat("originalPoints record - Improper value autoConfirm", originalPointsAutoConfirm, is(leadMerchantMaxDays));
        } else
            assertThat("originalPoints record - Improper value autoConfirm", originalPointsAutoConfirm, is("30"));

        System.out.println("originalPoints PENDING status - validation passed");
    }

    public void validateClickoutSpinAndSpunPointsPendingStatus() {
        System.out.println("Spin PENDING status - validation started!");
        List spin = transitionActions.extractSpinForClickout().get(0);
        String spinSpinUuid = spin.get(0).toString();
        String spinUserUuid = spin.get(1).toString();
        String spinSpunPointsId = spin.get(3).toString();
        String spinStatus = spin.get(4).toString();
        String updatedAt = spin.get(5).toString();
        String spunAt = spin.get(6).toString();

        assertThat("Spin record - Improper value! userId", spinUserUuid, is(dataExchanger.getUserProfile().getAccountDetails().getId()));

        if (dataExchanger.isSpinUsed()) {
            System.out.println(" -> spin (used) - validation started");
            assertThat("Spin record - Improper value spunPointsId", spinSpunPointsId, not("null"));
            assertThat("Spin record - Improper value status", spinStatus, is("USED"));
            System.out.println(" -> spin (used) - validation passed");

            //spunPoints record validation
            System.out.println(" -> spunPoints - validation started!");
            List spunPoints = mySQLConnectorPointsManager.getResult("SELECT activityInfo, delta, externalTransactionId, status, userId, autoConfirm, autoConfirmDate, merchantName" +
                    " FROM points_manager.Points" +
                    " WHERE id = " + spinSpunPointsId, asList("activityInfo", "delta", "externalTransactionId", "status", "userId", "autoConfirm", "autoConfirmDate", "merchantName")).get(0);

            String spunPointsActivityInfo = spunPoints.get(0).toString();
            int spunPointsDelta = Integer.parseInt(spunPoints.get(1).toString());
            String spunPointsExternalTransactionId = spunPoints.get(2).toString();
            String spunPointsStatus = spunPoints.get(3).toString();
            String spunPointsUserId = spunPoints.get(4).toString();
            String spunPointsAutoConfirm = spunPoints.get(5).toString();
            String spunPointsAutoConfirmDate = spunPoints.get(6).toString();
            String spunPointsMerchantName = spunPoints.get(7).toString();

            assertThat("spunPoints record - Improper value activityInfo", spunPointsActivityInfo, is(dataExchanger.getPointsDetails().getReasonText()));
            assertThat("spunPoints record - Improper value delta", spunPointsDelta, is(dataExchanger.getPointsDetails().getNumPoints()));
            assertThat("spunPoints record - Improper value externalTransactionId", spunPointsExternalTransactionId, is(dataExchanger.getPointsDetails().getExternalTransactionId()));
            assertThat("spunPoints record - Improper value status", spunPointsStatus, is("PENDING"));
            assertThat("spunPoints record - Improper value merchantName", spunPointsMerchantName, is("null"));

            if (dataExchanger.getClickoutDb().getLeads().equals("true")) {
                String leadMerchantMaxDays = mySQLConnectorAdminPortal.getSingleResult("SELECT daysToConfirmCommission FROM admin_ui.Merchant WHERE id = '" + dataExchanger.getTransitionTo().getMerchantId() + "'");
                assertThat("spunPoints record - Improper value autoConfirm", spunPointsAutoConfirm, is(leadMerchantMaxDays));
            } else
                assertThat("spunPoints record - Improper value autoConfirm", spunPointsAutoConfirm, is("30"));

            System.out.println(" -> spunPoints - validation passed");

        } else {
            System.out.println(" -> spin (not used) - validation started");
            assertThat("Spin record - Improper value spunPointsId", spinSpunPointsId, is("null"));
            assertThat("Spin record - Improper value status", spinStatus, is("AVAILABLE"));
            assertThat("Spin record - Improper updatedAt ", updatedAt, is("null"));
            assertThat("Spin record - Improper spunAt", spunAt, is("null"));
            System.out.println(" -> spin (not used) - validation passed");
        }

        System.out.println("Spin PENDING status - validation passed");
    }


    public void validateClickoutAndSpinAndPoints_declinedConfirmedStatus(String status) {
        //Clickout record validation
        System.out.println("Clickout status: " + status + " - validation started");
        assertThat("Clickout record - Improper value clickoutStatus", dataExchanger.getClickoutDb().getClickoutStatus(), is(status));
        System.out.println("Clickout status: " + status + " - validation passed");

        //original Points record validation
        if (!dataExchanger.getClickoutDb().getPointsId().equals("null")) {
            System.out.println("originalPoints status: " + status + " - validation started");
            String originalPointsStatus = mySQLConnectorPointsManager.getSingleResult("SELECT status FROM points_manager.Points WHERE id = " + dataExchanger.getClickoutDb().getPointsId());
            assertThat("originalPoints record - Improper value Status", originalPointsStatus, is(status));
            System.out.println("originalPoints status: " + status + " - validation passed");

            //Spin record validation
            System.out.println("Spin status: " + status + " - validation started");
            List spin = transitionActions.extractSpinForClickout().get(0);
            String spinSpinUuid = spin.get(0).toString();
            String spinUserUuid = spin.get(1).toString();
            String spinSpunPointsId = spin.get(3).toString();
            String spinStatus = spin.get(4).toString();
            String updatedAt = spin.get(5).toString();
            String spunAt = spin.get(6).toString();

            if (!dataExchanger.isSpinUsed()) {
                System.out.println(" -> spin (not used) status: " + status + " - validation started");
                assertThat("Spin record - Improper value status", spinStatus, is(status.equals("CONFIRMED") ? "AVAILABLE" : status));
                assertThat("Spin record - Improper spunAt", spunAt, is("null"));
                assertThat("Spin record - Improper spunPointsId", spinSpunPointsId, is("null"));
                System.out.println(" -> spin (not used) status: " + status + " - validation passed");
            } else {
                System.out.println(" -> spin (used) status: " + status + " - validation started");
                assertThat("Spin record - Improper value status", spinStatus, is(status.equals("CONFIRMED") ? "USED" : status));
                assertThat("Spin record - Improper updatedAt ", updatedAt, not("null"));
                assertThat("Spin record - Improper spunAt", spunAt, not("null"));

                System.out.println(" -> spunPoints status: " + status + " - validation started");
                String spunPointsStatus = mySQLConnectorPointsManager.getSingleResult("SELECT status FROM points_manager.Points WHERE id = " + spinSpunPointsId);
                assertThat("spunPoints record - Improper value Status", spunPointsStatus, is(status));
                System.out.println(" -> spunPoints status: " + status + " - validation passed");

                System.out.println(" -> spin (used) status: " + status + " - validation passed");
            }

            System.out.println("Spin status: " + status + " - validation passed");
        } else
            System.out.println("Points (and Spin) record not exists - nothing to validate after decline");


    }

    public void checkIfPointsWereAddedToUserAccountAfterClickoutAndSpin(String pointsStatus) {
        System.out.println("Validation of user balance is started");

        UserBalance userBalance = usersActions.getUserBalance("null", 200).getAsObject(UserBalance.class);

        int clickoutPoints = dataExchanger.getClickoutDb().getEpoints();
        int spinPoints = 0;
        if (dataExchanger.isSpinUsed())
            spinPoints = dataExchanger.getPointsDetails().getNumPoints();

        switch (pointsStatus) {
            case "PENDING":
                assertThat("Pending points were not updated", userBalance.getPendingPoints(), is(dataExchanger.getUserBalance().getPendingPoints() + clickoutPoints + spinPoints));
                break;
            case "CONFIRMED":
                assertThat("Confirmed points were not updated", userBalance.getConfirmedPoints(), is(dataExchanger.getUserBalance().getConfirmedPoints() + clickoutPoints + spinPoints));
                break;
            case "DECLINED":
                assertThat("Declined points were not updated", userBalance.getDeclinedPoints(), is(dataExchanger.getUserBalance().getDeclinedPoints() + clickoutPoints + spinPoints));
                break;
        }

        System.out.println("Validation of user balance passed");
    }

}