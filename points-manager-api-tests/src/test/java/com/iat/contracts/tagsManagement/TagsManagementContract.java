package com.iat.contracts.tagsManagement;

public class TagsManagementContract {

    public String getTagDataByKey(String tagKey) {
        return "/tags/" + tagKey + "?apiKey=accessKey";
    }

    //currently client set to epoints
    public String getAllTagsDataFromCurrentClient() {
        return "/tags?apiKey=accessKey";
    }

    public String createNewTag() {
        return "/tags?apiKey=accessKey";
    }

    public String returnCreateTagRequestBody(String tagKey, String cap, String frequency, String description, String autoConfirm, String imageUrl) {
        return "{\"tagName\":\"" + tagKey + "\",\"cap\":" + cap + ",\"frequency\":\"" + frequency + "\",\"imageUrl\":\"" + imageUrl + "\",\"description\":\"" + description + "\",\"autoConfirm\":" + autoConfirm + "}";
    }

}