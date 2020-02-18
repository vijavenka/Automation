package com.iat.repository.impl;

import com.iat.Config;
import com.iat.domain.createNewUser;
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

    private ValidatableResponse createUser(createNewUser body, String username, String password) {
        String sessionId = authorizeUser(username, password);
        String path = Config.getIatAdminUrl() + "/api/users";
        System.out.println("\nPath: Create User POST " + path + "\n");

        return given()
                .contentType(ContentType.JSON)
                .body(body)
                .sessionId("SESSION", sessionId)
                .when()
                .post(path)
                .then();
    }

    @Override
    public String createNewUser(createNewUser body, String username, String password) {
        ValidatableResponse response = createUser(body, username, password);
        System.out.println("\nCreate User RESPONSE");
        response.extract().response().prettyPrint();
        return response.statusCode(201).extract().body().asString();
    }

    private ValidatableResponse deleteUser(String userId, String username, String password) {
        String path = Config.getIatAdminUrl() + "/api/users/" + userId;
        System.out.println("\nPath: User DELETE " + path + "\n");

        String sessionId = authorizeUser(username, password);
        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId)
                .when()
                .delete(path)
                .then();
    }

    @Override
    public String deleteChosenUser(String userId, String username, String password) {
        ValidatableResponse response = deleteUser(userId, username, password);
        System.out.println("\nDelete User RESPONSE");
        response.extract().response().prettyPrint();
        return response.statusCode(200).extract().body().asString();
    }
}