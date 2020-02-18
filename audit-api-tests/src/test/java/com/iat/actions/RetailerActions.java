package com.iat.actions;


import com.iat.controller.RetailerController;
import com.iat.utils.JsonParserUtils;
import gherkin.deps.com.google.gson.JsonArray;
import io.restassured.response.ValidatableResponse;

public class RetailerActions {

    private RetailerController retailerController = new RetailerController();
    JsonParserUtils jsonParserUtils = JsonParserUtils.getInstance();

    public String createNewRetailer(String jsonBody, int code) {
        ValidatableResponse validatableResponse = retailerController.createNewRetailer(jsonBody, code);
        return validatableResponse.extract().response().asString();
    }

    public String deleteRetailer(String id, int code) {
        ValidatableResponse validatableResponse = retailerController.deleteRetailer(id, code);
        return validatableResponse.extract().response().asString();
    }

    public String deleteRetailerFromChosenChain(String retailerId, String chainId, int code) {
        ValidatableResponse validatableResponse = retailerController.deleteRetailerFromChosenChain(retailerId, chainId, code);
        return validatableResponse.extract().response().asString();
    }


    public String retailerTakeOverChosenStore(String retailerId, String storeId, int code) {
        ValidatableResponse validatableResponse = retailerController.retailerTakeOverChosenStore(retailerId, storeId, code);
        return validatableResponse.extract().response().asString();
    }

    public String getRetailersList(String params, int code) {
        ValidatableResponse validatableResponse = retailerController.getRetailersList(params, code, true);
        return validatableResponse.extract().response().asString();
    }

    public String getRetailersListWithoutPrint(String params, int code) {
        ValidatableResponse validatableResponse = retailerController.getRetailersList(params, code, false);
        return validatableResponse.extract().response().asString();
    }

    public String getRetailerById(String id, int code) {
        ValidatableResponse validatableResponse = retailerController.getRetailerById(id, code);
        return validatableResponse.extract().response().asString();
    }

    public String getRetailerIdForRetailerEmail(String retailerEmail) {
        JsonArray jsonArray = jsonParserUtils.convertStringToJsonArray(getRetailersListWithoutPrint("0;15;id,desc", 200));
        String retailerId = "";
        for (int i = 0; i < jsonArray.size(); i++) {
            if (jsonParserUtils.extractValueFromFlatJson(jsonArray.get(i).getAsJsonObject(), "email").equals(retailerEmail)) {
                retailerId = jsonParserUtils.extractValueFromFlatJson(jsonArray.get(i).getAsJsonObject(), "id");
                break;
            }
        }
        return retailerId;
    }
}