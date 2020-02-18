package com.iat.actions.pointsAllocation;

import com.iat.controller.pointsAllocation.AddPointsController;
import com.iat.domain.pointsAllocation.MultiplePointsTransaction;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;

public class AddPointsActions {

    private AddPointsController addPointsController = new AddPointsController();

    public ResponseContainer addPointsToUserByIdProperly(String pointsTransactionBody, String userId, String idType, int status) {
        return initResponseContainer(addPointsController.addPointsToUserById(pointsTransactionBody, userId, idType, status));
    }

    public ResponseContainer checkPointsAcquirePossibility(String userId, String idType, String pointsTransactionBody, int status) {
        return initResponseContainer(addPointsController.checkPointsAcquirePossibility(userId, idType, pointsTransactionBody, status));
    }

    public ResponseContainer bulkUploadPoints(String pointsTransactionBody, int status) {
        return initResponseContainer(addPointsController.bulkUploadPoints(pointsTransactionBody, status));
    }

    public ResponseContainer awardPointsToMultipleTagsAndClientsProperly(MultiplePointsTransaction pointsTransaction, int status) {
        return initResponseContainer(addPointsController.awardPointsToMultipleTagsAndClients(pointsTransaction, status));
    }
}