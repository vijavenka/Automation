package com.iat.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Department extends AbstractDomain {

    private String id;
    private String displayTitle;
    private String seoTitle;
    private String title;
    private int itemsCount;
}
