package com.iat.domain.EcardsConfig.milestones;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iat.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class MilestoneValue extends AbstractDomain {

    private int value;

    public MilestoneValue() {
    }

    public MilestoneValue(int value) {
        this.setValue(value);
    }
}