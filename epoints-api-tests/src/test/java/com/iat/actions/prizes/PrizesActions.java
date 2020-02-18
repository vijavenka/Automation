package com.iat.actions.prizes;

import com.iat.controller.prizes.PrizesController;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;

public class PrizesActions {

    private PrizesController prizesController = new PrizesController();

    public ResponseContainer getSpinsNumber(String uuid, String status, int responseStatus) {
        return initResponseContainer(prizesController.getSpinsNumber(uuid, status, responseStatus));
    }
}