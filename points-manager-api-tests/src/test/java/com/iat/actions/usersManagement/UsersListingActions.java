package com.iat.actions.usersManagement;

import com.iat.controller.usersManagement.UsersListingController;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;

public class UsersListingActions {

    private UsersListingController usersListingController = new UsersListingController();

    public ResponseContainer getUserSearchData(String limit, String sortField, String sortOrder, String offset, int status) {
        return initResponseContainer(usersListingController.getUsersSearchData(limit, sortField, sortOrder, offset, status));
    }

}