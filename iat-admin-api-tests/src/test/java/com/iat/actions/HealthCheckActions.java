package com.iat.actions;


import com.iat.controller.HealthCheckController;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;

public class HealthCheckActions {

    private HealthCheckController healthCheckController = new HealthCheckController();

    public ResponseContainer doHealthCheck() {
        return initResponseContainer(healthCheckController.doHealthCheck(), "");
    }

    public boolean isHealthCheckStatusOK() {
        doHealthCheck().getValidatableResponse().assertThat().statusLine("HTTP/1.1 200 ");
        return true;
    }
}
