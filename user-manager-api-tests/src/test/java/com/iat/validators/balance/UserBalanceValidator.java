package com.iat.validators.balance;

import com.iat.domain.UserBalance;
import com.iat.repository.impl.UserRepositoryImpl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;

public class UserBalanceValidator {
    public void checkIfBalanceValuesAreCorrectById(UserBalance response, String id) {
        UserBalance userBalance = new UserRepositoryImpl().findBalanceByUserId(id);
        assertThat(response, samePropertyValuesAs(userBalance));
    }
}