package com.iat.domain.SearchUser;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.iat.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
@Getter
@Setter
public class Manager extends AbstractDomain {

    private String name;
    private String email;

    public void setEmail(String email) {
        if (email != null && !email.isEmpty() && !email.contains("@"))
            email += new Date().getTime() + "@test.iat.admin";
        this.email = email;
    }
}