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
public class AuditCriteria {

    private String id;
    private String segmentationType;
    private String extRelId;
    private String storeId;
    private String auditId;
    private String questionId;

    private DateTime dateTime = new DateTime();
    private DataExchanger dataExchanger = DataExchanger.getInstance();

    public AuditCriteria(@JsonProperty("id") String id,
                         @JsonProperty("segmentationType") String segmentationType,
                         @JsonProperty("extRelId") String extRelId,
                         @JsonProperty("storeId") String storeId,
                         @JsonProperty("auditId") String auditId,
                         @JsonProperty("questionId") String questionId) {


        this.id = id;
        this.segmentationType = segmentationType;
        this.extRelId = extRelId;
        this.storeId = storeId;
        this.auditId = auditId.equals("previous_call") ? dataExchanger.getAuditObject().getId() : auditId;
        this.questionId = questionId;
    }

    @Override
    public String toString() {
        return ("{" +
                (id == null ? "" : "\"id\": " + id + ", ") +
                (segmentationType == null ? "" : "\"segmentationType\": \"" + segmentationType + "\", ") +
                (extRelId == null ? "" : "\"extRelId\":\"" + extRelId + "\", ") +
                (storeId == null ? "" : "\"storeId\":\"" + storeId + "\", ") +
                (auditId == null ? "" : "\"auditId\":\"" + auditId + "\", ") +
                (questionId == null ? "" : "\"questionId\":\"" + questionId + "\", ") +
                '}').replace(", }", "}");
    }
}
