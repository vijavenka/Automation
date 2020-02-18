package com.iat.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class RewardCriteria extends AbstractDomain {

    private String id;
    private String startDate;
    private String expiryDate;
    private String productId;
    private String productDescription;
    private String epointsAmount;
    private String purchaseType;
    private String partnerId;
    private String tagId;
}