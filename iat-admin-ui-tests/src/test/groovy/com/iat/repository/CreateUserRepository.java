package com.iat.repository;

import com.iat.domain.createNewUser;

public interface CreateUserRepository {

    String createNewUser(createNewUser body, String username, String password);

    String deleteChosenUser(String userId, String username, String password);

}