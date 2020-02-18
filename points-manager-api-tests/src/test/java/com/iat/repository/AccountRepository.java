package com.iat.repository;

import com.iat.domain.Account;

public interface AccountRepository {

    Account getAccountByUserAndBusinessType(long userId, String externalIdType);

    void setAccount(long userId, String externalIdType, int confirmed, int pending, int redeemed, int declined, int spent);

    void resetAccount(long userId, String externalIdType);
}