package com.iat.controller.rest;

import com.iat.contracts.rest.RestContract;
import com.iat.domain.UserDetails;
import com.iat.utils.SessionIdKeeper;
import com.iat.validators.ContractValidator;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;

import javax.jws.soap.SOAPBinding;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

@Slf4j
public class RestController {

    private RestContract restContract = new RestContract();
    private ContractValidator contractValidator = new ContractValidator();
    private SessionIdKeeper sessionId = SessionIdKeeper.getInstance();

    private ValidatableResponse getBrandingConfigRequest(int status) {
        String path = restContract.getBrandingConfigPath();

        log.info("Branding Path - GET: {}{}", RestAssured.baseURI, path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.get())
                .when()
                .get(path)
                .then()
                .statusCode(status);

    }

    public ValidatableResponse getBrandingConfig(boolean brandingAvailability, int status) {
        ValidatableResponse validatableResponse = getBrandingConfigRequest(status);
        if (brandingAvailability)
            contractValidator.validateResponseWithContract("/rest/GET-BrandingConfig-schema.json", validatableResponse, status);
        return validatableResponse;
    }


    private ValidatableResponse getUserProfileRequest(int status) {
        String path = restContract.getProfilePath();
        log.info("Profile Path - GET: {}{}", RestAssured.baseURI, path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.get())
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    private ValidatableResponse getUserProfileRequestOAuth(int status, String access_token) {
        String path = restContract.getProfilePath();
        log.info("Profile Path - GET: {}{}", RestAssured.baseURI, path);

        Header header = null;
        if (!access_token.equals("null"))
            header = new Header("Authorization", "Bearer " + access_token);

        return given()
                .contentType(ContentType.JSON)
                .header(header)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getUserProfile(String access_token, int status) {
        ValidatableResponse validatableResponse;
        if (access_token.equals("null"))
            validatableResponse = getUserProfileRequest(status);
        else
            validatableResponse = getUserProfileRequestOAuth(status, access_token);
        contractValidator.validateResponseWithContract("/rest/GET-profile.json", validatableResponse, status);
        return validatableResponse;
    }


    private ValidatableResponse getEmailVerificationRequest(String token, int status) {
        String path = restContract.getJoinsEmailConfirm(token);
        log.info("Email Verification Path - GET: {}{}", RestAssured.baseURI, path);

        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getEmailVerification(String token, int status) {
        ValidatableResponse validatableResponse = getEmailVerificationRequest(token, status);
        contractValidator.validateResponseWithContract("/rest/GET-confirmEmail-schema.json", validatableResponse, status);
        return validatableResponse;
    }

    private ValidatableResponse confirmEmailRequest(String token, String password, String firstName, String lastName, String gender, int status) {
        String path = restContract.getEmailConfirmationPath(token);
        log.info("Confirm email Path - POST: {}{}", RestAssured.baseURI, path);
        log.info("password: {}\n" +
                "firstName: {}\n" +
                "lastName: {}\n" +
                "gender: {}", password, firstName, lastName, gender);

        return given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("password", password)
                .formParam("firstName", firstName)
                .formParam("lastName", lastName)
                .formParam("gender", gender)
                .when()
                .post(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse confirmEmail(String token, String password, String firstName, String lastName, String gender, int status) {
        ValidatableResponse validatableResponse = confirmEmailRequest(token, password, firstName, lastName, gender, status);
        validatableResponse.assertThat().header("Location", containsString("/login/loginSuccess"));
//      above header check is used instead of 200 ok it was changed during authorization change
//      contractValidator.validateResponseWithContract("/GET-status-ok.json", validatableResponse, status);
        return validatableResponse;
    }

    private ValidatableResponse confirmEmailGDPRRequest(String token, UserDetails userDetails, int status) {
        String path = restContract.getEmailGDPRConfirmationPath(token);
        log.info("Confirm email Path - POST: {}{}", RestAssured.baseURI, path);
        log.info("password: {}\n" +
                "firstName: {}\n" +
                "lastName: {}\n" +
                "gender: {}\n" +
                "verified: {}\n" +
                "active: {}\n" +
                "privacyAccepted: {}\n" +
                "marketingPrefSMS: {}\n" +
                "marketingPrefEmail: {}\n" +
                "tncAccepted: {}", userDetails.getPassword(), userDetails.getFirstName(), userDetails.getLastName(),
                userDetails.getGender(), userDetails.getVerified(), userDetails.getActive(), userDetails.getPrivacyAccepted(),
                userDetails.getMarketingPrefSMS(), userDetails.getMarketingPrefEmail(), userDetails.getTncAccepted());

        return given()
                .contentType("application/json")
                .body(userDetails)
                .when()
                .post(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse confirmEmailGDPR(String token, UserDetails userDetails, int status) {
        ValidatableResponse validatableResponse = confirmEmailGDPRRequest(token, userDetails, status);
        if(status == 400 || status == 401)
            contractValidator.validateResponseWithContract("/ErrorResponse-schema.json", validatableResponse, status);
        else
            validatableResponse.assertThat().header("Location", containsString("/login/loginSuccess"));
//      above header check is used instead of 200 ok it was changed during authorization change
//      contractValidator.validateResponseWithContract("/GET-status-ok.json", validatableResponse, status);
        return validatableResponse;
    }

    private RequestSpecification givenSavePersonal(String firstName, String lastName, String title, String gender, String dob) {
        return given()
                .contentType("application/x-www-form-urlencoded; charset=ISO-8859-1")
                .formParam("personalDetails.firstName", firstName)
                .formParam("personalDetails.lastName", lastName)
                .formParam("personalDetails.title", title)
                .formParam("personalDetails.gender", gender)
                .formParam("personalDetails.dob", dob);
    }

    private ValidatableResponse savePersonalRequest(String firstName, String lastName, String title, String gender, String dob, int status) {
        String path = restContract.getSavePersonalDetailsPath();
        log.info("Save personal details Path - POST: {}{}", RestAssured.baseURI, path);

        return givenSavePersonal(firstName, lastName, title, gender, dob)
                .sessionId("SESSION", sessionId.get())
                .when()
                .post(path)
                .then()
                .statusCode(status);
    }

    private ValidatableResponse savePersonalRequestOAuth(String firstName, String lastName, String title, String gender, String dob, String access_token, int status) {
        String path = restContract.getSavePersonalDetailsPath();
        log.info("Save personal details Path - POST: {}{}", RestAssured.baseURI, path);

        Header header = null;
        if (!access_token.equals("null"))
            header = new Header("Authorization", "Bearer " + access_token);

        return givenSavePersonal(firstName, lastName, title, gender, dob)
                .header(header)
                .when()
                .post(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse savePersonalDetails(String firstName, String lastName, String title, String gender, String dob, String access_token, int status) {
        ValidatableResponse validatableResponse;
        if (access_token.equals("null"))
            validatableResponse = savePersonalRequest(firstName, lastName, title, gender, dob, status);
        else
            validatableResponse = savePersonalRequestOAuth(firstName, lastName, title, gender, dob, access_token, status);
        return validatableResponse;
    }

    private ValidatableResponse saveContact(String phoneNo, String house, String street, String city, String county, String country, String postCode, int status) {
        String path = restContract.getSaveContactDetailsPath();
        log.info("Save contact details Path - POST: {}{}", RestAssured.baseURI, path);

        return given()
                .contentType("application/x-www-form-urlencoded; charset=ISO-8859-1")
                .formParam("contactDetails.phoneNo", phoneNo)
                .formParam("contactDetails.address.house", house)
                .formParam("contactDetails.address.street", street)
                .formParam("contactDetails.address.city", city)
                .formParam("contactDetails.address.county", county)
                .formParam("contactDetails.address.country", country)
                .formParam("contactDetails.address.postCode", postCode)
                .sessionId("SESSION", sessionId.get())
                .when()
                .post(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse saveContactDetails(String phoneNo, String house, String street, String city, String county, String country, String postCode, int status) {
        return saveContact(phoneNo, house, street, city, county, country, postCode, status);
    }

    private ValidatableResponse saveConsent(boolean subscribedToMarketingEmails, int status) {
        String path = restContract.getSaveConsentDetailsPath();
        log.info("Save consent details Path - POST: {}{}", RestAssured.baseURI, path);

        return given()
                .contentType("application/x-www-form-urlencoded; charset=ISO-8859-1")
                .formParam("consentDetails.subscribedToMarketingEmails", subscribedToMarketingEmails)
                .sessionId("SESSION", sessionId.get())
                .when()
                .post(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse saveConsentDetails(boolean subscribedToMarketingEmails, int status) {
        return saveConsent(subscribedToMarketingEmails, status);
    }

    private ValidatableResponse saveEmail(String newEmail, int status) {
        String path = restContract.getSaveEmailPath();
        log.info("Save new email Path - POST: {}{}", RestAssured.baseURI, path);

        return given()
                .contentType("application/x-www-form-urlencoded; charset=ISO-8859-1")
                .formParam("accountDetails.email", newEmail)
                .sessionId("SESSION", sessionId.get())
                .when()
                .post(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse saveNewEmail(String newEmail, int status) {
        return saveEmail(newEmail, status);
    }

    private ValidatableResponse activateEmail(String token, int status) {
        String path = restContract.getEmailActivationPath(token);
        log.info("Activate new email Path - GET: {}{}", RestAssured.baseURI, path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.get())
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse activateNewEmail(String token, int status) {
        return activateEmail(token, status);
    }

    private ValidatableResponse changePassword(String currentPassword, String newPassword, int status) {
        String path = restContract.getChangePasswordPath();
        log.info("Change password Path - POST: {}{}", RestAssured.baseURI, path);

        return given()
                .contentType("application/x-www-form-urlencoded; charset=ISO-8859-1")
                .formParam("accountDetails.existingPassword", currentPassword)
                .formParam("accountDetails.newPassword", newPassword)
                .sessionId("SESSION", sessionId.get())
                .when()
                .post(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse changeUserPassword(String currentPassword, String newPassword, int status) {
        return changePassword(currentPassword, newPassword, status);
    }

    private ValidatableResponse getSubscriptionRegistration(String email, String plan, String coupon, int status){
        String path = restContract.getSubscriptionRegistrationPath(email, plan, coupon);
        log.info("Subscription registration Path - POST: {}{}", RestAssured.baseURI, path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.get())
                .when()
                .post(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse subscriptionRegistration(String email, String plan, String coupon, int status){
        ValidatableResponse validatableResponse = getSubscriptionRegistration(email, plan, coupon, status);
        if(status == 400 || status == 401)
            contractValidator.validateResponseWithContract("/ErrorResponse-schema.json", validatableResponse, status);
        else
            contractValidator.validateResponseWithContract("/rest/GET-subscriptionRegistration-schema.json", validatableResponse, status);
        return validatableResponse;
    }
}