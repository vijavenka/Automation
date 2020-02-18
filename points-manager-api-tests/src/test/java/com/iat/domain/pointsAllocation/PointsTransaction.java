package com.iat.domain.pointsAllocation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.iat.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static java.lang.Integer.parseInt;
import static org.junit.Assert.fail;

@JsonIgnoreProperties(ignoreUnknown = true)
//this class will be used for creating json body for points award of single user and bulk upload as well
@Getter
@Setter
public class PointsTransaction extends AbstractDomain {

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_ABSENT)

    private String numPoints;
    private String autoConfirm;
    private String clientId;
    private String onBehalfOfId;
    private String apiAccessKey;
    private String tag;
    private String pointsType;
    private String externalTransactionId;
    private String reasonText;
    private String sourceUri;
    private String merchantName;

    private List<String> users;
    private String allAssociatedUsers;
    private String localCurrencyCode;
    private String localCurrencyValue;
    private String conversionRate;

    private String userKey;

    @JsonIgnore
    public int getNumPointsInt() {
        try {
            return parseInt(numPoints);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            fail("numPoints failed when passing to int!");
            return -1;
        }
    }
}