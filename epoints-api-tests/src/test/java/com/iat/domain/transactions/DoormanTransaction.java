package com.iat.domain.transactions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iat.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DoormanTransaction extends AbstractDomain {

    private int delta;
    private String status;
    private int balance;
    private int tagId;
}