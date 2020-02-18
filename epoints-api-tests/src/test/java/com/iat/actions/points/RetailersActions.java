package com.iat.actions.points;

import com.iat.controller.points.RetailerController;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;

public class RetailersActions {

    private RetailerController retailerController = new RetailerController();

    public ResponseContainer getRecentlyVisitedRetailers(int status) {
        return initResponseContainer(retailerController.getRecentlyVisitedRetailers(status));
    }

    public ResponseContainer putDeleteFavouritesRetailers(String merchantId, String userId, boolean favourite, int status) {
        return initResponseContainer(retailerController.putDeleteFavouritesRetailers(merchantId, userId, favourite, status));
    }
}