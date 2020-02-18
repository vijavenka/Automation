package com.iat.contracts.accountManagement;

public class AccountActivationContract {

    public String getAccountActivationPath(String id, String idType, String accessKey) {
        return "/users/" + id + "/?idType=" + idType + "&apiKey=" + accessKey + "&fields=active";
    }
}
