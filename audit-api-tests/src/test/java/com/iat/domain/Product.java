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
public class Product {

    private String id;
    private String brandId;
    private String categoryId;
    private String description;
    private String productName;
    private String url;

    private DateTime dateTime = new DateTime();
    private DataExchanger dataExchanger = DataExchanger.getInstance();

    public Product(@JsonProperty("id") String id,
                   @JsonProperty("brandId") String brandId,
                   @JsonProperty("categoryId") String categoryId,
                   @JsonProperty("description") String description,
                   @JsonProperty("productName") String productName,
                   @JsonProperty("url") String url) {


        if (id != null) {
            this.id = id;
            this.productName = productName;
            this.brandId = brandId;
            this.categoryId = categoryId;
            this.description = description;
            this.url = url;
        } else {
            this.id = id;
            this.productName = productName == null ? productName : productName.isEmpty() ? productName : productName.contains("API_AUDIT_CMS_PRODUCT_") ? productName + dateTime.getMillis() : productName;
            this.brandId = brandId == null ? brandId : brandId.isEmpty() ? brandId : brandId.contains("previous_call") ? dataExchanger.getBrands().get(dataExchanger.getBrands().size() - 1).getId() : brandId;
            this.categoryId = categoryId == null ? categoryId : categoryId.isEmpty() ? categoryId : categoryId.contains("previous_call") ? dataExchanger.getCategories().get(dataExchanger.getCategories().size() - 1).getId() : categoryId;
            this.description = description;
            this.url = url;
        }


    }

    @Override
    public String toString() {
        return ("{" +
                (id == null ? "" : "\"id\": " + id + ", ") +
                (brandId == null ? "" : "\"brandId\": \"" + brandId + "\", ") +
                (categoryId == null ? "" : "\"categoryId\": \"" + categoryId + "\", ") +
                (description == null ? "" : "\"description\": \"" + description + "\", ") +
                (productName == null ? "" : "\"productName\": \"" + productName + "\", ") +
                (url == null ? "" : "\"url\":\"" + url + "\", ") +
                '}').replace(", }", "}");
    }
}
