package com.iat.actions.oauthProvider;

import com.iat.Config;
import com.iat.controller.oauthProvider.OauthProviderController;
import com.iat.utils.ResponseContainer;
import io.restassured.response.ValidatableResponse;

import static com.iat.utils.ResponseContainer.initResponseContainer;

public class OauthProviderActions {

    private OauthProviderController oauthProviderController = new OauthProviderController();

    //password
    public ResponseContainer authenticateUserByOauthPassword(String authorizationHeader, String grantType, String username, String password, int status) {
        return initResponseContainer(oauthProviderController.authenticateUserByOauth(authorizationHeader, grantType, username, password, "null", "null", status));
    }

    public ResponseContainer authenticateUserByOauthPassword(String grantType, String username, String password) {
        return authenticateUserByOauthPassword(Config.getOauthProviderAuthorizationHeader(), grantType, username, password, 200);
    }

    //refresh_token and facebook
    public ResponseContainer authenticateUserByOauthToken(String authorizationHeader, String grantType, String token, String facebookId, int status) {
        return initResponseContainer(oauthProviderController.authenticateUserByOauth(authorizationHeader, grantType, "null", "null", token, facebookId, status));
    }

    public ResponseContainer tokenCheck(String token, String clientId, String clientSecret, int status) {
        return initResponseContainer(oauthProviderController.tokenCheck(token, clientId, clientSecret, status));
    }

    public ResponseContainer useSingleUseToken(String token, int status) {
        ValidatableResponse validatableResponse = oauthProviderController.useSingleUseToken(token, status);
        if (status != 302)
            return initResponseContainer(validatableResponse);
        else {
            String url = validatableResponse.extract().response().then().extract().header("Location");
            return initResponseContainer(oauthProviderController.useSingleUseTokenRedirected(url, validatableResponse.extract().response().detailedCookies(), 200));
        }
    }
}