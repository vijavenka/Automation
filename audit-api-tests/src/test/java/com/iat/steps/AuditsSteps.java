package com.iat.steps;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iat.actions.AuditsActions;
import com.iat.domain.Audit;
import com.iat.utils.DataExchanger;
import com.iat.utils.JsonParserUtils;
import com.iat.validators.ContractValidator;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.deps.com.google.gson.JsonArray;
import gherkin.deps.com.google.gson.JsonElement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringContains.containsString;

public class AuditsSteps {

    private AuditsActions auditsActions = new AuditsActions();
    private ContractValidator contractValidator = new ContractValidator();
    DataExchanger dataExchanger = DataExchanger.getInstance();
    JsonParserUtils jsonParserUtils = JsonParserUtils.getInstance();
    String response;
    ObjectMapper mapper = new ObjectMapper();


    @When("^Get audits call is made$")
    public void getAuditsList() throws Throwable {
        response = auditsActions.getAuditsList(200);
        System.out.println("\nRESPONSE: " + response + "\n");
    }

    @Then("^Get audits call returns proper contract$")
    public void getAuditsListContractValidation() throws Throwable {
        //contract is validated at controller level
    }

    @Given("^Create audit call is made is made with jsonData '(.+?)', code '(.+?)'$")
    public void createAudit(String jsonBody, int code) throws Throwable {
        if (jsonBody.equals("DEFAULT"))
            jsonBody = "{\"auditName\": \"API_AUDIT_CMS_AUDIT_rewardCriteria_\", \"auditStart\": \"2025-01-01\", \"auditEnd\": \"2025-01-01\"}";


        mapper.enable(JsonParser.Feature.IGNORE_UNDEFINED, JsonParser.Feature.ALLOW_MISSING_VALUES);
        Audit audit = mapper.readValue(jsonBody, Audit.class);

        dataExchanger.setAuditObject(audit);
        response = auditsActions.createAudits(audit.toString(), code);
        dataExchanger.getAuditObject().setId(jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "id"));
    }


    @Then("^Create audit call returns proper data$")
    public void validateCreateAuditRequestResponse() throws Throwable {

        Audit auditToValidate = dataExchanger.getAuditObject();

        String responseId = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "id");
        String responseAuditName = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "auditName");
        String responseAuditStart = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "auditStart");
        String responseAuditEnd = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "auditEnd");


        assertThat("field id is incorrect", responseId, is(auditToValidate.getId()));
        assertThat("field auditName is incorrect", responseAuditName, is(auditToValidate.getAuditName()));
        assertThat("field auditStart is incorrect", responseAuditStart, is(String.valueOf(auditToValidate.getAuditStart())));
        assertThat("field auditEnd is incorrect", responseAuditEnd, is(auditToValidate.getAuditEnd()));

    }

    @Then("^Create audit call returns proper error message '(.+?)', description '(.+?)', fieldErrors '(.+?)'$")
    public void validateCreateAuditErrorMessage(String message, String description, String fieldErrors) throws Throwable {

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


    @When("^Delete audit by id '(.+?)', code '(.+?)'$")
    public void deleteAuditById(String auditId, int code) throws Throwable {
        if (auditId.equals("previous_call")) {
            auditsActions.deleteAuditById(dataExchanger.getAuditObject().getId(), code);
        } else {
            auditsActions.deleteAuditById(auditId, code);
        }

    }

}
