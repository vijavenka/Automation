package com.iat.contracts.deliveryAddress;

import com.iat.utils.StringConverter;

import java.util.Map;

public class UserDeliveryAddressContract {

    private StringConverter stringConverter = StringConverter.getInstance();

    public String getDeliveryAddressPath(String userId, String apiKey, String limit, String offset) {
        String path = "/users/" + userId + "/addresses";

        if (!apiKey.equals("null")) {
            path += "?apiKey=" + apiKey;

            if (!limit.equals("null"))
                path += "&limit=" + limit;
            if (!offset.equals("null"))
                path += "&offset=" + offset;
        }

        return path;
    }

    public String createDeliveryAddressRequestBody(String deliveryAddressParams) {
        Map<String, String> params = stringConverter.convertStringToHashMap(deliveryAddressParams);

        return "{\"firstName\":\"" + params.get("firstName") +
                "\", \"lastName\":\"" + params.get("lastName") +
                "\", \"house\":\"" + params.get("houseNumberOrName") +
                "\", \"street\":\"" + params.get("street") +
                "\", \"city\":\"" + params.get("townOrCity") +
                "\", \"county\":\"" + params.get("county") +
                "\", \"country\":\"" + params.get("country") +
                "\", \"postcode\":\"" + params.get("postcode") +
                "\", \"additionalInfo\":\"" + params.get("additionalInfo") +
                "\", \"phoneNo\":\"" + params.get("phoneNo") +
                "\"}";
    }
}
