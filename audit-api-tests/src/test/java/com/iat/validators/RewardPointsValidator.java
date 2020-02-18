package com.iat.validators;

import com.iat.actions.RewardPointsActions;
import com.iat.domain.Answer;
import com.iat.domain.Question;
import com.iat.domain.RewardCriteria;
import com.iat.domain.Store;
import com.iat.utils.DataExchanger;
import com.iat.utils.HelpFunctions;
import com.iat.utils.JsonParserUtils;
import gherkin.deps.com.google.gson.JsonArray;
import gherkin.deps.com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class RewardPointsValidator {

    private RewardPointsActions rewardPointsActions = new RewardPointsActions();
    JsonParserUtils jsonParserUtils = JsonParserUtils.getInstance();
    DataExchanger dataExchanger = DataExchanger.getInstance();
    HelpFunctions helpFunctions = new HelpFunctions();


    public void validateTodaysProperRewardPointsRecorded(boolean retailerAdded) {

        JsonArray rewardPointsCms = jsonParserUtils.convertStringToJsonArray(rewardPointsActions.filterRewardPointsForAudit(rewardPointsActions.getRewardPointsListWithoutPrint("0;20;auditId,desc&sort=id,desc", 200), dataExchanger.getAuditObject().getId()));

        for (Answer answer : dataExchanger.getAnswers()) {

            boolean rewardPointsFound = false;

            //find internal storeId
            String storeId = helpFunctions.findInternalStoreId(answer);

            //internal questionId
            String questionId = "";
            String productId = "";

            List<Question> questionsList;

            if (answer.getResults_question_type().equals("0")) {
                //for product questions internal questionId is store in column c_product_id
                questionsList = helpFunctions.getQuestionsListOfType("PRODUCT");
                questionId = answer.getResults_c_product_id();

                //extract productId
                for (Question question : questionsList) {
                    if (question.getId().equals(answer.getResults_c_product_id())) {
                        productId = question.getProductId();
                        break;
                    }
                }
            }

            if (answer.getResults_question_type().equals("3")) {
                //for adhoc questions adhoc_ext_id of question is stored in question_id column
                questionsList = helpFunctions.getQuestionsListOfType("ADHOC");

                //Extract questionId and productId
                for (Question question : questionsList) {
                    System.out.println("Entered: " + question.getId());
                    if (question.getAdhocExtId().equals(answer.getResults_question_id())) {
                        questionId = question.getId();
                        productId = "null";
                        break;
                    }
                }
            }

            //System.out.println("\nStoreId: " + storeId);
            //System.out.println("questionId: " + questionId);
            //System.out.println("productId: " + productId);
            RewardCriteria rewardCriteria = dataExchanger.getRewardCriteriaObject();

            //search for proper record created
            for (JsonElement rewardRecord : rewardPointsCms) {
                String auditIdRewardCms = rewardRecord.getAsJsonObject().get("auditId").toString();
                String rewardCriteriaIdRewardCms = rewardRecord.getAsJsonObject().get("rewardCriteriaId").toString();
                String pointsRewardCms = rewardRecord.getAsJsonObject().get("points").toString();
                String storeIdRewardCms = rewardRecord.getAsJsonObject().get("storeId").toString();
                String retailerIdRewardCms = rewardRecord.getAsJsonObject().get("retailerId").toString();
                String productIdRewardCms = rewardRecord.getAsJsonObject().get("productId").toString();
                String brandIdRewardCms = rewardRecord.getAsJsonObject().get("brandId").toString();
                String supplierIdRewardCms = rewardRecord.getAsJsonObject().get("supplierId").toString();

//                    System.out.println("\nauditIdRewardCms " + auditIdRewardCms);
//                    System.out.println("rewardCriteriaIdRewardCms " + rewardCriteriaIdRewardCms);
//                    System.out.println("pointsRewardCms " + pointsRewardCms);
//                    System.out.println("storeIdRewardCms " + storeIdRewardCms);
//                    System.out.println("retailerIdRewardCms " + retailerIdRewardCms);
//                    System.out.println("productIdRewardCms " + productIdRewardCms);
//                    System.out.println("brandIdRewardCms " + brandIdRewardCms);
//                    System.out.println("supplierIdRewardCms " + supplierIdRewardCms);

                if (dataExchanger.getAuditObject().getId().equals(auditIdRewardCms) &&
                        storeId.equals(storeIdRewardCms) &&
                        rewardCriteria.getId().equals(rewardCriteriaIdRewardCms) &&
                        productId.equals(productIdRewardCms)) {
                    assertThat("Points value is incorrect", rewardCriteria.getPoints(), is(pointsRewardCms));

                    if (retailerAdded) {
                        String retailerId = helpFunctions.findRetailerIdForStoreId(storeId);
                        assertThat("RetailerId is incorrect", retailerIdRewardCms, is(retailerId));
                    } else
                        assertThat("RetailerId should be null", retailerIdRewardCms, is("null"));

                    rewardPointsFound = true;
                    break;
                }
            }

            if (answer.getResults_response().equalsIgnoreCase("yes") || answer.getResults_response().equalsIgnoreCase("ranged and available")) {
                assertThat("Missing RewardPoints record for: " + "Answer: " + answer.getResults_response() + " RC id: " + rewardCriteria.getId() + " Points: " + rewardCriteria.getPoints() + " StoreId: " + storeId + " questionId: " + questionId + " productId: " + productId + " auditId: " + dataExchanger.getAuditObject().getId(), rewardPointsFound);
            } else {
                assertThat("Found RewardPoints record for: " + "Answer: " + answer.getResults_response() + " RC id: " + rewardCriteria.getId() + " Points: " + rewardCriteria.getPoints() + " StoreId: " + storeId + " questionId: " + questionId + " productId: " + productId + " auditId: " + dataExchanger.getAuditObject().getId(), !rewardPointsFound);
            }
        }
    }

    public void validatePremierProperRewardPointsRecorded(boolean retailerAdded) throws Throwable {

        JsonArray rewardPointsCms = jsonParserUtils.convertStringToJsonArray(rewardPointsActions.filterRewardPointsForAudit(rewardPointsActions.getRewardPointsListWithoutPrint("0;20;auditId,desc&sort=id,desc", 200), dataExchanger.getAuditObject().getId()));

        for (Answer answer : dataExchanger.getAnswers()) {

            boolean rewardPointsFound = false;

            //find internal storeId
            String storeId = helpFunctions.findInternalStoreId(answer);

            //internal questionId
            String questionId = answer.getResults_question_id();
            String productId = "";

            List<Question> questionsList = dataExchanger.getQuestions();

            //extract productId
            for (Question question : questionsList) {
                if (question.getId().equals(answer.getResults_question_id())) {
                    productId = question.getProductId();
                    break;
                }
            }

//            System.out.println("\nStoreId: " + storeId);
//            System.out.println("questionId: " + questionId);
//            System.out.println("productId: " + productId);

            RewardCriteria rewardCriteria = dataExchanger.getRewardCriteriaObject();

            //search for proper record created
            for (JsonElement rewardRecord : rewardPointsCms) {
                String auditIdRewardCms = rewardRecord.getAsJsonObject().get("auditId").toString();
                String rewardCriteriaIdRewardCms = rewardRecord.getAsJsonObject().get("rewardCriteriaId").toString();
                String pointsRewardCms = rewardRecord.getAsJsonObject().get("points").toString();
                String storeIdRewardCms = rewardRecord.getAsJsonObject().get("storeId").toString();
                String retailerIdRewardCms = rewardRecord.getAsJsonObject().get("retailerId").toString();
                String productIdRewardCms = rewardRecord.getAsJsonObject().get("productId").toString();
                String brandIdRewardCms = rewardRecord.getAsJsonObject().get("brandId").toString();
                String supplierIdRewardCms = rewardRecord.getAsJsonObject().get("supplierId").toString();

//                System.out.println("\nauditIdRewardCms " + auditIdRewardCms);
//                System.out.println("rewardCriteriaIdRewardCms " + rewardCriteriaIdRewardCms);
//                System.out.println("pointsRewardCms " + pointsRewardCms);
//                System.out.println("storeIdRewardCms " + storeIdRewardCms);
//                System.out.println("retailerIdRewardCms " + retailerIdRewardCms);
//                System.out.println("productIdRewardCms " + productIdRewardCms);
//                System.out.println("brandIdRewardCms " + brandIdRewardCms);
//                System.out.println("supplierIdRewardCms " + supplierIdRewardCms);

                if (dataExchanger.getAuditObject().getId().equals(auditIdRewardCms) &&
                        storeId.equals(storeIdRewardCms) &&
                        rewardCriteria.getId().equals(rewardCriteriaIdRewardCms) &&
                        productId.equals(productIdRewardCms)) {
                    assertThat("Points value is incorrect", rewardCriteria.getPoints(), is(pointsRewardCms));

                    if (retailerAdded) {
                        String retailerId = helpFunctions.findRetailerIdForStoreId(storeId);
                        assertThat("RetailerId is incorrect", retailerIdRewardCms, is(retailerId));
                    } else
                        assertThat("RetailerId should be null", retailerIdRewardCms, is("null"));

                    rewardPointsFound = true;
                    break;
                }
            }

            if (answer.getResults_response().equalsIgnoreCase("yes")) {
                assertThat("Missing RewardPoints record for: " + "Answer: " + answer.getResults_response() + " RC id: " + rewardCriteria.getId() + " Points: " + rewardCriteria.getPoints() + " StoreId: " + storeId + " questionId: " + questionId + " auditId: " + dataExchanger.getAuditObject().getId(), rewardPointsFound);
            } else {
                assertThat("Found RewardPoints record for: " + "Answer: " + answer.getResults_response() + " RC id: " + rewardCriteria.getId() + " Points: " + rewardCriteria.getPoints() + " StoreId: " + storeId + " questionId: " + questionId + " auditId: " + dataExchanger.getAuditObject().getId(), !rewardPointsFound);
            }
        }
    }


    public void validateTodaysProperRewardPointsRecorded2(boolean retailerAdded) {

        JsonArray rewardPointsCms = jsonParserUtils.convertStringToJsonArray(rewardPointsActions.filterRewardPointsForAudit(rewardPointsActions.getRewardPointsListWithoutPrint("0;20;auditId,desc&sort=id,desc", 200), dataExchanger.getAuditObject().getId()));

        for (Answer answer : dataExchanger.getAnswers()) {

            boolean rewardPointsFound = false;

            //find internal storeId
            String storeId = helpFunctions.findInternalStoreId(answer);

            //internal questionId
            String questionId = "";
            String productId = "";

            List<Question> questionsList;

            if (answer.getResults_question_type().equals("0")) {
                //for product questions internal questionId is store in column c_product_id
                questionsList = helpFunctions.getQuestionsListOfType("PRODUCT");
                questionId = answer.getResults_c_product_id();

                //extract productId
                for (Question question : questionsList) {
                    if (question.getId().equals(answer.getResults_c_product_id())) {
                        productId = question.getProductId();
                        break;
                    }
                }
            }

            if (answer.getResults_question_type().equals("3")) {
                //for adhoc questions adhoc_ext_id of question is stored in question_id column
                questionsList = helpFunctions.getQuestionsListOfType("ADHOC");

                //Extract questionId and productId
                for (Question question : questionsList) {
                    System.out.println("Entered: " + question.getId());
                    if (question.getAdhocExtId().equals(answer.getResults_question_id())) {
                        questionId = question.getId();
                        productId = "null";
                        break;
                    }
                }
            }

            //System.out.println("\nStoreId: " + storeId);
            //System.out.println("questionId: " + questionId);
            //System.out.println("productId: " + productId);
            RewardCriteria rewardCriteria = dataExchanger.getRewardCriteriaObject();

            //search for proper record created
            for (JsonElement rewardRecord : rewardPointsCms) {
                String auditIdRewardCms = rewardRecord.getAsJsonObject().get("auditId").toString();
                String rewardCriteriaIdRewardCms = rewardRecord.getAsJsonObject().get("rewardCriteriaId").toString();
                String pointsRewardCms = rewardRecord.getAsJsonObject().get("points").toString();
                String storeIdRewardCms = rewardRecord.getAsJsonObject().get("storeId").toString();
                String retailerIdRewardCms = rewardRecord.getAsJsonObject().get("retailerId").toString();
                String productIdRewardCms = rewardRecord.getAsJsonObject().get("productId").toString();
                String brandIdRewardCms = rewardRecord.getAsJsonObject().get("brandId").toString();
                String supplierIdRewardCms = rewardRecord.getAsJsonObject().get("supplierId").toString();

//                    System.out.println("\nauditIdRewardCms " + auditIdRewardCms);
//                    System.out.println("rewardCriteriaIdRewardCms " + rewardCriteriaIdRewardCms);
//                    System.out.println("pointsRewardCms " + pointsRewardCms);
//                    System.out.println("storeIdRewardCms " + storeIdRewardCms);
//                    System.out.println("retailerIdRewardCms " + retailerIdRewardCms);
//                    System.out.println("productIdRewardCms " + productIdRewardCms);
//                    System.out.println("brandIdRewardCms " + brandIdRewardCms);
//                    System.out.println("supplierIdRewardCms " + supplierIdRewardCms);

                if (dataExchanger.getAuditObject().getId().equals(auditIdRewardCms) &&
                        storeId.equals(storeIdRewardCms) &&
                        rewardCriteria.getId().equals(rewardCriteriaIdRewardCms) &&
                        productId.equals(productIdRewardCms)) {
                    assertThat("Points value is incorrect", rewardCriteria.getPoints(), is(pointsRewardCms));

                    if (retailerAdded) {
                        String retailerId = helpFunctions.findRetailerIdForStoreId(storeId);
                        assertThat("RetailerId is incorrect", retailerIdRewardCms, is(retailerId));
                    } else
                        assertThat("RetailerId should be null", retailerIdRewardCms, is("null"));

                    rewardPointsFound = true;
                    break;
                }
            }

            if (answer.getResults_response().equalsIgnoreCase("yes") || answer.getResults_response().equalsIgnoreCase("ranged and available")) {
                assertThat("Missing RewardPoints record for: " + "Answer: " + answer.getResults_response() + " RC id: " + rewardCriteria.getId() + " Points: " + rewardCriteria.getPoints() + " StoreId: " + storeId + " questionId: " + questionId + " productId: " + productId + " auditId: " + dataExchanger.getAuditObject().getId(), rewardPointsFound);
            } else {
                assertThat("Found RewardPoints record for: " + "Answer: " + answer.getResults_response() + " RC id: " + rewardCriteria.getId() + " Points: " + rewardCriteria.getPoints() + " StoreId: " + storeId + " questionId: " + questionId + " productId: " + productId + " auditId: " + dataExchanger.getAuditObject().getId(), !rewardPointsFound);
            }
        }
    }

    public void validateProperRewardPointsRecorded(boolean retailerAdded) throws Throwable {

        System.out.println("\nReward Points validation: ");
        //rewards for current audit
        JsonArray rewardPointsCms = jsonParserUtils.convertStringToJsonArray(rewardPointsActions.filterRewardPointsForAudit(rewardPointsActions.getRewardPointsListWithoutPrint("0;20;auditId,desc&sort=id,desc", 200), dataExchanger.getAuditObject().getId()));


        RewardCriteria rewardCriteria = dataExchanger.getRewardCriteriaObject();
        String[] rule = rewardCriteria.getCriteriaRule().split(" ");
        String ruleLogic = "";
        List<String> ruleQuestions = new ArrayList<>();
        for (String rulePart : rule) {
            if (rulePart.equalsIgnoreCase("and") || rulePart.equalsIgnoreCase("or"))
                ruleLogic = rulePart;
            else
                ruleQuestions.add(rulePart);
        }

        List<Store> stores = dataExchanger.getStores();
        for (Store store : stores) {
            System.out.println("StoreId: " + store.getId());

            boolean storeShouldBeRewarded = false;
            List<Boolean> storeAnswersBooleans = new ArrayList<>();
            List<Answer> storeAnswers = new ArrayList<>();

            for (Answer answer : dataExchanger.getAnswers()) {

                if (store.getId().equals(answer.getStoreId())) {
                    System.out.println("Answer for store found!");

                    if (answer.getAnswerText().equalsIgnoreCase("Yes") || answer.getAnswerText().equalsIgnoreCase("ranged and available")) {
                        System.out.println(" Answer (" + answer.getAnswerText() + ") boolean is: true ");
                        storeAnswersBooleans.add(true);
                    } else {
                        System.out.println(" Answer (" + answer.getAnswerText() + ") boolean is: false ");
                        storeAnswersBooleans.add(false);
                    }
                    storeAnswers.add(answer);
                }
            }

            System.out.println("Store answers count: " + storeAnswersBooleans.size());
            assertThat("Reward Criteria questions count and stored answers count for store: " + store.getId() + " not the same", storeAnswersBooleans.size(), is(ruleQuestions.size()));

            if (ruleLogic.equals("") || ruleLogic.equalsIgnoreCase("or")) {
                if (storeAnswersBooleans.contains(true))
                    storeShouldBeRewarded = true;
            } else {
                if (!storeAnswersBooleans.contains(false))
                    storeShouldBeRewarded = true;
            }

            System.out.println("Current store should be rewarded: " + storeShouldBeRewarded);


            boolean rewardPointsForStoreFound = false;

            for (JsonElement rewardRecord : rewardPointsCms) {
                String auditIdRewardCms = rewardRecord.getAsJsonObject().get("auditId").toString();
                String rewardCriteriaIdRewardCms = rewardRecord.getAsJsonObject().get("rewardCriteriaId").toString();
                String pointsRewardCms = rewardRecord.getAsJsonObject().get("points").toString();
                String storeIdRewardCms = rewardRecord.getAsJsonObject().get("storeId").toString();
                String retailerIdRewardCms = rewardRecord.getAsJsonObject().get("retailerId").toString();
                String productIdRewardCms = rewardRecord.getAsJsonObject().get("productId").toString();
                String brandIdRewardCms = rewardRecord.getAsJsonObject().get("brandId").toString();
                String supplierIdRewardCms = rewardRecord.getAsJsonObject().get("supplierId").toString();

//                System.out.println("\nauditIdRewardCms " + auditIdRewardCms);
//                System.out.println("rewardCriteriaIdRewardCms " + rewardCriteriaIdRewardCms);
//                System.out.println("pointsRewardCms " + pointsRewardCms);
//                System.out.println("storeIdRewardCms " + storeIdRewardCms);
//                System.out.println("retailerIdRewardCms " + retailerIdRewardCms);
//                System.out.println("productIdRewardCms " + productIdRewardCms);
//                System.out.println("brandIdRewardCms " + brandIdRewardCms);
//                System.out.println("supplierIdRewardCms " + supplierIdRewardCms);

                if (dataExchanger.getAuditObject().getId().equals(auditIdRewardCms) &&
                        store.getId().equals(storeIdRewardCms) &&
                        rewardCriteria.getId().equals(rewardCriteriaIdRewardCms)) {

                    assertThat("Points value is incorrect", rewardCriteria.getPoints(), is(pointsRewardCms));

                    if (retailerAdded) {
                        String retailerId = store.getRetailerId();
                        assertThat("RetailerId is incorrect", retailerIdRewardCms, is(retailerId));
                    } else
                        assertThat("RetailerId should be null", retailerIdRewardCms, is("null"));


                    rewardPointsForStoreFound = true;
                    System.out.println("Found RewardPoints!!!");
                }
            }

            if (storeShouldBeRewarded) {
                assertThat("Missing Reward Points record for: " + " RC id: " + rewardCriteria.getId() + " Points: " + rewardCriteria.getPoints() + " StoreId: " + store.getId() + " auditId: " + dataExchanger.getAuditObject().getId(), rewardPointsForStoreFound);
                System.out.println("Validation for found Reward Points passed");
            } else {
                assertThat("Found Reward Points record for: " + " RC id: " + rewardCriteria.getId() + " Points: " + rewardCriteria.getPoints() + " StoreId: " + store.getId() + " auditId: " + dataExchanger.getAuditObject().getId(), !rewardPointsForStoreFound);
                System.out.println("Validation for not found Reward Points passed");
            }

            System.out.println();
        }
    }
}
