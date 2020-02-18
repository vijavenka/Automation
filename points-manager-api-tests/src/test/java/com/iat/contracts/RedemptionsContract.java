package com.iat.contracts;

import com.iat.Config;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

public class RedemptionsContract {


    public String getRedemptionsPath(String apiKey, String ascending, String orderField, String offset, String limit, String startDate, String endDate, String searchField, String keyword, String withAddictivityInfo) {

        String path = "/redemptions";

        if (!apiKey.equals("null")) {
            path += "?apiKey=" + apiKey;

            if (!startDate.equals("null"))
                path += "&startDate=" + startDate;
            if (!endDate.equals("null"))
                path += "&endDate=" + endDate;
            if (!searchField.equals("null"))
                path += "&searchField=" + searchField;
            if (!keyword.equals("null"))
                path += "&keyword=" + keyword;
            if (!withAddictivityInfo.equals("null"))
                path += "&withAddictivityInfo=" + withAddictivityInfo;
            if (!ascending.equals("null"))
                path += "&ascending=" + ascending;
            if (!orderField.equals("null"))
                path += "&orderField=" + orderField;
            if (!offset.equals("null"))
                path += "&offset=" + offset;
            if (!limit.equals("null"))
                path += "&limit=" + limit;
        } else {
            if (!startDate.equals("null"))
                path += "?startDate=" + startDate;
            if (!endDate.equals("null"))
                path += "&endDate=" + endDate;
            if (!searchField.equals("null"))
                path += "&searchField=" + searchField;
            if (!keyword.equals("null"))
                path += "&keyword=" + keyword;
            if (!withAddictivityInfo.equals("null"))
                path += "&withAddictivityInfo=" + withAddictivityInfo;
            if (!ascending.equals("null"))
                path += "&ascending=" + ascending;
            if (!orderField.equals("null"))
                path += "&orderField=" + orderField;
            if (!offset.equals("null"))
                path += "&offset=" + offset;
            if (!limit.equals("null"))
                path += "&limit=" + limit;
        }
        return path;
    }

    public String getRedemptionsByIdPath(String apiKey, String redemptionId) {

        String path = "/redemptions/" + redemptionId;

        if (!apiKey.equals("null"))
            path += "?apiKey=" + apiKey;

        return path;
    }


    public String createRedemptionUpdateRequestBody(String fulfill) {
        List<String> requestParameters = asList(fulfill.split(","));
        List<String> parameterLabels = asList("fulfillMerchantName", "fulfillPrice", "fulfillDiffPrice", "fulfillDiffPercentage", "fulfillInviceNumber", "fulfillDate", "fulfillStatus");
        assertThat("Number of parameters and labels is not equal!", requestParameters, hasSize(parameterLabels.size()));
        String jsonRequest = buildJsonRequestBody(parameterLabels, requestParameters);
        return "{" + jsonRequest + "}";
    }


    public String getRedemptionsRefundPath(String apiKey) {

        String path = "/redemption/refund";

        if (!apiKey.equals("null"))
            path += "?apiKey=" + apiKey.replace("envDepends", Config.getPartnerAccessKey());

        return path;
    }


    private String buildJsonRequestBody(List<String> parameterLabels, List<String> parameterValues) {

        String json = "";
        int i = 0;
        for (String parameterValue : parameterValues) {
            if (parameterValue.equals("null")) continue;
            json += "\"" + parameterLabels.get(i++) + "\":\"" + parameterValue + "\",";
        }
        return json.substring(0, json.lastIndexOf(","));
    }
}
