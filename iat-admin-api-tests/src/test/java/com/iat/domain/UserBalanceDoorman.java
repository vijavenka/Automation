package com.iat.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class UserBalanceDoorman extends AbstractDomain {

    private long confirmed;
    private long redeemed;
    private long declined;
    private long spent;
    private long pending;
}