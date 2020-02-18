package com.iat.domain.rewardsHistory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.iat.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Epoints extends AbstractDomain {

    @JsonProperty("UK")
    private int uk;
    @JsonProperty("IE")
    private int ie;
}