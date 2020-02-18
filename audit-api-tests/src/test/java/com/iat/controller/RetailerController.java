package com.iat.controller;

import com.iat.Config;
import com.iat.actions.LoginActions;
import com.iat.contracts.RetailerContract;
import com.iat.utils.SessionIdKeeper;
import com.iat.validators.ContractValidator;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class RetailerController {

    private RetailerContract retailerContract = new RetailerContract();
    SessionIdKeeper bearer = SessionIdKeeper.getInstance();
    private ContractValidator contractValidator = new ContractValidator();


    private ValidatableResponse createRetailerRequest(String jsonBody, int code) {
        if (bearer.get().length() == 0) {
            new LoginActions().userLogIn(Config.iatCmsAdminCredentials);
        }

        String path = retailerContract.retailerPath();
        System.out.println("\nPath: POST " + path + "\n");
        System.out.println("\nBODY: " + jsonBody + "\n");
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + bearer.get())
                .body(jsonBody)
                .when()
                .post(path)
                .then()
                .statusCode(code);
    }

    public ValidatableResponse createNewRetailer(String jsonBody, int code) {
        ValidatableResponse validatableResponse = createRetailerRequest(jsonBody, code);
        System.out.println("\nPost Create retailer RESPONSE:");
        validatableResponse.extract().response().prettyPrint();
        if (code == 201)
            contractValidator.validateResponseWithContract("/retailer/POST-201-retailer.json", validatableResponse.extract().response().asString());
        return validatableResponse;
    }


    private ValidatableResponse deleteRetailerRequest(String id, int code) {
        String path = retailerContract.retailerByIdPath(id);
        System.out.println("\nPath: DELETE " + path + "\n");
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + bearer.get())
                .when()
                .delete(path)
                .then()
                .statusCode(code);
    }

    public ValidatableResponse deleteRetailer(String id, int code) {
        ValidatableResponse validatableResponse = deleteRetailerRequest(id, code);
        System.out.println("\nDelete retailer RESPONSE:");
        validatableResponse.extract().response().prettyPrint();
//        contractValidator.validateResponseWithContract("/suppliers/200-get-suppliers-list.json", validatableResponse.extract().response().asString());
        return validatableResponse;
    }

    private ValidatableResponse deleteRetailerFromChainRequest(String retailerId, String chainId, int code) {
        String path = retailerContract.deleRetailerFromChain(retailerId, chainId);
        System.out.println("\nPath: DELETE " + path + "\n");

        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + bearer.get())
                .when()
                .delete(path)
                .then()
                .statusCode(code);
    }

    public ValidatableResponse deleteRetailerFromChosenChain(String retailerId, String chainId, int code) {
        ValidatableResponse validatableResponse = deleteRetailerFromChainRequest(retailerId, chainId, code);
        System.out.println("\nDelete retailer from chain RESPONSE:");
        validatableResponse.extract().response().prettyPrint();
//        contractValidator.validateResponseWithContract("/suppliers/200-get-suppliers-list.json", validatableResponse.extract().response().asString());
        return validatableResponse;
    }


    private ValidatableResponse retailerTakeOverStoreRequest(String retailerId, String storeId, int code) {
        String path = retailerContract.retailerTakeOverStore(retailerId, storeId);
        System.out.println("\nPath: PUT " + path + "\n");

        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + bearer.get())
                .when()
                .put(path)
                .then()
                .statusCode(code);
    }

    public ValidatableResponse retailerTakeOverChosenStore(String retailerId, String storeId, int code) {
        ValidatableResponse validatableResponse = retailerTakeOverStoreRequest(retailerId, storeId, code);
        System.out.println("\nPPut take over store RESPONSE:");
        validatableResponse.extract().response().prettyPrint();
//        contractValidator.validateResponseWithContract("/suppliers/200-get-suppliers-list.json", validatableResponse.extract().response().asString());
        return validatableResponse;
    }

    private ValidatableResponse getRetailersListRequest(String params, int code) {
        String path = retailerContract.retailerPath(params);
        System.out.println("\nPath: GET " + path + "\n");

        return given()
                .header("Authorization", "Bearer " + bearer.get())
                .when()
                .get(path)
                .then()
                .statusCode(code);
    }

    public ValidatableResponse getRetailersList(String params, int code, boolean print) {
        ValidatableResponse validatableResponse = getRetailersListRequest(params, code);
        if (print) {
            System.out.println("\nGet retailers list RESPONSE:");
            validatableResponse.extract().response().prettyPrint();
        }

//        contractValidator.validateResponseWithContract("/suppliers/200-get-suppliers-list.json", validatableResponse.extract().response().asString());
        return validatableResponse;
    }


    private ValidatableResponse getRetailerByIdRequest(String id, int code) {
        String path = retailerContract.retailerByIdPath(id);
        System.out.println("\nPath: GET " + path + "\n");

        return given()
                .header("Authorization", "Bearer " + bearer.get())
                .when()
                .get(path)
                .then()
                .statusCode(code);
    }

    public ValidatableResponse getRetailerById(String id, int code) {
        ValidatableResponse validatableResponse = getRetailerByIdRequest(id, code);
        System.out.println("\nGet retailer RESPONSE:");
        validatableResponse.extract().response().prettyPrint();
//        contractValidator.validateResponseWithContract("/suppliers/200-get-suppliers-list.json", validatableResponse.extract().response().asString());
        return validatableResponse;
    }

}