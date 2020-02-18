package com.iat.contracts;


public class BrandContract {

    public String brandsPath() {
        return "/api/brands";
    }

    public String brandsByIdPath(String id) {
        return "/api/brands/" + id;
    }

}