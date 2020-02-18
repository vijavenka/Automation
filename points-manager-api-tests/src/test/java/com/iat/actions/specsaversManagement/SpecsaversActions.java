package com.iat.actions.specsaversManagement;


import com.iat.controller.specsaversManagement.SpecsaversController;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;
import static java.lang.String.format;

public class SpecsaversActions {

    private SpecsaversController specsaversController = new SpecsaversController();

    public ResponseContainer getReasonsForGroup(String params, int status) {
        String[] params2 = params.split(",");
        String shortName = params2[0];
        String apiKey = params2[1];
        String clientId = params2[2];

        return initResponseContainer(specsaversController.getReasonsForGroup(shortName, apiKey, clientId, status));
    }

    public ResponseContainer getReasonsForPartner(String apiKey, int status) {
        return initResponseContainer(specsaversController.getReasonsForPartner(apiKey, status));
    }

    public ResponseContainer getUsersInfoForPartner(String params, int status) {
        String[] params2 = params.split(",");
        String shortName = params2[0];
        String apiKey = params2[1];

        return initResponseContainer(specsaversController.getUsersInfoForPartner(shortName, apiKey, status));
    }

    public ResponseContainer getPartnerReportOverview(String params, int status) {
        String[] params2 = params.split(",");
        String shortName = params2[0];
        String apiKey = params2[1];
        String startDate = params2[2];
        String endDate = params2[3];

        return initResponseContainer(specsaversController.getReportOverviewForPartner(shortName, apiKey, startDate, endDate, status));
    }

    public ResponseContainer getAwardedPointsForPartner(String params, int status) {
        String[] params2 = params.split(",");
        String shortName = params2[0];
        String apiKey = params2[1];
        String page = params2[2];
        String pageSize = params2[3];
        String startDate = params2[4];
        String endDate = params2[5];

        return initResponseContainer(specsaversController.getAwardedPointsForPartner(shortName, apiKey, page, pageSize, startDate, endDate, status));
    }

    public ResponseContainer getRedeemedPointsForPartner(String params, int status) {
        String[] params2 = params.split(",");
        String shortName = params2[0];
        String apiKey = params2[1];
        String page = params2[2];
        String pageSize = params2[3];
        String startDate = params2[4];
        String endDate = params2[5];

        return initResponseContainer(specsaversController.getRedemeedPointsForPartner(shortName, apiKey, page, pageSize, startDate, endDate, status));
    }

    public ResponseContainer getNetsPointsForPartner(String params, int status) {
        String[] params2 = params.split(",");
        String shortName = params2[0];
        String apiKey = params2[1];
        String currency = params2[2];

        return initResponseContainer(specsaversController.getNetsPointsForPartner(shortName, apiKey, currency, status));
    }

    public String createErrorMessage(String params, String expErrorCode, String expErrorMsg) {

        String[] params2 = params.split(",");
        String shortName = params2[0];
        String apiKey = params2[1];
        String clientId = params2[2];

        if (expErrorCode.contains("GROUP"))
            return format(expErrorMsg, shortName);

        if (expErrorCode.contains("UNAUTHORIZED_PARTNER"))
            return format(expErrorMsg, clientId);

        if (expErrorCode.contains("PARTNER_IS_NOT_ACTIVE"))
            return format(expErrorMsg, apiKey);

        if (expErrorCode.contains("INVALID_GROUP"))
            return format(expErrorMsg, shortName);

        return expErrorMsg;
    }
}


