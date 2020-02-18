package com.iat.domain.EcardsConfig.milestones.usersList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iat.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class UserForMilestone extends AbstractDomain {

    private String uuid;
    private String fullName;
    private String email;
    private Boolean awarded;
    private int value;
}