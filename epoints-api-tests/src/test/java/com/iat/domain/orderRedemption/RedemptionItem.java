package com.iat.domain.orderRedemption;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iat.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RedemptionItem extends AbstractDomain {

    private String id;
    private String seoSlug;
    private String title;
    private RedemptionItemEpoints epoints;
    private RedemptionItemEpoints originalEpoints;
    private String imageUrl;
    private String thumbnailUrl;
    private Boolean promoted;
    private Boolean active;
    private String businessId;
    private Boolean exclusive;
    private String department;
    private String departmentSeoSlug;
    private List<String> categories;
    private List<String> categoriesSeoSlugs;
}
