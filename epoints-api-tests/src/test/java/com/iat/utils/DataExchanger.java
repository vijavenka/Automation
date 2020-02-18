package com.iat.utils;

/* Implementation of SessionIdKeeper was done in Singleton convention, please do not modify
*  To get instance of JsonParserUtils for usage, you have to directly add in method:
*  JsonParserUtils jsonParser = JsonParserUtils.getInstance();
*
*  Methods:
*  convertStringToJson - converts provided string to JsonObject
*  extractValueFromFlatJson - returns string value of provided jsonKey within JsonObject
*  extractValuesFromJsonArray - returns StringArray of all values connected with jsonKey.
*                              Method takes as input JsonObject, jsonArrayKey and jsonKey of
*                              seek value.
*
* */

import com.iat.domain.LoginSuccess;
import com.iat.domain.OAuth;
import com.iat.domain.TransitionTo;
import com.iat.domain.UserBalance;
import com.iat.domain.couponUsage.CouponUsageCashIn;
import com.iat.domain.orderRedemption.Order;
import com.iat.domain.orderRedemption.RedemptionItem;
import com.iat.domain.transactions.ClickoutDb;
import com.iat.domain.transactions.PointsDetails;
import com.iat.domain.userProfile.Address;
import com.iat.domain.userProfile.UserDetailsDoorman;
import com.iat.domain.userProfile.UserProfile;

import java.util.ArrayList;
import java.util.List;

public class DataExchanger {

    private static DataExchanger dataExchanger;

    private DataExchanger() {
    }

    private String uuid;
    private String token;
    private String email;
    private String password;
    private UserBalance userBalance;
    private ResponseContainer userCouponUsagePointsSummary;
    private CouponUsageCashIn couponUsageCashIn;
    private String externalId;
    private List<RedemptionItem> redemptionItem = new ArrayList<>();
    private Address deliveryAddress;
    private Order order;
    private UserProfile userProfile;
    private TransitionTo transitionTo;
    private ClickoutDb clickoutDb;
    private PointsDetails pointsDetails;
    private boolean spinUsed = false;
    private OAuth oAuth;
    private UserDetailsDoorman userDetailsDoorman;
    private ResponseContainer responseContainer;
    private LoginSuccess loginSuccess;

    public static DataExchanger getInstance() {
        if (dataExchanger == null)
            dataExchanger = new DataExchanger();
        return dataExchanger;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ResponseContainer getUserCouponUsagePointsSummary() {
        return userCouponUsagePointsSummary;
    }

    public void setUserCouponUsagePointsSummary(ResponseContainer userCouponUsagePointsSummary) {
        this.userCouponUsagePointsSummary = userCouponUsagePointsSummary;
    }

    public UserBalance getUserBalance() {
        return userBalance;
    }

    public void setUserBalance(UserBalance userBalance) {
        this.userBalance = userBalance;
    }

    public CouponUsageCashIn getCouponUsageCashIn() {
        return couponUsageCashIn;
    }

    public void setCouponUsageCashIn(CouponUsageCashIn couponUsageCashIn) {
        this.couponUsageCashIn = couponUsageCashIn;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public RedemptionItem getRedemptionItem(int itemNumber) {
        return redemptionItem.get(itemNumber);
    }

    public void setRedemptionItem(RedemptionItem redemptionItem) {
        this.redemptionItem.add(redemptionItem);
    }

    public void resetProductsList() {
        this.redemptionItem.clear();
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public TransitionTo getTransitionTo() {
        return transitionTo;
    }

    public void setTransitionTo(TransitionTo transitionTo) {
        this.transitionTo = transitionTo;
    }

    public ClickoutDb getClickoutDb() {
        return clickoutDb;
    }

    public void setClickoutDb(ClickoutDb clickoutDb) {
        this.clickoutDb = clickoutDb;
    }

    public PointsDetails getPointsDetails() {
        return pointsDetails;
    }

    public void setPointsDetails(PointsDetails pointsDetails) {
        this.pointsDetails = pointsDetails;
    }


    public boolean isSpinUsed() {
        return spinUsed;
    }

    public void setSpinUsed(boolean spinUsed) {
        this.spinUsed = spinUsed;
    }

    public OAuth getoAuth() {
        return oAuth;
    }

    public void setoAuth(OAuth oAuth) {
        this.oAuth = oAuth;
    }

    public UserDetailsDoorman getUserDetailsDoorman() {
        return userDetailsDoorman;
    }

    public void setUserDetailsDoorman(UserDetailsDoorman userDetailsDoorman) {
        this.userDetailsDoorman = userDetailsDoorman;
    }

    public ResponseContainer getResponseContainer() {
        return responseContainer;
    }

    public void setResponseContainer(ResponseContainer responseContainer) {
        this.responseContainer = responseContainer;
    }

    public LoginSuccess getLoginSuccess() {
        return loginSuccess;
    }

    public void setLoginSuccess(LoginSuccess loginSuccess) {
        this.loginSuccess = loginSuccess;
    }
}