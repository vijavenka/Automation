package com.iat.validators.authentication;

import com.iat.repository.UserRepository;
import com.iat.repository.impl.UserRepositoryImpl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class UserAuthenticationValidator {
    private UserRepository userRepository = new UserRepositoryImpl();

    public void checkUserUuidCorrectness(String email, String uuid) {
        assertThat("UUID was not returned!", userRepository.findByEmail(email).getUuid(), is(uuid));
    }

}