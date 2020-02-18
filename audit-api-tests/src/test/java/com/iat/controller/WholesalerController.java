package com.iat.controller;

import com.iat.Config;
import com.iat.actions.LoginActions;
import com.iat.contracts.WholesalerContract;
import com.iat.utils.SessionIdKeeper;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

public class WholesalerController {

    private WholesalerContract wholesalerContract = new WholesalerContract();
    SessionIdKeeper bearer = SessionIdKeeper.getInstance();

    private ValidatableResponse getAllWholesalers() {
        if (bearer.get().length() == 0) {
            new LoginActions().userLogIn(Config.iatCmsAdminCredentials);
        }

        String path = wholesalerContract.getWholesalers();
        System.out.println("\nPath: GET " + path + "\n");

        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + bearer.get())
                .when()
                .get(path)
                .then();
    }

    public ValidatableResponse getWholesalersList() {
        ValidatableResponse validatableResponse = getAllWholesalers();
        int code = validatableResponse.extract().response().getStatusCode();
        assertTrue("Incorrect response code: " + code + "\n" + validatableResponse.extract().response().asString(), code == 200);
        return validatableResponse;
    }

//    private ValidatableResponse getSuppliersListRequest(int code) {
//        String path = suppliersContract.suppliersPath();
//        System.out.println("\nPath: GET " + path + "\n");
//
//        return given()
//                .contentType(ContentType.JSON)
//                .header("Authorization", "Bearer " + bearer.get())
//                .when()
//                .get(path)
//                .then()
//                .statusCode(code);
//    }
//
//    public ValidatableResponse getSuppliersList(int code) {
//        ValidatableResponse validatableResponse = getSuppliersListRequest(code);
//        System.out.println("\nGet suppliers list RESPONSE:");
//        validatableResponse.extract().response().prettyPrint();
//        contractValidator.validateResponseWithContract("/suppliers/200-get-suppliers-list.json", validatableResponse.extract().response().asString());
//        return validatableResponse;
//    }

}