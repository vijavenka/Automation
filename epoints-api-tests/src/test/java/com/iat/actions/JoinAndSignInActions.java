package com.iat.actions;

import com.iat.controller.JoinAndSignInController;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;

public class JoinAndSignInActions {

    private JoinAndSignInController joinAndSignInController = new JoinAndSignInController();

    public ResponseContainer authorizeUser(String credentials) {
        return initResponseContainer(joinAndSignInController.authorizeUser(credentials));
    }

    public String getSessionIdForAuthUser(String credentials) {
        return authorizeUser(credentials).getCookie("SESSION");
    }

    public ResponseContainer joinEpoints(String email, int status) {
        return initResponseContainer(joinAndSignInController.joinEpoints(email, status));
    }

    public ResponseContainer joinEpointsForSpecifiedBusiness(String email, String businessType, String externalId, int status) {
        return initResponseContainer(joinAndSignInController.joinEpointsForBusiness(email, businessType, externalId, status));
    }

    public ResponseContainer joinEpointsMobile(String email, String firstName, String lastName, String password, int status) {
        return initResponseContainer(joinAndSignInController.joinEpointsMobile(email, firstName, lastName, password, status));
    }

}
