package com.iat.repository.impl;

import com.iat.Config;
import com.iat.domain.ecardAward;
import com.iat.repository.EcardsAwardRepository;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;


public class EcardsAwardRepositoryImpl implements EcardsAwardRepository {

    private String authorizeUser(String email, String password) {
        Response response = given().post(Config.getIatAdminUrl() + "/api/login?user=" + email + "&password=" + password);
        response.then().statusCode(302);
        return response.then().extract().response().getCookie("SESSION");
    }


    private ValidatableResponse setEcardsAwardingUsers(ecardAward body, String username, String password) {
        String sessionId = authorizeUser(username, password);
        return given()
                .contentType(ContentType.JSON)
                .body(body)
                .sessionId("SESSION", sessionId)
                .when()
                .put(Config.getIatAdminUrl() + "/api/ecards/award")
                .then();

    }

    public String setEcardsAward(ecardAward body, String username, String password) {
        ValidatableResponse response = setEcardsAwardingUsers(body, username, password);
        return response.statusCode(200).extract().body().asString();
    }
}