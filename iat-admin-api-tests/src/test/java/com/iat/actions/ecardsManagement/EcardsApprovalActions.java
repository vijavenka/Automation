package com.iat.actions.ecardsManagement;


import com.iat.controller.ecardsManagement.EcardsApprovalController;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;


public class EcardsApprovalActions {

    private EcardsApprovalController ecardsApprovalController = new EcardsApprovalController();


    public ResponseContainer getEcardsApprovalList(int status) {
        return initResponseContainer(ecardsApprovalController.getEcardsApprovalList(status), "Ecards Approval List RESPONSE:");
    }

    public ResponseContainer getEcardsApprovalById(String id, int status) {
        return initResponseContainer(ecardsApprovalController.getEcardsApprovalById(id, status), "Ecards Approval By Id RESPONSE:");
    }

    public ResponseContainer setEcardsApprovalAsApproved(String id, int status) {
        return initResponseContainer(ecardsApprovalController.setEcardsApprovalAsApproved(id, status), "Ecards Approve RESPONSE:");
    }

    public ResponseContainer setEcardsApprovalAsRejected(String id, String jsonBody, int status) {
        return initResponseContainer(ecardsApprovalController.setEcardsApprovalAsRejected(id, jsonBody, status), "Ecards Reject RESPONSE:");
    }

    public ResponseContainer getEcardsApprovalCounter(int status) {
        return initResponseContainer(ecardsApprovalController.getEcardsApprovalCounter(status), "Ecards Approval Counter RESPONSE:");
    }
}
