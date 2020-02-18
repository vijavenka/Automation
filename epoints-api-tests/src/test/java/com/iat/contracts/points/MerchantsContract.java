package com.iat.contracts.points;

public class MerchantsContract {

    public String getMerchantDetailsPath(String merchantId) {
        return "/rest/merchants/" + merchantId;
    }

    public String getPromotedMerchantDetailsPath(String ids) {
        String path = "/rest/merchants/list";

        if (!ids.equals(""))
            path += "?ids=" + ids;
        return path;
    }
}