package com.iat.domain.EcardsConfig;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iat.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class TemplatesConfig extends AbstractDomain {

    private Boolean useDefaultTemplatesSet;
}