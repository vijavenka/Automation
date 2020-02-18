package com.iat.repository;

import com.iat.domain.Token;
import com.iat.domain.UserBalanceDoorman;
import com.iat.domain.UserDetailsDoorman;

public interface UserRepository {
    UserBalanceDoorman findBalanceByUserId(String id);

    UserDetailsDoorman findById(String id);

    UserDetailsDoorman findByEmail(String email);

    Token findByUUID(String uuid);

    void removeUserById(String id);
}
