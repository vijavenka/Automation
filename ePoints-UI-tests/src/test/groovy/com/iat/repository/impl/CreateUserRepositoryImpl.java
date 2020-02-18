package com.iat.repository.impl;

import com.iat.Config;
import com.iat.domain.NewUser;
import com.iat.repository.CreateUserRepository;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class CreateUserRepositoryImpl implements CreateUserRepository {

    private String authorizeUser(String email, String password) {
        Response response = given().post(Config.getIatAdminUrl() + "/api/login?user=" + email + "&password=" + password);
        response.then().statusCode(302);
        return response.then().extract().response().getCookie("SESSION");
    }

    private ValidatableResponse createUser(NewUser body, String username, String password) {
        String sessionId = authorizeUser(username, password);
        return given()
                .contentType(ContentType.JSON)
                .body(body)
                .sessionId("SESSION", sessionId)
                .when()
                .post(Config.getIatAdminUrl() + "/api/users")
                .then();
    }

    public String createNewUser(NewUser body, String username, String password) {
        ValidatableResponse response = createUser(body, username, password);
        return response.statusCode(201).extract().response().getBody().jsonPath().getString("id");
    }
}