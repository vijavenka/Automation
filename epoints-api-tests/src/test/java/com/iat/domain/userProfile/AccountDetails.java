package com.iat.domain.userProfile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iat.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountDetails extends AbstractDomain {

    private boolean pointsAccounted;
    private String id;
    private String email;
    private String newPassword;
    private String existingPassword;
    private long passwordChangeDate;
    private boolean emailVerified;
    private long registrationDate;
    private String referrerLink;
    private boolean verified;
    private String partnersGroupName;
    private String partnersGroupShortName;
    private List<String> businessIds;
    private boolean passwordSet;
}