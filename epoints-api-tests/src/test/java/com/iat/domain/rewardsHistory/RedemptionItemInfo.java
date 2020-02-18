package com.iat.domain.rewardsHistory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iat.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RedemptionItemInfo extends AbstractDomain {

    private String id;
    private String title;
    private Epoints epoints;
    private String imageUrl;
    private String thumbnailUrl;
    private Boolean active;
    private String businessId;
}