package com.iat.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class UserBalance extends AbstractDomain {

    private int confirmed;
    private int redeemed;
    private int declined;
    private int pending;
}