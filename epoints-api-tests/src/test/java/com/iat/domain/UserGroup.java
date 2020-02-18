package com.iat.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class UserGroup extends AbstractDomain {

    private Long groupId;
    private Long partnerId;
    private Long departmentId;

    private String firstName;
    private String lastName;
    private String email;
    private String empNumber;
    private String gender;
    private Date birthDate;
    private Date createDate;
    private Date deleteDate;
    private String role;
    private String adminRole;
    private String status;
    private String departmentName;
    private String managerName;
    private String managerEmail;
    private Date companyStartDate;
    private String externalId;
    private String externalIdType;
}