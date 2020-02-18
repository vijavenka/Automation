package com.iat.domain.purchase;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iat.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BuyEpoints extends AbstractDomain {

    private int epoints;
    private String moneyValue;
}