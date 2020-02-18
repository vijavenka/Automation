package com.iat.actions.United.rewardsCriteria;

import com.iat.controller.United.rewardsCriteria.RewardsCriteriaController;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;

public class RewardsCriteriaActions {

    private RewardsCriteriaController rewardsCriteriaController = new RewardsCriteriaController();

    public ResponseContainer bulkUploadRewardsCriteria(String googleDocId, int status) {
        return initResponseContainer(rewardsCriteriaController.bulkUploadRewardsCriteria(googleDocId, status));
    }
}