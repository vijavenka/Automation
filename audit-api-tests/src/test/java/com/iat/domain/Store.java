package com.iat.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Store {

    private String id;
    private String storeName;
    private String licensed;
    private String active;
    private String auditGroup;
    private String storeType;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressLine4;
    private String postCode;
    private String country;
    private String extRelId;
    private String bigDlBranchId;
    private String chainId;
    private String retailerId;

    public Store(@JsonProperty("id") String id,
                 @JsonProperty("storeName") String storeName,
                 @JsonProperty("licensed") String licensed,
                 @JsonProperty("active") String active,
                 @JsonProperty("auditGroup") String auditGroup,
                 @JsonProperty("storeType") String storeType,
                 @JsonProperty("addressLine1") String addressLine1,
                 @JsonProperty("addressLine2") String addressLine2,
                 @JsonProperty("addressLine3") String addressLine3,
                 @JsonProperty("addressLine4") String addressLine4,
                 @JsonProperty("postCode") String postCode,
                 @JsonProperty("country") String country,
                 @JsonProperty("extRelId") String extRelId,
                 @JsonProperty("bigDlBranchId") String bigDlBranchId,
                 @JsonProperty("chainId") String chainId,
                 @JsonProperty("retailerId") String retailerId) {

        this.id = id;
        this.storeName = storeName;
        this.licensed = licensed;
        this.active = active;
        this.auditGroup = auditGroup;
        this.storeType = storeType;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.addressLine3 = addressLine3;
        this.addressLine4 = addressLine4;
        this.postCode = postCode;
        this.country = country;
        this.extRelId = extRelId;
        this.bigDlBranchId = bigDlBranchId;
        this.chainId = chainId;
        this.retailerId = retailerId;

    }

    public String toString() {
        return "{\n" +
                "\"storeName\": \"" + storeName + "\",\n" +
                "\"licensed\": " + licensed + ",\n" +
                "\"active\": " + active + ",\n" +
                "\"auditGroup\": \"" + auditGroup + "\",\n" +
                "\"storeType\": \"" + storeType + "\",\n" +
                "\"addressLine1\": \"" + addressLine1 + "\",\n" +
                "\"addressLine2\": \"" + addressLine2 + "\",\n" +
                "\"addressLine3\": \"" + addressLine3 + "\",\n" +
                "\"addressLine4\": \"" + addressLine4 + "\",\n" +
                "\"postCode\": \"" + postCode + "\",\n" +
                "\"country\": \"" + country + "\",\n" +
                "\"extRelId\": " + extRelId + ",\n" +
                "\"bigDlBranchId\": " + bigDlBranchId + ",\n" +
                "\"chainId\": " + chainId + ",\n" +
                "\"retailerId\": " + retailerId + "\n" +
                "}";
    }
}