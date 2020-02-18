package com.iat.repository.impl;

import com.iat.domain.EcardAward;
import com.iat.repository.EcardsAwardRepository;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;


public class EcardsAwardRepositoryImpl implements EcardsAwardRepository {

    private ValidatableResponse authorizeUser(String email, String password) {
        return given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("email", email)
                .formParam("password", password)
                .when()
                .post("rest/login/loginProcess")
                .then()
                .statusCode(302);
    }

    public String getSessionIdForAuthUser(String email, String password) {
        ValidatableResponse response = authorizeUser(email, password);
        return response.extract().cookie("SESSION");
    }


    private ValidatableResponse setEcardsAwardingUsers(EcardAward body, String username, String password) {
        String sessionId = getSessionIdForAuthUser(username, password);
        return given()
                .contentType(ContentType.JSON)
                .body(body)
                .sessionId("SESSION", sessionId)
                .when()
                .put("/rest/ecards")
                .then();

    }

    public String setEcardsAward(EcardAward body, String username, String password) {
        ValidatableResponse response = setEcardsAwardingUsers(body, username, password);
        return response.statusCode(201).extract().body().asString();
    }
}