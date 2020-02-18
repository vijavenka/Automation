package com.iat.actions.accountManagement;

import com.iat.controller.accountManagement.AccountActivationController;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;

public class AccountActivationActions {

    private AccountActivationController accountActivationController = new AccountActivationController();

    public ResponseContainer getAccountActivationStatus(String id, String idType, int status) {
        return initResponseContainer(accountActivationController.getAccountActivationStatus(id, idType, status));
    }

    public boolean getAccountActivationStatus(String id, String idType) {
        return getAccountActivationStatus(id, idType, 200).getBoolean("active");
    }
}