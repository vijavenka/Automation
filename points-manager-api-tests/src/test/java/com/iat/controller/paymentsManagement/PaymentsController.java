package com.iat.controller.paymentsManagement;

import com.iat.contracts.paymentsManagement.PaymentsContract;
import com.iat.utils.HMACHeader;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class PaymentsController {

    private HMACHeader hmacHeader = new HMACHeader();
    private PaymentsContract paymentsContract = new PaymentsContract();

    public ValidatableResponse createNewPaymentInSystem(String apiKey, String jsonParameters, int status) {
        String date = hmacHeader.generateHttpDate();
        String path = paymentsContract.addPayment(apiKey);

        log.info("Path: {}", path);

        return given().
                contentType(ContentType.JSON)
                .header("Authentication", hmacHeader.getHmacHeader("POST", null, null, path, date))
                .header("X-IAT-Date", date)
                .body(paymentsContract.returnAddUpdatePaymentBody(jsonParameters))
                .when()
                .post(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getDetailsOfSelectedPayment(String apiKey, String guid, int status) {
        String date = hmacHeader.generateHttpDate();
        String path = paymentsContract.getUpdatePayment(apiKey, guid);

        log.info("Path: {}", path);

        return given().
                contentType(ContentType.JSON)
                .header("Authentication", hmacHeader.getHmacHeader("GET", null, null, path, date))
                .header("X-IAT-Date", date)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse updatePaymentInSystem(String apiKey, String guid, String jsonParameters, int status) {
        String date = hmacHeader.generateHttpDate();
        String path = paymentsContract.getUpdatePayment(apiKey, guid);

        log.info("Path: {}", path);

        return given().
                contentType(ContentType.JSON)
                .header("Authentication", hmacHeader.getHmacHeader("POST", null, null, path, date))
                .header("X-IAT-Date", date)
                .body(paymentsContract.returnAddUpdatePaymentBody(jsonParameters))
                .when()
                .post(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse updateSelectedPaymentDetailsUsingInvalidParameters(String apiKey, String guid, int expResponseCode, String expErrorCode, String expErrorMsg) {
        String date = hmacHeader.generateHttpDate();
        String path = paymentsContract.getUpdatePayment(apiKey, guid);

        log.info("Path: {}", path);

        return given().
                contentType(ContentType.JSON)
                .header("Authentication", hmacHeader.getHmacHeader("GET", null, null, path, date))
                .header("X-IAT-Date", date)
                .when()
                .get(path)
                .then()
                .statusCode(expResponseCode)
                .header("X-Error-Code", expErrorCode)
                .header("X-Error-Message", expErrorMsg);
    }

}