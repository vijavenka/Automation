package com.iat.actions.partnersManagement;


import com.iat.controller.partnersManagement.PartnerTransactionsController;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;

public class PartnerTransactionsActions {

    private PartnerTransactionsController partnerTransactionsController = new PartnerTransactionsController();

    public ResponseContainer getPartnersTransactions(int status) {
        return getPartnersTransactions("null", status);
    }

    public ResponseContainer getPartnersTransactions(String apiKey, int status) {
        return initResponseContainer(partnerTransactionsController.getPartnersTransactions(apiKey, status));
    }

}
