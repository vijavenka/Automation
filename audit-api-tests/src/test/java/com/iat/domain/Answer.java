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
public class Answer {

    private String id;
    private String answerText;
    private String url;
    private String s3Url;
    private String auditId;
    private String questionId;
    private String storeId;

    private String results_CallID;
    private String results_c_Store_ID;
    private String results_question_id;
    private String results_response;
    private String results_c_product_id;
    private String results_question_type;

    private DateTime dateTime = new DateTime();
    private DataExchanger dataExchanger = DataExchanger.getInstance();

    public Answer() {
    }

    public Answer(@JsonProperty("id") String id,
                  @JsonProperty("answerText") String answerText,
                  @JsonProperty("url") String url,
                  @JsonProperty("s3Url") String s3Url,
                  @JsonProperty("auditId") String auditId,
                  @JsonProperty("questionId") String questionId,
                  @JsonProperty("storeId") String storeId) {


        this.id = id;
        this.answerText = answerText;
        this.url = url;
        this.s3Url = s3Url;
        this.auditId = auditId;
        this.questionId = questionId;
        this.storeId = storeId;
    }

    //    @Override
//        public String toString() {
//            return ("{" +
//                    (id == null ? "" : "\"id\": " + id + ", ") +
//                    (auditName == null ? "" : "\"auditName\": \"" + auditName + "\", ") +
//                    (auditStart == null ? "" : "\"auditStart\":\"" + auditStart + "\", ") +
//                    (auditEnd == null ? "" : "\"auditEnd\":\"" + auditEnd + "\", ") +
//                    '}').replace(", }", "}");
//    }
}
