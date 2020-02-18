package com.iat.actions;

import com.iat.controller.UserManagerUsersController;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;

public class UserManagerUsersActions {

    private UserManagerUsersController userManagerUsersController = new UserManagerUsersController();

    public ResponseContainer getUserAccountDetails(String id, String idType, String apiKey, int status) {
        return initResponseContainer(userManagerUsersController.getUserAccountDetails(id, idType, apiKey, status));
    }
}