package com.iat.actions.rest;


import com.iat.controller.rest.RestController;
import com.iat.domain.UserDetails;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;


public class RestActions {

    private RestController restController = new RestController();

    public ResponseContainer getBrandingConfig(boolean brandingAvailability, int status) {
        return initResponseContainer(restController.getBrandingConfig(brandingAvailability, status));
    }

    public ResponseContainer getUserProfile(String access_token, int status) {
        return initResponseContainer(restController.getUserProfile(access_token, status));
    }

    public ResponseContainer getUserProfile(int status) {
        return getUserProfile("null", status);
    }

    public ResponseContainer getUserProfile() {
        return getUserProfile("null", 200);
    }

    public ResponseContainer getEmailVerification(String token, int status) {
        return initResponseContainer(restController.getEmailVerification(token, status));
    }

    public ResponseContainer confirmEmail(String token, String password, String firstName, String lastName, String gender, int status) {
        return initResponseContainer(restController.confirmEmail(token, password, firstName, lastName, gender, status));
    }

    public ResponseContainer confirmEmailGDPR(String token, UserDetails userDetails, int status) {
        return initResponseContainer(restController.confirmEmailGDPR(token, userDetails, status));
    }

    public ResponseContainer savePersonalDetails(String firstName, String lastName, String title, String gender, String dob, String access_token, int status) {
        return initResponseContainer(restController.savePersonalDetails(firstName, lastName, title, gender, dob, access_token, status));
    }

    public ResponseContainer savePersonalDetails(String firstName, String lastName, String title, String gender, String dob, int status) {
        return savePersonalDetails(firstName, lastName, title, gender, dob, "null", status);
    }

    public ResponseContainer saveContactDetails(String phoneNo, String house, String street, String city, String county, String country, String postCode, int status) {
        return initResponseContainer(restController.saveContactDetails(phoneNo, house, street, city, county, country, postCode, status));
    }

    public ResponseContainer saveConsentDetails(boolean subscribedToMarketingEmails, int status) {
        return initResponseContainer(restController.saveConsentDetails(subscribedToMarketingEmails, status));
    }

    public ResponseContainer saveNewEmail(String newEmail, int status) {
        return initResponseContainer(restController.saveNewEmail(newEmail, status));
    }

    public ResponseContainer activateNewEmail(String token, int status) {
        return initResponseContainer(restController.activateNewEmail(token, status));
    }

    public ResponseContainer changeUserPassword(String currentPassword, String newPassword, int status) {
        return initResponseContainer(restController.changeUserPassword(currentPassword, newPassword, status));
    }

    public ResponseContainer subscriptionsRegistration(String email, String plan, String coupon, int status) {
         return initResponseContainer(restController.subscriptionRegistration(email, plan, coupon, status));
    }
}
