package com.iat.controller.accountManagement;

import com.iat.Config;
import com.iat.contracts.accountManagement.AccountActivationContract;
import com.iat.validators.ContractValidator;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class AccountActivationController {

    private AccountActivationContract accountActivationContract = new AccountActivationContract();
    private ContractValidator contractValidator = new ContractValidator();

    private ValidatableResponse getAccountActivationStatusRequest(String id, String idType, String accessKey, int status) {
        String path = Config.getUserManagerUrl() + accountActivationContract.getAccountActivationPath(id, idType, accessKey);
        log.info("Path: GET {}", path);

        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getAccountActivationStatus(String id, String idType, int status) {
        ValidatableResponse validatableResponse = getAccountActivationStatusRequest(id, idType, Config.getPartnerAccessKey(), status);
        contractValidator.validateResponseWithContract("accountActivationStatus-response-schema.json", validatableResponse, status);
        return validatableResponse;
    }
}