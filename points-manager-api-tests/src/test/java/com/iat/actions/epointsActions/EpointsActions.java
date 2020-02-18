package com.iat.actions.epointsActions;

import com.iat.controller.epointsController.EpointsController;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;

public class EpointsActions {

    private EpointsController epointsController = new EpointsController();

    public ResponseContainer getRedemptionProductList(int status) {
        return initResponseContainer(epointsController.getRedemptionProductList(status));
    }

    public ResponseContainer getRedemptionIndividualProduct(String seoSlug, String productId, int status) {
        return initResponseContainer(epointsController.getRedemptionIndividualProduct(seoSlug, productId, status));
    }
}
