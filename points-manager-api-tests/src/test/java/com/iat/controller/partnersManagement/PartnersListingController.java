package com.iat.controller.partnersManagement;

import com.iat.contracts.PartnerContract;
import com.iat.utils.HMACHeader;
import com.iat.validators.ContractValidator;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class PartnersListingController {

    private HMACHeader hmacHeader = new HMACHeader();
    private PartnerContract partnerContract = new PartnerContract();
    private ContractValidator contractValidator = new ContractValidator();

    private ValidatableResponse getDefaultPartnersListRequest(int status) {
        String date = hmacHeader.generateHttpDate();
        String path = partnerContract.getPartnersPath();
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

    public ValidatableResponse getDefaultPartnersList(int status) {
        ValidatableResponse validatableResponse = getDefaultPartnersListRequest(status);
        contractValidator.validateResponseWithContract("partnersList-response-schema.json", validatableResponse);
        return validatableResponse;
    }

    private ValidatableResponse getPartnersListByPageSizeRequest(int page, int size, int status) {
        String date = hmacHeader.generateHttpDate();
        String path = partnerContract.getPartnersPath();

        log.info("Path: {}", path);

        return given()
                .contentType(ContentType.JSON)
                .header("Authentication", hmacHeader.getHmacHeader("GET", null, null, path, date))
                .header("X-IAT-Date", date)
                .param("page", page)
                .param("size", size)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getPartnersListForPageSize(int page, int size, int status) {
        ValidatableResponse validatableResponse = getPartnersListByPageSizeRequest(page, size, status);
        contractValidator.validateResponseWithContract("partnersList-response-schema.json", validatableResponse);
        return validatableResponse;
    }

    private ValidatableResponse getPartnersListByShortNameRequest(String shortName, int status) {
        String date = hmacHeader.generateHttpDate();
        String path = partnerContract.getPartnersPath();

        log.info("Path: {}", path);

        return given()
                .contentType(ContentType.JSON)
                .header("Authentication", hmacHeader.getHmacHeader("GET", null, null, path, date))
                .header("X-IAT-Date", date)
                .param("shortName", shortName)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getPartnersListByShortName(String shortName, int status) {
        ValidatableResponse validatableResponse = getPartnersListByShortNameRequest(shortName, status);
        contractValidator.validateResponseWithContract("partnersList-response-schema.json", validatableResponse);
        return validatableResponse;
    }

    private ValidatableResponse getPartnersListByNameRequest(String name, int status) {
        String date = hmacHeader.generateHttpDate();
        String path = partnerContract.getPartnersPath();

        log.info("Path: {}", path);

        return given()
                .contentType(ContentType.JSON)
                .header("Authentication", hmacHeader.getHmacHeader("GET", null, null, path, date))
                .header("X-IAT-Date", date)
                .param("name", name)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getPartnersListByName(String name, int status) {
        ValidatableResponse validatableResponse = getPartnersListByNameRequest(name, status);
        contractValidator.validateResponseWithContract("partnersList-response-schema.json", validatableResponse);
        return validatableResponse;
    }
}