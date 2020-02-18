package com.iat.repository;

import com.iat.domain.User;
import com.iat.domain.UserBalance;

public interface UserRepository {
    User findByEmail(String email);

    UserBalance findBalanceByUserId(String id);

}
