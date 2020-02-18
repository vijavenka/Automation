package com.iat.actions.transactions;


import com.iat.contracts.transactions.IdType;
import com.iat.controller.transactions.TransactionsController;
import com.iat.domain.transactions.PointsDetails;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;

public class TransactionsActions {

    private TransactionsController transactionsController = new TransactionsController();

    public ResponseContainer postTransactions(PointsDetails pointsDetails, String id, IdType idType, int status) {
        return initResponseContainer(transactionsController.postTransactions(pointsDetails, id, idType, status));
    }

    public ResponseContainer postTransactions(PointsDetails pointsDetails, String uuid, int status) {
        return postTransactions(pointsDetails, uuid, IdType.UUID, status);
    }

}