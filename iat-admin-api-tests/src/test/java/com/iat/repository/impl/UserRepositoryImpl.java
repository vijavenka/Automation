package com.iat.repository.impl;

import com.iat.Config;
import com.iat.domain.Token;
import com.iat.domain.UserBalanceDoorman;
import com.iat.domain.UserDetailsDoorman;
import com.iat.repository.UserRepository;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.delete;
import static io.restassured.RestAssured.get;

@Slf4j
public class UserRepositoryImpl implements UserRepository {


    public UserBalanceDoorman findBalanceByUserId(String id) {
        log.info("{}/users/{}/balance?db={}", Config.getDoormanUrl(), id, "true");
        return get(Config.getDoormanUrl() + "/users/" + id + "/balance?db=true").getBody().as(UserBalanceDoorman.class);
    }

    public UserDetailsDoorman findByEmail(String email) {
        log.info("Doorman GET: {}/users?email={}", Config.getDoormanUrl(), email);
        Response response = get(Config.getDoormanUrl() + "/users?email=" + email);
        return response.getBody().as(UserDetailsDoorman.class);
    }

    @Override
    public UserDetailsDoorman findById(String uuid) {
        log.info("Doorman GET: {}/users/{}", Config.getDoormanUrl(), uuid);
        return get(Config.getDoormanUrl() + "/users/" + uuid + "").getBody().as(UserDetailsDoorman.class);
    }

    @Override
    public Token findByUUID(String uuid) {
        log.info("Doorman get: {}/users/{}/tokens", Config.getDoormanUrl(), uuid);
        Response response = get(Config.getDoormanUrl() + "/users/" + uuid + "/tokens");
        response.then().statusCode(200);
        return response.getBody().as(Token.class);
    }

    @Override
    public void removeUserById(String id) {
        log.info("Doorman DELETE: {}/users/{}", Config.getDoormanUrl(), id);
        delete(Config.getDoormanUrl() + "/users/" + id).then().statusCode(200);
    }
}
