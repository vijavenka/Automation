package com.iat.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.iat.domain.departmentsStructure.Department;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
@Getter
@Setter
public class User extends AbstractDomain {

    private String id;
    private String employeeNumber;
    private Date createdAt;
    private String name;
    private String email;
    private String gender;
    private String birthDate;
    private Department department;
    private String role;
    private String adminRole;
    private String status;
    private String companyStartDate;
    private List<EmailChange> emailChanges;
    private User user;
    private User manager;
    private String companyUsername;

    public User() {
    }

    public User(String employeeNumber, String email, String name, Department department, String role, String adminRole, String status) {
        this(employeeNumber, email, name, null, null, department, role, adminRole, status, null,null);
    }

    public User(String employeeNumber, String email, String name, String gender, String birthDate, Department department, String role, String adminRole, String status, String companyStartDate, String companyUsername) {
        this.setEmployeeNumber(employeeNumber);
        this.setEmail(email);
        this.setName(name);
        this.setGender(gender);
        this.setBirthDate(birthDate);
        this.setDepartment(department);
        this.setRole(role);
        this.setAdminRole(adminRole);
        this.setStatus(status);
        this.setCompanyStartDate(companyStartDate);
        this.setCompanyUsername(companyUsername);
    }

    public void update(User userUpdateTo) {
        this.employeeNumber = userUpdateTo.getEmployeeNumber() == null ? employeeNumber : userUpdateTo.getEmployeeNumber().equals("null") ? "" : userUpdateTo.getEmployeeNumber();
        this.name = userUpdateTo.getName() == null ? name : userUpdateTo.getName().equals("null") ? "" : userUpdateTo.getName();
        this.email = userUpdateTo.getEmail() == null ? email : userUpdateTo.getEmail().isEmpty() ? "" : userUpdateTo.getEmail().contains("@") ? userUpdateTo.getEmail() : userUpdateTo.getEmail() + new DateTime().getMillis() + "@test.iat.admin";
        this.department = userUpdateTo.getDepartment() == null ? department : userUpdateTo.getDepartment().getId() == null ? new Department(null, null, null, null, null, true) : userUpdateTo.getDepartment();
        this.role = userUpdateTo.getRole() == null ? role : userUpdateTo.getRole().equals("null") ? "" : userUpdateTo.getRole();
        this.adminRole = userUpdateTo.getAdminRole() == null ? adminRole : userUpdateTo.getAdminRole().equals("null") ? "" : userUpdateTo.getAdminRole();
        this.status = userUpdateTo.getStatus() == null ? status : userUpdateTo.getStatus().equals("null") ? "" : userUpdateTo.getStatus();
        this.companyStartDate = userUpdateTo.getCompanyStartDate() == null ? companyStartDate : userUpdateTo.getCompanyStartDate().equals("null") ? "" : userUpdateTo.getCompanyStartDate();
        this.gender = userUpdateTo.getGender() == null ? gender : userUpdateTo.getGender().equals("null") ? "" : userUpdateTo.getGender();
        this.birthDate = userUpdateTo.getBirthDate() == null ? birthDate : userUpdateTo.getBirthDate().equals("null") ? "" : userUpdateTo.getBirthDate();
        this.emailChanges = null; //during profile update we cannot have emailChanges in body
        this.companyUsername = userUpdateTo.getCompanyUsername() == null ? "" : userUpdateTo.getCompanyUsername().isEmpty() ? "" : userUpdateTo.getCompanyUsername().contains("@") ? userUpdateTo.getCompanyUsername() : userUpdateTo.getCompanyUsername() + new DateTime().getMillis() + "@test.iat.admin";
    }

    public void setEmail(String email) {
        if (email != null && !email.isEmpty() && !email.contains("@"))
            email += new Date().getTime() + "@test.iat.admin";
        this.email = email;
    }

    public void setDepartment(Department department) {
        if (department != null && department.getId() == null)
            department = new Department(null, null, null, null, null, true);
        this.department = department;
    }

    public void setCompanyUsername(String companyUsername) {
        if (companyUsername != null && !companyUsername.isEmpty() && !companyUsername.contains("@"))
            companyUsername += new Date().getTime() + "@test.iat.admin";
        this.companyUsername = companyUsername;
    }
}