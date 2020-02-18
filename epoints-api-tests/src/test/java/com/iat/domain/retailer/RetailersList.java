package com.iat.domain.retailer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iat.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RetailersList extends AbstractDomain {

    private List<Retailer> merchants;
    private int searchResultsCount;
    private int favouritesCount;
    private List<String> departments;
    private List<String> prefixes;
}