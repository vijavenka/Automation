package com.iat.contract;

public class TransactionSearchContract {

    public String getTransactionSearch(String network, String networkTransactionId, String userId, Long startLong, Long endLong, String orderBy, boolean ascendingBool, int pageInt, int pageSizeInt) {

        String path = "/search/transaction/network/{network}/networkTransactionId/{networkTransactionId}/userId/"
                + "{userId}/start/{start}/end/{end}/orderBy/{orderBy}/ascending/{ascending}/page/{page}/size/{pageSize}";

        String start = startLong.toString();
        String end = endLong.toString();
        String ascending = ascendingBool ? "true" : "false";
        String page = Integer.toString(pageInt);
        String pageSize = Integer.toString(pageSizeInt);

        path = path.replace("{network}", network).replace("{networkTransactionId}", networkTransactionId)
                .replace("{userId}", userId).replace("{start}", start)
                .replace("{end}", end).replace("{orderBy}", orderBy)
                .replace("{ascending}", ascending).replace("{page}", page)
                .replace("{pageSize}", pageSize);

        return path;
    }
}