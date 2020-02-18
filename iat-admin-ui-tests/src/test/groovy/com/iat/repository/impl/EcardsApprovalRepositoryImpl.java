package com.iat.repository.impl;

import com.iat.Config;
import com.iat.domain.ecardApprovalReject;
import com.iat.repository.EcardsApprovalRepository;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;


public class EcardsApprovalRepositoryImpl implements EcardsApprovalRepository {

    private String authorizeUser(String email, String password) {
        Response response = given().post(Config.getIatAdminUrl() + "/api/login?user=" + email + "&password=" + password);
        response.then().statusCode(302);
        return response.then().extract().response().getCookie("SESSION");
    }


    private ValidatableResponse rejectChosenEcardBatch(ecardApprovalReject body, String id, String username, String password) {
        String sessionId = authorizeUser(username, password);
        return given()
                .contentType(ContentType.JSON)
                .body(body)
                .sessionId("SESSION", sessionId)
                .when()
                .post(Config.getIatAdminUrl() + "/api/ecards/approval/" + id + "/reject")
                .then();
    }

    public String rejectEcard(ecardApprovalReject body, String id, String username, String password) {
        ValidatableResponse response = rejectChosenEcardBatch(body, id, username, password);
        return response.statusCode(200).extract().body().asString();
    }

    private ValidatableResponse approveChosenEcardBatch(String id, String username, String password) {
        String sessionId = authorizeUser(username, password);
        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId)
                .when()
                .post(Config.getIatAdminUrl() + "/api/ecards/approval/" + id + "/approve")
                .then();
    }

    public String approveEcard(String id, String username, String password) {
        ValidatableResponse response = approveChosenEcardBatch(id, username, password);
        return response.statusCode(200).extract().body().asString();
    }

    private ValidatableResponse getNotifications(String username, String password) {
        String sessionId = authorizeUser(username, password);
        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId)
                .when()
                .get(Config.getIatAdminUrl() + "/api/notifications")
                .then();
    }

    public String getAllAdminNotifications(String username, String password) {
        ValidatableResponse response = getNotifications(username, password);
        return response.statusCode(200).extract().body().asString();
    }

    private ValidatableResponse readNotifications(String body, String username, String password) {
        String sessionId = authorizeUser(username, password);
        return given()
                .contentType(ContentType.JSON)
                .body(body)
                .sessionId("SESSION", sessionId)
                .when()
                .post(Config.getIatAdminUrl() + "/api/notifications/read")
                .then();
    }

    public String markNotificationsAsRead(String body, String username, String password) {
        ValidatableResponse response = readNotifications(body, username, password);
        return response.statusCode(200).extract().body().asString();
    }
}