package com.iat.repository;

import com.iat.domain.passwordRemind;

public interface UserAccountManagementRepository {

    String remindUserPassword(passwordRemind body);

}