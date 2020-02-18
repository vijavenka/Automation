package com.iat.repository;

import com.iat.domain.User;

public interface UserRepository {
    User findUserByUserId(String id);

    User findByEmail(String email);

    void deleteUserFromDynamoAndPointsManager(String uuid);

    String returnAllUserData(String email);
}
