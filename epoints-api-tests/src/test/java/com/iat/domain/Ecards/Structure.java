package com.iat.domain.Ecards;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iat.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Structure extends AbstractDomain {

    private String name;
    private String userNumber;
    private List<Department> departments;
}
