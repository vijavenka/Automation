package com.iat.repository.impl;

import com.iat.Config;
import com.iat.domain.Token;
import com.iat.domain.User;
import com.iat.domain.UserBalance;
import com.iat.repository.UserRepository;
import io.restassured.response.Response;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.delete;
import static io.restassured.RestAssured.get;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public UserBalance findBalanceByUserId(String id) {
        return get(Config.getDoormanUrl() + "/users/" + id + "/balance?db=true").getBody().as(UserBalance.class);
    }

    @Override
    public User findByEmail(String email) {
        Response response = get(Config.getDoormanUrl() + "/users?email=" + email);
        return response.getBody().as(User.class);
    }

    @Override
    public String getTokenValueByUUID(String uuid) {
        Response response = get(Config.getDoormanUrl() + "/users/" + uuid + "/tokens");
        response.then().statusCode(200);
        if (response.getBody().asString().contains("[")) {
            List<Token> tokens = Arrays.asList(response.getBody().as(Token[].class));
            return tokens.get(0).getToken(); //0 as it is most recent active token
        } else
            return response.getBody().as(Token.class).getToken();
    }

    @Override
    public void deletUserFromDynamoAndPointsManager(String uuid) {
        Response response = delete(Config.getDoormanUrl() + "/users/" + uuid);
        response.then().statusCode(200);
    }

}
