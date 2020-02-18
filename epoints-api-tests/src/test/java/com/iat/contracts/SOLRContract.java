package com.iat.contracts;

import com.iat.Config;

public class SOLRContract {

    public String dealsByQueryPath(String query) {
        return Config.getSolrUrl() + "offerings_shard1_replica1/select?wt=json&indent=true&fq=b_active:true&" + query;
    }

    public String vouchersByIdQueryPath(String voucherId) {
        return Config.getSolrUrl() + "vouchers_shard1_replica1/select?wt=json&indent=true&fq=id:\"" + voucherId + "\"";
    }

}