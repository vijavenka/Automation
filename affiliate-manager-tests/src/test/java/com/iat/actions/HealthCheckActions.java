package com.iat.actions;

import com.iat.controller.HealthCheckController;

public class HealthCheckActions {

    private HealthCheckController healthCheckController = new HealthCheckController();

    public void doHealthCheck() {
        System.out.println("Health check response should be empty.");
        healthCheckController.getHealthCheck().extract().response().prettyPrint();
    }
}