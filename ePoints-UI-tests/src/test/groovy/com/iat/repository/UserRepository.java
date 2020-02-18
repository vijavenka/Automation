package com.iat.repository;

import com.iat.domain.User;
import com.iat.domain.UserBalance;

public interface UserRepository {
    UserBalance findBalanceByUserId(String id);

    User findByEmail(String email);

    String getTokenValueByUUID(String uuid);

    void deletUserFromDynamoAndPointsManager(String uuid);

}
