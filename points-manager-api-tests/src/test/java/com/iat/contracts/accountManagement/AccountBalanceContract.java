package com.iat.contracts.accountManagement;

public class AccountBalanceContract {

    public String getAccountBalanceByIdPath(String userId, String idType, String apiKey) {
        return "/users/" + userId + "/balance?idType=" + idType + "&apiKey=" + apiKey;
    }
}