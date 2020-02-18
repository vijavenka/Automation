package com.iat.actions.rewards;

import com.iat.controller.rewards.ProductDetailsController;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;

public class ProductDetailsActions {

    private ProductDetailsController productDetailsController = new ProductDetailsController();

    public ResponseContainer getRedemptionItemDetails(String id, int status) {
        return initResponseContainer(productDetailsController.getRedemptionItemDetails(id, status));
    }

    public ResponseContainer getRedemptionItemRelatedProducts(String id, String params, int status) {
        String[] params2 = params.split(";");
        String page = params2[0];
        String pageSize = params2[1];

        return initResponseContainer(productDetailsController.getRedemptionItemRelatedProducts(id, page, pageSize, status));
    }
}
