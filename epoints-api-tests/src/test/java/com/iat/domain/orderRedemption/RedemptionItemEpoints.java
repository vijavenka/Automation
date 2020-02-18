package com.iat.domain.orderRedemption;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.iat.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RedemptionItemEpoints extends AbstractDomain {

    @JsonProperty("UK")
    private Integer uk;
    @JsonProperty("IE")
    private Integer ie;
}
