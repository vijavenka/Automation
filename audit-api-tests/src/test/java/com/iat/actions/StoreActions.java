package com.iat.actions;

import com.iat.controller.StoreController;
import com.iat.utils.DataExchanger;
import com.iat.utils.ExcelUtilities;
import com.iat.utils.HelpFunctions;
import com.iat.utils.JsonParserUtils;
import gherkin.deps.com.google.gson.JsonArray;
import io.restassured.response.ValidatableResponse;

import java.net.SocketException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class StoreActions {

    private StoreController storeController = new StoreController();
    JsonParserUtils jsonParserUtils = JsonParserUtils.getInstance();
    ExcelUtilities excelUtilities = new ExcelUtilities();
    HelpFunctions helpFunctions = new HelpFunctions();
    Date dateTime = new Date();

    DataExchanger dataExchanger = DataExchanger.getInstance();


    public String createStore(String storeBody, int code) {
        ValidatableResponse validatableResponse = storeController.createStore(storeBody, code);
        return validatableResponse.extract().response().asString();
    }

    public String updateStore(String jsonBody, int code) {
        ValidatableResponse validatableResponse = storeController.updateStore(jsonBody, code);
        return validatableResponse.extract().response().asString();
    }

    public String searchForStores(String query, int code) {
        ValidatableResponse validatableResponse = storeController.searchStores(query, code);
        return validatableResponse.extract().response().asString();
    }

    public String getStores(String params, int code, boolean print) {
        ValidatableResponse validatableResponse = storeController.getStores(params, code, print);
        return validatableResponse.extract().response().asString();
    }

    public String deleteStore(String storeId, int code) {
        ValidatableResponse validatableResponse = storeController.deleteStore(storeId, code);
        return validatableResponse.extract().response().asString();
    }

    public String getStore(String storeId, int code) {
        ValidatableResponse validatableResponse = storeController.getStoreById(storeId, code);
        return validatableResponse.extract().response().asString();
    }


    public String bulkUploadStoresAndRetailers(String partnerShortName, String fileName, int code) throws SocketException {
        ValidatableResponse validatableResponse = storeController.bulkUploadStoresAndRetailers(partnerShortName, fileName, code);
        return validatableResponse.extract().response().asString();
    }


    public String bulkUploadStoresPremierOnly(String partnerShortName, String fileName, int code) throws SocketException {
        ValidatableResponse validatableResponse = storeController.bulkUploadStoresPremierOnly(partnerShortName, fileName, code);
        return validatableResponse.extract().response().asString();
    }


    public String getStoreIdForStoreName(String storeName) {
        JsonArray jsonArray = jsonParserUtils.convertStringToJsonArray(getStores("0;1000;storeName,asc", 200, false));
        String storeId = "";
        boolean storeFound = false;
        for (int i = 0; i < jsonArray.size(); i++) {
            if (jsonParserUtils.extractValueFromFlatJson(jsonArray.get(i).getAsJsonObject(), "storeName").equals(storeName)) {
                storeId = jsonParserUtils.extractValueFromFlatJson(jsonArray.get(i).getAsJsonObject(), "id");
                storeFound = true;
                break;
            }
        }
        if (!storeFound) {
            assertTrue("Store not found on stores list using name", storeFound);
        }
        return storeId;
    }

    public String getStoreIdForStoreExtRelId(String extRelId) {
        JsonArray jsonArray = jsonParserUtils.convertStringToJsonArray(getStores("0;15;id,desc", 200, true));
        String storeId = "";
        boolean storeFound = false;
        for (int i = 0; i < jsonArray.size(); i++) {
            if (jsonParserUtils.extractValueFromFlatJson(jsonArray.get(i).getAsJsonObject(), "extRelId").equals(extRelId)) {
                storeId = jsonParserUtils.extractValueFromFlatJson(jsonArray.get(i).getAsJsonObject(), "id");
                storeFound = true;
                break;
            }
        }
        if (!storeFound) {
            assertTrue("Store not found on stores list using extRelId: " + extRelId, storeFound);
        }
        return storeId;
    }

    public String getStoreIdForStoreExtRelIdNotFound(String extRelId) {
        JsonArray jsonArray = jsonParserUtils.convertStringToJsonArray(getStores("0;15;id,desc", 200, false));
        String storeId = "";
        boolean storeFound = false;
        for (int i = 0; i < jsonArray.size(); i++) {
            if (jsonParserUtils.extractValueFromFlatJson(jsonArray.get(i).getAsJsonObject(), "extRelId").equals(extRelId)) {
                storeId = jsonParserUtils.extractValueFromFlatJson(jsonArray.get(i).getAsJsonObject(), "id");
                storeFound = true;
                break;
            }
        }
        if (storeFound) {
            assertTrue("Store found on stores list using extRelId", storeFound);
        }
        return storeId;
    }


    public String getStoreIdForStoreExtRelIdAndChain(String extRelId, String chain) {
        JsonArray jsonArray = jsonParserUtils.convertStringToJsonArray(getStores("0;15;id,desc", 200, false));
        String storeId = "";
        boolean storeFound = false;
        for (int i = 0; i < jsonArray.size(); i++) {
            if (jsonParserUtils.extractValueFromFlatJson(jsonArray.get(i).getAsJsonObject(), "extRelId").equals(extRelId) &&
                    jsonParserUtils.extractValueFromFlatJson(jsonArray.get(i).getAsJsonObject(), "chainId").equals(chain)) {
                storeId = jsonParserUtils.extractValueFromFlatJson(jsonArray.get(i).getAsJsonObject(), "id");
                storeFound = true;
                break;
            }
        }
        if (!storeFound) {
            assertTrue("Store not found on stores list using extRelId and chain", storeFound);
        }
        return storeId;
    }


//    public List<String> updateImportRetailersFileUniqueStoresExtRelIdsAndReturn(String fileName){
//        ExcelUtilities excelUtilities = new ExcelUtilities();
//        excelUtilities.openHeadless(fileName, "src//bulkUploadFiles//");
//        int sheetNo = 0;
//
//        int rowsAmount = excelUtilities.getRowsAmount(sheetNo);
//        List<String> storeRelIds =  new ArrayList<>();
//        String generatedRelId = "7777" + String.valueOf(helpFunctions.returnRandomValue(10000));
//        String columnData = generatedRelId;
//        storeRelIds.add(generatedRelId);
//
//        for (int i = 1; i < rowsAmount; i++){
//            generatedRelId = "7777" + String.valueOf(helpFunctions.returnRandomValue(10000));
//            storeRelIds.add(generatedRelId);
//            columnData += ("," + generatedRelId);
//        }
//
//        excelUtilities.modifyCellsForColumnAtSheetAsDouble("ext_rel_id", columnData, 0);
//        excelUtilities.close();
//
//        return storeRelIds;
//    }

    public void updateImportRetailersFileExtRelIdsStores(String fileName, List<String> storeRelIds) {
        ExcelUtilities excelUtilities = new ExcelUtilities();
        excelUtilities.openHeadless(fileName, "src//bulkUploadFiles//");
        int sheetNo = 0;

//        System.out.println("Rows: " + rowsAmount);
        String columnData = storeRelIds.get(0);

        for (int i = 1; i < storeRelIds.size(); i++) {
            columnData += ("," + storeRelIds.get(i));
        }

        excelUtilities.modifyCellsForColumnAtSheetAsDouble("ext_rel_id", columnData, sheetNo);
        excelUtilities.close();
    }


    public List<String> extractStoreExtRelIdFromImportRetailers(String fileName) {
        ExcelUtilities excelUtilities = new ExcelUtilities();
        excelUtilities.openHeadless(fileName, "src//bulkUploadFiles//");
        int sheetNo = 0;

        int rowsAmount = excelUtilities.getRowsAmount(sheetNo);
        List<String> storeRelIds = new ArrayList<>();

        for (int i = 0; i < rowsAmount; i++) {
            storeRelIds.add(excelUtilities.getCellNumericValueAsString(sheetNo, (i + 1), "ext_rel_id"));
        }

        return storeRelIds;
    }


    public void updateEmailsInImportRetailers(String fileName) {
        ExcelUtilities excelUtilities = new ExcelUtilities();
        excelUtilities.openHeadless(fileName, "src//bulkUploadFiles//");
        int sheetNo = 0;

        int rowsAmount = excelUtilities.getRowsAmount(sheetNo);

        dateTime = new Date();
        for (int i = 0; i < rowsAmount; i++) {
            if (i == 0)
                dataExchanger.setGeneratedRetailerMail(("API_AUDIT_CMS_" + i + "_test_" + dateTime.getTime() + "@iat.test.pl").toLowerCase());
            else
                dataExchanger.addGeneratedRetailerMail((",API_AUDIT_CMS_" + i + "_test_" + dateTime.getTime() + "@iat.test.pl").toLowerCase());
        }

        excelUtilities.modifyCellsForColumn("Test email", dataExchanger.getGeneratedRetailerMail());
        excelUtilities.close();
    }


}