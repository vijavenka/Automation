package com.iat.domain.userProfile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.iat.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class UserDetailsDoorman extends AbstractDomain {

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
    private Long birthDate;
    private Boolean ePointsContact;
    private Boolean thirdPartyContact;
    private String testUser;
    private String idType;
    private String epointsToken;
    private Boolean verified;
    private Boolean revoked;
    private Boolean active;
    @JsonProperty("userGroups")
    private List<UserGroupDoorman> userGroupDoormen;
    private String registrationSiteName;
    private String registrationSiteShortName;
    private String registrationSiteId;
    private String externalIdUnited;
    private String epointsReceiverScope;
    private String userIdType;
    private String registrationDate;
    private String ecardsVisible;
    private Boolean mustChangePassword;
    private String userKey;
    private Boolean emailVerified;
    private String partnersGroupName;
    private Boolean unsubscribed;
    private String epointsForEcards;
    private String partnersGroupShortName;

    public void setEmail(String email) {
        if (email != null && !email.isEmpty() && !email.contains("@"))
            email += new DateTime().getMillis() + "@test.bdl";
        this.email = email;
    }
}
