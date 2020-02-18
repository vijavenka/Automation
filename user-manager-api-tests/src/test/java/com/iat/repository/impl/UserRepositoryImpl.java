package com.iat.repository.impl;

import com.iat.Config;
import com.iat.domain.User;
import com.iat.domain.UserActivation;
import com.iat.domain.UserBalance;
import com.iat.repository.UserRepository;
import com.iat.utils.ResponseContainer;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static com.iat.utils.ResponseContainer.initResponseContainer;
import static io.restassured.RestAssured.delete;
import static io.restassured.RestAssured.get;

@Slf4j
public class UserRepositoryImpl implements UserRepository {

    private ValidatableResponse getUserByIdRequest(String uuid) {
        //by default db=false this mean search in dynamo db
        return getUserByIdRequest(uuid, false);
    }

    private ValidatableResponse getUserByIdRequest(String uuid, boolean db) {
        String path = Config.getIatDoormanUrl() + "/users/" + uuid;
        if (db) path += "?db=true";
        log.info("Doorman GET: {}", path);
        return get(path).then().statusCode(200);
    }

    private ValidatableResponse getUserByEmailRequest(String email) {
        String path = Config.getIatDoormanUrl() + "/users?email=" + email;
        log.info("Doorman GET: {}", path);
        return get(path).then().statusCode(200);
    }

    private ValidatableResponse getBalanceByIdRequest(String id) {
        String path = Config.getIatDoormanUrl() + "/users/" + id + "/balance" + "?db=true";
        log.info("Doorman GET: {}", path);
        return get(path).then().statusCode(200);
    }

    @Override
    public User findByEmail(String email) {
        ResponseContainer response = initResponseContainer(getUserByEmailRequest(email));
        return response.getAsObject(User.class);
    }

    @Override
    public UserActivation findActiveStateById(String id) {
        ResponseContainer response = initResponseContainer(getUserByIdRequest(id));
        return response.getAsObject(UserActivation.class);
    }

    @Override
    public ResponseContainer findDetailsById(String id) {
        return initResponseContainer(getUserByIdRequest(id));
    }

    @Override
    public UserBalance findBalanceByUserId(String id) {
        ResponseContainer response = initResponseContainer(getBalanceByIdRequest(id));
        return response.getAsObject(UserBalance.class);
    }

    @Override
    public void removeUserById(String id) {
        log.info("Doorman DELETE: " + Config.getIatDoormanUrl() + "/users/" + id);
        delete(Config.getIatDoormanUrl() + "/users/" + id).then().statusCode(200);
    }
}
