package com.iat.domain.EcardsConfig.milestones;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iat.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class SingleMilestoneElement extends AbstractDomain {

    private int id;
    private int value;
}