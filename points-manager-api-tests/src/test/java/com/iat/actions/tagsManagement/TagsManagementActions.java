package com.iat.actions.tagsManagement;

import com.iat.controller.tagsManagement.TagsManagementController;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;

public class TagsManagementActions {

    private TagsManagementController tagsManagementController = new TagsManagementController();

    public ResponseContainer getTagByTagKeyResponse(String tagKey, int status) {
        return initResponseContainer(tagsManagementController.getTagByTagKey(tagKey, status));
    }

    public ResponseContainer getTagsResponse(int status) {
        return initResponseContainer(tagsManagementController.getTagsForCurrentClient(status));
    }

    public ResponseContainer createNewTag(String tagKey, String cap, String frequency, String description, String autoConfirm, String imageUrl, int status) {
        return initResponseContainer(tagsManagementController.createNewTag(tagKey, cap, frequency, description, autoConfirm, imageUrl, status));
    }
}