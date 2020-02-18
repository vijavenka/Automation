package com.iat.controller.ecardsManagement;

import com.iat.contracts.ecardsManagement.EcardsApprovalContract;
import com.iat.utils.DataExchanger;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class EcardsApprovalController {

    private EcardsApprovalContract ecardsApprovalContract = new EcardsApprovalContract();
    private DataExchanger sessionId = DataExchanger.getInstance();


    private ValidatableResponse getEcardsApprovalListRequest(int status) {
        String path = ecardsApprovalContract.getEcardsApprovalListPath();

        log.info("Path: GET {}", path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.getSessionId())
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getEcardsApprovalList(int status) {
        return getEcardsApprovalListRequest(status);
    }


    private ValidatableResponse getEcardsApprovalByIdRequest(String id, int status) {
        String path = ecardsApprovalContract.getEcardsApprovalByIdPath(id);

        log.info("Path: GET {}", path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.getSessionId())
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getEcardsApprovalById(String id, int status) {
        return getEcardsApprovalByIdRequest(id, status);
    }


    private ValidatableResponse setEcardsApprovalAsApprovedRequest(String id, int status) {
        String path = ecardsApprovalContract.setEcardsApprovalAsApprovedPath(id);

        log.info("Path: POST {}", path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.getSessionId())
                .when()
                .post(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse setEcardsApprovalAsApproved(String id, int status) {
        return setEcardsApprovalAsApprovedRequest(id, status);
    }


    private ValidatableResponse setEcardsApprovalAsRejectedRequest(String id, String jsonBody, int status) {
        String path = ecardsApprovalContract.setEcardsApprovalAsRejectedPath(id);
        log.info("Path: POST {}", path);
        log.info("BODY: {}", jsonBody);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.getSessionId())
                .body(jsonBody)
                .when()
                .post(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse setEcardsApprovalAsRejected(String id, String jsonBody, int status) {
        return setEcardsApprovalAsRejectedRequest(id, jsonBody, status);
    }


    private ValidatableResponse getEcardsApprovalCounterRequest(int status) {
        String path = ecardsApprovalContract.getEcardsApprovalCounterPath();

        log.info("Path: GET {}", path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.getSessionId())
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getEcardsApprovalCounter(int status) {
        return getEcardsApprovalCounterRequest(status);
    }

}