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
public class Category {

    private String id;
    private String categoryName;
    private String label;
    private String parent;

    private DateTime dateTime = new DateTime();
    private DataExchanger dataExchanger = DataExchanger.getInstance();

    public Category(@JsonProperty("id") String id,
                    @JsonProperty("categoryName") String categoryName,
                    @JsonProperty("parent") String parent,
                    @JsonProperty("label") String label) {


        this.id = id;
        this.categoryName = categoryName == null ? categoryName : categoryName.isEmpty() ? categoryName : categoryName.contains("API_AUDIT_CMS_CATEGORY_") ? categoryName + dateTime.getMillis() : categoryName;
        this.label = label == null ? label : label.isEmpty() ? label : label.contains("API_AUDIT_CMS_CATEGORY_LABEL_") ? label + dateTime.getMillis() : label;
        this.parent = parent;
    }

    @Override
    public String toString() {
        return ("{" +
                (id == null ? "" : "\"id\": " + id + ", ") +
                (categoryName == null ? "" : "\"categoryName\": \"" + categoryName + "\", ") +
                (label == null ? "" : "\"label\":\"" + label + "\", ") +
                (parent == null ? "" : "\"parent\":\"" + parent + "\", ") +
                '}').replace(", }", "}");
    }
}
