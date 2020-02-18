package com.iat.domain.rewardsHistory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iat.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeliveryAddress extends AbstractDomain {

    private String country;
    private String county;
    private String city;
    private String postCode;
    private String address1;
    private String address2;
    private String name;
    private String phoneNo;
}