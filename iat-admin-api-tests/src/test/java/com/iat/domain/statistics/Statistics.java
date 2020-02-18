package com.iat.domain.statistics;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iat.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Statistics extends AbstractDomain {

    private String statisticId;
    private String statisticLabel;
    private int totalCount;
    private int previousTotalCount;
    private String from;
    private String to;
    private String groupedBy;
    private String domainType;
    private List<ItemStatistic> items;
    private int itemCount;
    private boolean truncated;
    private List<ItemHeader> header;
}