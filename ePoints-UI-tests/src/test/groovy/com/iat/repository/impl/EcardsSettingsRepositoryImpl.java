package com.iat.repository.impl;

import com.iat.Config;
import com.iat.domain.EcardsSettings;
import com.iat.repository.EcardsSettingsRepository;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;


public class EcardsSettingsRepositoryImpl implements EcardsSettingsRepository {

    private String authorizeUser(String email, String password) {
        Response response = given().post(Config.getIatAdminUrl() + "/api/login?user=" + email + "&password=" + password);
        response.then().statusCode(302);
        return response.then().extract().response().getCookie("JSESSIONID");
    }

    public void setEcardsPointsSharing(EcardsSettings settings, String credentials) {
        String sessionId = authorizeUser(credentials.split(",")[0], credentials.split(",")[1]);
        given()
                .contentType(ContentType.JSON)
                .sessionId(sessionId)
                .body(settings)
                .post(Config.getIatAdminUrl() + "/api/ecards/settings/pointsSharing")
                .then()
                .statusCode(200);
    }

    private ValidatableResponse getReasons(String credentials) {
        String sessionId = authorizeUser(credentials.split(",")[0], credentials.split(",")[1]);
        return given()
                .contentType(ContentType.JSON)
                .sessionId(sessionId)
                .when()
                .get(Config.getIatAdminUrl() + "/api/ecards/reasons/")
                .then()
                .statusCode(200);
    }

    public String getAllReasons(String credentials) {
        ValidatableResponse response = getReasons(credentials);
        return response.statusCode(200).extract().body().asString();
    }
}
