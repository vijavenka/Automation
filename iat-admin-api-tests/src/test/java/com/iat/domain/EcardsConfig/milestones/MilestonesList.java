package com.iat.domain.EcardsConfig.milestones;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iat.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class MilestonesList extends AbstractDomain {

    private String valueType;
    private List<SingleMilestoneElement> values;
}