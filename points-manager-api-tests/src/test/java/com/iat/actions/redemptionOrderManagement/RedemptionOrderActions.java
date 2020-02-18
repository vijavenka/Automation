package com.iat.actions.redemptionOrderManagement;

import com.iat.Config;
import com.iat.controller.redemptionOrderManagement.RedemptionOrderController;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;
import static java.lang.String.format;

public class RedemptionOrderActions {

    private RedemptionOrderController redemptionOrderController = new RedemptionOrderController();


    public ResponseContainer getRedemptionOrder(String params, int status) {
        String[] params2 = params.split(",");
        String apiKey = params2[0].replace("envDepends", Config.getPartnerAccessKey());
        String orderId = params2[1];

        return initResponseContainer(redemptionOrderController.getRedemptionOrder(apiKey, orderId, status));
    }

    public ResponseContainer createRedemptionOrder(String params, String orderJsonObject, int status) {
        String[] params2 = params.split(",");
        String userId = params2[0];
        String idType = params2[1];
        String apiKey = params2[2].replace("envDepends", Config.getPartnerAccessKey());

        return initResponseContainer(redemptionOrderController.createRedemptionOrder(userId, idType, apiKey, orderJsonObject, status));
    }

    public ResponseContainer getRecentlyRedeemed(String params, int status) {
        String[] params2 = params.split(",");
        String apiKey = params2[0].replace("envDepends", Config.getPartnerAccessKey());
        String region = params2[1];
        String zone = params2[2];
        String offset = params2[3];
        String limit = params2[4];

        return initResponseContainer(redemptionOrderController.getRecentlyRedeemed(apiKey, region, zone, offset, limit, status));
    }

    public ResponseContainer getRedemptionOrderHistory(String params, int status) {
        String[] params2 = params.split(",");
        String userId = params2[0];
        String idType = params2[1];
        String apiKey = params2[2].replace("envDepends", Config.getPartnerAccessKey());
        String ascending = params2[3];
        String offset = params2[4];
        String limit = params2[5];
        String startDate = params2[6];
        String endDate = params2[7];

        return initResponseContainer(redemptionOrderController.getRedemptionOrderHistory(userId, idType, apiKey, ascending, offset, limit, startDate, endDate, status));
    }

    public String createErrorMessage(String params, String expErrorCode, String expErrorMsg) {

        String[] params2 = params.split(",");
        String userId = params2[0];
        String apiKey = params2[2].replace("envDepends", Config.getPartnerAccessKey());

        if (expErrorCode.contains("PARTNER"))
            return format(expErrorMsg, apiKey);

        if (expErrorCode.contains("USER"))
            return format(expErrorMsg, userId);

        return expErrorMsg;
    }
}