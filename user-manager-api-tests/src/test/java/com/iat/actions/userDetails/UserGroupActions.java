package com.iat.actions.userDetails;

import com.iat.controller.userDetails.UserGroupController;
import com.iat.domain.UserGroup;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;

public class UserGroupActions {

    private UserGroupController userGroupController = new UserGroupController();

    private ResponseContainer postGroup(String userKey, String idType, String apiKey, UserGroup userGroup, int status) {
        return initResponseContainer(userGroupController.postGroup(userKey, idType, apiKey, userGroup, status));
    }

    public ResponseContainer postGroup(String userKey, String apiKey, UserGroup userGroup, int status) {
        return postGroup(userKey, "UUID", apiKey, userGroup, status);
    }

    public ResponseContainer postGroup(String userKey, String apiKey, UserGroup userGroup) {
        return postGroup(userKey, apiKey, userGroup, 200);
    }

    private ResponseContainer deleteGroup(String userKey, String idType, String apiKey, UserGroup userGroup, int status) {
        return initResponseContainer(userGroupController.deleteGroup(userKey, idType, apiKey, userGroup, status));
    }

    public ResponseContainer deleteGroup(String userKey, String apiKey, UserGroup userGroup, int status) {
        return deleteGroup(userKey, "UUID", apiKey, userGroup, status);
    }

    public ResponseContainer deleteGroup(String userKey, String apiKey, UserGroup userGroup) {
        return deleteGroup(userKey, apiKey, userGroup, 200);
    }

    private ResponseContainer getGroups(String userKey, String idType, String apiKey, int status) {
        return initResponseContainer(userGroupController.getGroups(userKey, idType, apiKey, status));
    }

    public ResponseContainer getGroups(String userKey, String apiKey, int status) {
        return getGroups(userKey, "UUID", apiKey, status);
    }

    public ResponseContainer getGroups(String userKey, String apiKey) {
        return getGroups(userKey, apiKey, 200);
    }
}