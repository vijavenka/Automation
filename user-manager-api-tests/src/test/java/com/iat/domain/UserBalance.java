package com.iat.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class UserBalance {

    private Integer confirmed;
    private Integer redeemed;
    private Integer declined;
    private Integer pending;

    public UserBalance() {
    }

    public UserBalance(int confirmed, int redeemed, int declined, int pending) {
        this.confirmed = confirmed;
        this.redeemed = redeemed;
        this.declined = declined;
        this.pending = pending;
    }

    @JsonIgnore
    @Override
    public String toString() {
        try {
            return new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return e.toString();
        }
    }
}
