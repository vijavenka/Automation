package com.iat.contracts;

import com.iat.Config;

public class UserManagerUsersContract {

    public String getUserDetailsPath(String id, String idType, String apiKey) {
        return Config.getUserManagerApiUrl() + "/users/" + id + "/?idType=" + idType + "&apiKey=" + apiKey;
    }

}