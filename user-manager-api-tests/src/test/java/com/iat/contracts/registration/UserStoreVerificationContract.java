package com.iat.contracts.registration;

public class UserStoreVerificationContract {

    public String getUserStoreVerificationPath(String email, String lastName, String partnerId, String apiKey) {
        return "/users/verifyNewStore?apiKey=" + apiKey + "&email=" + email + "&lastName=" + lastName + "&partnerId=" + partnerId;
    }
}
