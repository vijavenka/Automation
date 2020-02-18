package com.iat.actions.ecardsManagement.ECardsTemplates;


import com.iat.controller.ecardsManagement.ECardsTemplates.ImageUploadController;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;


public class ImageUploadActions {

    private ImageUploadController imageUploadController = new ImageUploadController();

    public ResponseContainer uploadImage(String imageName, String rescale, int status) {
        return initResponseContainer(imageUploadController.uploadImage(imageName, rescale, status));
    }
}