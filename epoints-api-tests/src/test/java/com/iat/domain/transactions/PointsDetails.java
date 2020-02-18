package com.iat.domain.transactions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iat.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PointsDetails extends AbstractDomain {

    private String tag;
    private int numPoints;
    private String reasonText;
    private String pointsType;
    private String apiAccessKey;
    private String clientId;
    private String externalTransactionId;

    private PointsDetails(String tag, int numPoints, String reasonText, String pointsType, String apiAccessKey, String clientId, String externalTransactionId) {
        this.setTag(tag);
        this.setNumPoints(numPoints);
        this.setReasonText(reasonText);
        //pointsType should be populated but for tag "3radical" the value is irrelevant
        this.setPointsType(pointsType);
        this.setApiAccessKey(apiAccessKey);
        this.setClientId(clientId);
        this.setExternalTransactionId(externalTransactionId);
    }

    public PointsDetails(int numPoints, String pointsType, String externalTransactionId) {
        this("roulette", numPoints, "roulette reason " + externalTransactionId, pointsType, "2rQX5b3ePFJmaidQCu3u9Guh8", "3radical", externalTransactionId);
    }
}