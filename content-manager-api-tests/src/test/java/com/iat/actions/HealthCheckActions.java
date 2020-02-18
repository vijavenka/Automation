package com.iat.actions;

import com.iat.controller.HealthCheckController;

public class HealthCheckActions {

    private HealthCheckController healthCheckController = new HealthCheckController();

    public void checkIfContentManagerIsResponding() {
        healthCheckController.doHealthCheck(200);
    }
}