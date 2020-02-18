package com.iat.controller.oauthProvider;

import com.iat.Config;
import com.iat.contracts.oauthProvider.OauthProviderContract;
import com.iat.validators.ContractValidator;
import io.restassured.http.Cookies;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class OauthProviderController {

    private OauthProviderContract oauthProviderContract = new OauthProviderContract();
    private ContractValidator contractValidator = new ContractValidator();

    private ValidatableResponse authenticateByOauth(String authorizationHeader, String grantType, String username, String password, String token, String facebookId, int status) {

        String path;
        path = Config.getOauthProviderUrl() + oauthProviderContract.getOauthPath(grantType, token, facebookId, username, password);

        log.info("OAuth authentication Path: POST {}", path);

        return given()
                .contentType("application/x-www-form-urlencoded")
                .urlEncodingEnabled(false)
                .headers("Authorization", "Basic " + authorizationHeader)
                .when()
                .post(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse authenticateUserByOauth(String authorizationHeader, String grantType, String username, String password, String token, String facebookId, int status) {
        ValidatableResponse validatableResponse = authenticateByOauth(authorizationHeader, grantType, username, password, token, facebookId, status);

        if (status == 400 || (grantType.contains("facebook") && status != 200) || (grantType.contains("single_use") && status != 200))
            contractValidator.validateResponseWithContract("oauthProvider/GET-400-oauthToken-error.json", validatableResponse);
        else if (status == 401 && !grantType.contains("facebook"))
            contractValidator.validateResponseWithContract("oauthProvider/GET-400-oauthToken-error-with-message.json", validatableResponse);
        else {
            //status 200
            if (grantType.contains("single_use"))
                contractValidator.validateResponseWithContract("oauthProvider/GET-200-oauthToken-singleUseToken.json", validatableResponse, status);
            else
                contractValidator.validateResponseWithContract("oauthProvider/GET-200-oauthToken.json", validatableResponse, status);
        }


        return validatableResponse;
    }


    private ValidatableResponse tokenCheckRequest(String token, String clientId, String clientSecret, int status) {

        String path = Config.getOauthProviderUrl() + oauthProviderContract.getOauthCheckPath(token, clientId, clientSecret);

        log.info("Check token Path: POST {}", path);

        return given()
                .contentType("application/x-www-form-urlencoded")
                .urlEncodingEnabled(false)
                .when()
                .post(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse tokenCheck(String token, String clientId, String clientSecret, int status) {
        ValidatableResponse validatableResponse = tokenCheckRequest(token, clientId, clientSecret, status);
        if (status == 400)
            contractValidator.validateResponseWithContract("oauthProvider/GET-400-oauthToken-error.json", validatableResponse);
        else
            contractValidator.validateResponseWithContract("oauthProvider/POST-200-tokenCheck.json", validatableResponse, status);
        return validatableResponse;
    }

    private ValidatableResponse useSingleUseTokenRequest(String token, int status) {

        String path = Config.getEpointsUrl() + "/rest/oauth/authenticate?authentication=" + token;

        log.info("Use token Path: POST {}", path);

        return given()
                .contentType("application/x-www-form-urlencoded")
                .urlEncodingEnabled(false)
                .redirects().follow(false)
                .when()
                .post(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse useSingleUseToken(String token, int status) {
        //endpoint not user contract not checked, to be updated when endpoint enabled
        return useSingleUseTokenRequest(token, status);
    }

    private ValidatableResponse useSingleUseTokenRedirectedRequest(String url, Cookies cookies, int status) {

        log.info("Use token redirect Path: GET {}", url);

        return given()
                .contentType("application/x-www-form-urlencoded")
                .urlEncodingEnabled(false)
                .redirects().follow(false)
                .cookies(cookies)
                .when()
                .get(url)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse useSingleUseTokenRedirected(String url, Cookies cookies, int status) {
        ValidatableResponse validatableResponse = useSingleUseTokenRedirectedRequest(url, cookies, status);
        contractValidator.validateResponseWithContract("oauthProvider/GET-200-single_use-token-epoints-authorization.json", validatableResponse, status);
        return validatableResponse;
    }
}