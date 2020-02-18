package com.iat.actions;

import com.iat.controller.TransactionSearchController;
import com.iat.utils.ResponseContainer;

import java.io.FileNotFoundException;
import java.util.Date;

import static com.iat.utils.ResponseContainer.initResponseContainer;

public class TransactionSearchActions {

    private TransactionSearchController transactionSearchController = new TransactionSearchController();

    public ResponseContainer searchClickouts(String network, String networkTransactionId, String userId, Long start,
                                             Long end, String orderBy, boolean ascending, int page, int pageSize, int status) throws FileNotFoundException {

        return initResponseContainer(transactionSearchController.getTransactionSearch(network,
                networkTransactionId, userId, start, end, orderBy, ascending, page, pageSize, status), "\nTransaction Search response:");
    }

    public ResponseContainer searchClickoutsBetweenDates(Date from, Date to, int status) throws FileNotFoundException {
        return searchClickouts("any", "any", "any", from.getTime(),
                to.getTime(), "id", true, 0, 10000, status);
    }
}