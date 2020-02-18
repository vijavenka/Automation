package com.iat.contracts;

public class HealthCheckContract {

    public String getHealthCheckPath() {
        return "/rest/healthCheck";
    }

    public String getHeartBeatPath() {
        return "/rest/heartbeat";
    }
}
