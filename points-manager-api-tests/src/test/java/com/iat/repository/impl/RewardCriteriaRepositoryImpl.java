package com.iat.repository.impl;

import com.iat.Config;
import com.iat.domain.RewardCriteria;
import com.iat.repository.RewardCriteriaRepository;

import static io.restassured.RestAssured.delete;
import static io.restassured.RestAssured.get;

public class RewardCriteriaRepositoryImpl implements RewardCriteriaRepository {
    @Override
    public RewardCriteria findRewardCriteriaByProductId(String productId) {
        System.out.println("Get: " + Config.getDoormanUrl() + "/rewardsCriteria?productId=" + productId);
        return get(Config.getDoormanUrl() + "/rewardsCriteria?productId=" + productId).getBody().as(RewardCriteria.class);
    }

    @Override
    public void deleteRewardCriteriaByProductId(String productId) {
        System.out.println("Delete: " + Config.getDoormanUrl() + "/rewardsCriteria?productId=" + productId);
        delete(Config.getDoormanUrl() + "/rewardsCriteria?productId=" + productId);
    }

}