package com.iat.actions;


import com.iat.controller.LoginController;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;


public class LoginActions {

    private LoginController loginController = new LoginController();

    public ResponseContainer userLogIn(String credentials) {
        return userLogIn(credentials, 302);
    }

    public ResponseContainer userLogIn(String credentials, int status) {
        if (status != 302)
            return initResponseContainer(loginController.userLogIn(credentials, status));
        else
            return initResponseContainer(loginController.userLogIn(credentials, status), "");
    }

}
