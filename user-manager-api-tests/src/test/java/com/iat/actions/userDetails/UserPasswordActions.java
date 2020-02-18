package com.iat.actions.userDetails;

import com.iat.controller.userDetails.UserPasswordController;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;

public class UserPasswordActions {

    private UserPasswordController userPasswordController = new UserPasswordController();

    public ResponseContainer resetUserAccountPassword(String email, String apiKey, int status) {
        return initResponseContainer(userPasswordController.resetUserAccountPassword(email, apiKey, status));
    }
}
