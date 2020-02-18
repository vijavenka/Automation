package com.iat.repository.impl;

import com.iat.Config;
import com.iat.domain.Token;
import com.iat.domain.UserBalanceDoorman;
import com.iat.domain.UserDetails;
import com.iat.repository.UserRepository;
import com.iat.utils.ResponseContainer;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.delete;
import static io.restassured.RestAssured.get;

@Slf4j
public class UserRepositoryImpl implements UserRepository {

    private Response getUserById(String uuid) {
        //by default db=false this mean search in dynamo db
        log.info("GET: {}/users/{}", Config.getDoormanUrl(), uuid);
        return get(Config.getDoormanUrl() + "/users/" + uuid);
    }

    public UserDetails findByEmail(String email) {
        log.info("Doorman get: {}/users?email={}", Config.getDoormanUrl(), email);
        ValidatableResponse validatableResponse = get(Config.getDoormanUrl() + "/users?email=" + email)
                .then()
                .statusCode(200);
        return validatableResponse.extract().response().as(UserDetails.class);
    }

    @Override
    public UserDetails findById(String uuid) {
        log.info("Doorman GET: {}/users/{}", Config.getDoormanUrl(), uuid);
        return get(Config.getDoormanUrl() + "/users/" + uuid).getBody().as(UserDetails.class);
    }

    @Override
    public Token findByUUID(String uuid) {
        log.info("Doorman get: {}/users/{}/tokens", Config.getDoormanUrl(), uuid);
        ValidatableResponse validatableResponse = get(Config.getDoormanUrl() + "/users/" + uuid + "/tokens")
                .then()
                .statusCode(200);
        return validatableResponse.extract().response().as(Token.class);
    }

    @Override
    public UserBalanceDoorman findBalanceByUserId(String id) {
        return get(Config.getDoormanUrl() + "/users/" + id + "/balance" + "?db=true").getBody().as(UserBalanceDoorman.class);
    }

    @Override
    public UserBalanceDoorman findBalanceByUserIdForSpecifiedBusiness(String id, String businessId) {
        return get(Config.getDoormanUrl() + "/users/" + id + "/balance/" + businessId + "?db=true").getBody().as(UserBalanceDoorman.class);
    }

    @Override
    public ResponseContainer findDetailsById(String id) {
        ResponseContainer response = new ResponseContainer(
                getUserById(id)
                        .then()
                        .statusCode(200)
        );
        response.prettyPrint();
        return response;
    }

    @Override
    public void removeUserById(String id) {
        log.info("Doorman DELETE: {}/users/{}", Config.getDoormanUrl(), id);
        delete(Config.getDoormanUrl() + "/users/" + id).then().statusCode(200);
    }

    @Override
    public String getUserTransactions(String userId, String status, String businessId) {
        log.info("Doorman get: {}/users/transactions?userId={}&status={}&businessId={}", Config.getDoormanUrl(), userId, status, businessId);
        return get(Config.getDoormanUrl() + "/user/transactions?userId=" + userId + "&status=" + status + "&businessId=" + businessId).getBody().asString();
    }

    @Override
    public String getUserRewardsHistory(String userId, String businessId) {
        log.info("Doorman get: {}/users/rewardsHistory?userId={}&businessId={}", Config.getDoormanUrl(), userId, businessId);
        return get(Config.getDoormanUrl() + "/user/rewardsHistory?userId=" + userId + "&businessId=" + businessId).getBody().asString();
    }

}