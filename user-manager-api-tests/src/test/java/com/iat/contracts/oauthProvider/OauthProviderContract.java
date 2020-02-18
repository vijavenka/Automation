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


    public String getOauthCheckPath(String token, String clientId, String clientSecret) {
        String path = "/oauth/check_token/";

        if (!clientId.equals("null"))
            path += "&clientId=" + clientId;
        else
            path += "&clientId=bdl";
        if (!clientSecret.equals("null"))
            path += "&clientSecret=" + clientSecret;
        else
            path += "&clientSecret=bdl_secret";
        if (!token.equals("null"))
            path += "&token=" + token;

        return path.replace("/&", "?");
    }

}