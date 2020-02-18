package com.iat.actions.accountManagement;

import com.iat.Config;
import com.iat.controller.accountManagement.AccountBalanceController;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;

public class AccountBalanceActions {

    private AccountBalanceController accountBalanceController = new AccountBalanceController();

    public ResponseContainer getAccountBalance(String userId, String idType, String apiKey, int status) {
        return initResponseContainer(accountBalanceController.getAccountBalance(userId, idType, apiKey, status));
    }

    public ResponseContainer getAccountBalance(String userId, String idType, int status) {
        return getAccountBalance(userId, idType, Config.getPartnerAccessKey(), status);
    }
}
