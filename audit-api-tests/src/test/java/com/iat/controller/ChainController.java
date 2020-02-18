package com.iat.controller;

import com.iat.Config;
import com.iat.actions.LoginActions;
import com.iat.contracts.ChainContract;
import com.iat.domain.Chain;
import com.iat.utils.SessionIdKeeper;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

public class ChainController {

    private ChainContract chainContract = new ChainContract();
    SessionIdKeeper bearer = SessionIdKeeper.getInstance();

    private ValidatableResponse createChain(Chain jsonBody) {
        if (bearer.get().length() == 0) {
            new LoginActions().userLogIn(Config.iatCmsAdminCredentials);
        }

        String path = chainContract.createNewChain();
        System.out.println("\nPath: POST " + path + "\n");
        System.out.println("\nBODY: " + jsonBody.toString() + "\n");
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + bearer.get())
                .body(jsonBody.toString())
                .when()
                .post(path)
                .then();
    }

    public ValidatableResponse createNewChain(Chain chainBody) {
        ValidatableResponse validatableResponse = createChain(chainBody);
        int code = validatableResponse.extract().response().getStatusCode();
        assertTrue("Incorrect response code: " + code + "\n" + validatableResponse.extract().response().asString(), code == 201);
        return validatableResponse;
    }

    private ValidatableResponse deleteChain(String chainId) {
        String path = chainContract.deleteChainById(chainId);
        System.out.println("\nPath: DELETE " + path + "\n");
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + bearer.get())
                .when()
                .delete(path)
                .then();
    }

    public ValidatableResponse deleteSpecificChain(String chainId) {
        ValidatableResponse validatableResponse = deleteChain(chainId);
        int code = validatableResponse.extract().response().getStatusCode();
        assertTrue("Incorrect response code: " + code + "\n" + validatableResponse.extract().response().asString(), code == 200);
        return validatableResponse;
    }

    private ValidatableResponse getChain(String chainId) {
        String path = chainContract.getChainById(chainId);
        System.out.println("\nPath: GET " + path + "\n");
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + bearer.get())
                .when()
                .get(path)
                .then();
    }

    public ValidatableResponse getChainDetailsById(String chainId) {
        ValidatableResponse validatableResponse = getChain(chainId);
        int code = validatableResponse.extract().response().getStatusCode();
        assertTrue("Incorrect response code: " + code + "\n" + validatableResponse.extract().response().asString(), code == 200);
        return validatableResponse;
    }


    private ValidatableResponse getChainsListRequest(int code) {
        String path = chainContract.getChainsPath("null;null;null");
        System.out.println("\nPath: GET " + path + "\n");

        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + bearer.get())
                .when()
                .get(path)
                .then()
                .statusCode(code);
    }

    public ValidatableResponse getChainsList(int code) {
        ValidatableResponse validatableResponse = getChainsListRequest(code);
        System.out.println("\nGet chains list RESPONSE:");
        validatableResponse.extract().response().prettyPrint();
//        contractValidator.validateResponseWithContract("/suppliers/200-get-suppliers-list.json", validatableResponse.extract().response().asString());
        return validatableResponse;
    }

}