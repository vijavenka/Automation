package com.iat.actions;

import com.iat.controller.HealthCheckController;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;
import static org.hamcrest.Matchers.containsString;

public class HealthCheckActions {

    private HealthCheckController healthCheckController = new HealthCheckController();

    public ResponseContainer checkIfHealthCheckStatusOK(int status) {
        return initResponseContainer(healthCheckController.checkIfHealthCheckStatusOK(status));
    }

    public boolean checkIfPointsManagerIsResponding() {
        ResponseContainer response = checkIfHealthCheckStatusOK(200);
        response.getValidatableResponse()
                .assertThat().statusLine(containsString("HTTP/1.1 200"));
        return true;
    }
}