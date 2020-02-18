package com.iat.contracts.activationStatus;

public class UserActivationStatusContract {

    //TODO: Make idType and apiKey variable

    public String setUserActivationStatus(String userId, String idType, String apiKey, String activateStatus) {
        return "/users/" + userId + "/activation/" + activateStatus + "?idType=" + idType + "&apiKey=" + apiKey;
    }
}