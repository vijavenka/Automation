package com.iat.contracts;

public class BasketContract {

    public String userBasketPath(String uuid) {
        return "/rest/users/" + uuid + "/basket";
    }

}