package com.iat.domain.vouchers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iat.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class IndividualVoucherItem extends AbstractDomain {

    private String id;
    private String description;
    private String title;
    private String seoSlug;
    private String voucherUrl;
    private String voucherCode;
    private String merchantId;
    private String merchantName;
    private String merchantImageUrl;
    private String merchantDomain;
    private String brandName;
    private long validFrom;
    private long validTo;
    private int epointsMultiplier;
    private String encodedVoucherUrl;

    @JsonIgnore
    public Date getValidToDate() {
        return new Date(validTo);
    }
}