package com.iat.actions;


import com.iat.controller.LoginController;
import com.iat.utils.JsonParserUtils;
import com.iat.utils.SessionIdKeeper;


public class LoginActions {

    SessionIdKeeper bearer = SessionIdKeeper.getInstance();
    private LoginController loginController = new LoginController();
    JsonParserUtils jsonParserUtils = JsonParserUtils.getInstance();

    public void userLogIn(String credentials) {
        String tokenResponse = loginController.userLogInProperly(credentials).extract().response().asString();
        bearer.set(jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(tokenResponse), "access_token"));
    }


}
