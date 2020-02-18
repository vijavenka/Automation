package com.iat.actions;


import com.iat.controller.ChainController;
import com.iat.domain.Chain;
import com.iat.utils.JsonParserUtils;
import gherkin.deps.com.google.gson.JsonArray;
import io.restassured.response.ValidatableResponse;

import static org.junit.Assert.assertTrue;

public class ChainActions {

    private ChainController chainController = new ChainController();
    JsonParserUtils jsonParserUtils = JsonParserUtils.getInstance();

    public String createNewChain(Chain chainBody) {
        ValidatableResponse validatableResponse = chainController.createNewChain(chainBody);
        System.out.println("\nRESPONSE: " + validatableResponse.extract().response().asString() + "\n");
        return validatableResponse.extract().response().asString();
    }

    public String deleteSpecificChain(String chainId) {
        ValidatableResponse validatableResponse = chainController.deleteSpecificChain(chainId);
        System.out.println("\nRESPONSE: " + validatableResponse.extract().response().asString() + "\n");
        return validatableResponse.extract().response().asString();
    }

    public String getChainDetailsById(String chainId) {
        ValidatableResponse validatableResponse = chainController.getChainDetailsById(chainId);
        System.out.println("\nRESPONSE: " + validatableResponse.extract().response().asString() + "\n");
        return validatableResponse.extract().response().asString();
    }


    public String getChainsList(int code) {
        ValidatableResponse validatableResponse = chainController.getChainsList(code);
        return validatableResponse.extract().response().asString();
    }


    public String getChainIdForChainName(String chainName) {
        JsonArray jsonArray = jsonParserUtils.convertStringToJsonArray(getChainsList(200));
        String id = "";
        boolean found = false;
        for (int i = 0; i < jsonArray.size(); i++) {
            if (jsonParserUtils.extractValueFromFlatJson(jsonArray.get(i).getAsJsonObject(), "chainName").equals(chainName)) {
                id = jsonParserUtils.extractValueFromFlatJson(jsonArray.get(i).getAsJsonObject(), "id");
                found = true;
                break;
            }
        }
        if (!found) {
            assertTrue("chain not found on chains list using name: " + chainName, found);
        }
        return id;
    }
}