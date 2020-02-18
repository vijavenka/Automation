package com.iat.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class User {

    private String uuid;
    private String email;
    private String registrationSiteShortName;
    private String confirmed;
    private String redeemed;
    private String declined;
    private String pending;


    public User(@JsonProperty("uuid") String uuid,
                @JsonProperty("email") String email,
                @JsonProperty("registrationSiteShortName") String registrationSiteShortName,
                @JsonProperty("confirmed") String confirmed,
                @JsonProperty("redeemed") String redeemed,
                @JsonProperty("declined") String declined,
                @JsonProperty("pending") String pending
    ) {

        this.uuid = uuid;
        this.email = email;
        this.registrationSiteShortName = registrationSiteShortName;
        this.confirmed = confirmed;
        this.redeemed = redeemed;
        this.declined = declined;
        this.pending = pending;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return uuid != null ? uuid.equals(user.uuid) : user.uuid == null;
    }

    @Override
    public int hashCode() {
        return uuid != null ? uuid.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "uuid='" + uuid + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
