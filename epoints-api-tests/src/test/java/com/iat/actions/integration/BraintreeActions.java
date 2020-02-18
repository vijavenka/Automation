package com.iat.actions.integration;

import com.iat.controller.integration.BraintreeController;
import com.iat.domain.integration.ConvertToEPoints;
import com.iat.utils.ResponseContainer;
import static com.iat.utils.ResponseContainer.initResponseContainer;

public class BraintreeActions {

    private BraintreeController braintreeController = new BraintreeController();

    public ResponseContainer getTransactions(ConvertToEPoints convertToEpoints, int transactionStatus){
        return initResponseContainer(braintreeController.getTransactions(convertToEpoints, transactionStatus));
    }
}
