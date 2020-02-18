package com.iat.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import static com.iat.utils.DateTimeUtil.Format;
import static com.iat.utils.DateTimeUtil.convertToDate;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Points extends AbstractDomain {

    private Long id;
    private String status;
    private Long tagId;
    private Long partnerId;
    private String partnerExternalId;
    private String activityInfo;
    private String externalTransactionId;
    private Long rewardCriteriaId;
    private String rewardCriteriaDescription;
    private String onBehalfOfId;
    private Date autoConfirmDate;
    private Integer delta;
    private Integer balance;
    private Long accountId;
    @JsonIgnore
    private String autoConfirmDateString;

    public Date getAutoConfirmDate() {
        if (autoConfirmDate == null && autoConfirmDateString != null)
            return convertToDate(autoConfirmDateString, Format.yyyyMMdd, true);
        return autoConfirmDate;
    }
}