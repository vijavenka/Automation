package com.iat.controller;

import com.iat.Config;
import com.iat.actions.LoginActions;
import com.iat.contracts.StoreContract;
import com.iat.utils.SessionIdKeeper;
import com.iat.validators.ContractValidator;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import java.io.File;
import java.net.SocketException;

import static io.restassured.RestAssured.given;


public class StoreController {

    private StoreContract storeContract = new StoreContract();
    SessionIdKeeper bearer = SessionIdKeeper.getInstance();
    private ContractValidator contractValidator = new ContractValidator();

    private ValidatableResponse createStoreRequest(String jsonBody, int code) {
        if (bearer.get().length() == 0) {
            new LoginActions().userLogIn(Config.iatCmsAdminCredentials);
        }

        String path = storeContract.getStoresPath();
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

    public ValidatableResponse createStore(String storeBody, int code) {
        ValidatableResponse validatableResponse = createStoreRequest(storeBody, code);
        System.out.println("\nPost create store RESPONSE:");
        validatableResponse.extract().response().prettyPrint();
//        contractValidator.validateResponseWithContract("/suppliers/200-get-suppliers-list.json", validatableResponse.extract().response().asString());
        return validatableResponse;
    }

    private ValidatableResponse updateStoreRequest(String jsonBody, int code) {
        if (bearer.get().length() == 0) {
            new LoginActions().userLogIn(Config.iatCmsAdminCredentials);
        }

        String path = storeContract.getStoresPath();
        System.out.println("\nPath: PUT " + path + "\n");
        System.out.println("\nBODY: " + jsonBody + "\n");
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + bearer.get())
                .body(jsonBody)
                .when()
                .put(path)
                .then()
                .statusCode(code);
    }

    public ValidatableResponse updateStore(String jsonBody, int code) {
        ValidatableResponse validatableResponse = updateStoreRequest(jsonBody, code);
        System.out.println("\nPost update store RESPONSE:");
        validatableResponse.extract().response().prettyPrint();
//        contractValidator.validateResponseWithContract("/suppliers/200-get-suppliers-list.json", validatableResponse.extract().response().asString());
        return validatableResponse;
    }

    private ValidatableResponse searchStoresRequest(String query, int code) {
        String path = storeContract.searchStores();
        System.out.println("\nPath: GET " + path + "\n");
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + bearer.get())
                .param(query)
                .when()
                .get(path)
                .then()
                .statusCode(code);
    }

    public ValidatableResponse searchStores(String query, int code) {
        ValidatableResponse validatableResponse = searchStoresRequest(query, code);
        System.out.println("\nGet search stores RESPONSE:");
        validatableResponse.extract().response().prettyPrint();
//        contractValidator.validateResponseWithContract("/suppliers/200-get-suppliers-list.json", validatableResponse.extract().response().asString());
        return validatableResponse;
    }

    private ValidatableResponse getStoresRequest(String params, int code) {
        String path = storeContract.getStoresPath(params);
        System.out.println("\nPath: GET " + path + "\n");
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + bearer.get())
                .when()
                .get(path)
                .then()
                .statusCode(code);
    }

    public ValidatableResponse getStores(String params, int code, boolean print) {
        ValidatableResponse validatableResponse = getStoresRequest(params, code);
        if (print) {
            System.out.println("\nGet stores list RESPONSE:");
            validatableResponse.extract().response().prettyPrint();
        }
//        contractValidator.validateResponseWithContract("/suppliers/200-get-suppliers-list.json", validatableResponse.extract().response().asString());
        return validatableResponse;
    }

    private ValidatableResponse deleteStoreRequest(String storeId, int code) {
        String path = storeContract.getStoreByIdPath(storeId);
        System.out.println("\nPath: DELETE " + path + "\n");
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + bearer.get())
                .when()
                .delete(path)
                .then()
                .statusCode(code);
    }

    public ValidatableResponse deleteStore(String storeId, int code) {
        ValidatableResponse validatableResponse = deleteStoreRequest(storeId, code);
        System.out.println("\nDelete store RESPONSE:");
        validatableResponse.extract().response().prettyPrint();
//        contractValidator.validateResponseWithContract("/suppliers/200-get-suppliers-list.json", validatableResponse.extract().response().asString());
        return validatableResponse;
    }

    private ValidatableResponse getStoreByIdRequest(String storeId, int code) {
        String path = storeContract.getStoreByIdPath(storeId);
        System.out.println("\nPath: GET " + path + "\n");
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + bearer.get())
                .when()
                .get(path)
                .then()
                .statusCode(code);
    }

    public ValidatableResponse getStoreById(String storeId, int code) {
        ValidatableResponse validatableResponse = getStoreByIdRequest(storeId, code);
        System.out.println("\nGet store by id RESPONSE:");
        validatableResponse.extract().response().prettyPrint();
//        contractValidator.validateResponseWithContract("/suppliers/200-get-suppliers-list.json", validatableResponse.extract().response().asString());
        return validatableResponse;
    }

    private ValidatableResponse bulkUploadStoresRetailers(String partnerShortName, String fileName, int code) throws SocketException {
        String path = storeContract.getStoresBulkUploadRetailersPath(partnerShortName);
        System.out.println("\nPath: POST " + path + "\n");
        System.out.println("Path: File " + "src//bulkUploadFiles/" + fileName + "\n");

        return given()
                .multiPart("file", new File("src//bulkUploadFiles/" + fileName))
                .header("Authorization", "Bearer " + bearer.get())
                .when()
                .post(path)
                .then()
                .statusCode(code);
    }

    public ValidatableResponse bulkUploadStoresAndRetailers(String partnerShortName, String fileName, int code) throws SocketException {
        ValidatableResponse validatableResponse = bulkUploadStoresRetailers(partnerShortName, fileName, code);
        System.out.println("\nPost Upload stores & retailers RESPONSE:");
        validatableResponse.extract().response().prettyPrint();
//        contractValidator.validateResponseWithContract("/suppliers/200-get-suppliers-list.json", validatableResponse.extract().response().asString());
        return validatableResponse;
    }


    private ValidatableResponse bulkUploadStoresPremierOnlyRequest(String partnerShortName, String fileName, int code) throws SocketException {
        String path = storeContract.getStoresBulkUploadStoresPath(partnerShortName);
        System.out.println("\nPath: POST " + path + "\n");

        return given()
                .multiPart("file", new File("src//bulkUploadFiles//uploadStores//" + fileName))
                .header("Authorization", "Bearer " + bearer.get())
                .when()
                .post(path)
                .then()
                .statusCode(code);
    }


    public ValidatableResponse bulkUploadStoresPremierOnly(String partnerShortName, String fileName, int code) throws SocketException {
        ValidatableResponse validatableResponse = bulkUploadStoresPremierOnlyRequest(partnerShortName, fileName, code);
        System.out.println("\nPost Upload Premier stores RESPONSE:");
        validatableResponse.extract().response().prettyPrint();
//        contractValidator.validateResponseWithContract("/suppliers/200-get-suppliers-list.json", validatableResponse.extract().response().asString());
        return validatableResponse;
    }

}