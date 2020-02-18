package com.iat.repository.impl;

import com.iat.Config;
import com.iat.domain.ProductId;
import com.iat.repository.ProductsRepository;

import java.util.List;

import static io.restassured.RestAssured.get;
import static java.util.Arrays.asList;

public class ProductRepositoryImpl implements ProductsRepository {

    @Override
    public List<ProductId> getProductsByOrderId(String orderId) {
        return asList(get(Config.getDoormanUrl() + "/productsIds?orderId=" + orderId).getBody().as(ProductId[].class));
    }

}