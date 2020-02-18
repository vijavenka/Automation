package com.iat.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper

@JsonIgnoreProperties(ignoreUnknown = true)
class EcardAward {

    private String reasonId
    private String templateId
    private String message
    private Integer pointsValue
    private List<String> usersKey
    private List<String> cc

    String getReasonId() {
        return reasonId
    }

    void setReasonId(String reasonId) {
        this.reasonId = reasonId
    }

    String getTemplateId() {
        return templateId
    }

    void setTemplateId(String templateId) {
        this.templateId = templateId
    }

    String getMessage() {
        return message
    }

    void setMessage(String message) {
        this.message = message
    }

    Integer getPointsValue() {
        return pointsValue
    }

    void setPointsValue(Integer pointsValue) {
        this.pointsValue = pointsValue
    }

    List<String> getUsersKey() {
        return usersKey
    }

    void setUsersKey(List<String> usersKey) {
        this.usersKey = usersKey
    }

    List<String> getCc() {
        return cc
    }

    void setCc(List<String> cc) {
        this.cc = cc
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