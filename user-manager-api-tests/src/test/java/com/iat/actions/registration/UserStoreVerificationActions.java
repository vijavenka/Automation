package com.iat.actions.registration;

import com.iat.controller.registration.UserStoreVerficationController;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;

public class UserStoreVerificationActions {

    private UserStoreVerficationController userStoreVerficationController = new UserStoreVerficationController();

    public ResponseContainer verifyNewStore(String userEmail, String userLastName, String partnerId, String apiKey, int status) {
        return initResponseContainer(userStoreVerficationController.verifyNewStoreName(userEmail, userLastName, partnerId, apiKey, status));
    }
}
