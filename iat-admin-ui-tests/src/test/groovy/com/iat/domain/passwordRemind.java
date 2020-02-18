package com.iat.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class passwordRemind extends AbstractDomain {

    private String email;

    public passwordRemind(String email) {
        this.setEmail(email);
    }
}