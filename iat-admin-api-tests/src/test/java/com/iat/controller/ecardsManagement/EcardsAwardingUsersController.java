package com.iat.controller.ecardsManagement;

import com.iat.contracts.ecardsManagement.EcardsAwardingUsersContract;
import com.iat.domain.ecardsAwarding.EcardsSent;
import com.iat.utils.DataExchanger;
import com.iat.validators.ContractValidator;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class EcardsAwardingUsersController {

    private EcardsAwardingUsersContract ecardsAwardingUsersContract = new EcardsAwardingUsersContract();
    private DataExchanger sessionId = DataExchanger.getInstance();
    private ContractValidator contractValidator = new ContractValidator();


    private ValidatableResponse getEcardsAwardingPointsRequest(String sortField, String ascending, String page, String maxResults,
                                                               String dateFrom, String dateTo, String from, String to, String points,
                                                               String amount, String senderDepartment, String receiverDepartment,
                                                               String approvalStatus, int status) {

        String path = ecardsAwardingUsersContract.getEcardsAwardingUserPath(sortField, ascending, page, maxResults,
                dateFrom, dateTo, from, to, points, amount, senderDepartment, receiverDepartment, approvalStatus);

        log.info("Path: GET {}", path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.getSessionId())
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getEcardsAwardingPoints(String sortField, String ascending, String page, String maxResults,
                                                       String dateFrom, String dateTo, String from, String to, String points,
                                                       String amount, String senderDepartment, String receiverDepartment,
                                                       String approvalStatus, int status) {

        ValidatableResponse validatableResponse = getEcardsAwardingPointsRequest(sortField, ascending, page, maxResults,
                dateFrom, dateTo, from, to, points, amount, senderDepartment, receiverDepartment, approvalStatus, status);
        if (status == 400)
            contractValidator.validateResponseWithContract("ErrorResponse-schema-fieldsValidation.json", validatableResponse);
        else
            contractValidator.validateResponseWithContract("ecards/GET-ecard-award.json", validatableResponse, status);

        return validatableResponse;

    }


    private ValidatableResponse setEcardsAwardingUsersRequest(EcardsSent jsonBodyObject, int status) {
        String path = ecardsAwardingUsersContract.setEcardsAwardingUserPath();

        log.info("Path: PUT {}", path);
        log.info("jSON_BODY: {}", jsonBodyObject);
        return given()
                .contentType(ContentType.JSON)
                .body(jsonBodyObject)
                .sessionId("SESSION", sessionId.getSessionId())
                .when()
                .put(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse setEcardsAwardingPoints(EcardsSent jsonBodyObject, int status) {
        ValidatableResponse validatableResponse = setEcardsAwardingUsersRequest(jsonBodyObject, status);
        // if (status == 200 || status == 201) //Id in response (probably ecardsBatchId
        if (status == 400)
            contractValidator.validateResponseWithContract("ErrorResponse-schema-fieldsValidation.json", validatableResponse);
        else if (status != 200 && status != 201)
            contractValidator.validateResponseWithContract("ErrorResponse-schema.json", validatableResponse);

        return validatableResponse;
    }

    private ValidatableResponse getEcardsAwardingStatsRequest(int status) {
        String path = ecardsAwardingUsersContract.getEcardsAwardingStatsPath();

        log.info("Path: GET {}", path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.getSessionId())
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getEcardsAwardingStats(int status) {
        ValidatableResponse validatableResponse = getEcardsAwardingStatsRequest(status);
        if (status == 400)
            contractValidator.validateResponseWithContract("ErrorResponse-schema-fieldsValidation.json", validatableResponse);
        else
            contractValidator.validateResponseWithContract("ecards/GET-Ecards-stats.json", validatableResponse, status);

        return validatableResponse;
    }

}