package com.iat.validators.paymentsManagement;

import com.iat.utils.ResponseContainer;

import java.util.List;

import static java.lang.Float.parseFloat;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PaymentsValidator {

    public void checkIfReturnedPaymentDetailsAreAsExpected(ResponseContainer response, String jsonParameters) {
        //check parameters set during new user creation
        List<String> parameterLabels = asList("guid", "paymentId", "status", "userId", "idType", "moneyValue", "epoints", "tax", "fee", "total", "payerFirstname", "payerLastname",
                "payerEmail", "currency");
        List<String> parameterValues = asList(jsonParameters.split(","));
        int i = -1;
        for (String parameterLabel : parameterLabels) {
            i++;
            if (parameterLabel.equals("null")) continue;
            if (asList("moneyValue", "tax", "fee", "total").contains(parameterLabel))
                assertThat(parameterLabel + " are not equal!", response.getFloat(parameterLabel), is(parseFloat(parameterValues.get(i))));
            else
                assertThat(parameterLabel + " are not equal!", response.getString(parameterLabel), is(parameterValues.get(i)));
        }
    }

}