package com.iat.contracts.userDetails;

public class UserExternalContract {

    public String getRetailer(String externalId, String externalIdType, String apiKey) {

        String path = "/users/externalId/" + externalId + "&&&";

        if (!externalIdType.equals("null"))
            path += "&externalIdType=" + externalIdType;
        if (!apiKey.equals("null"))
            path += "&apiKey=" + apiKey;

        return path.replace("&&&&", "&&&?").replace("&&&", "");
    }
}