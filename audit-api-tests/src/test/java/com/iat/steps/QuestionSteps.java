package com.iat.steps;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iat.actions.QuestionActions;
import com.iat.domain.Question;
import com.iat.utils.DataExchanger;
import com.iat.utils.JsonParserUtils;
import com.iat.validators.ContractValidator;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.deps.com.google.gson.JsonArray;
import gherkin.deps.com.google.gson.JsonElement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringContains.containsString;

public class QuestionSteps {

    private QuestionActions questionActions = new QuestionActions();
    private ContractValidator contractValidator = new ContractValidator();
    DataExchanger dataExchanger = DataExchanger.getInstance();
    JsonParserUtils jsonParserUtils = JsonParserUtils.getInstance();
    String response, lastCreatedQuestionId;
    ObjectMapper mapper = new ObjectMapper();


    @When("^Get question call is made$")
    public void getQuestionList() throws Throwable {
        response = questionActions.getQuestionsList(200);
    }

    @Then("^Get question call returns proper contract$")
    public void validateQuestionListContract() throws Throwable {
        //TODO contract validation
    }

    @When("^Create question call is made with jsonData '(.+?)', code '(.+?)'$")
    public void createQuestion(String jsonBody, int code) throws Throwable {
        if (jsonBody.equals("DEFAULT"))
            jsonBody = "{\"questionText\":\"API_AUDIT_CMS_QUESTION_" + dataExchanger.getQuestions().size() + "_\",\"adhocExtId\": \"RANDOM\",\"questionType\":\"PRODUCT\",\"placement\":\"FREEZER\",\"imagesNumber\":0,\"extRelId\": null,\"id\":null, \"productId\": \"previous_call\", \"categoryId\": null} ";


        mapper.enable(JsonParser.Feature.IGNORE_UNDEFINED, JsonParser.Feature.ALLOW_MISSING_VALUES);
        Question question = mapper.readValue(jsonBody, Question.class);

        response = questionActions.createQuestion(question.toString(), code);
        String id = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "id");
        dataExchanger.addQuestionToQuestionsList(question);
        dataExchanger.getQuestions().get(dataExchanger.getQuestions().size() - 1).setId(id);

        lastCreatedQuestionId = id;

        Thread.sleep(1000);
    }

    @Then("^Create question call returns proper data$")
    public void validateCreateQuestionRequestResponse() throws Throwable {

        Question questionToValidate = dataExchanger.getQuestions().get(dataExchanger.getQuestions().size() - 1);

        String responseId = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "id");
        String responseQuestionText = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "questionText");
        String responseaAdhocExtId = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "adhocExtId");
        String responsequestionType = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "questionType");
        String responsePlacement = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "placement");
        String responseImagesNumber = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "imagesNumber");
        String responseExtRelId = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "extRelId");
        String responseProductId = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "productId");
        String responseCategoryId = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "categoryId");

        assertThat("field id is incorrect", responseId, is(questionToValidate.getId()));
        assertThat("field questionText is incorrect", responseQuestionText, is(questionToValidate.getQuestionText()));
        assertThat("field adhocExtId is incorrect", responseaAdhocExtId, is(String.valueOf(questionToValidate.getAdhocExtId())));
        assertThat("field questionType is incorrect", responsequestionType, is(questionToValidate.getQuestionType()));
        assertThat("field placement is incorrect", responsePlacement, is(String.valueOf(questionToValidate.getPlacement())));
        assertThat("field imagesNumber is incorrect", responseImagesNumber, is(String.valueOf(questionToValidate.getImagesNumber())));
        assertThat("field extRelId is incorrect", responseExtRelId, is(String.valueOf(questionToValidate.getExtRelId())));
        assertThat("field productId is incorrect", responseProductId, is(String.valueOf(questionToValidate.getProductId())));
        assertThat("field categoryId is incorrect", responseCategoryId, is(String.valueOf(questionToValidate.getCategoryId())));

    }

    @Then("^Create question call returns proper error message '(.+?)', description '(.+?)', fieldErrors '(.+?)'$")
    public void validateCreateQuestionErrorMessage(String message, String description, String fieldErrors) throws Throwable {

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
                for (JsonElement responseElement : responseFieldErrorsJsonArray) {
                    if (errorsFieldName.equals(responseElement.getAsJsonObject().get("field").getAsString())) {
                        foundField = true;
                        String errorsObjectName = validationElement.getAsJsonObject().get("objectName").getAsString();
                        String errorsMessage = validationElement.getAsJsonObject().get("message").getAsString();

                        assertThat("Field [" + errorsFieldName + "] error have incorrect objectName", responseElement.getAsJsonObject().get("objectName").getAsString(), is(errorsObjectName));
                        assertThat("Field [" + errorsFieldName + "] error have incorrect message", responseElement.getAsJsonObject().get("message").getAsString(), is(errorsMessage));
                        break;
                    }
                }
                assertThat("Field [" + errorsFieldName + "] not found in response", foundField, is(true));
            }
        } else {
            String responseFieldErrors = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "fieldErrors");
            assertThat("field fieldErrors: " + responseFieldErrors + " is incorrect, should be: " + fieldErrors, responseFieldErrors, is(fieldErrors));
        }
    }


    @Then("^Get question by id '(.+?)', code '(.+?)'$")
    public void getQuestionById(String id, int code) throws Throwable {
        if (id.equals("previous_call"))
            id = lastCreatedQuestionId;

        response = questionActions.getQuestionById(id, 200);
    }

    @Then("^Get question by id returns proper data$")
    public void validateGetQuestionById() throws Throwable {
        Question questionToValidate = dataExchanger.getQuestions().get(dataExchanger.getQuestions().size() - 1);

        String responseId = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "id");
        String responseQuestionText = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "questionText");
        String responseaAdhocExtId = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "adhocExtId");
        String responsequestionType = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "questionType");
        String responsePlacement = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "placement");
        String responseImagesNumber = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "imagesNumber");
        String responseExtRelId = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "extRelId");
        String responseProductId = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "productId");
        String responseCategoryId = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "categoryId");

        assertThat("field id is incorrect", responseId, is(questionToValidate.getId()));
        assertThat("field questionText is incorrect", responseQuestionText, is(questionToValidate.getQuestionText()));
        assertThat("field adhocExtId is incorrect", responseaAdhocExtId, is(String.valueOf(questionToValidate.getAdhocExtId())));
        assertThat("field questionType is incorrect", responsequestionType, is(questionToValidate.getQuestionType()));
        assertThat("field placement is incorrect", responsePlacement, is(String.valueOf(questionToValidate.getPlacement())));
        assertThat("field imagesNumber is incorrect", responseImagesNumber, is(String.valueOf(questionToValidate.getImagesNumber())));
        assertThat("field extRelId is incorrect", responseExtRelId, is(String.valueOf(questionToValidate.getExtRelId())));
        assertThat("field productId is incorrect", responseProductId, is(String.valueOf(questionToValidate.getProductId())));
        assertThat("field categoryId is incorrect", responseCategoryId, is(String.valueOf(questionToValidate.getCategoryId())));

    }

    @Then("^Delete question by id '(.+?)', code '(.+?)'$")
    public void deleteQuestionById(String id, int code) throws Throwable {
        if (id.equals("previous_call"))
            id = lastCreatedQuestionId;

        response = questionActions.deleteQuestionById(id, code);
    }


}