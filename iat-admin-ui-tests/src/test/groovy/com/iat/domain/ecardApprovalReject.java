package com.iat.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class ecardApprovalReject extends AbstractDomain {

    private String message;

    public ecardApprovalReject(String message) {
        this.setMessage(message);
    }
}