package com.iat.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper

@JsonIgnoreProperties(ignoreUnknown = true)
class UserBalance {

    private int confirmed
    private int redeemed
    private int declined
    private int pending

    int getConfirmed() {
        return confirmed
    }

    void setConfirmed(int confirmed) {
        this.confirmed = confirmed
    }

    int getRedeemed() {
        return redeemed
    }

    void setRedeemed(int redeemed) {
        this.redeemed = redeemed
    }

    int getDeclined() {
        return declined
    }

    void setDeclined(int declined) {
        this.declined = declined
    }

    int getPending() {
        return pending
    }

    void setPending(int pending) {
        this.pending = pending
    }

    @JsonIgnore
    @Override
    String toString() {
        try {
            return new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(this)
        } catch (JsonProcessingException e) {
            e.printStackTrace()
            return e.toString()
        }
    }
}
