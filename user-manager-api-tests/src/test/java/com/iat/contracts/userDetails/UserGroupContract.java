package com.iat.contracts.userDetails;

public class UserGroupContract {

    public String groupPath(String userKey, String idType, String apiKey) {

        String path = "/users/$USER_KEY/group?".replace("$USER_KEY", userKey);

        if (!idType.equals("null"))
            path += "&idType=" + idType;
        if (!apiKey.equals("null"))
            path += "&apiKey=" + apiKey;

        return path.replace("?&", "?");
    }
}