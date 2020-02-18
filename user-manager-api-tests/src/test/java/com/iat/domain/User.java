package com.iat.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class User {

    private String uuid;
    private String email;
    private String userKey;
    private String epointsToken;

    public void setUuid(String uuid) {
        if (uuid == null && this.userKey != null)
            uuid = this.userKey;
        this.uuid = uuid;
    }

    public void setUserKey(String userKey) {
        if (this.uuid == null && userKey != null)
            this.uuid = userKey;
        this.userKey = userKey;
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