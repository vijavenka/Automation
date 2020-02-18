package com.iat.contracts.paymentsManagement;

import com.iat.utils.HelpFunctions;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

public class PaymentsContract {

    public String addPayment(String apiKey) {
        return "/payments?apiKey=" + apiKey;
    }

    public String getUpdatePayment(String apiKey, String guid) {
        return "/payments/" + guid + "?apiKey=" + apiKey;
    }

    public String returnRandomCreateUserRequestBody() {
        HelpFunctions helpFunctions = new HelpFunctions();
        int randomValue = helpFunctions.returnRandomValue(10000);
        return "uuid" + randomValue + ",paymentId" + randomValue + ",status" + randomValue + ",611fb78b-a9b6-4670-b4d4-0bb728f74be3,UUID," + randomValue + "," + randomValue * 200 + "," +
                "" + randomValue * 0.2 + "," + randomValue * 0.1 + "," + (randomValue + (randomValue * 0.3)) + ",payerFirstName" + randomValue + ",payerLastName" + randomValue + ",pointsManagerTestsUser@gmail.pl,currency" + randomValue;
    }

    public String returnAddUpdatePaymentBody(String jsonParameters) {
        List<String> requestParameters = asList(jsonParameters.split(","));
        List<String> parameterLabels = asList("guid", "paymentId", "status", "userId", "idType", "moneyValue", "epoints", "tax", "fee", "total", "payerFirstname", "payerLastname",
                "payerEmail", "currency");
        String jsonRequest = "";
        assertThat("Number of parameters and labels is not equal!", requestParameters, hasSize(parameterLabels.size()));
        return buildJsonRequestBody(jsonRequest, parameterLabels, requestParameters);
    }

    private String buildJsonRequestBody(String jsonRequest, List<String> parameterLabels, List<String> parameterValues) {
        String json = jsonRequest;

        int i = -1;
        for (String parameterLabel : parameterLabels) {
            i++;
            if (parameterValues.get(i).equals("null"))
                continue;
            if (asList("moneyValue", "tax", "fee", "total", "epoints", "createdAt").contains(parameterLabel))
                json += "\"" + parameterLabel + "\":" + parameterValues.get(i) + ",";
            else
                json += "\"" + parameterLabel + "\":\"" + parameterValues.get(i) + "\",";
        }
        if (json.length() > 1) {
            System.out.println("{" + json.substring(0, json.lastIndexOf(",")) + "}");
            return "{" + json.substring(0, json.lastIndexOf(",")) + "}";
        } else
            return "{}";
    }

}