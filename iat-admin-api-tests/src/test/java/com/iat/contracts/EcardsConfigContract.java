package com.iat.contracts;


public class EcardsConfigContract {

    public String getEcardsSettingsPath(String settingsType) {

        String path = "/api/ecards/settings/";

        if (!settingsType.equals("null")) {
            path += settingsType;
        }

        return path;
    }
}
