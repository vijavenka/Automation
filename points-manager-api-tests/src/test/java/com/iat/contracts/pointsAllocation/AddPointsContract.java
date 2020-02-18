package com.iat.contracts.pointsAllocation;

public class AddPointsContract {

    public String getAddPointsPathById(String id, String idType) {
        return "/transactions/user/" + id + "/?idType=" + idType;
    }

    public String checkPointsAcquirePossibility(String userId, String idType) {
        return "/users/" + userId + "/points-availability?idType=" + idType;
    }

    public String bulkUploadPoints() {
        return "/transactions/bulk";
    }

    public String multipleAwards() {
        return "/transactions/multiple-awards";
    }

}