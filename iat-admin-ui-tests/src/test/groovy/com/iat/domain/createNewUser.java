package com.iat.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class createNewUser extends AbstractDomain {

    private String employeeNumber;
    private String name;
    private String birthDate;
    private String email;
    private String gender;
    private String companyStartDate;
    private String role;
    private String adminRole;
    private String status;
    private Department department;

    public createNewUser(String employeeNumber, String name, String birthDate, String email, String gender,
                         String companyStartDate, String role, String adminRole, String status, Long departmentId) {
        this.setEmployeeNumber(employeeNumber);
        this.setName(name);
        this.setBirthDate(birthDate);
        this.setEmail(email);
        this.setGender(gender);
        this.setCompanyStartDate(companyStartDate);
        this.setRole(role);
        this.setAdminRole(adminRole);
        this.setStatus(status);
        this.setDepartment(new Department(departmentId));

    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Getter
    @Setter
    public class Department extends AbstractDomain {
        Long id;

        public Department(Long id) {
            this.setId(id);
        }
    }
}