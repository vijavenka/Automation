package com.iat.contracts;


public class ProductContract {

    public String productsPath() {
        return "/api/products";
    }

    public String productsByIdPath(String id) {
        return "/api/products/" + id;
    }

}