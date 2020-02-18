package com.iat.utils;

import com.iat.domain.User;
import com.iat.domain.orderRedemption.Order;
import com.iat.domain.pointsAllocation.MultiplePointsTransaction;
import com.iat.domain.pointsAllocation.PointsTransaction;
import com.iat.domain.pointsAllocation.PointsTransactionUpdate;
import com.iat.repository.UserRepository;
import com.iat.repository.impl.UserRepositoryImpl;

public class ResponseHolder {

    private static ResponseHolder responseHolder = new ResponseHolder();

    private ResponseContainer response;
    private String userId;
    private String userIdType;
    private User user;
    private UserRepository userRepository = new UserRepositoryImpl();
    private PointsTransaction pointsTransaction;
    private PointsTransactionUpdate pointsTransactionUpdate;
    private Order order;
    private String orderTransactionId;

    private MultiplePointsTransaction multiplepointsTransaction;

    private ResponseHolder() {
    }

    public static ResponseHolder getInstance() {
        return responseHolder;
    }

    public ResponseContainer getResponse() {
        return response;
    }

    public void setResponse(ResponseContainer response) {
        this.response = response;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserIdType() {
        return userIdType;
    }

    public void setUserIdType(String userIdType) {
        this.userIdType = userIdType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUserIdBasedOnType(String userId, String idType) {
        setUserId((idType.equals("UUID")) ? userId : userRepository.findByEmail(userId).getUuid());
    }

    public PointsTransaction getPointsTransaction() {
        return pointsTransaction;
    }

    public void setPointsTransaction(PointsTransaction pointsTransaction) {
        this.pointsTransaction = pointsTransaction;
    }

    public MultiplePointsTransaction getMultiplepointsTransaction() {
        return multiplepointsTransaction;
    }

    public void setMultiplepointsTransaction(MultiplePointsTransaction multiplepointsTransaction) {
        this.multiplepointsTransaction = multiplepointsTransaction;
    }

    public PointsTransactionUpdate getPointsTransactionUpdate() {
        return pointsTransactionUpdate;
    }

    public void setPointsTransactionUpdate(PointsTransactionUpdate pointsTransactionUpdate) {
        this.pointsTransactionUpdate = pointsTransactionUpdate;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getOrderTransactionId() {
        return orderTransactionId;
    }

    public void setOrderTransactionId(String orderTransactionId) {
        this.orderTransactionId = orderTransactionId;
    }
}