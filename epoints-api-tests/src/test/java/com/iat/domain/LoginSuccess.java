package com.iat.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginSuccess extends AbstractDomain{

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private boolean verified;
    private String subscriptionStatus;
    private String expiredMembershipType;
    private String activeMembershipType;
    private long registrationDate;
    private boolean oneOffFeePaid;
    private boolean cancelClickedAtUpgradePopup;
    private boolean isGDPRAccepted;
    private boolean isManager;
    private boolean isBusinessUser;
}
