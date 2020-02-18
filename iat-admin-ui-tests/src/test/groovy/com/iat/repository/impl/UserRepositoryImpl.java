package com.iat.repository.impl;

import com.iat.Config;
import com.iat.domain.User;
import com.iat.repository.UserRepository;
import io.restassured.response.Response;

import static io.restassured.RestAssured.delete;
import static io.restassured.RestAssured.get;

public class UserRepositoryImpl implements UserRepository {

    public User findByEmail(String email) {
        Response response = get(Config.getDoormanUrl() + "/users?email=" + email);
        response.then().statusCode(200);
        return response.getBody().as(User.class);
    }

    public void deletUserFromDynamoAndPointsManager(String uuid) {
        Response response = delete(Config.getDoormanUrl() + "/users/" + uuid);
        response.then().statusCode(200);
    }
}