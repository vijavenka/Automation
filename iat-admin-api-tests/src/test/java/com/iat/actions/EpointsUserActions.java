package com.iat.actions;


import com.iat.controller.EpointsUserController;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;


public class EpointsUserActions {

    private EpointsUserController epointsUserController = new EpointsUserController();

    public ResponseContainer confirmEmail(String token, String password, String firstName, String lastName, String gender, int status) {
        return initResponseContainer(epointsUserController.confirmEmail(token, password, firstName, lastName, gender, status));
    }

}
