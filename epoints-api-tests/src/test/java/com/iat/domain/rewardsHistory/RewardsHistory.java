package com.iat.domain.rewardsHistory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iat.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RewardsHistory extends AbstractDomain {

    private List<Reward> rewards;

    public RewardsHistory() {
    }

    public RewardsHistory(List<Reward> rewards) {
        this.setRewards(rewards);
    }
}