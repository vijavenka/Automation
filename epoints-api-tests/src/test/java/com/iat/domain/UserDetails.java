package com.iat.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class UserDetails extends AbstractDomain {

    private String uuid;
    private String userId;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String mobileNumber;
    private String houseNumberOrName;
    private String street;
    private String townOrCity;
    private String county;
    private String country;
    private String postcode;
    private String region;
    private String language;
    private String title;
    private String gender;
    private long birthDate;
    private String ePointsContact;
    private String thirdPartyContact;
    private String testUser;
    private String idType;
    private String epointsToken;
    private String verified;
    private String revoked;
    private String active;
    private List<UserGroup> userGroups;
    private String registrationSiteName;
    private String registrationSiteShortName;
    private String registrationSiteId;
    private String externalIdUnited;
    private String epointsReceiverScope;
    private String userIdType;
    private String registrationDate;
    private String ecardsVisible;
    private String mustChangePassword;
    private String userKey;
    private String emailVerified;
    private String partnersGroupName;
    private boolean unsubscribed;
    private String epointsForEcards;
    private String partnersGroupShortName;
    private String privacyAccepted;
    private String marketingPrefSMS;
    private String marketingPrefEmail;
    private String tncAccepted;

    public void setEmail(String email) {
        if (email != null && !email.isEmpty() && !email.contains("@"))
            email += new Date().getTime() + "@test.bdl";
        this.email = email;
    }
}
