package com.iat.domain.purchase;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iat.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BuyEpointsDetails extends AbstractDomain {

    private String redirectUrl;
    private int moneyValue;
    private int epoints;
    private double tax;
    private double fee;
    private double total;
}