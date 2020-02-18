package com.iat.domain.EcardsConfig.milestones;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iat.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class MilestonesConfig extends AbstractDomain {

    private String name;
    private Boolean active;

    public MilestonesConfig() {
    }

    public MilestonesConfig(String name, Boolean active) {
        this.setName(name);
        this.setActive(active);
    }
}