package com.iat.contracts.userDetails;

public class UserDetailsContract {

    public String resendVerificationEmail(String userId, String idType, String apiKey, String resendType) {
        return "/users/" + userId + "/resend?idType=" + idType + "&apiKey=" + apiKey + "&resendType=" + resendType;
    }

    public String getUserDetailsPath(String userId, String idType, String apiKey) {
        return "/users/" + userId + "/?idType=" + idType + "&apiKey=" + apiKey;
    }

    public String getUserDetailsByEpointsTokenPath(String epointsToken, String apiKey) {
        return "/users/epointsToken/" + epointsToken + "?apiKey=" + apiKey;
    }
}