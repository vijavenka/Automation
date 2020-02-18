package com.iat.contracts.pointsAllocation;

public class PointsUpdateContract {

    public String updatePointsTransaction(String transactionId) {
        return "/transactions/" + transactionId;
    }

}