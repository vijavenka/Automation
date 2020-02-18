package com.iat.repository.impl;

import com.iat.Config;
import com.iat.domain.Token;
import com.iat.domain.User;
import com.iat.repository.UserRepository;
import io.restassured.response.Response;

import static io.restassured.RestAssured.delete;
import static io.restassured.RestAssured.get;


public class UserRepositoryImpl implements UserRepository {


    public User findUserByUserId(String id) {
        System.out.println("Doorman: Get " + Config.getDoormanUrl() + "/users/" + id + "/balance?db=true");
        Response response = get(Config.getDoormanUrl() + "/users/" + id + "/balance?db=true");
        response.prettyPrint();
        return response.getBody().as(User.class);
    }

    public User findByEmail(String email) {
        System.out.println("Doorman: Get " + Config.getDoormanUrl() + "/users?email=" + email);
        Response response = get(Config.getDoormanUrl() + "/users?email=" + email);
        response.prettyPrint();
        return response.getBody().as(User.class);
    }

    public String returnAllUserData(String email) {
        System.out.println("Doorman: Get " + Config.getDoormanUrl() + "/users?email=" + email);
        Response response = get(Config.getDoormanUrl() + "/users?email=" + email);
        response.prettyPrint();
        return response.getBody().asString();
    }

    public String getTokenValueByUUID(String uuid) {
        Response response = get(Config.getDoormanUrl() + "/users/" + uuid + "/tokens");
        response.then().statusCode(200);
        return response.getBody().as(Token.class).getToken();
    }

    public void deleteUserFromDynamoAndPointsManager(String uuid) {
        System.out.println("Doorman: Delete " + Config.getDoormanUrl() + "/users/" + uuid);
        Response response = delete(Config.getDoormanUrl() + "/users/" + uuid);
        response.then().statusCode(200);
    }

}
