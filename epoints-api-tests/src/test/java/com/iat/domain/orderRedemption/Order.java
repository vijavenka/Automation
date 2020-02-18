package com.iat.domain.orderRedemption;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iat.domain.AbstractDomain;
import com.iat.domain.userProfile.Address;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order extends AbstractDomain {


    private String id;
    private Address address;
    private List<Product> items;
    private Integer totalEpoints;

    public Order() {
    }

    public Order(Address address, List<Product> items, Integer totalEpoints) {
        this.setAddress(address);
        this.setItems(items);
        this.setTotalEpoints(totalEpoints);
    }
}
