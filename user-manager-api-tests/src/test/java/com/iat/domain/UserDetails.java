package com.iat.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_ABSENT)
@Getter
@Setter
public class UserDetails {

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
    private String birthDate;
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
    private String unsubscribed;
    private String epointsForEcards;
    private String partnersGroupShortName;
    private boolean hardBounced;

    public void setEmail(String email) {
        this.email = email == null ? email : email.isEmpty() ? email : email.contains("@") ? email : email + new DateTime().getMillis() + "@test.bdl";
    }

    public String getePointsContact() {
        return ePointsContact;
    }

    public void setePointsContact(String ePointsContact) {
        this.ePointsContact = ePointsContact;
    }

    @JsonIgnore
    @Override
    public String toString() {
        try {
            return new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return e.toString();
        }
    }
}
