package com.iat.domain.transactions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iat.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClickoutDb extends AbstractDomain {

    private String id;
    private String affiliateNetwork;
    private String clickoutStatus;
    private String clickoutType;
    private String commisionAmount;
    private String commisionCurrency;
    private String confirmedDate;
    private String declineDate;
    private Integer epoints;
    private String merchant;
    private String merchantId;
    private String networkTransactionDate;
    private String pointsId;
    private String reference;
    private String saleAmount;
    private String transactionId;
    private String updateStatusDate;
    private String userId;
    private String zone;
    private String leads;
    private String daysToConfirm;
}