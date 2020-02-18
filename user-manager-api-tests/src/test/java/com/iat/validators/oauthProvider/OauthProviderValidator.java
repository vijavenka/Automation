package com.iat.validators.oauthProvider;

import com.iat.utils.ResponseContainer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;

public class OauthProviderValidator {

    public void checkIfOauthResponsesDifferOnlyInExpiryTime(ResponseContainer oauthResponse, ResponseContainer oauthResponseWithDelay, int delay) {
        assertThat("Different access_token between responses", oauthResponse.getString("access_token"), is(oauthResponseWithDelay.getString("access_token")));
        assertThat("Different token_type between responses", oauthResponse.getString("token_type"), is(oauthResponseWithDelay.getString("token_type")));
        assertThat("Different refresh_token between responses", oauthResponse.getString("refresh_token"), is(oauthResponseWithDelay.getString("refresh_token")));
        assertThat("Different scope between responses", oauthResponse.getString("scope"), is(oauthResponseWithDelay.getString("scope")));
        assertThat("Different uuid between responses", oauthResponse.getDouble("expires_in"), is(closeTo(oauthResponseWithDelay.getDouble("expires_in") + delay, 1)));
    }
}