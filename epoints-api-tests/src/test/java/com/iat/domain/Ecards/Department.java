package com.iat.domain.Ecards;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iat.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Department extends AbstractDomain {

    private String id;
    private String name;
    private String userNumber;
    private String managerName;
    private List<Department> subdepartments;
    private Boolean active;
}
