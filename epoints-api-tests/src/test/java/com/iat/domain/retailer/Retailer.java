package com.iat.domain.retailer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iat.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Retailer extends AbstractDomain {

    private String id;
    private String name;
    private String imageUrl;
    private String description;
    private String siteUrl;
    private int epointsMultiplier;
    private String merchantDomain;
    private String networkCode;
    private String zone;
    private int maximumCommission;
    private int daysToConfirmCommission;
    private boolean leadGenerator;
    private String seoTitle;
    private int vouchersCount;
    private boolean favourite;
    private String termsAndConditions;
}
