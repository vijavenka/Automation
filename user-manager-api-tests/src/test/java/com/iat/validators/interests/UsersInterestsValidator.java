package com.iat.validators.interests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;

public class UsersInterestsValidator {

    public void checkIfMerchantWasReturnedOnInterestsList(String response, String merchantId, boolean shouldBeReturned) {
        if (shouldBeReturned)
            assertThat("Returned merchant list does not contain added merchant id", response, containsString(merchantId));
        else
            assertThat("Returned merchant list contains added merchant id", response, not(containsString(merchantId)));
    }
}