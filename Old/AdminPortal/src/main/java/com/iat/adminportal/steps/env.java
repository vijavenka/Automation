package com.iat.adminportal.steps;

import com.iat.adminportal.executors.IExecutor;
import com.iat.adminportal.executors.SeleniumExecutor;
import cucumber.api.java.After;
import cucumber.api.java.Before;

/**
 * Created by kbaranowski on 2017-12-13.
 */
public class env {
    DataExchanger dataExchanger = DataExchanger.getInstance();
    IExecutor executor;

    @Before
    public void set_up() {
        if (dataExchanger.getExecutor() == null) {
            this.executor = new SeleniumExecutor();
            dataExchanger.setExecutor(executor);
        } else {
            this.executor = dataExchanger.getExecutor();
        }
    }

    @After
    public void after() {
        executor.stop();
    }
}