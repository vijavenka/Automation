package com.iat.controller.oauthProvider;

import com.iat.Config;
import com.iat.contracts.oauthProvider.OauthProviderContract;
import com.iat.validators.ContractValidator;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class OauthProviderController {

    private OauthProviderContract oauthProviderContract = new OauthProviderContract();
    private ContractValidator contractValidator = new ContractValidator();

    private ValidatableResponse authenticateByOauthRequest(String authorizationHeader, String grantType, String username, String password, String token, String facebookId, int status) {

        String path;
        path = Config.getOauthProviderUrl() + oauthProviderContract.getOauthPath(grantType, token, facebookId, username, password);

        log.info("OAuth authentication Path: POST {}", path);

        return given()
                .contentType("application/x-www-form-urlencoded")
                .urlEncodingEnabled(false)
                .header("Authorization", "Basic " + authorizationHeader)
                .when()
                .post(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse authenticateByOauth(String authorizationHeader, String grantType, String username, String password, String token, String facebookId, int status) {
        ValidatableResponse validatableResponse = authenticateByOauthRequest(authorizationHeader, grantType, username, password, token, facebookId, status);

        if (status == 200) {
            if (grantType.contains("single_use"))
                contractValidator.validateResponseWithContract("/oauthProvider/GET-200-oauthToken-singleUseToken.json", validatableResponse, status);
            else
                contractValidator.validateResponseWithContract("/oauthProvider/GET-200-oauthToken.json", validatableResponse, status);
        } else if (status == 400 || grantType.contains("facebook") || grantType.contains("single_use"))
            contractValidator.validateResponseWithContract("/oauthProvider/GET-400-oauthToken-error.json", validatableResponse);
        else if (status == 401 && !grantType.contains("facebook"))
            contractValidator.validateResponseWithContract("/oauthProvider/GET-400-oauthToken-error-with-message.json", validatableResponse);

        return validatableResponse;
    }
}