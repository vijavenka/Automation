package com.iat.actions.deliveryAddress;

import com.iat.controller.deliveryAddress.UserDeliveryAddressController;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;

public class UserDeliveryAddressActions {

    private UserDeliveryAddressController deliveryAddressController = new UserDeliveryAddressController();


    public ResponseContainer getDeliveryAddress(String userId, String apiKey, String limit, String offset, int status) {
        return initResponseContainer(deliveryAddressController.getDeliveryAddress(userId, apiKey, limit, offset, status));
    }

    public ResponseContainer addDeliveryAddress(String userId, String apiKey, String deliveryAddressParams, int status) {
        return initResponseContainer(deliveryAddressController.createDeliveryAddress(userId, apiKey, deliveryAddressParams, status));
    }
}
