package com.iat.contracts;


public class SuppliersContract {

    public String suppliersPath() {
        return "/api/suppliers?size=1000";
    }

    public String suppliersByIdPath(String id) {
        return "/api/suppliers/" + id;
    }

}