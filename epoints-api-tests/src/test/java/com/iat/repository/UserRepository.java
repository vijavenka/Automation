package com.iat.repository;

import com.iat.domain.Token;
import com.iat.domain.UserBalanceDoorman;
import com.iat.domain.UserDetails;
import com.iat.utils.ResponseContainer;

public interface UserRepository {
    UserDetails findByEmail(String email);

    UserDetails findById(String id);

    Token findByUUID(String uuid);

    UserBalanceDoorman findBalanceByUserId(String id);

    UserBalanceDoorman findBalanceByUserIdForSpecifiedBusiness(String id, String businessId);

    ResponseContainer findDetailsById(String id);

    void removeUserById(String id);

    String getUserTransactions(String userId, String status, String businessId);

    String getUserRewardsHistory(String userId, String businessId);
}
