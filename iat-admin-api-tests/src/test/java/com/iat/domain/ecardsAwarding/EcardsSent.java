package com.iat.domain.ecardsAwarding;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.iat.Config;
import com.iat.actions.ecardsManagement.EcardsReasonsActions;
import com.iat.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
@Getter
@Setter
public class EcardsSent extends AbstractDomain {

    private String reasonId;
    private String templateId;
    private String message;
    private Integer pointsValue;
    private List<String> usersKey;
    private List<String> cc;
    private List<Product> products;

    public void setReasonId(String reasonId) throws Throwable {
        if (reasonId != null)
            reasonId = new EcardsReasonsActions().getReasonIdForName(reasonId);
        this.reasonId = reasonId;
    }

    public void setTemplateId(String templateId) {
        if (templateId != null && templateId.equalsIgnoreCase("DEFAULT"))
            templateId = Config.getEcardDefaultTemplateId();
        this.templateId = templateId;
    }
}