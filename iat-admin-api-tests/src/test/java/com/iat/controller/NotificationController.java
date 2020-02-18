package com.iat.controller;

import com.iat.contracts.NotificationsContract;
import com.iat.utils.DataExchanger;
import com.iat.validators.ContractValidator;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class NotificationController {

    private NotificationsContract notificationsContract = new NotificationsContract();
    private DataExchanger sessionId = DataExchanger.getInstance();
    private ContractValidator contractValidator = new ContractValidator();


    private ValidatableResponse getNotificationsRequest(int status) {
        String path = notificationsContract.getNotificationsPath();

        log.info("Path: GET {}", path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.getSessionId())
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getNotifications(int status) {
        ValidatableResponse validatableResponse = getNotificationsRequest(status);
        contractValidator.validateResponseWithContract("notifications/GET-notifications.json", validatableResponse, status);
        return validatableResponse;
    }


    private ValidatableResponse setNotificationsReadRequest(String jsonBody, int status) {
        String path = notificationsContract.setNotyficationsReadPath();

        log.info("Path: POST {}", path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.getSessionId())
                .body(jsonBody)
                .when()
                .post(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse setNotificationsRead(String jsonBody, int status) {
        return setNotificationsReadRequest(jsonBody, status);
    }

}