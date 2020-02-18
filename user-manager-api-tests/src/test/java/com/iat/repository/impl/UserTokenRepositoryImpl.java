package com.iat.repository.impl;

import com.iat.Config;
import com.iat.domain.UserToken;
import com.iat.repository.UserTokenRepository;
import com.iat.utils.ResponseContainer;
import io.restassured.response.ValidatableResponse;

import static com.iat.utils.ResponseContainer.initResponseContainer;
import static io.restassured.RestAssured.get;

public class UserTokenRepositoryImpl implements UserTokenRepository {

    private ValidatableResponse getTokensRequest(String uuid) {
        String path = Config.getIatDoormanUrl() + "/users/" + uuid + "/tokens";
        return get(path).then().statusCode(200);
    }

    @Override
    public UserToken getToken(String uuid) {
        ResponseContainer response = initResponseContainer(getTokensRequest(uuid));
        return response.getAsObject(UserToken.class);
    }
}
