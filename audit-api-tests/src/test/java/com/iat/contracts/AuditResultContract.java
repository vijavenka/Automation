package com.iat.contracts;


public class AuditResultContract {

    public String getAuditResultPath(String chainId, String auditId) {
        return "/api/audit-result/chain/" + chainId + "/audit/" + auditId;
    }

}