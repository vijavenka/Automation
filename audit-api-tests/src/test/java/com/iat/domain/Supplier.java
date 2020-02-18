package com.iat.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_ABSENT)
@Getter
@Setter
public class Supplier {

    private String id;
    private String supplierName;
    private String partnerApiKey;

    private DateTime dateTime = new DateTime();

    public Supplier() {
    }

    public Supplier(@JsonProperty("id") String id,
                    @JsonProperty("supplierName") String supplierName,
                    @JsonProperty("partnerApiKey") String partnerApiKey) {

        if (id != null) {
            this.id = id;
            this.supplierName = supplierName;
            this.partnerApiKey = partnerApiKey;
        } else {
            this.id = id;
            this.supplierName = supplierName == null ? supplierName : supplierName.isEmpty() ? supplierName : supplierName.contains("API_AUDIT_CMS_SUPPLIER_") ? supplierName + dateTime.getMillis() : supplierName;
            this.partnerApiKey = partnerApiKey;
        }

    }

    @Override
    public String toString() {
        return ("{" +
                (id == null ? "" : "\"id\": " + id + ", ") +
                (supplierName == null ? "" : "\"supplierName\": \"" + supplierName + "\", ") +
                (partnerApiKey == null ? "" : "\"partnerApiKey\":\"" + partnerApiKey + "\", ") +
                '}').replace(", }", "}");
    }
}
