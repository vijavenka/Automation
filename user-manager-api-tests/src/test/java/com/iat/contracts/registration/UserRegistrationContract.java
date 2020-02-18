package com.iat.contracts.registration;

public class UserRegistrationContract {

    public String getUserAccountCreationPath(String apiKey) {
        return "/users?apiKey=" + apiKey;
    }
}