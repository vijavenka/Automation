package com.iat.contracts;


public class AuditsContract {

    public String auditsPath() {
        return "/api/audits";
    }

    public String auditsByIdPath(String id) {
        return "/api/audits/" + id;
    }

}