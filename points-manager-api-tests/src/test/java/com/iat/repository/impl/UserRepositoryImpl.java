package com.iat.repository.impl;

import com.iat.Config;
import com.iat.domain.User;
import com.iat.domain.UserBalance;
import com.iat.repository.UserRepository;
import io.restassured.response.Response;

import static io.restassured.RestAssured.get;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public User findByEmail(String email) {
        Response response = get(Config.getDoormanUrl() + "/users?email=" + email);
        response.then().statusCode(200);
        return response.getBody().as(User.class);
    }

    @Override
    public UserBalance findBalanceByUserId(String id) {
        return get(Config.getDoormanUrl() + "/users/" + id + "/balance").getBody().as(UserBalance.class);
    }

    public static void main(String[] args) {
        System.out.println(new UserRepositoryImpl().findByEmail("pointsManagerTestsUser@gmail.pl"));
    }
}
