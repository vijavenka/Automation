package com.iat.contracts.interests;

public class UsersInterestsContract {

    public String getPostDeleteInterestsPath(String merchantId, String userId, String userIdType, String apiKey) {
        return "/users/" + userId + "/merchants/" + merchantId + "?idType=" + userIdType + "&apiKey=" + apiKey;
    }

    public String getUserInterestsPath(String userId, String userIdType, String apiKey) {
        return "/users/" + userId + "/interests?idType=" + userIdType + "&apiKey=" + apiKey;
    }
}