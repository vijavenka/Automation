package com.iat.contracts;

/**
 * Created by miwanczyk on 2016-01-20.
 */
public class LoginContract {

    public String getLoginPath(String credentials) {

        String[] params2 = credentials.split(",");
        String login = params2[0];
        String password = params2[1];

        String path = "/api/login";

        if (!login.equals("null")) {
            path += "?user=" + login;

            if (!password.equals("null"))
                path += "&password=" + password;
        }

        return path;
    }

}
