package com.iat.actions.authentication;

import com.iat.controller.authentication.UserAuthenticationController;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;

public class UserAuthenticationActions {

    private UserAuthenticationController userAuthenticationController = new UserAuthenticationController();

    public ResponseContainer authenticateUser(String email, String password, String apiKey, int status) {
        return initResponseContainer(userAuthenticationController.authenticateUser(email, password, apiKey, status));
    }

    public ResponseContainer authenticateUserWithFacebook(String accessToken, String facebookId, String apiKey, int status) {
        return initResponseContainer(userAuthenticationController.authenticateUserWithFacebook(accessToken, facebookId, apiKey, status));
    }

}