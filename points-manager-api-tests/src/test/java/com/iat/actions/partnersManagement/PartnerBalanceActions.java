package com.iat.actions.partnersManagement;

import com.iat.controller.partnersManagement.PartnerBalanceController;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;

public class PartnerBalanceActions {

    private PartnerBalanceController partnerBalanceController = new PartnerBalanceController();

    public ResponseContainer getPartnerBalance(String params, int status) {
        String[] params2 = params.split(";");
        String clientId = params2[0];
        String apiKey = params2[1];
        String fields = params2[2];
        String startDate = params2[3];
        String endDate = params2[4];

        return initResponseContainer(partnerBalanceController.getPartnerBalance(clientId, apiKey, fields, startDate, endDate, status));
    }
}
