package com.iat.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class EpointsRange extends AbstractDomain {

    private int from;
    private int to;
    private int itemsCount;
    private String label;
}
