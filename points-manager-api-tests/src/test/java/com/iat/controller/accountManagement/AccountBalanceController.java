package com.iat.controller.accountManagement;

import com.iat.Config;
import com.iat.contracts.accountManagement.AccountBalanceContract;
import com.iat.utils.DataExchanger;
import com.iat.utils.auth.ContentApiAuthentication;
import com.iat.validators.ContractValidator;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class AccountBalanceController {

    private AccountBalanceContract accountBalanceContract = new AccountBalanceContract();
    private ContractValidator contractValidator = new ContractValidator();
    private ContentApiAuthentication authentication = new ContentApiAuthentication();
    private DataExchanger dataExchanger = DataExchanger.getInstance();

    private ValidatableResponse getAccountBalanceRequest(String userId, String idType, String apiKey, int statusCode) {
        String path = accountBalanceContract.getAccountBalanceByIdPath(userId, idType, apiKey);
        log.info("Balance Path: {}{}", Config.getBaseUrl(), path);

        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(path)
                .then()
                .statusCode(statusCode);
    }

    private ValidatableResponse getAccountBalanceEwsRequest(String userId, String idType, String apiKey, int statusCode) {
        String path = Config.getEwsUrl() + accountBalanceContract.getAccountBalanceByIdPath(userId, idType, apiKey);
        log.info("Balance Path: {}", path);

        return given()
                .contentType(ContentType.JSON)
                .headers(authentication.auth(path, "GET"))
                .port(443)
                .when()
                .get(path)
                .then()
                .statusCode(statusCode);
    }

    public ValidatableResponse getAccountBalance(String userId, String idType, String apiKey, int status) {
        ValidatableResponse validatableResponse = dataExchanger.isEwsRequest() ?
                getAccountBalanceEwsRequest(userId, idType, apiKey, status)
                :
                getAccountBalanceRequest(userId, idType, apiKey, status);

        if (status == 200)
            contractValidator.validateResponseWithContract("balance-response-schema.json", validatableResponse, status);
        return validatableResponse;
    }
}
