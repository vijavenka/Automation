package com.iat.controller.prizes;

import com.iat.contracts.prizes.PrizesContract;
import com.iat.utils.SessionIdKeeper;
import com.iat.validators.ContractValidator;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class PrizesController {

    private PrizesContract prizesContract = new PrizesContract();
    private ContractValidator contractValidator = new ContractValidator();

    private SessionIdKeeper sessionId = SessionIdKeeper.getInstance();

    private ValidatableResponse getSpinsNumberRequest(String uuid, String status, int responseStatus) {
        String path = prizesContract.spinsPath(uuid, status);

        log.info("GET social status Path: {}{}", RestAssured.baseURI, path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.get())
                .when()
                .get(path)
                .then()
                .statusCode(responseStatus);
    }

    public ValidatableResponse getSpinsNumber(String uuid, String status, int responseStatus) {
        ValidatableResponse validatableResponse = getSpinsNumberRequest(uuid, status, responseStatus);
        contractValidator.validateResponseWithContract("/prizes/GET-200-SpinsCount-schema.json", validatableResponse, responseStatus);
        return validatableResponse;
    }

}