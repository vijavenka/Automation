package com.iat.controller.registration;

import com.iat.contracts.userDetails.UserTokenContract;
import com.iat.validators.ContractValidator;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class UserTokenController {

    private UserTokenContract userTokenContract = new UserTokenContract();
    private ContractValidator contractValidator = new ContractValidator();

    private ValidatableResponse verifyUserTokenRequest(String apiKey, String token, int status) {
        String path = userTokenContract.getTokenVerificationPath(apiKey, token);
        log.info("Token Path: GET {}{}", RestAssured.baseURI, path);
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse verifyUserToken(String apiKey, String token, int status) {
        ValidatableResponse validatableResponse = verifyUserTokenRequest(apiKey, token, status);
        contractValidator.validateResponseWithContract("userVerify-response-schema.json", validatableResponse, status);
        return validatableResponse;
    }
}
