package com.iat.actions.registration;

import com.iat.controller.registration.UserTokenController;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;

public class UserTokenActions {

    private UserTokenController userTokenController = new UserTokenController();

    public ResponseContainer verifyUserToken(String apiKey, String token, int status) {
        return initResponseContainer(userTokenController.verifyUserToken(apiKey, token, status));
    }
}
