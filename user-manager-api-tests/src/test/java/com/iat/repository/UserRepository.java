package com.iat.repository;

import com.iat.domain.User;
import com.iat.domain.UserActivation;
import com.iat.domain.UserBalance;
import com.iat.utils.ResponseContainer;

public interface UserRepository {
    User findByEmail(String email);

    UserActivation findActiveStateById(String id);

    ResponseContainer findDetailsById(String id);

    UserBalance findBalanceByUserId(String id);

    void removeUserById(String id);
}
