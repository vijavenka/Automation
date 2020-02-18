package com.iat.controller.partnersManagement;

import com.iat.contracts.PartnerContract;
import com.iat.utils.HMACHeader;
import com.iat.validators.ContractValidator;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class PartnerBalanceController {

    private HMACHeader hmacHeader = new HMACHeader();
    private PartnerContract partnerContract = new PartnerContract();
    private ContractValidator contractValidator = new ContractValidator();

    private ValidatableResponse getPartnerBalanceRequest(String clientId, String apiKey, String fields, String startDate, String endDate, int status) {
        String date = hmacHeader.generateHttpDate();
        String path = partnerContract.getPartnerBalancePath(clientId, apiKey, fields, startDate, endDate);

        log.info("Path: {}", path);

        return given()
                .contentType(ContentType.JSON)
                .header("Authentication", hmacHeader.getHmacHeader("GET", null, null, path, date))
                .header("X-IAT-Date", date)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getPartnerBalance(String clientId, String apiKey, String fields, String startDate, String endDate, int status) {
        ValidatableResponse validatableResponse = getPartnerBalanceRequest(clientId, apiKey, fields, startDate, endDate, status);
        if (status == 200)
            contractValidator.validateResponseWithContract("partnerBalance-response-schema.json", validatableResponse);
        return validatableResponse;
    }
}