package com.iat.domain.orderRedemption;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iat.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Product extends AbstractDomain {

    private String productId;
    private String imageUrl;
    private String title;
    private Integer numPoints;
    private Integer quantity;
    private String merchantName;
    private String merchantId;
    private String department;
    private String brand;
    private List<String> categories;
    private Float localCurrencyValue;
    private String currency;
    private Integer conversionRate;
}