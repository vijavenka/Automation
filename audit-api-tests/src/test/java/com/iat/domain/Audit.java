package com.iat.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.iat.utils.DataExchanger;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_ABSENT)
@Getter
@Setter
public class Audit {

    private String id;
    private String auditName;
    private String auditStart;
    private String auditEnd;

    private DateTime dateTime = new DateTime();
    private DataExchanger dataExchanger = DataExchanger.getInstance();

    public Audit(@JsonProperty("id") String id,
                 @JsonProperty("auditName") String auditName,
                 @JsonProperty("auditStart") String auditStart,
                 @JsonProperty("auditEnd") String auditEnd) {


        this.id = id;
        this.auditName = auditName == null ? auditName : auditName.isEmpty() ? auditName : auditName.contains("API_AUDIT_CMS_AUDIT_") ? auditName + dateTime.getMillis() : auditName;
        this.auditStart = auditStart;
        this.auditEnd = auditEnd;
    }

    @Override
    public String toString() {
        return ("{" +
                (id == null ? "" : "\"id\": " + id + ", ") +
                (auditName == null ? "" : "\"auditName\": \"" + auditName + "\", ") +
                (auditStart == null ? "" : "\"auditStart\":\"" + auditStart + "\", ") +
                (auditEnd == null ? "" : "\"auditEnd\":\"" + auditEnd + "\", ") +
                '}').replace(", }", "}");
    }
}
