package com.iat.domain.ecardsAwarding;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.iat.Config;
import com.iat.actions.ecardsManagement.EcardsReasonsActions;
import com.iat.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
@Getter
@Setter
public class Product extends AbstractDomain {

    private String productTitle;
    private String productId;
    private Integer productPoints;
    private Integer quantity;
}

