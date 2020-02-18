package com.iat.controller.epointsPurchase;

import com.iat.contracts.purchase.EpointsPurchaseContract;
import com.iat.domain.purchase.BuyEpoints;
import com.iat.utils.SessionIdKeeper;
import com.iat.validators.ContractValidator;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class EpointsPurchaseController {

    private EpointsPurchaseContract epointsPurchaseContract = new EpointsPurchaseContract();
    private ContractValidator contractValidator = new ContractValidator();

    private SessionIdKeeper sessionId = SessionIdKeeper.getInstance();

    private ValidatableResponse purchaseEpointsRequest(BuyEpoints jsonBody, int status) {
        String path = epointsPurchaseContract.getEpointsPurchasePath();

        log.info("GET social status Path: {}{}", RestAssured.baseURI, path);
        log.info("BODY: {}", jsonBody.toString());

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.get())
                .body(jsonBody)
                .when()
                .post(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse purchaseEpoints(BuyEpoints jsonBody, int status) {
        ValidatableResponse validatableResponse = purchaseEpointsRequest(jsonBody, status);
        contractValidator.validateResponseWithContract("/purchase/POST-200-epointsPurchase.json", validatableResponse, status);
        return validatableResponse;
    }
}