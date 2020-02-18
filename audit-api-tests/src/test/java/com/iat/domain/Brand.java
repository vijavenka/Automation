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
public class Brand {

    private String id;
    private String brandName;
    private String supplierId;

    private DateTime dateTime = new DateTime();
    private DataExchanger dataExchanger = DataExchanger.getInstance();

    public Brand(@JsonProperty("id") String id,
                 @JsonProperty("brandName") String brandName,
                 @JsonProperty("supplierId") String supplierId) {


        if (id != null) {
            this.id = id;
            this.brandName = brandName;
            this.supplierId = supplierId;

        } else {
            this.id = id;
            this.brandName = brandName == null ? brandName : brandName.isEmpty() ? brandName : brandName.contains("API_AUDIT_CMS_BRAND_") ? brandName + dateTime.getMillis() : brandName;
            this.supplierId = supplierId == null ? supplierId : supplierId.isEmpty() ? supplierId : supplierId.contains("previous_call") ? dataExchanger.getSuppliers().get(dataExchanger.getSuppliers().size() - 1).getId() : supplierId;

        }
    }

    @Override
    public String toString() {
        return ("{" +
                (id == null ? "" : "\"id\": " + id + ", ") +
                (brandName == null ? "" : "\"brandName\": \"" + brandName + "\", ") +
                (supplierId == null ? "" : "\"supplierId\":" + supplierId + ", ") +
                '}').replace(", }", "}");
    }
}
