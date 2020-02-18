package com.iat.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Account extends AbstractDomain {

    private Integer confirmed;
    private Integer pending;
    private Integer redeemed;
    private Integer declined;
    private Integer spent;
}