package com.iat.domain.transactions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iat.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction extends AbstractDomain {

    private double date;
    private String title;
    private String imageUrl;
    private Site site;
    private EarnedPoints earnedPoints;
    private int spentPoints;
    private int balance;
    private String tagName;
}