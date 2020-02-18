package com.iat.contracts;

/**
 * Created by miwanczyk on 2016-01-20.
 */
public class HealthCheckContract {

    public String getHealthCheckPath() {
        return "/heartbeat";
    }

}
