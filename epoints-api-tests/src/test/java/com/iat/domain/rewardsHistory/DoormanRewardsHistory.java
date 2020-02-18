package com.iat.domain.rewardsHistory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iat.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DoormanRewardsHistory extends AbstractDomain {

    private List<DoormanReward> rewards;
}