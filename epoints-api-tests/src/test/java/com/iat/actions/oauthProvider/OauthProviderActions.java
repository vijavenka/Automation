package com.iat.actions.oauthProvider;

import com.iat.controller.oauthProvider.OauthProviderController;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;

public class OauthProviderActions {

    private OauthProviderController oauthProviderController = new OauthProviderController();

    //password
    public ResponseContainer authenticateUserByOauthPassword(String authorizationHeader, String grantType, String username, String password, int status) {
        return initResponseContainer(oauthProviderController.authenticateByOauth(authorizationHeader, grantType, username, password, "null", "null", status));
    }

    //refresh_token and facebook
    public ResponseContainer authenticateUserByOauthToken(String authorizationHeader, String grantType, String token, String facebookId, int status) {
        return initResponseContainer(oauthProviderController.authenticateByOauth(authorizationHeader, grantType, "null", "null", token, facebookId, status));
    }
}