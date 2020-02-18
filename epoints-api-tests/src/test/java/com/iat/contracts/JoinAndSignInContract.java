package com.iat.contracts;

public class JoinAndSignInContract {

    public String getLoginPath() {
        return "rest/login/loginProcess";
    }

    public String getJoinPath(String email) {
        return "rest/join/init/" + email + "/";
    }

    public String getJoinPath(String email, String businessType, String externalId) {
        return "rest/join/init/" + email + "/" + businessType + "/" + externalId + "/";
    }

    public String getJoinMobilePath(String email, String firstName, String lastName, String password) {
        String path = "rest/join/init-mobile/";

        if (!email.equals("null"))
            path += email + "/";

        if (!firstName.equals("null"))
            path += "&firstName=" + firstName;


        if (!lastName.equals("null"))
            path += "&lastName=" + lastName;

        if (!password.equals("null"))
            path += "&password=" + password;


        return path.replace("/&", "/?");
    }

}