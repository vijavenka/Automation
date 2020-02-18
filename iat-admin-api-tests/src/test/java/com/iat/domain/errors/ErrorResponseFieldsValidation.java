package com.iat.domain.errors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iat.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class ErrorResponseFieldsValidation extends AbstractDomain {

    private String timestamp;
    private List<Error> errors;
}