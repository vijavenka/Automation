package com.iat.domain.EcardsConfig;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iat.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class PointsAllocations extends AbstractDomain {

    private List<PointsAllocation> allocations;

    public PointsAllocations() {
    }

    public PointsAllocations(PointsAllocation... pointsAllocations) {
        this.setAllocations(new ArrayList<>());
        for (PointsAllocation pointsAllocation : pointsAllocations)
            this.getAllocations().add(pointsAllocation);
    }
}