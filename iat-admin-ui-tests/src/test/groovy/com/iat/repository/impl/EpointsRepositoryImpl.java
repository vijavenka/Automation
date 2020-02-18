package com.iat.repository.impl;

import com.iat.Config;
import com.iat.repository.EpointsRepository;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;

public class EpointsRepositoryImpl implements EpointsRepository {

    private ValidatableResponse confirmEmailRequest(String token, String password, String firstName, String lastName, String gender, int status) {
        String path = Config.getEpointsUrl() + "/rest/join/confirm-email/" + token;
        System.out.println("\nConfirm email Path - POST: " + path);
        System.out.println("password: " + password);
        System.out.println("firstName: " + firstName);
        System.out.println("lastName: " + lastName);
        System.out.println("gender: " + gender);

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

    @Override
    public String confirmEmail(String token, String password, String firstName, String lastName, String gender, int status) {
        ValidatableResponse validatableResponse = confirmEmailRequest(token, password, firstName, lastName, gender, status);
        assertThat("Wrong redirection url after account confirmation", validatableResponse.extract().headers().get("Location").toString().contains("/login/loginSuccess"));
        System.out.println("\nRESPONSE:");
        validatableResponse.extract().response().prettyPrint();
        return validatableResponse.extract().response().getBody().asString();
    }

}