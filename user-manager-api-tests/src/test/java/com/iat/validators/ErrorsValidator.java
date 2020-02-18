package com.iat.validators;

import com.iat.utils.ResponseContainer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

public class ErrorsValidator {

    public void validateErrorResponse(ResponseContainer response, int status, String expErrorCode, String responseMessage, boolean validateHeader) {
        assertThat("Incorrect Response Code!", response.getInt("status"), is(status));
        assertThat("Incorrect Error Code!", response.getString("error"), is(expErrorCode));
        assertThat("Incorrect Message!", response.getString("message"), containsString(responseMessage));

        if (validateHeader)
            validateHeaderErrorResponse(response, expErrorCode, responseMessage);
    }

    public void validateErrorResponse(ResponseContainer response, int status, String expErrorCode, String responseMessage) {
        validateErrorResponse(response, status, expErrorCode, responseMessage, false);
    }

    public void validateHeaderErrorResponse(ResponseContainer response, String expErrorCode, String responseMessage) {
        response.getValidatableResponse()
                .assertThat()
                .header("X-Error-Code", expErrorCode)
                .header("X-Error-Message", responseMessage);
    }

    public void validateOauthErrorResponse(ResponseContainer response, String error, String errorDescription) {
        assertThat("Incorrect error!", response.get("error"), is(error));
        assertThat("Incorrect error_description!", response.getString("error_description"), is(errorDescription));
    }
}
