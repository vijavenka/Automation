package com.iat.repository;

import com.iat.domain.RewardCriteria;

public interface RewardCriteriaRepository {
    RewardCriteria findRewardCriteriaByProductId(String productId);

    void deleteRewardCriteriaByProductId(String productId);
}