package com.iat.domain.EcardsConfig;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iat.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class PointsSharing extends AbstractDomain {

    private String userSharePointsRange;
    private String managerSharePointsRange;
    private Boolean sharePointsWithManager;
    private String approvalProcess;
    private Integer approvalThreshold;
    private Boolean allowGlobalPassword;
    private Boolean globalPasswordSet;
    private String globalPassword;
}