package com.iat.actions;


import com.iat.controller.AuditsController;
import com.iat.utils.JsonParserUtils;
import gherkin.deps.com.google.gson.JsonArray;
import io.restassured.response.ValidatableResponse;

import static org.junit.Assert.assertTrue;


public class AuditsActions {

    private AuditsController auditsController = new AuditsController();
    JsonParserUtils jsonParserUtils = JsonParserUtils.getInstance();

    public String getAuditsList(int code) {
        ValidatableResponse validatableResponse = auditsController.getAuditsList(code);
        return validatableResponse.extract().response().asString();
    }


    public String createAudits(String jsonBody, int code) {
        ValidatableResponse validatableResponse = auditsController.createAudit(jsonBody, code);
        return validatableResponse.extract().response().asString();
    }

    public String deleteAuditById(String id, int code) {
        ValidatableResponse validatableResponse = auditsController.deleteAuditById(id, code);
        return validatableResponse.extract().response().asString();
    }

    public String getIdForAuditName(String auditName) {
        String response = getAuditsList(200);

        JsonArray jsonArray = jsonParserUtils.convertStringToJsonArray(response);
        String auditId = "";
        boolean auditFound = false;
        for (int i = 0; i < jsonArray.size(); i++) {
            if (jsonParserUtils.extractValueFromFlatJson(jsonArray.get(i).getAsJsonObject(), "auditName").equals(auditName)) {
                auditId = jsonParserUtils.extractValueFromFlatJson(jsonArray.get(i).getAsJsonObject(), "id");
                auditFound = true;
                break;
            }
        }

        assertTrue("Audit not found on audit list using name", auditFound);
        return auditId;
    }

}
