package com.iat.actions;

import com.iat.Config;
import com.iat.controller.AuditResultController;
import com.iat.domain.Question;
import com.iat.domain.RewardCriteria;
import com.iat.utils.*;
import io.restassured.response.ValidatableResponse;

import java.util.ArrayList;
import java.util.List;

public class AuditResultActions {

    private AuditResultController auditResultController = new AuditResultController();
    private AnswerActions answerActions = new AnswerActions();
    private JdbcDatabaseConnector mySQLConnector_audit = new JdbcDatabaseConnector(Config.mysqlConnectionPool_audit);
    private JdbcDatabaseConnector mySQLConnector_pointsManager = new JdbcDatabaseConnector(Config.mysqlConnectionPool_pointsManager);
    JsonParserUtils jsonParserUtils = JsonParserUtils.getInstance();
    DataExchanger dataExchanger = DataExchanger.getInstance();
    HelpFunctions helpFunctions = new HelpFunctions();
    private int sleep = 1000;
    private int limit = 30;
    String response;


    public void updateTodaysAuditResultsFileUniqueQuestionIds(String fileName) {
        ExcelUtilities excelUtilities = new ExcelUtilities();
        excelUtilities.openHeadless(fileName, "src//bulkUploadFiles//");
        int sheetNo = 1;
        int rowsAmount = excelUtilities.getRowsAmount(sheetNo);

        String[] criteriaRuleQuestionIds = dataExchanger.getRewardCriteriaObject().getCriteriaRule().split(" ");
        List<Question> questionsList = dataExchanger.getQuestions();
        List<String> columnNameToModify = new ArrayList<>();
        List<String> columnCellData = new ArrayList<>();

        int criteriaRuleQuestionsCount = 0;
        for (String criteriaRuleQuestionId : criteriaRuleQuestionIds) {
            if (!(criteriaRuleQuestionId.equalsIgnoreCase("and") || criteriaRuleQuestionId.equalsIgnoreCase("or"))) {
                criteriaRuleQuestionsCount++;
            }
        }
        System.out.println("Criteria rules questions count: " + criteriaRuleQuestionsCount);
        int howManyLoops = rowsAmount / criteriaRuleQuestionsCount;

        while (howManyLoops > 0) {
            for (String criteriaRuleQuestionId : criteriaRuleQuestionIds) {
                if (!(criteriaRuleQuestionId.equalsIgnoreCase("and") || criteriaRuleQuestionId.equalsIgnoreCase("or"))) {
                    System.out.println("questionId: " + criteriaRuleQuestionId);
                    String columnName = "c_product_id";
                    String cellData = criteriaRuleQuestionId;
                    for (Question question : questionsList) {
                        if (question.getId().equals(criteriaRuleQuestionId)) {
                            if (question.getQuestionType().equals("ADHOC")) {
                                columnName = "question_id";
                                cellData = question.getAdhocExtId();
                            }
                            break;
                        }
                    }
                    System.out.println(" columnName " + columnName + " cellData " + cellData);
                    columnNameToModify.add(columnName);
                    columnCellData.add(cellData);
                }
            }
            howManyLoops--;
        }

        excelUtilities.modifyCellsForColumnAtSheet(columnNameToModify, columnCellData, sheetNo);
        excelUtilities.close();

    }

    public void updatePremierAuditResultsFileUniqueQuestionIds(String fileName) {
        ExcelUtilities excelUtilities = new ExcelUtilities();
        excelUtilities.openHeadless(fileName, "src//bulkUploadFiles//");
        int sheetNo = 0;

        List<String> columnCellData = new ArrayList<>();
        List<String> columnNameToModify = new ArrayList<>();
        int rowsAmount = excelUtilities.getRowsAmount(sheetNo);

        String[] criteriaRuleQuestionIds = dataExchanger.getRewardCriteriaObject().getCriteriaRule().split(" ");

        int criteriaRuleQuestionsCount = 0;
        for (String criteriaRuleQuestionId : criteriaRuleQuestionIds) {
            if (!(criteriaRuleQuestionId.equalsIgnoreCase("and") || criteriaRuleQuestionId.equalsIgnoreCase("or"))) {
                criteriaRuleQuestionsCount++;
            }
        }
        System.out.println("Criteria rules questions count: " + criteriaRuleQuestionsCount);

        int howManyLoops = rowsAmount / criteriaRuleQuestionsCount;

        while (howManyLoops > 0) {
            for (String criteriaRuleQuestionId : criteriaRuleQuestionIds) {
                if (!(criteriaRuleQuestionId.equalsIgnoreCase("and") || criteriaRuleQuestionId.equalsIgnoreCase("or"))) {
                    System.out.println("questionId: " + criteriaRuleQuestionId);
                    String columnName = "Question ID";
                    String cellData = criteriaRuleQuestionId;

                    System.out.println("columnName " + columnName + " cellData " + cellData);
                    columnNameToModify.add(columnName);
                    columnCellData.add(cellData);
                }
//            }
                howManyLoops--;
            }
//
            excelUtilities.modifyCellsForColumnAtSheet(columnNameToModify, columnCellData, sheetNo);
            excelUtilities.close();


//            ExcelUtilities excelUtilities = new ExcelUtilities();
//            excelUtilities.openHeadless(fileName, "src//bulkUploadFiles//");
//            int sheetNo = 0;
//
//            int rowsAmount = excelUtilities.getRowsAmount(sheetNo);
//            //TODO need to be extend for complex question rules - right now simply for single question id in rule
//            String questionId = dataExchanger.getRewardCriteriaObject().getCriteriaRule();
//
//            String columnData = questionId;
//            for (int i = 1; i < rowsAmount; i++)
//                columnData += ("," + questionId);
//
//            System.out.println("Modify Premier/Nisa auditResults file: " + fileName + " to new questions: \n" + columnData);
//            excelUtilities.modifyCellsForColumnAtSheet("Question ID", columnData, sheetNo);
//            excelUtilities.close();
        }
    }

    public List<String> updateTodaysAuditResultsFileUniqueStoresExtRelIdsAndReturn(String fileName) {
        ExcelUtilities excelUtilities = new ExcelUtilities();
        excelUtilities.openHeadless(fileName, "src//bulkUploadFiles//");
        int sheetNo = 0;

        int rowsAmount = excelUtilities.getRowsAmount(sheetNo);
        List<String> storeRelIds = new ArrayList<>();
        String generatedRelId = String.valueOf(helpFunctions.returnRandomValue(10000));
        String columnData = generatedRelId;
        storeRelIds.add(generatedRelId);
//        System.out.println("0 " + storeRelIds.get(0));
        for (int i = 1; i < rowsAmount; i++) {
            generatedRelId = String.valueOf(helpFunctions.returnRandomValue(10000));
            storeRelIds.add(generatedRelId);
//            System.out.println(i + " " + storeRelIds.get(i) + " rows " +rowsAmount);
            columnData += ("," + generatedRelId);
        }

        excelUtilities.modifyCellsForColumnAtSheetAsDouble("c_Store_ID", columnData, 0);
//        excelUtilities.modifyCellsForColumnAtSheet("c_Store_ID", columnData, 0);
        excelUtilities.close();

        return storeRelIds;
    }

    public List<String> updatePremierAuditResultsFileUniqueStoresExtRelIdsAndReturn(String fileName) {
        RewardCriteria rewardCriteria = dataExchanger.getRewardCriteriaObject();
        ExcelUtilities excelUtilities = new ExcelUtilities();
        excelUtilities.openHeadless(fileName, "src//bulkUploadFiles//");
        int sheetNo = 0;

        int rowsAmount = excelUtilities.getRowsAmount(sheetNo);
        List<String> storeRelIds = new ArrayList<>();
        String generatedRelId = "7777" + String.valueOf(helpFunctions.returnRandomValue(10000));
        String columnData = generatedRelId;
        storeRelIds.add(generatedRelId);
        for (int i = 1; i < rowsAmount; i++) {
//            if (!(rewardCriteria.getCriteriaRule().toLowerCase().contains("and") || rewardCriteria.getCriteriaRule().toLowerCase().contains("or"))) {
//                generatedRelId = "7777" + String.valueOf(helpFunctions.returnRandomValue(10000));
//                storeRelIds.add(generatedRelId);
//            }

            columnData += ("," + generatedRelId);
        }

        excelUtilities.modifyCellsForColumnAtSheet("Location ID", columnData, 0);
        excelUtilities.close();

        return storeRelIds;
    }


    @Deprecated
    public void updateAuditResultsFileToBeTheSameAsInCreatedStores(String fileName, List<String> storesIds) {
        ExcelUtilities excelUtilities = new ExcelUtilities();
        excelUtilities.openHeadless(fileName, "src//bulkUploadFiles//");

        String columnData = "";
        for (int i = 0; i < storesIds.size(); i++) {
            if (i == 0)
                columnData += storesIds.get(i);
            else
                columnData += "," + storesIds.get(i);
        }

        excelUtilities.modifyCellsForColumnAtSheet("c_Store_ID", columnData, 0);
        excelUtilities.close();

    }

    public String auditResult(String chainId, String auditId, String fileName, int code) {
        ValidatableResponse validatableResponse = auditResultController.auditResult(chainId, auditId, fileName, code);
        return validatableResponse.extract().response().asString();
    }


    public void waitAfterAuditProcessing() throws Throwable {
        int counter = 0;

        while (answerActions.filterAnswersForAudit(answerActions.getAnswersListWithoutPrint("0;20;auditId,desc", 200), dataExchanger.getAuditObject().getId()).equals("[]")) {
            Thread.sleep(sleep);
            counter++;
            System.out.println("Sleep loop: " + counter + "\n");
            if (counter > limit)
                break;
        }
    }


}