package com.iat.repository;

import com.iat.domain.UserToken;

public interface UserTokenRepository {
    UserToken getToken(String uuid);
}
