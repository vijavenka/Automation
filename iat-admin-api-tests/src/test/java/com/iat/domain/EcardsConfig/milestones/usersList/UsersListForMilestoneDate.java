package com.iat.domain.EcardsConfig.milestones.usersList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iat.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class UsersListForMilestoneDate extends AbstractDomain {

    private String day;
    private List<UserForMilestone> items;
}