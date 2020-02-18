package com.iat.domain.departmentsStructure;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iat.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Structure extends AbstractDomain {

    private String name;
    private String userNumber;
    private List<Department> departments;
}
