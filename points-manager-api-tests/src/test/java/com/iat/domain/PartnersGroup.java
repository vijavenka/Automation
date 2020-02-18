package com.iat.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class PartnersGroup extends AbstractDomain {

    private String name;
    private String shortName;
    private String id;
}