package com.iat.actions;


import com.iat.controller.FixCallsController;
import com.iat.utils.JsonParserUtils;
import io.restassured.response.ValidatableResponse;


public class FixCallsActions {

    private FixCallsController fixCallsController = new FixCallsController();
    JsonParserUtils jsonParserUtils = JsonParserUtils.getInstance();

    public String fixMissingSuppliersPartnerIds(int code) {
        ValidatableResponse validatableResponse = fixCallsController.fixMissingSuppliersPartnerIds(code);
        return validatableResponse.extract().response().asString();
    }


}
