package com.iat.controller.partnersManagement;

import com.iat.contracts.PartnerContract;
import com.iat.utils.HMACHeader;
import com.iat.validators.ContractValidator;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class CreatePartnerController {

    private HMACHeader hmacHeader = new HMACHeader();
    private PartnerContract partnerContract = new PartnerContract();
    private ContractValidator contractValidator = new ContractValidator();

    private ValidatableResponse createPartnerRequest(String name, String siteURL, String description, String email, String logoURL, String group, int status) {
        String date = hmacHeader.generateHttpDate();
        String path = partnerContract.getPartnersPath();
        String jsonPost = partnerContract.getPartnersPostBody(name, siteURL, description, email, logoURL, group);

        log.info("Path: {}", path);
        log.info("BODY: {}", jsonPost);

        return given()
                .contentType(ContentType.JSON)
                .header("Authentication", hmacHeader.getHmacHeader("POST", null, null, path, date))
                .header("X-IAT-Date", date)
                .body(jsonPost)
                .when()
                .post(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse createNewPartner(String name, String siteURL, String description, String email, String logoURL, String group, int status) {
        ValidatableResponse validatableResponse = createPartnerRequest(name, siteURL, description, email, logoURL, group, status);
        if (status > 399)
            contractValidator.validateResponseWithContract("", validatableResponse);
        else
            contractValidator.validateResponseWithContract("partnerCreation-response-schema.json", validatableResponse);
        return validatableResponse;
    }

    private ValidatableResponse bulkUploadPartnersRequest(String groupShortName, String googleDocId, int status) {
        String date = hmacHeader.generateHttpDate();
        String path = partnerContract.getPartnersImportPath(groupShortName, googleDocId);

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

    public ValidatableResponse bulkUploadPartners(String groupShortName, String googleDocId, int status) {
        ValidatableResponse validatableResponse = bulkUploadPartnersRequest(groupShortName, googleDocId, status);
        if (status == 200)
            contractValidator.validateResponseWithContract("/GET-200-bulkUpload.json", validatableResponse);
        else
            contractValidator.validateResponseWithContract("/GET-400-bulkUploadError.json", validatableResponse);
        return validatableResponse;
    }

}