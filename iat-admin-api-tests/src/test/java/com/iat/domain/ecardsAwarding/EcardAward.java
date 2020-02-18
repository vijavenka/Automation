package com.iat.domain.ecardsAwarding;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.iat.domain.AbstractDomain;
import com.iat.domain.SearchUser.SearchUser;
import lombok.Getter;
import lombok.Setter;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
@Getter
@Setter
public class EcardAward extends AbstractDomain {

    private Long id;
    private String createdAt;
    private SearchUser sentFrom;
    private SearchUser sentTo;
    private String reason;
    private String eCardPreviewLink;
    private Integer points;
    private Float amount;
    private String managerToUser;
    private String approvalStatus;
}