package com.iat.actions;

import com.iat.controller.MerchantsController;

public class MerchantsActions {

    private MerchantsController merchantsController = new MerchantsController();

    public String getListOfMerchants() {
        return merchantsController.getListOfMerchants();
    }
}
