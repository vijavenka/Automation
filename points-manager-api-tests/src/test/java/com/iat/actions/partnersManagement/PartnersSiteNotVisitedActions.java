package com.iat.actions.partnersManagement;


import com.iat.controller.partnersManagement.PartnersSiteNotVisitedController;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;
import static java.lang.String.format;

public class PartnersSiteNotVisitedActions {

    private PartnersSiteNotVisitedController partnersSiteNotVisitedController = new PartnersSiteNotVisitedController();


    public ResponseContainer getPartnersSiteNotVisited(String params, int status) {
        String[] params2 = params.split(",");
        String userId = params2[0];
        String idType = params2[1];
        String apiKey = params2[2];
        String limit = params2[3];
        String offset = params2[4];
        String random = params2[5];

        return initResponseContainer(partnersSiteNotVisitedController.getPartnersSiteNotVisited(userId, idType, apiKey, limit, offset, random, status));
    }

    public String createErrorMessage(String params, String expErrorCode, String expErrorMsg) {

        String[] params2 = params.split(",");
        String userId = params2[0];
        String idType = params2[1];
        String apiKey = params2[2];

        if (expErrorCode.contains("PARTNER"))
            return format(expErrorMsg, apiKey);

        if (expErrorCode.contains("USER"))
            return format(expErrorMsg, userId);

        return expErrorMsg;
    }
}
