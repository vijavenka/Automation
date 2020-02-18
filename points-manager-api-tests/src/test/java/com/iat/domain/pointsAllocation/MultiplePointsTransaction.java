package com.iat.domain.pointsAllocation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.iat.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class MultiplePointsTransaction extends AbstractDomain {

    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    private String createdAt;
    private List<PointsTransaction> details;
}