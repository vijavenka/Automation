package com.iat.domain.userProfile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iat.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserProfile extends AbstractDomain {

    private AccountDetails accountDetails;
    private PersonalDetails personalDetails;
    private ContactDetails contactDetails;
    private ConsentDetails consentDetails;
    private boolean ecardsVisible;
    private String epointsReceiverScope;
    private boolean couponUsageVisible;
}