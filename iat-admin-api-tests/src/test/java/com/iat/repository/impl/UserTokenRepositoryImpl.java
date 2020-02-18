package com.iat.repository.impl;

import com.iat.Config;
import com.iat.domain.Token;
import com.iat.repository.UserTokenRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static io.restassured.RestAssured.get;
import static org.codehaus.groovy.runtime.InvokerHelper.asList;

@Slf4j
public class UserTokenRepositoryImpl implements UserTokenRepository {
    @Override
    public List<Token> getTokens(String uuid) {
        log.info("GET: {}/users/{}/tokens", Config.getDoormanUrl(), uuid);
        List<Token> tokens = asList(get(Config.getDoormanUrl() + "/users/" + uuid + "/tokens").getBody().as(Token.class));
        System.out.println(tokens);
        return tokens;

    }

}