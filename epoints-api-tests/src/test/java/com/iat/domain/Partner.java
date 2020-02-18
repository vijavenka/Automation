package com.iat.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Partner extends AbstractDomain {

    private String id;
    private String name;
    private String shortName;
    private String externalId;
    private String groupId;
}