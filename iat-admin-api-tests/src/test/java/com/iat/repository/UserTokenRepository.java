package com.iat.repository;

import com.iat.domain.Token;

import java.util.List;

public interface UserTokenRepository {
    List<Token> getTokens(String uuid);
}