package com.iat.controller;

import com.iat.contracts.JoinAndSignInContract;
import com.iat.utils.DataExchanger;
import com.iat.validators.ContractValidator;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Slf4j
public class JoinAndSignInController {

    private JoinAndSignInContract joinAndSignInContract = new JoinAndSignInContract();
    private ContractValidator contractValidator = new ContractValidator();
    private DataExchanger dataExchanger = DataExchanger.getInstance();

    private ValidatableResponse authorizeUserRequest(String email, String password) {
        String path = joinAndSignInContract.getLoginPath();
        log.info("Login Path: {}", path);

        return given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("email", email.contains("@") ? email : dataExchanger.getEmail())
                .formParam("password", password)
                .when()
                .post(path)
                .then()
                .statusCode(allOf(greaterThanOrEqualTo(200), lessThan(400)));
    }

    public ValidatableResponse authorizeUser(String credentials) {
        return authorizeUserRequest(credentials.split(",")[0], credentials.split(",")[1]);
    }


    private ValidatableResponse joinEpointsRequest(String email, int status) {
        String path = joinAndSignInContract.getJoinPath(email);
        log.info("Join Path: GET {}", path);

        return given()
                .contentType("application/x-www-form-urlencoded")
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse joinEpoints(String email, int status) {
        ValidatableResponse validatableResponse = joinEpointsRequest(email, status);
        contractValidator.validateResponseWithContract("/GET-status-ok.json", validatableResponse, status);
        return validatableResponse;
    }

    private ValidatableResponse joinEpointsForBusinessRequest(String email, String businessType, String externalId, int status) {
        String path = joinAndSignInContract.getJoinPath(email, businessType, externalId);
        log.info("Join Path: GET {}{}", RestAssured.baseURI, path);

        return given()
                .contentType("application/x-www-form-urlencoded")
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse joinEpointsForBusiness(String email, String businessType, String externalId, int status) {
        ValidatableResponse validatableResponse = joinEpointsForBusinessRequest(email, businessType, externalId, status);
        contractValidator.validateResponseWithContract("/GET-status-ok.json", validatableResponse, status);
        return validatableResponse;
    }

    private ValidatableResponse joinEpointsMobileRequest(String email, String firstName, String lastName, String password, int status) {
        String path = joinAndSignInContract.getJoinMobilePath(email, firstName, lastName, password);

        log.info("Join Path: GET {}", path);

        return given()
                .contentType("application/x-www-form-urlencoded")
                .urlEncodingEnabled(false)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse joinEpointsMobile(String email, String firstName, String lastName, String password, int status) {
        ValidatableResponse validatableResponse = joinEpointsMobileRequest(email, firstName, lastName, password, status);
        contractValidator.validateResponseWithContract("/GET-status-ok.json", validatableResponse, status);
        return validatableResponse;
    }

}