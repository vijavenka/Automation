package com.iat.domain.vouchers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iat.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class FacetFilters extends AbstractDomain {

    private List<String> d_validFrom;
    private List<String> d_validTo;
    private List<String> b_zoneUK;
}