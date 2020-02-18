package com.iat.actions;

import com.iat.controller.SOLRController;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;

public class SOLRActions {

    private SOLRController solrController = new SOLRController();

    public ResponseContainer getSOLRRedemptionsByQuery(String query, int status) {
        return initResponseContainer(solrController.getRedemptionsByQuery(query, status), "SOLAR RESPONSE:");
    }

    public int getSOLRRedemptionsCount(String query) {
        return getSOLRRedemptionsByQuery(query, 200).getInt("response.numFound");
    }

    public ResponseContainer getVouchersForQuery(String voucherId, int status) {
        return initResponseContainer(solrController.getVouchersForQuery(voucherId, status), "SOLAR RESPONSE:");
    }

}