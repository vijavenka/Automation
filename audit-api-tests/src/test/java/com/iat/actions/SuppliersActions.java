package com.iat.actions;


import com.iat.controller.SuppliersController;
import com.iat.utils.JsonParserUtils;
import gherkin.deps.com.google.gson.JsonArray;
import io.restassured.response.ValidatableResponse;

import static org.junit.Assert.assertTrue;


public class SuppliersActions {

    private SuppliersController suppliersController = new SuppliersController();
    JsonParserUtils jsonParserUtils = JsonParserUtils.getInstance();
    private String auditId = "";
    private int generatedRewardCriteriaPoints = 0;

    public String getSuppliersList(int code) {
        ValidatableResponse validatableResponse = suppliersController.getSuppliersList(code);
        return validatableResponse.extract().response().asString();
    }


    public String createSupplier(String jsonBody, int code) {
        ValidatableResponse validatableResponse = suppliersController.createSupplier(jsonBody, code);
        return validatableResponse.extract().response().asString();
    }

    public String getSupplierById(String id, int code) {
        ValidatableResponse validatableResponse = suppliersController.getSupplierById(id, code);
        return validatableResponse.extract().response().asString();
    }

    public String deleteSupplierById(String id, int code) {
        ValidatableResponse validatableResponse = suppliersController.deleteSupplierById(id, code);
        return validatableResponse.extract().response().asString();
    }


    public String getIdforSupplierName(String supplierName) {
        String response = getSuppliersList(200);

        JsonArray jsonArray = jsonParserUtils.convertStringToJsonArray(response);
        String id = "";
        boolean supplierFound = false;
        for (int i = 0; i < jsonArray.size(); i++) {
            if (jsonParserUtils.extractValueFromFlatJson(jsonArray.get(i).getAsJsonObject(), "supplierName").equals(supplierName)) {
                id = jsonParserUtils.extractValueFromFlatJson(jsonArray.get(i).getAsJsonObject(), "id");
                supplierFound = true;
                break;
            }
        }
        if (!supplierFound) {
            assertTrue("Supplier not found on stores list using name", supplierFound);
        }
        return id;
    }


}
