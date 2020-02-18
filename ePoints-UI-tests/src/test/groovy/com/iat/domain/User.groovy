package com.iat.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper

@JsonIgnoreProperties(ignoreUnknown = true)
class User {

    private String uuid
    private String email

    String getUuid() {
        return uuid
    }

    void setUuid(String uuid) {
        this.uuid = uuid
    }

    String getEmail() {
        return email
    }

    void setEmail(String email) {
        this.email = email
    }

    @JsonIgnore
    @Override
    String toString() {
        try {
            return new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(this)
        } catch (JsonProcessingException e) {
            e.printStackTrace()
            return e.toString()
        }
    }
}
