package com.iat.contracts;


public class RetailerContract {

    public String retailerPath() {
        return "/api/retailers";
    }

    public String retailerPath(String params) {
        String[] param = params.split(";");

        String path = "/api/retailers/";

        if (param.length > 1) {
            String page = param[0];
            String size = param[1];
            String sort = param[2];

            if (!page.equals("null"))
                path += "&page=" + page;

            if (!size.equals("null"))
                path += "&size=" + size;

            if (!sort.equals("null"))
                path += "&sort=" + sort;

        }
        return path.replace("/&", "?");
    }

    public String retailerByIdPath(String id) {
        return "/api/retailers/" + id;
    }

    public String deleRetailerFromChain(String retailerId, String chainId) {
        return "/api/retailers/" + retailerId + "/remove/" + chainId;
    }

    public String retailerTakeOverStore(String retailerId, String storeId) {
        return "/api/retailers/" + retailerId + "/take-over-store/" + storeId;
    }

}