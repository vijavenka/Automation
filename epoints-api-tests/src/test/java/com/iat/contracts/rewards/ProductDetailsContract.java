package com.iat.contracts.rewards;

public class ProductDetailsContract {

    public String getProductDetailsByIdPath(String id) {
        return "/rest/spend/" + id;
    }

    public String getRedemptionItemRelatedProductsPath(String id, String page, String pageSize) {
        String path = "/rest/spend/" + id + "/similar/";

        if (!page.equals("null"))
            path += "&page=" + page;

        if (!pageSize.equals("null"))
            path += "&pageSize=" + pageSize;

        return path.replace("/&", "?");
    }

}