package com.iat.domain.EcardsConfig;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iat.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import static java.util.Arrays.asList;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Reason extends AbstractDomain {

    private String id;
    private String name;
    private String userToUserReasonRange;
    private String managerToUserReasonRange;
    private Integer managerToUserMin;
    private Integer userToUserMin;
    private Integer managerToUserMax;
    private Integer userToUserMax;

    public Reason() {
    }

    public Reason(Integer managerToUserMin, Integer managerToUserMax, Integer userToUserMin, Integer userToUserMax) {
        this.setManagerToUserMin(managerToUserMin);
        this.setManagerToUserMax(managerToUserMax);
        this.setUserToUserMin(userToUserMin);
        this.setUserToUserMax(userToUserMax);
    }

    public void setName(String name) {
        if (name != null && asList("QA Rox ", "Reason for wizard 1st login ").contains(name))
            name += new Date().getTime();
        this.name = name;
    }
}