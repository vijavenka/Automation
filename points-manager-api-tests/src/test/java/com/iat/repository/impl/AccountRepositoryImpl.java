package com.iat.repository.impl;

import com.iat.Config;
import com.iat.domain.Account;
import com.iat.repository.AccountRepository;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.post;

public class AccountRepositoryImpl implements AccountRepository {

    @Override
    public Account getAccountByUserAndBusinessType(long userId, String externalIdType) {
        System.out.println("GET: " + Config.getDoormanUrl() + "/account?userId=" + userId + "&externalId=" + externalIdType);
        get(Config.getDoormanUrl() + "/account?userId=" + userId + "&externalId=" + externalIdType).prettyPrint();
        return get(Config.getDoormanUrl() + "/account?userId=" + userId + "&externalId=" + externalIdType).as(Account.class);
    }

    @Override
    public void setAccount(long userId, String externalIdType, int confirmed, int pending, int redeemed, int declined, int spent) {
        post(Config.getDoormanUrl() + "/account?userId=" + userId + "&externalId=" + externalIdType +
                "&confirmed=" + confirmed + "&pending=" + pending + "&redeemed=" + redeemed +
                "&declined=" + declined + "&spent=" + spent);
    }

    @Override
    public void resetAccount(long userId, String externalIdType) {
        this.setAccount(userId, externalIdType, 0, 0, 0, 0, 0);
    }
}