package com.iat.contracts.epointsContract;

public class EpointsContract {

    public String getEpointsRedemptionItemsList() {
        return "/rest/search/redemptionitems?filters=&page=0&pageSize=4&sort=i_epointsToPurchase|asc";
    }

    public String getEpointsRedemptionSingleItem(String seoSlug, String productId) {
        return "/rest/spend/" + seoSlug + "/" + productId;
    }

}