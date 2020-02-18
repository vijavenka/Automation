package com.iat.validators.usersManagement;

import com.iat.domain.UserBalance;
import com.iat.repository.UserRepository;
import com.iat.repository.impl.UserRepositoryImpl;
import com.iat.utils.ResponseContainer;
import com.iat.utils.ResponseHolder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UserBalanceValidator {
    private UserRepository userRepository;
    private ResponseHolder responseHolder;

    public UserBalanceValidator() {
        userRepository = new UserRepositoryImpl();
        responseHolder = ResponseHolder.getInstance();
    }

    public void checkIfBalanceValuesAreCorrect(ResponseContainer response, String userId) {
        assertThat(response.getAsObject(UserBalance.class), samePropertyValuesAs(userRepository.findBalanceByUserId(userId)));
    }

    public void checkIfBallanceWasNotReturned(String expErrorCode, String expErrorMsg) {
        ResponseContainer response = responseHolder.getResponse();
        assertThat("Incorrect Error Code!", response.getString("error"), is(expErrorCode));
        if (!expErrorCode.equals("Bad Request"))
            assertThat("Incorrect Message Code!", response.getString("message"), is(expErrorMsg));
        else
            assertThat("Incorrect Message Code!", response.getString("message"), anyOf(
                    containsString(expErrorMsg),
                    containsString("Failed to convert value of type [java.lang.String] to required type [iat.compassmassive.api.points.UserIdType]")));
    }

}