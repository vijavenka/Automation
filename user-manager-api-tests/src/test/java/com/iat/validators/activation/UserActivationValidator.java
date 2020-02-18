package com.iat.validators.activation;

import com.iat.repository.UserRepository;
import com.iat.repository.impl.UserRepositoryImpl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class UserActivationValidator {

    private final UserRepository userRepository = new UserRepositoryImpl();

    public void checkIfAccountActiveStateIsValid(String userEmail, String activationStatus) {
        if (activationStatus.equals("1") || activationStatus.equals("0"))
            activationStatus = activationStatus.equals("1") ? "true" : "false";
        assertThat(userRepository.findActiveStateById(userRepository.findByEmail(userEmail).getUuid()).getActive(), is(activationStatus.equals("true")));
    }
}
