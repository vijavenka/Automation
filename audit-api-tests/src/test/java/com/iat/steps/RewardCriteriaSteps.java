package com.iat.steps;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iat.actions.RewardCriteriaActions;
import com.iat.domain.RewardCriteria;
import com.iat.utils.DataExchanger;
import com.iat.utils.JsonParserUtils;
import com.iat.validators.ContractValidator;
import com.iat.validators.RewardsCriteriaValidator;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.deps.com.google.gson.JsonArray;
import gherkin.deps.com.google.gson.JsonElement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringContains.containsString;

public class RewardCriteriaSteps {

    private RewardCriteriaActions rewardCriteriaActions = new RewardCriteriaActions();
    private ContractValidator contractValidator = new ContractValidator();
    private RewardsCriteriaValidator rewardsCriteriaValidator = new RewardsCriteriaValidator();
    JsonParserUtils jsonParserUtils = JsonParserUtils.getInstance();
    DataExchanger dataExchanger = DataExchanger.getInstance();
    String response;
    ObjectMapper mapper = new ObjectMapper();


    //Check if it's possible to get lists of all awailable reward-criteria
    @When("^Get reward-criteria call is made$")
    public void getRewardCriteria() throws Throwable {
        response = rewardCriteriaActions.getRewardCriteriaList(200);
    }

    @Then("^Get reward-criteria call returns proper contract$")
    public void get_reward_criteria_call_returns_proper_contract() throws Throwable {
        //validated in controller
    }


    @When("^Create reward criteria call is made for following jsonData '(.+?)', code '(\\d+)'$")
    public void createRewardCriteriaCallJson(String jsonBody, int code) throws Throwable {
        mapper.enable(JsonParser.Feature.IGNORE_UNDEFINED, JsonParser.Feature.ALLOW_MISSING_VALUES);
        RewardCriteria rewardCriteria = mapper.readValue(jsonBody, RewardCriteria.class);

        dataExchanger.setRewardCriteriaObject(rewardCriteria);
        response = rewardCriteriaActions.createRewardCriteria(rewardCriteria.toString(), code);

        String id = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "id");
        String tagKey = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "tagKey");
        dataExchanger.getRewardCriteriaObject().setId(id);
        dataExchanger.getRewardCriteriaObject().setTagKey(tagKey);
    }


    @Then("^Create reward criteria call returns proper data$")
    public void validateIfCreateRewardCriteriaCall() throws Throwable {
        RewardCriteria rewardCriteria = dataExchanger.getRewardCriteriaObject();
        String id = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "id");
        String criteriaName = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "criteriaName");
        String criteriaRule = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "criteriaRule");
        String points = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "points");
        String tagKey = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "tagKey");
        String auditId = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "auditId");

        assertThat("field criteriaName is incorrect", criteriaName, is(String.valueOf(rewardCriteria.getCriteriaName())));
        assertThat("field criteriaRule is incorrect", criteriaRule, is(String.valueOf(rewardCriteria.getCriteriaRule())));
        assertThat("field points is incorrect", points, is(String.valueOf(rewardCriteria.getPoints())));
        assertThat("field auditId is incorrect", auditId, is(String.valueOf(rewardCriteria.getAuditId())));
    }

    @Then("^Tag was created for reward criteria in points-manager under proper partner$")
    public void validateIfTagCreationInPointsManager() throws Throwable {
        rewardsCriteriaValidator.validateIfTagCreationInPointsManager(response);
    }

    @Then("^Delete reward-criteria by id '(.+?)'$")
    public void deleteRewardsCriteriaById(String rewardCriteriaId) throws Throwable {
        if (rewardCriteriaId.equals("previous_call"))
            rewardCriteriaId = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "id");

        rewardCriteriaActions.deleteRewardCriteria(rewardCriteriaId, 200);
    }

    @Then("Create reward criteria call returns proper error message '(.+?)', description '(.+?)', fieldErrors '(.+?)'$")
    public void validateCreateRewardCriteriaErrorMessage(String message, String description, String fieldErrors) throws Throwable {
        String responseMessage = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "message");
        String responseDescription = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "description");

        assertThat("field message is incorrect", responseMessage, is(message));
        assertThat("field description is incorrect", responseDescription, containsString(description));

        if (message.equals("error.validation") && fieldErrors.contains("fieldErrors")) {
            JsonArray responseFieldErrorsJsonArray = new gherkin.deps.com.google.gson.JsonParser().parse(response).getAsJsonObject().getAsJsonArray("fieldErrors");
            JsonArray validateFieldErrorsJsonArray = new gherkin.deps.com.google.gson.JsonParser().parse(fieldErrors).getAsJsonObject().getAsJsonArray("fieldErrors");

            for (JsonElement validationElement : validateFieldErrorsJsonArray) {
                boolean foundField = false;
                String errorsFieldName = validationElement.getAsJsonObject().get("field").getAsString();
                String errorsMessage = validationElement.getAsJsonObject().get("message").getAsString();

                for (JsonElement responseElement : responseFieldErrorsJsonArray) {
                    if (errorsFieldName.equals(responseElement.getAsJsonObject().get("field").getAsString()) && errorsMessage.equals(responseElement.getAsJsonObject().get("message").getAsString())) {
                        foundField = true;
                        String errorsObjectName = validationElement.getAsJsonObject().get("objectName").getAsString();


                        assertThat("Field [" + errorsFieldName + "] error have incorrect objectName", responseElement.getAsJsonObject().get("objectName").getAsString(), is(errorsObjectName));
                        assertThat("Field [" + errorsFieldName + "] error have incorrect message", responseElement.getAsJsonObject().get("message").getAsString(), is(errorsMessage));
                        break;
                    }
                }
                assertThat("Field [" + errorsFieldName + "] with message [" + errorsMessage + "] not found in response", foundField, is(true));
            }
        } else {
            String responseFieldErrors = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "fieldErrors");
            assertThat("field fieldErrors: " + responseFieldErrors + " is incorrect, should be: " + fieldErrors, responseFieldErrors, is(fieldErrors));
        }

    }


    @Given("^Partner for related supplier '(.+?)' not exist in points-manager$")
    public void checkIfPartnerExists(String supplierName) throws Throwable {
        if (supplierName.equals("previous_call"))
            supplierName = dataExchanger.getSuppliers().get(dataExchanger.getSuppliers().size() - 1).getSupplierName();

        rewardsCriteriaValidator.validatePartnerNotExistForSupplier(supplierName);
    }

}