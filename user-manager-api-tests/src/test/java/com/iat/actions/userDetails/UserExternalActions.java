package com.iat.actions.userDetails;

import com.iat.controller.userDetails.UserExternalController;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;

public class UserExternalActions {

    private UserExternalController userExternalController = new UserExternalController();

    public ResponseContainer getRetailer(String externalId, String externalIdType, String apiKey, int status) {
        return initResponseContainer(userExternalController.getRetailer(externalId, externalIdType, apiKey, status));
    }
}