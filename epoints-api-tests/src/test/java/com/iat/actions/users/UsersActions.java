package com.iat.actions.users;


import com.iat.controller.users.UsersController;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;

public class UsersActions {

    private UsersController usersController = new UsersController();

    public ResponseContainer getUserBalance(String businessId, int status) {
        return initResponseContainer(usersController.getUserBalance(businessId, "null", status));
    }

    public ResponseContainer getUserBalance(String businessId, String access_token, int status) {
        return initResponseContainer(usersController.getUserBalance(businessId, access_token, status));
    }

    public ResponseContainer getUserTransactions(String uuid, int page, int size, String sort, String type, String businessId, int status) {
        return initResponseContainer(usersController.getUserTransactions(uuid, page, size, sort, type, businessId, status));
    }

    public ResponseContainer getUserTransactions(String uuid, int page, int size, String sort, String type, String businessId) {
        return getUserTransactions(uuid, page, size, sort, type, businessId, 200);
    }

    public ResponseContainer getUserRewardsHistory(String email, int size, String businessId, int status) {
        return initResponseContainer(usersController.getUserRewardsHistory(email, size, businessId, status), "Rewards History RESPONSE:");
    }

    public ResponseContainer redemptionOrder(String businessId, String email, String jsonBody, int status) {
        return redemptionOrder(businessId, email, jsonBody, "null", status);
    }

    public ResponseContainer redemptionOrder(String businessId, String email, String jsonBody, String access_token, int status) {
        if (status != 200)
            return initResponseContainer(usersController.redemptionOrder(businessId, email, jsonBody, access_token, status));
        return initResponseContainer(usersController.redemptionOrder(businessId, email, jsonBody, access_token, status), "");
    }

    public ResponseContainer getAccountDashboard(String userId, int page, int size, String sort, String type, int status) {
        return initResponseContainer(usersController.getAccountDashboard(userId, page, size, sort, type, status));
    }

    public ResponseContainer getMembershipType(int status) {
        return initResponseContainer(usersController.getMembershipType(status));
    }
}