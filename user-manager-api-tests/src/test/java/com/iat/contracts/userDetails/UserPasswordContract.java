package com.iat.contracts.userDetails;

public class UserPasswordContract {

    public String resetUserPasswordPath(String email, String apiKey) {
        return "/forgottenPassword?apiKey=" + apiKey + "&email=" + email;
    }
}
