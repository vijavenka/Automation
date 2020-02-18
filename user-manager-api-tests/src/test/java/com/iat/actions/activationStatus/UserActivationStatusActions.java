package com.iat.actions.activationStatus;

import com.iat.controller.activationStatus.UserActivationStatusController;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;

public class UserActivationStatusActions {

    private UserActivationStatusController userActivationController = new UserActivationStatusController();

    public ResponseContainer setNewUserActivationStatus(String userIdString, String idType, String apiKey, String newActivateStatus, int status) {
        return initResponseContainer(userActivationController.setActivationStatus(userIdString, idType, apiKey, newActivateStatus, status));
    }
}