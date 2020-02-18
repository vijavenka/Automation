package com.iat.actions.registration;

import com.iat.controller.registration.UserRegistrationController;
import com.iat.domain.UserDetails;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;

public class UserRegistrationActions {

    private UserRegistrationController userRegistrationController = new UserRegistrationController();

    public ResponseContainer createUserAccountByJson(UserDetails jsonBody, String apiKey, int status) {
        return initResponseContainer(userRegistrationController.createUserAccountByJson(jsonBody, apiKey, status));
    }

}