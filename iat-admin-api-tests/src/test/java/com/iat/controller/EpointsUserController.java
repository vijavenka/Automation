package com.iat.controller;

import com.iat.Config;
import com.iat.contracts.EpointsUserContract;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class EpointsUserController {

    private EpointsUserContract epointsUserContract = new EpointsUserContract();

    private ValidatableResponse confirmEmailRequest(String token, String password, String firstName, String lastName, String gender, int status) {
        String path = Config.getEpointsUrl() + epointsUserContract.getEmailConfirmationPath(token);
        log.info("Confirm email Path - POST: {}", password);
        log.info("password: {}]\n" +
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
                .then();
//                .statusCode(status);
    }

    public ValidatableResponse confirmEmail(String token, String password, String firstName, String lastName, String gender, int status) {
        return confirmEmailRequest(token, password, firstName, lastName, gender, status);
    }
}