package com.iat.domain.userProfile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iat.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Address extends AbstractDomain {

    private boolean pointsAccounted;
    private String firstName;
    private String lastName;
    private String house;
    private String street;
    private String city;
    private String county;
    private String country;
    private String postCode;
    private String phoneNo;

    public Address() {
    }

    public Address(String firstName, String lastName, String house, String street, String city, String county, String country, String postCode, String phoneNo) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setHouse(house);
        this.setStreet(street);
        this.setCity(city);
        this.setCounty(county);
        this.setCountry(country);
        this.setPostCode(postCode);
        this.setPhoneNo(phoneNo);
    }
}