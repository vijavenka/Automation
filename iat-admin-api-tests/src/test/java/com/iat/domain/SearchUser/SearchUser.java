package com.iat.domain.SearchUser;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.iat.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
@Getter
@Setter
public class SearchUser extends AbstractDomain {

    private String uuid;
    private String name;
    private String email;
    private String departmentName;
    private Manager manager;
}