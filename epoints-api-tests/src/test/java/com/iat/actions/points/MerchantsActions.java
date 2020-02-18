package com.iat.actions.points;

import com.iat.controller.points.MerchantsController;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;

public class MerchantsActions {

    private MerchantsController merchantDetailsController = new MerchantsController();

    public ResponseContainer getMerchantDetails(String merchantId, int status) {
        return initResponseContainer(merchantDetailsController.getMerchantDetails(merchantId, status));
    }

    public ResponseContainer getPromotedMerchantsDetails(String ids, int status) {
        return initResponseContainer(merchantDetailsController.getPromotedMerchantsDetails(ids, status));
    }
}