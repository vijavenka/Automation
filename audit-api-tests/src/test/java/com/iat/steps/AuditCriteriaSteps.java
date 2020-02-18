package com.iat.steps;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iat.actions.AuditCriteriaActions;
import com.iat.domain.AuditCriteria;
import com.iat.domain.Question;
import com.iat.domain.Store;
import com.iat.utils.DataExchanger;
import com.iat.utils.JsonParserUtils;
import com.iat.validators.AuditCriteriaValidator;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

public class AuditCriteriaSteps {

    private AuditCriteriaActions auditCriteriaActions = new AuditCriteriaActions();
    private AuditCriteriaValidator auditCriteriaValidator = new AuditCriteriaValidator();
    DataExchanger dataExchanger = DataExchanger.getInstance();
    ObjectMapper mapper = new ObjectMapper();

    JsonParserUtils jsonParserUtils = JsonParserUtils.getInstance();
    String response;


    @Given("^Create AuditCriteria for test$")
    public void createAuditCriteriaForAuditResultFile() throws Throwable {
        mapper.enable(JsonParser.Feature.IGNORE_UNDEFINED, JsonParser.Feature.ALLOW_MISSING_VALUES);
        AuditCriteria auditCriteria;

        String jsonBody;
        List<Store> storesList = dataExchanger.getStores();
        List<Question> questionsList = dataExchanger.getQuestions();

        for (Store store : storesList) {
            for (Question question : questionsList) {
                jsonBody = "{\"segmentationType\" : null, \"extRelId\" : \"" + store.getExtRelId() + "\", \"storeId\" : " + store.getId() + ", \"auditId\" : " + dataExchanger.getAuditObject().getId() + ", \"questionId\": " + question.getId() + "}";
                auditCriteria = mapper.readValue(jsonBody, AuditCriteria.class);
                auditCriteriaActions.createAuditCriteria(auditCriteria.toString(), 201);
            }
        }
    }


    @When("^Generate AuditCriteria file for '(.+?)'$")
    public void generateAuditCriteria(String segmentation) throws Throwable {
//        String jsonBody = auditCriteriaActions.generateAuditCriteriaExportBody(segmentation);
//        auditCriteriaActions.exportRewardCriteria(jsonBody);
    }

    @Then("^AuditCriteria was properly generated for '(.+?)'$")
    public void validateIfAuditCriteriaProperlyGeneratedForSegmentation(String segmentation) throws Throwable {
//        String[] segmentation2 = segmentation.split(";");
//        int questionsNo = segmentation2[1].split(",").length;
//
//        int generatedAuditCriteriaNo = Integer.parseInt(auditCriteriaActions.getExportedAuditCriteriaCountSQL());
//        int storeSegmentationNo = Integer.parseInt(auditCriteriaActions.getStoresCountSQL(segmentation));
//
//        System.out.println("AuditCriteria count: " + generatedAuditCriteriaNo + " for segmentation: " + segmentation + ", Stores * questions No. count : " + (questionsNo * storeSegmentationNo) + " (stores count)");
//        assertTrue("AuditCriteria count: " + generatedAuditCriteriaNo + " for segmentation: " + segmentation + " is incorrect, should be: " + (questionsNo * storeSegmentationNo) + " (stores count)", generatedAuditCriteriaNo == (questionsNo * storeSegmentationNo));
//
//        auditCriteriaActions.validateIfRandomRowsHaveProperValuesAccordingSelectedSegmentation(segmentation);
    }

    @When("^Generate AuditCriteria file for incorrect data '(.+?)', '(.+?)'$")
    public void generateAuditCriteriaForIncorrectData(String segmentation, String status) throws Throwable {
//        String jsonBody = auditCriteriaActions.generateAuditCriteriaExportBody(segmentation);
//        response = auditCriteriaActions.exportRewardCriteria(jsonBody, status);
    }

    @Then("^Response for generating AuditCriteria file for incorrect data '(.+?)', '(.+?)', '(.+?)', '(.+?)', '(.+?)', '(.*?)' is in proper format$")
    public void validateGenerateAuditCriteriaForIncorrectDataErrorMessage(String segmentation, String message, String description, String objectNameFE, String fieldFE, String messageFE) throws Throwable {
//        auditCriteriaValidator.validateGenerationAuditCriteriaErrorMessages(response, segmentation, message, description, objectNameFE, fieldFE, messageFE);
    }
}