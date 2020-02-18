package com.iat.actions;


import com.iat.controller.WholesalerController;
import com.iat.utils.JsonParserUtils;
import gherkin.deps.com.google.gson.JsonArray;
import io.restassured.response.ValidatableResponse;

import static org.junit.Assert.assertTrue;

public class WholesalerActions {

    private WholesalerController wholesalerController = new WholesalerController();
    JsonParserUtils jsonParserUtils = JsonParserUtils.getInstance();

    public String getWholesalersList() {
        ValidatableResponse validatableResponse = wholesalerController.getWholesalersList();
        return validatableResponse.extract().response().asString();
    }

    public String getWholesalerIdForWholesalerName(String storeName) {
        JsonArray jsonArray = jsonParserUtils.convertStringToJsonArray(getWholesalersList());
        String wholesalerId = "";
        boolean wholesalerFound = false;
        for (int i = 0; i < jsonArray.size(); i++) {
            if (jsonParserUtils.extractValueFromFlatJson(jsonArray.get(i).getAsJsonObject(), "wholesalerName").equals(storeName)) {
                wholesalerId = jsonParserUtils.extractValueFromFlatJson(jsonArray.get(i).getAsJsonObject(), "id");
                wholesalerFound = true;
                break;
            }
        }
        if (!wholesalerFound) {
            assertTrue("Wholesaler not found on wholesalers list using name", wholesalerFound);
        }
        return wholesalerId;
    }

}