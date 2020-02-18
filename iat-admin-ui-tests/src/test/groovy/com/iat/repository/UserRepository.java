package com.iat.repository;

import com.iat.domain.User;

public interface UserRepository {
    User findByEmail(String email);

    void deletUserFromDynamoAndPointsManager(String uuid);
}
