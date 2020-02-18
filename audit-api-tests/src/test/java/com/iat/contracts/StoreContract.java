package com.iat.contracts;


public class StoreContract {

    public String searchStores() {
        return "GET /api/_search/stores";
    }

    public String getStoresPath(String params) {
        String[] param = params.split(";");

        String path = "/api/stores/";

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

    public String getStoresPath() {
        return "/api/stores/";
    }

    public String getStoreByIdPath(String storeId) {
        return "/api/stores/" + storeId;
    }

    public String getStoresBulkUploadRetailersPath(String partnerShortName) {
        return "/api/stores/upload-retailers/partner/" + partnerShortName;
    }

    public String getStoresBulkUploadStoresPath(String partnerShortName) {
        return "/api/stores/upload-stores/partner/" + partnerShortName;
    }

}