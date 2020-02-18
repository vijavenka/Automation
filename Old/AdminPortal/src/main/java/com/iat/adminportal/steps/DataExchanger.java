package com.iat.adminportal.steps;

import com.iat.adminportal.executors.IExecutor;

public class DataExchanger {

    private static DataExchanger dataExchanger;

    private DataExchanger() {
    }

    IExecutor executor = null;

    public static DataExchanger getInstance() {

        if (dataExchanger == null) {
            dataExchanger = new DataExchanger();
        }
        return dataExchanger;
    }

    public IExecutor getExecutor() {
        return executor;
    }

    public void setExecutor(IExecutor executor) {
        this.executor = executor;
    }

}