package com.iat.controller.integration;

import com.iat.contracts.integration.BraintreeContract;
import com.iat.domain.integration.ConvertToEPoints;
import com.iat.utils.SessionIdKeeper;
import com.iat.validators.ContractValidator;
import com.iat.validators.integration.BraintreeValidator;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class BraintreeController {

    private BraintreeContract braintreeContract = new BraintreeContract();
    private SessionIdKeeper sessionId = SessionIdKeeper.getInstance();
    private BraintreeValidator braintreeValidator = new BraintreeValidator();
    private ContractValidator contractValidator = new ContractValidator();
    
    private ValidatableResponse getTransactionResponse(ConvertToEPoints convertToEpoints, int transactionStatus) {
        String path = braintreeContract.getTransactionPath();
        log.info("Profile Path - GET: {}{}", RestAssured.baseURI, path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.get())
                .body(convertToEpoints)
                .when()
                .post(path)
                .then()
                .statusCode(transactionStatus);
    }

    public ValidatableResponse getTransactions(ConvertToEPoints convertToEpoints, int transactionStatus){
        ValidatableResponse validatableResponse = getTransactionResponse(convertToEpoints,transactionStatus);
        if(transactionStatus == 503)
            contractValidator.validateResponseWithContract("/ErrorResponse-schema.json", validatableResponse, transactionStatus);
                return validatableResponse;
    }
}
