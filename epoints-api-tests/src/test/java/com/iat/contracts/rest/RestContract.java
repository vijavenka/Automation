package com.iat.contracts.rest;

public class RestContract {

    public String getBrandingConfigPath() {
        return "/rest/branding/config";
    }

    public String getProfilePath() {
        return "/rest/my-account/profile/load";
    }

    public String getJoinsEmailConfirm(String token) {
        String path = "/rest/join/confirm-email/";

        if (!token.equals("null"))
            path += token;

        return path;
    }

    public String getEmailConfirmationPath(String token) {
        return "/rest/join/confirm-email/" + token;
    }

    public String getEmailGDPRConfirmationPath(String token) {
        return "/rest/join/confirm-email/" + token + "/v2";
    }

    public String getSavePersonalDetailsPath() {
        return "rest/my-account/profile/save/personalDetails";
    }

    public String getSaveContactDetailsPath() {
        return "/rest/my-account/profile/save/contactDetails";
    }

    public String getSaveConsentDetailsPath() {
        return "/rest/my-account/profile/save/consentDetails";
    }

    public String getSaveEmailPath() {
        return "/rest/my-account/profile/save/email";
    }

    public String getEmailActivationPath(String token) {
        return "/rest/mail/activate/" + token;
    }

    public String getChangePasswordPath() {
        return "/rest/my-account/profile/save/password";
    }

    public String getSubscriptionRegistrationPath(String email, String plan, String coupon) {
        String path = "/rest/user/" + email + "/subscription_registration?plan=" + plan + "&couponCode=" + coupon;
        return path;
    }

}