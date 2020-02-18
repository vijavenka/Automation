package com.iat.actions.interests;

import com.iat.controller.interests.UsersInterestsController;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;

public class UsersInterestsActions {

    private UsersInterestsController usersInterestsController = new UsersInterestsController();

    public ResponseContainer getUserInterests(String userId, String userIdType, String apiKey, int status) {
        return initResponseContainer(usersInterestsController.getListOfUserInterests(userId, userIdType, apiKey, status));
    }

    public ResponseContainer addMerchantIdToUserInterests(String merchantId, String userId, String userIdType, String apiKey, int status) {
        return initResponseContainer(usersInterestsController.postNewUserInterest(merchantId, userId, userIdType, apiKey, status));
    }

    public ResponseContainer deleteMerchantIdFromUserInterests(String merchantId, String userId, String userIdType, String apiKey, int status) {
        return initResponseContainer(usersInterestsController.deleteUserInterests(merchantId, userId, userIdType, apiKey, status));
    }
}