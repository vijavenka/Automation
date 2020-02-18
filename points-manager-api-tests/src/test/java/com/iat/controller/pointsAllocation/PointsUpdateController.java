package com.iat.controller.pointsAllocation;

import com.iat.contracts.pointsAllocation.PointsUpdateContract;
import com.iat.utils.HMACHeader;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class PointsUpdateController {

    private HMACHeader hmacHeader = new HMACHeader();
    private PointsUpdateContract pointsUpdateContract = new PointsUpdateContract();

    private ValidatableResponse updatePointsTransactionByTransactionIdRequest(String transactionId, String pointsTransactionUpdateBody, int status) {
        String date = hmacHeader.generateHttpDate();
        String path = pointsUpdateContract.updatePointsTransaction(transactionId);

        log.info("Path: POST {}", path);
        log.info("Body: {}", pointsTransactionUpdateBody);

        return given()
                .contentType(ContentType.JSON)
                .header("Authentication", hmacHeader.getHmacHeader("PUT", null, null, path, date))
                .header("X-IAT-Date", date)
                .body(pointsTransactionUpdateBody)
                .when()
                .put(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse updatePointsTransactionByTransactionId(String transactionId, String pointsTransactionBody, int status) {
        return updatePointsTransactionByTransactionIdRequest(transactionId, pointsTransactionBody, status);
    }
}