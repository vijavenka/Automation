package com.iat.domain.orderRedemption;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iat.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product extends AbstractDomain {

    private String id;
    private String productId;
    private String seoSlug;
    private String imageUrl;
    private String title;
    private String businessId;
    private Integer quantity;
    private RedemptionItemEpoints epoints;
    private List<String> categories;
    private List<String> categoriesSeoSlugs;
    private String departmentSeoSlug;
    private float price;
    private float discountedPrice;
    private int discountedPriceInEpoints;
    private float totalDeliveryCost;
}
