package com.iat.domain.Ecards;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.iat.Config;
import com.iat.actions.ecardsSection.EcardsSendEcardToUsersActions;
import com.iat.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class EcardsSent extends AbstractDomain {

    private String reasonId;
    private String templateId;
    private String message;
    private Integer pointsValue;
    private List<String> usersKey;
    private List<String> cc;

    public void setReasonId(String reasonId) {
        if (reasonId != null)
            reasonId = new EcardsSendEcardToUsersActions().getEcardsReasonIdByName(reasonId);
        this.reasonId = reasonId;
    }

    public void setTemplateId(String templateId) {
        if (templateId != null && templateId.equalsIgnoreCase("DEFAULT"))
            templateId = Config.getEcardDefaultTemplateId();
        this.templateId = templateId;
    }
}