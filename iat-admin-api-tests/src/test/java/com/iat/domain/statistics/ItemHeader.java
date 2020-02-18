package com.iat.domain.statistics;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iat.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class ItemHeader extends AbstractDomain {

    private boolean id;
    private String key;
    private String type;
    private String label;
}