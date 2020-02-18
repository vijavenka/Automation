package com.iat.domain.rewardsHistory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iat.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DoormanReward extends AbstractDomain {

    private String createdAt;
    private String title;
    private String quantity;
    private String name;
    private String address1;
    private String address2;
    private String city;
    private String country;
    private String county;
    private String postcode;
    private String region;
    private String phone;
    private String imageUrl;
    private String numPoints;
}