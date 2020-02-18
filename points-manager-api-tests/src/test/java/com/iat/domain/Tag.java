package com.iat.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Tag extends AbstractDomain {

    private String id;
    private String tagKey;
    private String partnerId;
    private String description;
}