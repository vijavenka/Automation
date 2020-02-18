package com.iat.actions.United;

import com.iat.controller.United.TransactionsController;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;

public class TransactionsActions {

    private TransactionsController transactionsController = new TransactionsController();

    public ResponseContainer processTransactions(String dataUrl, String externalId, String transactionDate, int status) {
        return initResponseContainer(transactionsController.processTransactions(dataUrl, externalId, transactionDate, status));
    }
}