package com.iat.domain.pointsAllocation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.iat.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class PointsTransactionUpdate extends AbstractDomain {

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_ABSENT)

    private String apiAccessKey;
    private String clientId;
    private String onBehalfOfId;
    private String externalTransactionId;
    private String pointsType;
    private String reasonText;
    private String sourceUri;
    private String userId;
    private String userIdType;
}