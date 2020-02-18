package com.iat.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class UserBalance {

    private final int confirmed;
    private final int redeemed;
    private final int declined;
    private final int pending;

    public UserBalance(
            @JsonProperty("confirmed")
                    int confirmed,
            @JsonProperty("redeemed")
                    int redeemed,
            @JsonProperty("declined")
                    int declined,
            @JsonProperty("pending")
                    int pending
    ) {
        this.confirmed = confirmed;
        this.redeemed = redeemed;
        this.declined = declined;
        this.pending = pending;
    }

    @Override
    public String toString() {
        return "UserBalance{" +
                "confirmed=" + confirmed +
                ", redeemed=" + redeemed +
                ", declined=" + declined +
                ", pending=" + pending +
                '}';
    }
}
