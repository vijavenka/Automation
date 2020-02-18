package com.iat.domain.EcardsConfig;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iat.Config;
import com.iat.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class PointsAllocation extends AbstractDomain {

    private String receiverId;
    private String message;
    private String amount;
    private char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPRSTUVWXYZ".toCharArray();

    public PointsAllocation() {
    }

    public PointsAllocation(String receiverId, String message, String amount) {
        this.setReceiverId(receiverId);
        this.setMessage(message);
        this.setAmount(amount);
    }

    public void setReceiverId(String receiverId) {
        if (receiverId != null)
            receiverId = Config.getDepartmentIdForName(receiverId);
        this.receiverId = receiverId;
    }

    public void setMessage(String message) {
        if (message != null)
            message = chars[new Random().nextInt(chars.length)] + " " + message;
        this.message = message;
    }
}