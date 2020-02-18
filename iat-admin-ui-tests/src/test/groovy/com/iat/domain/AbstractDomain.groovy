package com.iat.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL

abstract class AbstractDomain {

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

    @JsonIgnore
    String toJsonRequest() {
        try {
            return new ObjectMapper().setSerializationInclusion(NON_NULL).writer().withDefaultPrettyPrinter().writeValueAsString(this)
        } catch (JsonProcessingException e) {
            e.printStackTrace()
            return e.toString()
        }
    }
}
