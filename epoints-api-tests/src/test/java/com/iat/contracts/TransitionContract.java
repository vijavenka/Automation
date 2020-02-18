package com.iat.contracts;


public class TransitionContract {

    public String getTransitionToPath(String merchantId) {
        String path = "/transition-to?type=merchant";
        if (!merchantId.equals(""))
            path += "&merchantId=" + merchantId;
        return path;
    }

    public String getTransitionToLeadsPath(String offerId) {
        String path = "/transition-to?type=lead";
        if (!offerId.equals(""))
            path += "&offerId=" + offerId;
        return path;
    }

}