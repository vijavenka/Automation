package com.iat.domain.rewardsHistory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iat.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Reward extends AbstractDomain {

    private double date;
    private RedemptionItemInfo redemptionItemInfo;
    private int quantity;
    private int epointsSpentForOneProduct;
    private DeliveryAddress deliveryAddress;
}