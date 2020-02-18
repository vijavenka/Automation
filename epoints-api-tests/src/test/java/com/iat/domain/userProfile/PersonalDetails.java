package com.iat.domain.userProfile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iat.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonalDetails extends AbstractDomain {

    private boolean pointsAccounted;
    private String firstName;
    private String lastName;
    private String title;
    private String gender;
    private long dob;
}