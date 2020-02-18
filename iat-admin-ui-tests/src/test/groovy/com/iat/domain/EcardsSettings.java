package com.iat.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class EcardsSettings extends AbstractDomain {

    private String userSharePointsRange;
    private String managerSharePointsRange;
    private String sharePointsWithManager;
    private String approvalProcess;
    private String approvalThreshold;
    private String allowGlobalPassword;
    private String globalPassword;
    private String globalPasswordSet;

    public EcardsSettings(String userSharePointsRange, String managerSharePointsRange, String sharePointsWithManager,
                          String approvalProcess, String approvalThreshold, String allowGlobalPassword, String globalPassword, String globalPasswordSet) {

        this.setUserSharePointsRange(userSharePointsRange);
        this.setManagerSharePointsRange(managerSharePointsRange);
        this.setSharePointsWithManager(sharePointsWithManager);
        this.setApprovalProcess(approvalProcess);
        this.setApprovalThreshold(approvalThreshold);
        this.setAllowGlobalPassword(allowGlobalPassword);
        this.setGlobalPassword(globalPassword);
        this.setGlobalPasswordSet(globalPasswordSet);
    }
}