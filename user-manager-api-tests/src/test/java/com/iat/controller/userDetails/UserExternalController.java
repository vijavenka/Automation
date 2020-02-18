package com.iat.controller.userDetails;

import com.iat.contracts.userDetails.UserExternalContract;
import com.iat.validators.ContractValidator;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class UserExternalController {

    private ContractValidator contractValidator = new ContractValidator();
    private UserExternalContract userExternalContract = new UserExternalContract();

    private ValidatableResponse getRetailerRequest(String externalId, String externalIdType, String apiKey, int status) {
        String path = userExternalContract.getRetailer(externalId, externalIdType, apiKey);

        log.info("Path: GET {}{}", RestAssured.baseURI, path);

        return given().
                contentType(ContentType.JSON)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getRetailer(String externalId, String externalIdType, String apiKey, int status) {
        ValidatableResponse validatableResponse = getRetailerRequest(externalId, externalIdType, apiKey, status);
        contractValidator.validateResponseWithContract("GET-200-userExternal.json", validatableResponse, status);

        return validatableResponse;
    }
}