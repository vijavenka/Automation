package com.iat.contracts.authentication;

import com.iat.utils.DataExchanger;

public class UserAuthenticationContract {

    private DataExchanger dataExchanger = DataExchanger.getInstance();

    public String authenticateUser(String email, String password, String apiKey) {

        String path = "/authentication/by-epoints/";

        if (!email.equals("null"))
            path += "&email=" + (email.contains("@") ? email : dataExchanger.getEmail());
        if (!password.equals("null"))
            path += "&password=" + password;
        if (!apiKey.equals("null"))
            path += "&apiKey=" + apiKey;

        return path.replace("/&", "?");
    }

    public String authenticateUserWithFacebook(String accessToken, String facebookId, String apiKey) {

        String path = "/authentication/by-facebook/";

        if (!accessToken.equals("null"))
            path += "&accessToken=" + accessToken;
        if (!facebookId.equals("null"))
            path += "&facebookId=" + facebookId;
        if (!apiKey.equals("null"))
            path += "&apiKey=" + apiKey;

        return path.replace("/&", "?");
    }
}