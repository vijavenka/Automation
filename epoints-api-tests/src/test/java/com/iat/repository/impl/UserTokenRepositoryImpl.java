package com.iat.repository.impl;

import com.iat.Config;
import com.iat.domain.UserToken;
import com.iat.repository.UserTokenRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static io.restassured.RestAssured.get;
import static java.util.Collections.singletonList;

@Slf4j
public class UserTokenRepositoryImpl implements UserTokenRepository {
    @Override
    public List<UserToken> getTokens(String uuid) {
        log.info("GET: {}/users/{}/tokens", Config.getDoormanUrl(), uuid);
        return singletonList(get(Config.getDoormanUrl() + "/users/" + uuid + "/tokens").getBody().as(UserToken.class));
    }

}