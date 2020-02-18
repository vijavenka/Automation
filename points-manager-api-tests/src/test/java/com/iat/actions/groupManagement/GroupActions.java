package com.iat.actions.groupManagement;

import com.iat.controller.groupManagement.GroupController;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;

public class GroupActions {

    private GroupController groupController = new GroupController();

    public ResponseContainer getGroupPartnersProperly(String shortName, int status) {
        return initResponseContainer(groupController.getGroupPartners(shortName, status));
    }
}