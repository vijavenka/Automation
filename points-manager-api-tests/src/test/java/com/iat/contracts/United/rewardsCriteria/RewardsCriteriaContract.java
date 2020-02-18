package com.iat.contracts.United.rewardsCriteria;

public class RewardsCriteriaContract {

    public String getRewardsCriteriaImportPath(String googleDocId) {
        String path = "/reward-criteria/import/";

        if (!googleDocId.equals("null"))
            path += "&googleDocId=" + googleDocId;
        return path.replace("/&", "/?");
    }

}