package com.iat.contracts.balance;

public class UserBalanceContract {

    public String getUserBalanceByEmailPath(String email, String idType, String apiKey) {
        return "/users/" + email + "/balance?idType=" + idType + "&apiKey=" + apiKey;
    }
}