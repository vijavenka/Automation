package com.iat.actions;


import com.iat.controller.HealthCheckController;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;

public class HealthCheckActions {

    private HealthCheckController healthCheckController = new HealthCheckController();

    public ResponseContainer getHealthCHeck(int status) {
        return initResponseContainer(healthCheckController.getHealthCheck(status));
    }

    public ResponseContainer getHeartBeat(int status) {
        return initResponseContainer(healthCheckController.getHeartBeat(status));
    }

}
