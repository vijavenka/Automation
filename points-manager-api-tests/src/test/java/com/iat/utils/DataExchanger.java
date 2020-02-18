package com.iat.utils;


import com.iat.domain.Points;

import java.util.ArrayList;
import java.util.List;

public class DataExchanger {

    private static DataExchanger dataExchanger;
    private List<Points> pointsList = new ArrayList<>();
    private boolean ewsRequest = false;

    private DataExchanger() {
    }

    public static DataExchanger getInstance() {

        if (dataExchanger == null)
            dataExchanger = new DataExchanger();
        return dataExchanger;
    }

    public List<Points> getPointsList() {
        return pointsList;
    }

    public void setPointsList(List<Points> pointsList) {
        this.pointsList = pointsList;
    }

    public boolean isEwsRequest() {
        return ewsRequest;
    }

    public void setEwsRequest(boolean ewsRequest) {
        this.ewsRequest = ewsRequest;
    }
}

