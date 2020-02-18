package com.iat.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Partner extends AbstractDomain {

    private String id;
    private String name;
    private String shortName;
    private String externalId;
    private String groupId;
}