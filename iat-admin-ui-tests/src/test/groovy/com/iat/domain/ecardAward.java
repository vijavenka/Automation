package com.iat.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class ecardAward {

    private String reasonId;
    private String templateId;
    private String message;
    private String pointsValue;
    private List<String> usersKey;
    private List<String> cc;

    public ecardAward(String reasonId, String templateId, String message, String pointsValue, List<String> usersKey, List<String> cc) {

        this.setReasonId(reasonId);
        this.setTemplateId(templateId);
        this.setMessage(message);
        this.setPointsValue(pointsValue);
        this.setUsersKey(usersKey);
        this.setCc(cc);
    }
}