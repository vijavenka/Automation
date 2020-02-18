package com.iat.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class EcardsSettingsPointsSharing extends AbstractDomain {

    private String userSharePointsRange;
    private String managerSharePointsRange;
    private String sharePointsWithManager;
    private String approvalProcess;

    public EcardsSettingsPointsSharing() {
    }

    public EcardsSettingsPointsSharing(String userSharePointsRange,
                                       String managerSharePointsRange,
                                       String sharePointsWithManager,
                                       String approvalProcess) {

        this.setUserSharePointsRange(userSharePointsRange);
        this.setManagerSharePointsRange(managerSharePointsRange);
        this.setSharePointsWithManager(sharePointsWithManager);
        this.setApprovalProcess(approvalProcess);
    }
}
