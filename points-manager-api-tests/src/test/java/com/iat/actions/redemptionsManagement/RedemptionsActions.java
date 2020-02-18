package com.iat.actions.redemptionsManagement;

import com.iat.controller.redemptionsManagement.RedemptionsController;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;

public class RedemptionsActions {

    private RedemptionsController redemptionsController = new RedemptionsController();


    public ResponseContainer getRedemptions(String params, int status) {
        String[] params2 = params.split(",");
        String apiKey = params2[0];
        String ascending = params2[1];
        String orderField = params2[2];
        String offset = params2[3];
        String limit = params2[4];
        String startDate = params2[5];
        String endDate = params2[6];
        String searchField = params2[7];
        String keyword = params2[8];
        String withAddictivityInfo = params2[9];

        return initResponseContainer(redemptionsController.getRedemptions(apiKey, ascending, orderField, offset, limit, startDate, endDate, searchField, keyword, withAddictivityInfo, status));
    }

    public ResponseContainer getRedemptionsById(String params, int status) {
        String[] params2 = params.split(",");
        String apiKey = params2[0];
        String redemptionId = params2[1];

        return initResponseContainer(redemptionsController.getRedemptionsById(apiKey, redemptionId, status));
    }

    public ResponseContainer createRedemptionsUpdate(String params, String fulfill, int status) {
        String[] params2 = params.split(",");
        String apiKey = params2[0];
        String redemptionId = params2[1];

        return initResponseContainer(redemptionsController.createRedemptionsUpdate(apiKey, redemptionId, fulfill, status));
    }

    public ResponseContainer createRedemptionsRefund(String apiKey, String jsonBody, int status) {
        return initResponseContainer(redemptionsController.createRedemptionsRefundProperly(apiKey, jsonBody, status));
    }

    public String createErrorMessage(String params, String expErrorCode, String expErrorMsg) {
        String[] params2 = params.split(",");
        String apiKey = params2[0];
        String redemptionId = params2[1];

        if (expErrorCode.contains("PARTNER"))
            expErrorMsg = String.format(expErrorMsg, apiKey);

        if (expErrorCode.contains("REDEMPTION"))
            expErrorMsg = String.format(expErrorMsg, redemptionId);

        return expErrorMsg;
    }

}
