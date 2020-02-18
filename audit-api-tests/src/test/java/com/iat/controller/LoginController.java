package com.iat.controller;

import com.iat.contracts.LoginContract;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class LoginController {

    private LoginContract loginContract = new LoginContract();

    private ValidatableResponse userLogIn(String credentials) {
        String path = loginContract.getLoginPath(credentials);
        System.out.println("\nPath: POST " + path + "\n");

        return given()
                .when()
                .post(path)
                .then();
    }

    public ValidatableResponse userLogInProperly(String credentials) {
        ValidatableResponse validatableResponse = userLogIn(credentials);
//        int code = validatableResponse.extract().response().getStatusCode();
//        assertTrue("Incorrect response code: " + code + "\n" + validatableResponse.extract().response().asString(), code == 200);
        System.out.println("\nResponse: " + validatableResponse.extract().response().asString());
        return validatableResponse;
    }

}