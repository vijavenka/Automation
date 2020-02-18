package com.iat.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonRawValue
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper

@JsonIgnoreProperties(ignoreUnknown = true)
class NewUser {

    private String employeeNumber
    private String email
    private String name
    private String gender
    private String birthDate
    @JsonRawValue
    private String department
    private String role
    private String adminRole
    private String status

    String getEmployeeNumber() {
        return employeeNumber
    }

    void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber
    }

    String getEmail() {
        return email
    }

    void setEmail(String email) {
        this.email = email
    }

    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }

    String getGender() {
        return gender
    }

    void setGender(String gender) {
        this.gender = gender
    }

    String getBirthDate() {
        return birthDate
    }

    void setBirthDate(String birthDate) {
        this.birthDate = birthDate
    }

    String getDepartment() {
        return department
    }

    void setDepartment(String department) {
        department = "{\"id\": $department}"
        this.department = department
    }

    String getRole() {
        return role
    }

    void setRole(String role) {
        this.role = role
    }

    String getAdminRole() {
        return adminRole
    }

    void setAdminRole(String adminRole) {
        this.adminRole = adminRole
    }

    String getStatus() {
        return status
    }

    void setStatus(String status) {
        this.status = status
    }

    @JsonIgnore
    @Override
    String toString() {
        try {
            return new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(this)
        } catch (JsonProcessingException e) {
            e.printStackTrace()
            return e.toString()
        }
    }
}
