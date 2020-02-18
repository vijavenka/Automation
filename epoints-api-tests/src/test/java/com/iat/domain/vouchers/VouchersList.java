package com.iat.domain.vouchers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iat.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class VouchersList extends AbstractDomain {

    private int allCount;
    private int pageSize;
    private List<IndividualVoucherItem> results;
    private List<String> facets;
    private FacetFilters facetFilters;
    private int pagesCount;
}