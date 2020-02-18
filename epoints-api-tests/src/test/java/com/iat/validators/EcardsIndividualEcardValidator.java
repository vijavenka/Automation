package com.iat.validators;

import com.iat.utils.ResponseContainer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

public class EcardsIndividualEcardValidator {

    public void checkIndividualEcardDataCorectness(ResponseContainer responseTemp, ResponseContainer response) {
        assertThat(response.getString("reasonId"), is(responseTemp.getString("ecards[0].reasonId")));
        assertThat(response.getString("templateUrl"), is(responseTemp.getString("ecards[0].templateUrl")));
        assertThat(response.getString("message"), is(responseTemp.getString("ecards[0].message")));
        assertThat(response.getInt("pointsValue"), is(responseTemp.getInt("ecards[0].pointsValue")));
        assertThat(response.getString("id"), is(responseTemp.getString("ecards[0].id")));
        assertThat(response.getString("createdAt"), is(responseTemp.getString("ecards[0].createdAt")));

        assertThat(responseTemp.getString("ecards[0].from.firstName"), containsString(response.getString("from.firstName")));
        assertThat(response.getString("from.email"), is(responseTemp.getString("ecards[0].from.email")));
        assertThat(response.getString("receiver.email"), is(responseTemp.getString("ecards[0].receiver.email")));
    }
}
