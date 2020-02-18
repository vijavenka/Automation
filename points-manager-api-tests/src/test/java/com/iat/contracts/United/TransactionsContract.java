package com.iat.contracts.United;

public class TransactionsContract {

    public String getTransactionsProcessingPath(String dataUrl, String externalId, String transactionDate) {
        String path = "/triggers/process-transactions/";

        if (!externalId.equals("null"))
            path += "&externalId=" + externalId;
        if (!dataUrl.equals("null"))
            path += "&dataUrl=" + dataUrl;

        if (!transactionDate.equals("null"))
            path += "&transactionDate=" + transactionDate;

        return path.replace("/&", "?");
    }

}