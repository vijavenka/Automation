package com.iat.repository;

import com.iat.domain.ProductId;

import java.util.List;

public interface ProductsRepository {
    List<ProductId> getProductsByOrderId(String orderId);

}