package com.iat.contracts;


public class RewardCriteriaContract {

    public String rewardCriteriaPath() {
        return "/api/reward-criteria";
    }

    public String rewardCriteriaByIdPath(String id) {
        return "/api/reward-criteria/" + id;
    }

}