package com.iat.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper

@JsonIgnoreProperties(ignoreUnknown = true)
class EcardsSettings {

    private String userSharePointsRange
    private String managerSharePointsRange
    private String sharePointsWithManager
    private String approvalProcess

    String getUserSharePointsRange() {
        return userSharePointsRange
    }

    void setUserSharePointsRange(String userSharePointsRange) {
        this.userSharePointsRange = userSharePointsRange
    }

    String getManagerSharePointsRange() {
        return managerSharePointsRange
    }

    void setManagerSharePointsRange(String managerSharePointsRange) {
        this.managerSharePointsRange = managerSharePointsRange
    }

    String getSharePointsWithManager() {
        return sharePointsWithManager
    }

    void setSharePointsWithManager(String sharePointsWithManager) {
        this.sharePointsWithManager = sharePointsWithManager
    }

    String getApprovalProcess() {
        return approvalProcess
    }

    void setApprovalProcess(String approvalProcess) {
        this.approvalProcess = approvalProcess
    }

    @JsonIgnore
    @Override
    String toString() {
        try {
            return new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(this)
        } catch (JsonProcessingException e) {
            e.printStackTrace()
            return e.toString()
        }
    }
}