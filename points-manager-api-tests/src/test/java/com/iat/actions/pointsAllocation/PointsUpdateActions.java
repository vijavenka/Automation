package com.iat.actions.pointsAllocation;

import com.iat.controller.pointsAllocation.PointsUpdateController;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;

public class PointsUpdateActions {

    private PointsUpdateController pointsUpdateController = new PointsUpdateController();

    public ResponseContainer updatePointsTransactionByTransactionId(String transactionId, String pointsTransactionBody, int status) {
        return initResponseContainer(pointsUpdateController.updatePointsTransactionByTransactionId(transactionId, pointsTransactionBody, status));
    }

}