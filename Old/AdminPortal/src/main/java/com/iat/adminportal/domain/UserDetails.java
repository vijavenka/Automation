package com.iat.adminportal.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class UserDetails {

    private String uuid;
    private String userId;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String mobileNumber;
    private String house;
    private String street;
    private String city;
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
    private Long passwordChangedAt;

    private Date lastLoginAt;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email != null && !email.isEmpty() && !email.contains("@"))
            email += new Date().getTime() + "@test.bdl";
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(long birthDate) {
        this.birthDate = birthDate;
    }

    public String getePointsContact() {
        return ePointsContact;
    }

    public void setePointsContact(String ePointsContact) {
        this.ePointsContact = ePointsContact;
    }

    public String getTestUser() {
        return testUser;
    }

    public void setTestUser(String testUser) {
        this.testUser = testUser;
    }

    public String getThirdPartyContact() {
        return thirdPartyContact;
    }

    public void setThirdPartyContact(String thirdPartyContact) {
        this.thirdPartyContact = thirdPartyContact;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getEpointsToken() {
        return epointsToken;
    }

    public void setEpointsToken(String epointsToken) {
        this.epointsToken = epointsToken;
    }

    public String getVerified() {
        return verified;
    }

    public void setVerified(String verified) {
        this.verified = verified;
    }

    public String getRevoked() {
        return revoked;
    }

    public void setRevoked(String revoked) {
        this.revoked = revoked;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public List<UserGroup> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(List<UserGroup> userGroups) {
        this.userGroups = userGroups;
    }

    public String getRegistrationSiteName() {
        return registrationSiteName;
    }

    public void setRegistrationSiteName(String registrationSiteName) {
        this.registrationSiteName = registrationSiteName;
    }

    public String getRegistrationSiteShortName() {
        return registrationSiteShortName;
    }

    public void setRegistrationSiteShortName(String registrationSiteShortName) {
        this.registrationSiteShortName = registrationSiteShortName;
    }

    public String getRegistrationSiteId() {
        return registrationSiteId;
    }

    public void setRegistrationSiteId(String registrationSiteId) {
        this.registrationSiteId = registrationSiteId;
    }

    public String getExternalIdUnited() {
        return externalIdUnited;
    }

    public void setExternalIdUnited(String externalIdUnited) {
        this.externalIdUnited = externalIdUnited;
    }

    public String getEpointsReceiverScope() {
        return epointsReceiverScope;
    }

    public void setEpointsReceiverScope(String epointsReceiverScope) {
        this.epointsReceiverScope = epointsReceiverScope;
    }

    public String getUserIdType() {
        return userIdType;
    }

    public void setUserIdType(String userIdType) {
        this.userIdType = userIdType;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getEcardsVisible() {
        return ecardsVisible;
    }

    public void setEcardsVisible(String ecardsVisible) {
        this.ecardsVisible = ecardsVisible;
    }

    public String getMustChangePassword() {
        return mustChangePassword;
    }

    public void setMustChangePassword(String mustChangePassword) {
        this.mustChangePassword = mustChangePassword;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public String getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(String emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getPartnersGroupName() {
        return partnersGroupName;
    }

    public void setPartnersGroupName(String partnersGroupName) {
        this.partnersGroupName = partnersGroupName;
    }

    public boolean isUnsubscribed() {
        return unsubscribed;
    }

    public void setUnsubscribed(boolean unsubscribed) {
        this.unsubscribed = unsubscribed;
    }

    public String getEpointsForEcards() {
        return epointsForEcards;
    }

    public void setEpointsForEcards(String epointsForEcards) {
        this.epointsForEcards = epointsForEcards;
    }

    public String getPartnersGroupShortName() {
        return partnersGroupShortName;
    }

    public void setPartnersGroupShortName(String partnersGroupShortName) {
        this.partnersGroupShortName = partnersGroupShortName;
    }

    public Long getPasswordChangedAt() {
        return passwordChangedAt;
    }

    public void setPasswordChangedAt(Long passwordChangedAt) {
        this.passwordChangedAt = passwordChangedAt;
    }

    public Date getLastLoginAt() {
        return lastLoginAt;
    }

    public void setLastLoginAt(Date lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
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
