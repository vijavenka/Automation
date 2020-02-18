package com.iat.contracts;

public class EpointsUserContract {

    public String getEpointsUserPath(String keywoard) {

        String path = "/api/epoints-users";

        if (!keywoard.equals("null")) {
            path += "?keywoard=" + keywoard;
        }
        return path;
    }

    public String getEmailConfirmationPath(String token) {
        return "/rest/join/confirm-email/" + token;
    }
}
