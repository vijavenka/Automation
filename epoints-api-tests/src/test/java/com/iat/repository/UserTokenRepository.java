package com.iat.repository;

import com.iat.domain.UserToken;

import java.util.List;

public interface UserTokenRepository {
    List<UserToken> getTokens(String uuid);
}