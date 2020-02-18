package com.iat.domain.orderRedemption;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iat.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Order extends AbstractDomain {

    private Boolean gift;
    private String deductionURI;
    private String name;
    private String address1;
    private String address2;
    private String city;
    private String county;
    private String country;
    private String postcode;
    private String phone;
    private String zone;
    private String region;
    private List<Product> products;
}