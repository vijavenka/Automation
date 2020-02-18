package com.iat.steps.oauthEpointsAuthorisation;

import com.iat.Config;
import com.iat.actions.JoinAndSignInActions;
import com.iat.actions.oauthProvider.OauthProviderActions;
import com.iat.domain.OAuth;
import com.iat.utils.DataExchanger;
import com.iat.utils.ResponseContainer;
import com.iat.utils.SessionIdKeeper;
import cucumber.api.java.en.When;

public class OauthEpointsAuthorisationSteps {

    private OauthProviderActions oauthProviderActions = new OauthProviderActions();
    private DataExchanger dataExchanger = DataExchanger.getInstance();
    private ResponseContainer response;
    private SessionIdKeeper sessionId = SessionIdKeeper.getInstance();
    private JoinAndSignInActions joinAndSignInActions = new JoinAndSignInActions();

    @When("^User is authenticating with following data '(.+?)', '(.+?)', '(.+?)', '(\\d+)' password based grant type$")
    public void authorizeUserWithOauthForPasswordGrantType(String grantType, String credentials, String authorizationHeader, int status) throws Throwable {
        if (authorizationHeader.equals("default"))
            authorizationHeader = Config.getOauthProviderAuthorizationHeader();

        String username = null, password = null;
        switch (credentials) {
            case "previous_call":
                username = dataExchanger.getEmail();
                password = dataExchanger.getPassword();
                break;
            case "epointsUserDefault_1":
                username = Config.getEpointsUserDefault_1.split(",")[0];
                password = Config.getEpointsUserDefault_1.split(",")[1];
                break;
            case "unitedUserDefault_1":
                username = Config.getUnitedUserDefault_1.split(",")[0];
                password = Config.getUnitedUserDefault_1.split(",")[1];
                break;
        }

        response = oauthProviderActions.authenticateUserByOauthPassword(authorizationHeader, grantType, username, password, status);
        dataExchanger.setoAuth(response.getAsObject(OAuth.class));
        sessionId.set(joinAndSignInActions.getSessionIdForAuthUser(username+","+password));
    }
}