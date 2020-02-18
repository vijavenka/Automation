package com.iat.contracts;


public class ChainContract {

    public String createNewChain() {
        return "/api/chains";
    }

    public String deleteChainById(String chainId) {
        return "/api/chains/" + chainId;
    }

    public String getChainById(String chainId) {
        return "/api/chains/" + chainId;
    }

    public String getChainsPath(String params) {
        String[] param = params.split(";");

        String path = "/api/chains/";

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

}