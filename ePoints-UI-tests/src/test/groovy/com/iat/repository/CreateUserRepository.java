package com.iat.repository;

import com.iat.domain.NewUser;

public interface CreateUserRepository {

    String createNewUser(NewUser body, String username, String password);

}
