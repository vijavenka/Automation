package com.iat.validators;

import com.iat.Config;
import com.iat.actions.AnswerActions;
import com.iat.actions.RewardPointsActions;
import com.iat.domain.Retailer;
import com.iat.domain.RewardCriteria;
import com.iat.repository.impl.UserRepositoryImpl;
import com.iat.utils.DataExchanger;
import com.iat.utils.JdbcDatabaseConnector;
import com.iat.utils.JsonParserUtils;
import gherkin.deps.com.google.gson.JsonArray;
import gherkin.deps.com.google.gson.JsonElement;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class AuditResultsValidator {

    private AnswerActions answerActions = new AnswerActions();
    private RewardPointsActions rewardPointsActions = new RewardPointsActions();
    private JdbcDatabaseConnector mySQLConnector_audit = new JdbcDatabaseConnector(Config.mysqlConnectionPool_audit);
    private JdbcDatabaseConnector mySQLConnector_pointsManager = new JdbcDatabaseConnector(Config.mysqlConnectionPool_pointsManager);
    JsonParserUtils jsonParserUtils = JsonParserUtils.getInstance();
    DataExchanger dataExchanger = DataExchanger.getInstance();

    public void validateAuditResultsProperlyAwardedRetailer(boolean defaultBalanceValue) {

        JsonArray rewardPointsCms = jsonParserUtils.convertStringToJsonArray(rewardPointsActions.filterRewardPointsForAudit(rewardPointsActions.getRewardPointsList("0;20;auditId,desc&sort=id,desc", 200), dataExchanger.getAuditObject().getId()));

        for (Retailer retailer : dataExchanger.getRetailers()) {
            int balanceBefore = 50;
            if (!defaultBalanceValue)
                balanceBefore = Integer.parseInt(retailer.getBalance());

            int balanceAfter = Integer.parseInt(new UserRepositoryImpl().findUserByUserId(retailer.getUuid()).getConfirmed());

//            System.out.println("Retailer: id: " + retailer.getId() + " uuid: " + retailer.getUuid());
//            System.out.println(" Balance Before: " + balanceBefore);

            for (JsonElement rewardRecord : rewardPointsCms) {
                String rewardCriteriaIdRewardCms = rewardRecord.getAsJsonObject().get("rewardCriteriaId").toString();
                String pointsRewardCms = rewardRecord.getAsJsonObject().get("points").toString();
                String retailerIdRewardCms = rewardRecord.getAsJsonObject().get("retailerId").toString();

//                System.out.println("rewardCriteriaIdRewardCms " + rewardCriteriaIdRewardCms + " pointsRewardCms " + pointsRewardCms + " retailerIdRewardCms " + retailerIdRewardCms);

                if (retailerIdRewardCms.equals(retailer.getId())) {
                    balanceBefore += Integer.parseInt(pointsRewardCms);
//                    System.out.println("Balance increased: " + balanceBefore);
                    //break because in rewardPoints table record with points is stored for each question from rewardCriteria.rule (this mean "duplicates" are stored)
                    break;
                }
            }
            assertThat("Improper balance after award audit results", balanceAfter, is(balanceBefore));
        }
    }

    public void validatePointsRecordInPointsManager() {
        RewardCriteria rewardCriteria = dataExchanger.getRewardCriteriaObject();
        String rewardCriteriaId = rewardCriteria.getId();
        String rewardCriteriaPoints = rewardCriteria.getPoints();
        String rewardCriteriaTagKey = rewardCriteria.getTagKey();

        JsonArray rewardPointsCms = jsonParserUtils.convertStringToJsonArray(rewardPointsActions.filterRewardPointsForAudit(rewardPointsActions.getRewardPointsListWithoutPrint("0;20;auditId,desc&sort=id,desc", 200), dataExchanger.getAuditObject().getId()));

        for (JsonElement rewardRecord : rewardPointsCms) {
            String idRewardsCms = rewardRecord.getAsJsonObject().get("id").toString();
            String auditIdRewardCms = rewardRecord.getAsJsonObject().get("auditId").toString();
            String rewardCriteriaIdRewardCms = rewardRecord.getAsJsonObject().get("rewardCriteriaId").toString();
            String pointsRewardCms = rewardRecord.getAsJsonObject().get("points").toString();
            String storeIdRewardCms = rewardRecord.getAsJsonObject().get("storeId").toString();
            String retailerIdRewardCms = rewardRecord.getAsJsonObject().get("retailerId").toString();
            String productIdRewardCms = rewardRecord.getAsJsonObject().get("productId").toString();
            String brandIdRewardCms = rewardRecord.getAsJsonObject().get("brandId").toString();
            String supplierIdRewardCms = rewardRecord.getAsJsonObject().get("supplierId").toString();

//            System.out.println("\nRewardPoints - id: " + idRewardsCms +
//                    "\nauditIdRewardCms " + auditIdRewardCms +
//                    "\nrewardCriteriaIdRewardCms " + rewardCriteriaIdRewardCms +
//                    "\npointsRewardCms " + pointsRewardCms +
//                    "\nstoreIdRewardCms " + storeIdRewardCms +
//                    "\nretailerIdRewardCms " + retailerIdRewardCms +
//                    "\nproductIdRewardCms " + productIdRewardCms +
//                    "\nbrandIdRewardCms " + brandIdRewardCms +
//                    "\nsupplierIdRewardCms " + supplierIdRewardCms);

            String pointsDelta = mySQLConnector_pointsManager.getSingleResult("SELECT delta from Points where activityInfo like \'%-" + storeIdRewardCms + "-" + dataExchanger.getAuditObject().getId() + "\'");
            assertThat("Improper delta for Points record for criteriaId: " + rewardCriteriaId, pointsDelta, is(rewardCriteriaPoints));
            String pointsExternalTransactionId = mySQLConnector_pointsManager.getSingleResult("SELECT externalTransactionId from Points where activityInfo like \'%-" + storeIdRewardCms + "-" + dataExchanger.getAuditObject().getId() + "\'");
            assertThat("Improper externalTransactionId for Points record for criteriaId: " + rewardCriteriaId, pointsExternalTransactionId, is(rewardCriteriaId));
            String pointsTagId = mySQLConnector_pointsManager.getSingleResult("SELECT tagId from Points where activityInfo like \'%-" + storeIdRewardCms + "-" + dataExchanger.getAuditObject().getId() + "\'");
            String pointsActivityInfo = mySQLConnector_pointsManager.getSingleResult("SELECT activityInfo from Points where activityInfo like \'%-" + storeIdRewardCms + "-" + dataExchanger.getAuditObject().getId() + "\'");
            String pointsTagKey = mySQLConnector_pointsManager.getSingleResult("SELECT tagKey from Tag where id = " + pointsTagId);
            assertThat("Improper tagKey for Points record for criteriaId: " + rewardCriteriaId, pointsTagKey, is(rewardCriteriaTagKey));

            System.out.println("\nPoints record in points-manager: " + "" +
                    "\npointsDelta " + pointsDelta +
                    "\npointsExternalTransactionId " + pointsExternalTransactionId +
                    "\npointsTagId " + pointsTagId +
                    "\npointsActivityInfo " + pointsActivityInfo +
                    "\npointsTagKey " + pointsTagKey);
        }
    }
}
