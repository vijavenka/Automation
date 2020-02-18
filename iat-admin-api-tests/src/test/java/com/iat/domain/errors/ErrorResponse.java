package com.iat.domain.errors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iat.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class ErrorResponse extends AbstractDomain {

    private String timestamp;
    private int status;
    private String error;
    private String exception;
    private String message;
    private String path;
}