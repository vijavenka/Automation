package com.iat.contracts.userDetails;

public class UserTokenContract {

    public String getTokenVerificationPath(String apiKey, String token) {
        return "/verify?apiKey=" + apiKey + "&token=" + token;
    }
}
