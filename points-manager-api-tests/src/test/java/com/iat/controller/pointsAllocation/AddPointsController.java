package com.iat.controller.pointsAllocation;

import com.iat.Config;
import com.iat.contracts.pointsAllocation.AddPointsContract;
import com.iat.domain.pointsAllocation.MultiplePointsTransaction;
import com.iat.utils.DataExchanger;
import com.iat.utils.HMACHeader;
import com.iat.utils.auth.ContentApiAuthentication;
import com.iat.validators.ContractValidator;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class AddPointsController {

    private HMACHeader hmacHeader = new HMACHeader();
    private AddPointsContract addPointsContract = new AddPointsContract();
    private ContractValidator contractValidator = new ContractValidator();
    private ContentApiAuthentication authentication = new ContentApiAuthentication();
    private DataExchanger dataExchanger = DataExchanger.getInstance();

    private ValidatableResponse addPointsToUserByIdRequest(String pointsTransactionBody, String userId, String idType, int status) {
        String date = hmacHeader.generateHttpDate();
        String path = addPointsContract.getAddPointsPathById(userId, idType);

        log.info("Path: POST {}", path);
        log.info("Body: {}", pointsTransactionBody);

        return given()
                .contentType(ContentType.JSON)
                .header("Authentication", hmacHeader.getHmacHeader("POST", null, null, path, date))
                .header("X-IAT-Date", date)
                .when()
                .body(pointsTransactionBody)
                .post(path)
                .then()
                .statusCode(status);
    }

    private ValidatableResponse addPointsToUserByIdRequestEws(String pointsTransactionBody, String userId, String idType, int status) {
        String date = hmacHeader.generateHttpDate();
        String path = Config.getEwsUrl() + addPointsContract.getAddPointsPathById(userId, idType);

        log.info("Path: POST {}", path);
        log.info("Body: {}", pointsTransactionBody);

        return given()
                .contentType(ContentType.JSON)
                .header("Authentication", hmacHeader.getHmacHeader("POST", null, null, path, date))
                .headers(authentication.auth(path, "POST"))
                .port(443)
                .header("X-IAT-Date", date)
                .when()
                .body(pointsTransactionBody)
                .post(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse addPointsToUserById(String pointsTransactionBody, String userId, String idType, int status) {
        return dataExchanger.isEwsRequest() ?
                addPointsToUserByIdRequestEws(pointsTransactionBody, userId, idType, status)
                :
                addPointsToUserByIdRequest(pointsTransactionBody, userId, idType, status);
    }

    private ValidatableResponse checkPointsAcquirePossibilityRequest(String userId, String idType, String pointsTransactionBody, int status) {
        String date = hmacHeader.generateHttpDate();
        String path = addPointsContract.checkPointsAcquirePossibility(userId, idType);

        log.info("Path: POST {}", path);
        log.info("Body: {}", pointsTransactionBody);

        return given()
                .contentType(ContentType.JSON)
                .header("Authentication", hmacHeader.getHmacHeader("GET", null, null, path, date))
                .header("X-IAT-Date", date)
                .when()
                .body(pointsTransactionBody)
                .post(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse checkPointsAcquirePossibility(String userId, String idType, String pointsTransactionBody, int status) {
        ValidatableResponse validatableResponse = checkPointsAcquirePossibilityRequest(userId, idType, pointsTransactionBody, status);
        contractValidator.validateResponseWithContract("pointsAvailabilityForUser-response-schema.json", validatableResponse, status);
        return validatableResponse;
    }

    private ValidatableResponse bulkUploadPointsRequest(String pointsTransactionBody, int status) {
        String date = hmacHeader.generateHttpDate();
        String path = addPointsContract.bulkUploadPoints();

        log.info("Path: POST {}", path);
        log.info("Body: {}", pointsTransactionBody);

        return given()
                .contentType(ContentType.JSON)
                .header("Authentication", hmacHeader.getHmacHeader("GET", null, null, path, date))
                .header("X-IAT-Date", date)
                .when()
                .body(pointsTransactionBody)
                .post(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse bulkUploadPoints(String pointsTransactionBody, int status) {
        return bulkUploadPointsRequest(pointsTransactionBody, status);
    }

    private ValidatableResponse awardPointsToMultipleTagsAndClientsRequest(MultiplePointsTransaction pointsTransaction, int status) {
        String date = hmacHeader.generateHttpDate();
        String path = addPointsContract.multipleAwards();

        log.info("Path: POST {}", path);
        log.info("Body: {}", pointsTransaction);

        return given()
                .contentType(ContentType.JSON)
                .header("Authentication", hmacHeader.getHmacHeader("GET", null, null, path, date))
                .header("X-IAT-Date", date)
                .when()
                .body(pointsTransaction)
                .post(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse awardPointsToMultipleTagsAndClients(MultiplePointsTransaction pointsTransaction, int status) {
        return awardPointsToMultipleTagsAndClientsRequest(pointsTransaction, status);
    }
}