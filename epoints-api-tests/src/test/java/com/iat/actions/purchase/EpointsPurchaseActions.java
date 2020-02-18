package com.iat.actions.purchase;


import com.iat.controller.epointsPurchase.EpointsPurchaseController;
import com.iat.domain.purchase.BuyEpoints;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;

public class EpointsPurchaseActions {

    private EpointsPurchaseController epointsPurchaseController = new EpointsPurchaseController();

    public ResponseContainer purchaseEpoints(BuyEpoints jsonBody, int status) {
        return initResponseContainer(epointsPurchaseController.purchaseEpoints(jsonBody, status), "Get social status RESPONSE:");
    }
}