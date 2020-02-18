package com.iat.controller.partnersManagement;

import com.iat.contracts.PartnerContract;
import com.iat.utils.HMACHeader;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class PartnersSiteNotVisitedController {

    private HMACHeader hmacHeader = new HMACHeader();
    private PartnerContract partnerContract = new PartnerContract();

    public ValidatableResponse getPartnersSiteNotVisited(String userId, String idType, String apiKey, String limit, String offset, String random, int status) {
        String date = hmacHeader.generateHttpDate();
        String path = partnerContract.getPartnersSiteNotVisitedPath(userId, idType, apiKey, limit, offset, random);

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
}