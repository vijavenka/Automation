package com.iat.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class Token {

    private Boolean active
    private String tokenType
    private String token
    private String userId

    Boolean getActive() {
        return active
    }

    void setActive(Boolean active) {
        this.active = active
    }

    String getTokenType() {
        return tokenType
    }

    void setTokenType(String tokenType) {
        this.tokenType = tokenType
    }

    void setToken(String token) {
        this.token = token
    }

    String getToken() {
        return token
    }

    String getUserId() {
        return userId
    }

    void setUserId(String userId) {
        this.userId = userId
    }

    @Override
    String toString() {
        return "token: " + token
    }
}