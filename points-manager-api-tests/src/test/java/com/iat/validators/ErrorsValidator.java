package com.iat.validators;

import com.iat.utils.ResponseContainer;

import static com.iat.utils.matchers.CustomMatchers.hasLength;
import static org.hamcrest.Matchers.*;

public class ErrorsValidator {

    public void validateHeaderErrorResponse(ResponseContainer response, String expErrorCode, String responseMessage) {
        if (expErrorCode.equals("not_specified")) expErrorCode = null;
        if (responseMessage.equals("not_specified")) responseMessage = null;
        response.getValidatableResponse()
                .assertThat()
                .header("X-Error-Code", expErrorCode)
                .header("X-Error-Message", anyOf(
                        //when message is long we want to check only part of it
                        allOf(hasLength(greaterThanOrEqualTo(140)), containsString(responseMessage)),
                        allOf(hasLength(lessThan(140)), is(responseMessage))
                ));
    }
}
