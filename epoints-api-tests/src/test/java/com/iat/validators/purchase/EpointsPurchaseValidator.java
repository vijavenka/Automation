package com.iat.validators.purchase;

import com.iat.domain.purchase.BuyEpoints;
import com.iat.domain.purchase.BuyEpointsDetails;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class EpointsPurchaseValidator {

    public void validateBuyEpointsResponseParameters(BuyEpoints buyEpoints, BuyEpointsDetails buyEpointsDetails) {
        assertThat("Wrong moneyValue", Integer.parseInt(buyEpoints.getMoneyValue()), is(buyEpointsDetails.getMoneyValue()));
        assertThat("Wrong epoints", buyEpoints.getEpoints(), is(buyEpointsDetails.getEpoints()));
        assertThat("Wrong redirectUrl", buyEpointsDetails.getRedirectUrl().contains("paypal"));
        assertThat("Wrong tax", Double.parseDouble(buyEpoints.getMoneyValue()) * 0.21, is(buyEpointsDetails.getTax()));
        assertThat("Wrong fee", Double.parseDouble(buyEpoints.getMoneyValue()) * 0.05, is(buyEpointsDetails.getFee()));
        assertThat("Wrong total", Integer.parseInt(buyEpoints.getMoneyValue()) + Double.parseDouble(buyEpoints.getMoneyValue()) * 0.21 + Double.parseDouble(buyEpoints.getMoneyValue()) * 0.05, is(buyEpointsDetails.getTotal()));
    }
}