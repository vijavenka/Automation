package com.iat.actions.userDetails;

import com.iat.controller.registration.UserTokenController;
import com.iat.controller.userDetails.UserDetailsController;
import com.iat.controller.userDetails.UserVerificationController;
import com.iat.domain.UserDetails;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;

public class UserDetailsActions {

    private UserVerificationController userVerificationController = new UserVerificationController();
    private UserTokenController userTokenController = new UserTokenController();
    private UserDetailsController userDetailsController = new UserDetailsController();


    public ResponseContainer resendUserEmailChangeOrRegistrationEmail(String userId, String idType, String apiKey, String resendType, int status) {
        return initResponseContainer(userVerificationController.resendChangeOrRegistrationEmail(userId, idType, apiKey, resendType, status));
    }

    public ResponseContainer resendUserEmailChangeOrRegistrationEmail(String userId, String idType, String apiKey, String resendType) {
        return resendUserEmailChangeOrRegistrationEmail(userId, idType, apiKey, resendType, 200);
    }

    public ResponseContainer verifyUserToken(String apiKey, String token, int status) {
        return initResponseContainer(userTokenController.verifyUserToken(apiKey, token, status));
    }

    public ResponseContainer updateUserAccountDetailsByJson(String userId, String idType, String apiKey, UserDetails userDetails, int status) {
        return initResponseContainer(userDetailsController.updateUserAccountDetailsByJson(userId, idType, apiKey, userDetails, status));
    }

    public ResponseContainer getUserAccountDetails(String email, String idType, String apiKey) {
        return getUserAccountDetails(email, idType, apiKey, 200);
    }

    public ResponseContainer getUserAccountDetails(String email, String idType, String apiKey, int status) {
        return initResponseContainer(userDetailsController.getUserAccountDetails(email, idType, apiKey, status));
    }

    public ResponseContainer getUserAccountDetailsById(String userKey, String apiKey, int status) {
        return initResponseContainer(userDetailsController.getUserAccountDetails(userKey, "UUID", apiKey, status));
    }

    public ResponseContainer getUserAccountDetailsByEpointsToken(String epointsToken, String apiKey, int status) {
        return initResponseContainer(userDetailsController.getUserAccountDetailsByEpointsToken(epointsToken, apiKey, status));
    }
}