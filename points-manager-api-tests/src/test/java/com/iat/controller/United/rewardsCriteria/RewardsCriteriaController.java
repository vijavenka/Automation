package com.iat.controller.United.rewardsCriteria;

import com.iat.contracts.United.rewardsCriteria.RewardsCriteriaContract;
import com.iat.utils.HMACHeader;
import com.iat.validators.ContractValidator;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class RewardsCriteriaController {

    private HMACHeader hmacHeader = new HMACHeader();
    private RewardsCriteriaContract rewardsCriteriaContract = new RewardsCriteriaContract();
    private ContractValidator contractValidator = new ContractValidator();

    private ValidatableResponse importCriteria(String googleDocId, int status) {
        String date = hmacHeader.generateHttpDate();
        String path = rewardsCriteriaContract.getRewardsCriteriaImportPath(googleDocId);

        log.info("GET Path: {}:{}{}", RestAssured.baseURI, RestAssured.port, path);

        return given()
                .contentType(ContentType.JSON)
                .header("Authentication", hmacHeader.getHmacHeader("GET", null, null, path, date))
                .header("X-IAT-Date", date)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse bulkUploadRewardsCriteria(String googleDocId, int status) {
        ValidatableResponse validatableResponse = importCriteria(googleDocId, status);
        if (status == 200)
            contractValidator.validateResponseWithContract("/GET-200-bulkUpload.json", validatableResponse);
        else
            contractValidator.validateResponseWithContract("/GET-400-bulkUploadError.json", validatableResponse);
        return validatableResponse;
    }

}