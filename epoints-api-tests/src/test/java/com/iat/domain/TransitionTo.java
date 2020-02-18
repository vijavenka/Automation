package com.iat.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransitionTo extends AbstractDomain {

    private String merchantId;
    private String merchantName;
    private String multiplier;
    private String shopName;
    private String shopLogo;
    private String redirectUrl;
    private String currency;
    private String ratio;
    private String maximumCommission;
    private String leadGenerator;
}
