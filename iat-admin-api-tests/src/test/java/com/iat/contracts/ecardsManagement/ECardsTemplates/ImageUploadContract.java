package com.iat.contracts.ecardsManagement.ECardsTemplates;

public class ImageUploadContract {

    public String uploadImagePath(String rescale) {
        return "/api/image/?rescale=" + rescale;
    }

}