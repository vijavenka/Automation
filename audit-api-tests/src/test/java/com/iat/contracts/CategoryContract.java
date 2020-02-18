package com.iat.contracts;


public class CategoryContract {

    public String categoriesPath() {
        return "/api/categories";
    }

    public String categoryByIdPath(String id) {
        return "/api/categories/" + id;
    }

}