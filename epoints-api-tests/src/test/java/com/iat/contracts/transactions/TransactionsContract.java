package com.iat.contracts.transactions;

public class TransactionsContract {

    public String getTransactionsPath(String id, IdType idType) {
        return "http://test-proxy-qa-0.iatlimited.com:8913/transactions/rest/user/" + id + "/?idType=" + idType.toString();
    }
}