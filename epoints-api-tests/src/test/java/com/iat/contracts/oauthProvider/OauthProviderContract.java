package com.iat.contracts.oauthProvider;

public class OauthProviderContract {


    public String getOauthPath(String grantType, String token, String facebookId, String username, String password) {
        String path = "/oauth/token/";

        if (!grantType.equals("null"))
            path += "&grant_type=" + grantType;
        if (!username.equals("null"))
            path += "&username=" + username;
        if (!password.equals("null"))
            path += "&password=" + password;
        if (!token.equals("null")) {
            if (grantType.equals("refresh_token"))
                path += "&" + grantType + "=" + token;
            else if (grantType.contains("facebook"))
                path += "&access_token=" + token;
            else if (grantType.contains("single_use"))
                path += "&token=" + token;
        }

        if (!facebookId.equals("null"))
            path += "&facebook_id=" + facebookId;

        return path.replace("/&", "?");
    }
}