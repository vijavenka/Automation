package com.iat.contracts;


public class LoginContract {

    public String getLoginPath(String credentials) {

        String username = credentials.split(",")[0];
        String password = credentials.split(",")[1];

        return "/oauth/token" + "?username=" + username + "&password=" + password +
                "&grant_type=password" +
                "&scope=read write" +
                "&client_secret=xWDsdaazj23addL-1213s" +
                "&client_id=auditcmsapp";
    }
}