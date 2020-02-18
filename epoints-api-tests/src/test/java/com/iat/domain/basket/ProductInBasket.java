package com.iat.domain.basket;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iat.domain.AbstractDomain;
import com.iat.domain.rewardsHistory.Epoints;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductInBasket extends AbstractDomain {

    private String id;
    private String productId;
    private String businessId;
    private String seoSlug;
    private String imageUrl;
    private String title;
    private int quantity;
    private Epoints epoints;
    private List<String> categories;
    private List<String> categoriesSeoSlugs;
    private String departmentSeoSlug;

    public ProductInBasket() {
    }

    public ProductInBasket(String productId, int quantity) {
        this.setProductId(productId);
        this.setQuantity(quantity);
    }
}