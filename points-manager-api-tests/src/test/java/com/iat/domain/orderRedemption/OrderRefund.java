package com.iat.domain.orderRedemption;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iat.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class OrderRefund extends AbstractDomain {

    private String id;
    private String activityInfo;
    private String refundDate;
}