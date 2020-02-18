package com.iat.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserBalance extends AbstractDomain {

    private int confirmedPoints;
    private int redeemedPoints;
    private int declinedPoints;
    private int totalPoints;
    private int pendingPoints;
}
