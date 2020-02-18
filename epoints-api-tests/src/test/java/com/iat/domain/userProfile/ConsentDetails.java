package com.iat.domain.userProfile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iat.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsentDetails extends AbstractDomain {

    private boolean subscribedToMarketingEmails;
}