package com.iat.actions;


import com.iat.controller.BasketController;
import com.iat.domain.basket.Basket;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;

public class BasketActions {

    private BasketController basketController = new BasketController();

    public ResponseContainer updateBasket(String uuid, Basket basket, int status) {
        return initResponseContainer(basketController.updateBasket(uuid, basket, "null", status));
    }

    public ResponseContainer updateBasket(String uuid, Basket basket, String access_token, int status) {
        return initResponseContainer(basketController.updateBasket(uuid, basket, access_token, status));
    }

    public ResponseContainer updateBasket(String uuid, Basket basket) {
        return updateBasket(uuid, basket, 200);
    }

    public ResponseContainer updateBasket(String uuid, Basket basket, String access_token) {
        return updateBasket(uuid, basket, access_token, 200);
    }

    public ResponseContainer getBasket(String uuid, int status) {
        return initResponseContainer(basketController.getBasket(uuid, "null", status));
    }

    public ResponseContainer getBasket(String uuid, String access_token, int status) {
        return initResponseContainer(basketController.getBasket(uuid, access_token, status));
    }
}