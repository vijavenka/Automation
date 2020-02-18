package com.iat.repository.impl;

import com.iat.Config;
import com.iat.domain.Token;
import com.iat.repository.UserTokenRepository;

import java.util.List;

import static io.restassured.RestAssured.get;
import static org.codehaus.groovy.runtime.InvokerHelper.asList;

public class UserTokenRepositoryImpl implements UserTokenRepository {
    @Override
    public List<Token> getTokens(String uuid) {
        System.out.println("\nGET: " + Config.getDoormanUrl() + "/users/" + uuid + "/tokens");
        return asList(get(Config.getDoormanUrl() + "/users/" + uuid + "/tokens").getBody().as(Token.class));

    }

}