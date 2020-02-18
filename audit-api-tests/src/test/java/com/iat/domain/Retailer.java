package com.iat.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Retailer {

    private String id;
    private String retailerName;
    private String email;
    private String wholesalerId;
    private List<Chain> chains;
    private String uuid;
    private String balance;

    private DateTime dateTime = new DateTime();

    public Retailer() {
    }

    public Retailer(@JsonProperty("id") String id,
                    @JsonProperty("retailerName") String retailerName,
                    @JsonProperty("email") String email,
                    @JsonProperty("wholesalerId") String wholesalerId,
                    @JsonProperty("chains") List<Chain> chains,
                    @JsonProperty("epointsUuid") String uuid) {

        this.id = id;
        this.retailerName = retailerName == null ? retailerName : retailerName.isEmpty() ? retailerName : retailerName.contains("API_AUDIT_CMS_RETAILER_") ? retailerName + dateTime.getMillis() : retailerName;
        this.email = email == null ? email : email.isEmpty() ? email : !email.contains("@") ? email + "@test." + dateTime.getMillis() + ".iat" : email;
        this.wholesalerId = wholesalerId;
        this.chains = chains;
        this.uuid = uuid;

    }

    @Override
    public String toString() {
        return ("{" +
                (id == null ? "" : "\"id\": " + id + ", ") +
                (retailerName == null ? "" : "\"retailerName\": \"" + retailerName + "\", ") +
                (email == null ? "" : "\"email\":\"" + email + "\", ") +
                (chains == null ? "" : "\"chains\":" + chains + ", ") +
                (wholesalerId == null ? "" : "\"wholesalerId\":\"" + wholesalerId + "\", ") +
                '}').replace(", }", "}");
    }

}