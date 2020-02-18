package com.iat.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class EcardsSettingsTemplates extends AbstractDomain {

    private String useDefaultTemplatesSet;

    public EcardsSettingsTemplates() {
    }

    public EcardsSettingsTemplates(String useDefaultTemplatesSet) {
        this.setUseDefaultTemplatesSet(useDefaultTemplatesSet);
    }
}
