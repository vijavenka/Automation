package com.iat.actions;


import com.iat.Config;
import com.iat.controller.TransitionController;
import com.iat.domain.transactions.ClickoutDb;
import com.iat.utils.DataExchanger;
import com.iat.utils.JdbcDatabaseConnector;
import com.iat.utils.ResponseContainer;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static com.iat.utils.DateTimeUtil.*;
import static com.iat.utils.ResponseContainer.initResponseContainer;
import static java.lang.Integer.parseInt;
import static java.lang.Thread.sleep;
import static java.util.Arrays.asList;
import static org.exparity.hamcrest.date.DateMatchers.sameOrAfter;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;


public class TransitionActions {

    private DataExchanger dataExchanger = DataExchanger.getInstance();
    private TransitionController transitionController = new TransitionController();
    private JdbcDatabaseConnector mySQLConnectorAffiliateNetwork = new JdbcDatabaseConnector(Config.mysqlConnectionPoolAffiliateManager);
    private JdbcDatabaseConnector mySQLConnectorPointsManager = new JdbcDatabaseConnector(Config.mysqlConnectionPoolPointsManager);
    private JdbcDatabaseConnector mySQLConnectorAdminPortal = new JdbcDatabaseConnector(Config.mysqlConnectionPoolAdminPortal);


    public ResponseContainer getTransitionTo(String merchantId, int status) {
        return initResponseContainer(transitionController.getTransitionTo(merchantId, status));
    }

    public ResponseContainer getTransitionToLeads(String offerId, int status) {
        return initResponseContainer(transitionController.getTransitionToLeads(offerId, status));
    }

    public ResponseContainer getClickout(String path, int status) {
        return initResponseContainer(transitionController.getClickout(path, status));
    }

    public void extractClickoutFromDbAndStore(String id) {
        HashMap<Integer, List> clickouts;
        ClickoutDb clickoutDb;

        if (id == null) {
            clickouts = mySQLConnectorAffiliateNetwork.getResult("SELECT id, affiliateNetwork, clickoutStatus, clickoutType, commissionAmount, commissionCurrency, confirmedDate, declineDate, epoints, merchant, merchantId, networkTransactionDate, pointsId, reference, saleAmount, transactionId, updateStatusDate, userId, zone, leads, daysToConfirm" +
                    " FROM affiliate_manager.Clickout" +
                    " WHERE p1Parameter = \"api_automation\"" +
                    " order by id desc LIMIT 1", asList("id", "affiliateNetwork", "clickoutStatus", "clickoutType", "commissionAmount", "commissionCurrency", "confirmedDate", "declineDate",
                    "epoints", "merchant", "merchantId", "networkTransactionDate", "pointsId", "reference", "saleAmount", "transactionId", "updateStatusDate", "userId", "zone", "leads", "daysToConfirm"));
            clickoutDb = new ClickoutDb();
            clickoutDb.setId(clickouts.get(0).get(0).toString());
        } else {
            clickouts = mySQLConnectorAffiliateNetwork.getResult("SELECT id, affiliateNetwork, clickoutStatus, clickoutType, commissionAmount, commissionCurrency, confirmedDate, declineDate, epoints, merchant, merchantId, networkTransactionDate, pointsId, reference, saleAmount, transactionId, updateStatusDate, userId, zone, leads, daysToConfirm" +
                    " FROM affiliate_manager.Clickout" +
                    " WHERE id = " + id, asList("id", "affiliateNetwork", "clickoutStatus", "clickoutType", "commissionAmount", "commissionCurrency", "confirmedDate", "declineDate",
                    "epoints", "merchant", "merchantId", "networkTransactionDate", "pointsId", "reference", "saleAmount", "transactionId", "updateStatusDate", "userId", "zone", "leads", "daysToConfirm"));

            clickoutDb = dataExchanger.getClickoutDb();
        }

        clickoutDb.setAffiliateNetwork(clickouts.get(0).get(1).toString());
        clickoutDb.setClickoutStatus(clickouts.get(0).get(2).toString());
        clickoutDb.setClickoutType(clickouts.get(0).get(3).toString());
        clickoutDb.setCommisionAmount(clickouts.get(0).get(4).toString());
        clickoutDb.setCommisionCurrency(clickouts.get(0).get(5).toString());
        clickoutDb.setConfirmedDate(clickouts.get(0).get(6).toString());
        clickoutDb.setDeclineDate(clickouts.get(0).get(7).toString());
        clickoutDb.setEpoints(parseInt(clickouts.get(0).get(8).toString()));
        clickoutDb.setMerchant(clickouts.get(0).get(9).toString());
        clickoutDb.setMerchantId(clickouts.get(0).get(10).toString());
        clickoutDb.setNetworkTransactionDate(clickouts.get(0).get(11).toString());
        clickoutDb.setPointsId(clickouts.get(0).get(12).toString());
        clickoutDb.setReference(clickouts.get(0).get(13).toString());
        clickoutDb.setSaleAmount(clickouts.get(0).get(14).toString());
        clickoutDb.setTransactionId(clickouts.get(0).get(15).toString());
        clickoutDb.setUpdateStatusDate(clickouts.get(0).get(16).toString());
        clickoutDb.setUserId(clickouts.get(0).get(17).toString());
        clickoutDb.setZone(clickouts.get(0).get(18).toString());
        clickoutDb.setLeads(clickouts.get(0).get(19).toString());
        clickoutDb.setDaysToConfirm(clickouts.get(0).get(20).toString());

        if (id == null)
            dataExchanger.setClickoutDb(clickoutDb);
    }

    public List referenceForLiveClickout(String affiliateNetworkId, String merchantId, String clickoutStatus) {
        JdbcDatabaseConnector mySQLConnector = new JdbcDatabaseConnector(Config.mysqlConnectionPoolAffiliateManagerLIVE);

        HashMap<Integer, List> liveClickout = mySQLConnector.getResult("SELECT reference, transactionId, networkTransactionDate, createdDate FROM affiliate_manager.Clickout WHERE " +
                "affiliateNetworkId = '" + affiliateNetworkId +
                "' and merchantId like '" + merchantId +
                "' and clickoutStatus = '" + clickoutStatus +
                "' and merchant != 'Argos Rewards'" +
                " group by reference having count(*) = 1 order by id desc LIMIT 1", asList("reference", "transactionId", "networkTransactionDate", "createdDate"));

        assertThat("Live clickout not found for affiliateNetworkId = '" + affiliateNetworkId +
                "' and merchantId like '" + merchantId +
                "' and clickoutStatus = '" + clickoutStatus, liveClickout.get(0), is(notNullValue()));

        Date createdDate = convertToDate(liveClickout.get(0).get(3).toString(), Format.yyyyMMdd_HHmmss, true);
        assertThat("Found clickout should not be older than 60 days! State of the production's clickouts is not allowing to complete test!",
                createdDate, is(sameOrAfter(
                        addUtcOffset(adjustDateByDays(adjustTimeOfTheDay(new Date(), -1, -1, 0, 0), -60))
                )));
        return liveClickout.get(0);
    }

    public void updateClickoutReference(String newReference) {
        System.out.println("update clickout reference to: " + newReference);
        mySQLConnectorAffiliateNetwork.execute("UPDATE affiliate_manager.Clickout SET reference = '" + newReference + "' WHERE id = " + dataExchanger.getClickoutDb().getId());
    }

    public void updateClickoutTransactionDetails(String transactionId, String networkTransactionDate) {
        System.out.println("update clickout transactionId to: " + transactionId + ", networkTransactionDate to: " + networkTransactionDate);
        mySQLConnectorAffiliateNetwork.execute("UPDATE affiliate_manager.Clickout SET transactionId = " + transactionId + ", networkTransactionDate = '" + networkTransactionDate + "' WHERE id = " + dataExchanger.getClickoutDb().getId());

        System.out.println("update Points records (original) externalTransactionId to: " + transactionId);
        mySQLConnectorPointsManager.execute("UPDATE points_manager.Points SET externalTransactionId = '" + transactionId + "' WHERE externalTransactionId = '" + dataExchanger.getClickoutDb().getTransactionId() + "'");
    }

    public void updateClickoutForXDaysToConfirm() {
        System.out.println("Modify clickout updateStatusDate to be confirmed");

        List clickout = mySQLConnectorAffiliateNetwork.getResult("SELECT updateStatusDate, daysToConfirm FROM affiliate_manager.Clickout WHERE id = " + dataExchanger.getClickoutDb().getId(), asList("updateStatusDate", "daysToConfirm")).get(0);
        int days;
        if (dataExchanger.getClickoutDb().getLeads().equals("true"))
            days = parseInt(mySQLConnectorAdminPortal.getSingleResult("SELECT daysToConfirmCommission FROM admin_ui.Merchant WHERE id = '" + dataExchanger.getTransitionTo().getMerchantId() + "'"));
        else
            days = parseInt(clickout.get(1).toString()) + 2;

        Date date = convertToDate(clickout.get(0).toString(), Format.yyyyMMddHHmmss, true);
        String newUpdateStatusDate = convertToString(adjustDateByDays(date, -days), Format.yyyyMMddHHmmss);

        System.out.println("update affiliate_manager.Clickout set updateStatusDate = '" + newUpdateStatusDate + "' where id = " + dataExchanger.getClickoutDb().getId());
        mySQLConnectorAffiliateNetwork.execute("UPDATE affiliate_manager.Clickout SET updateStatusDate = '" + newUpdateStatusDate + "' WHERE id = " + dataExchanger.getClickoutDb().getId());
    }

    public ResponseContainer triggerAffiliateManagerReportsFor(String networkCode, int status) {
        return initResponseContainer(transitionController.triggerAffiliateManagerReportsFor(networkCode, status));
    }


    public void waitForClickoutToUpdate(String id, String status) throws Throwable {
        int counter = 0;
        System.out.println("Wait for affiliate manager reports processing - started!");
        while (!mySQLConnectorAffiliateNetwork.getSingleResult("SELECT clickoutStatus FROM affiliate_manager.Clickout WHERE id = " + id).equals(status)) {
            //todo below
            //increased to 5 minutes. Of course it will be handled a lot smarter in near future
            if (counter > 60 * 5)
                break;

            sleep(1000);
            counter++;
            System.out.print("Sleep loop (waiting for clickout status is updated): " + counter + "\n");

            if (counter % 20 == 0) {
                triggerAffiliateManagerReportsFor("awin", 200);
                triggerAffiliateManagerReportsFor("comjunction", 200);
            }
        }
        System.out.println("Wait Ends!");
    }


    public HashMap<Integer, List> extractSpinForClickout() {
        return mySQLConnectorPointsManager.getResult("SELECT spinUuid, userUuid, originalPointsId, spunPointsId, status, updatedAt, spunAt FROM points_manager.Spin " +
                        "WHERE originalPointsId = " + dataExchanger.getClickoutDb().getPointsId(),
                asList("spinUuid", "userUuid", "originalPointsId", "spunPointsId", "status", "updatedAt", "spunAt"));
    }
}
