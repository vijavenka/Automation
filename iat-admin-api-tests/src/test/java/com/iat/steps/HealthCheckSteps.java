package com.iat.steps;

import com.iat.actions.HealthCheckActions;
import cucumber.api.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by miwanczyk on 2016-01-19.
 */
public class HealthCheckSteps {

    private HealthCheckActions healthCheckActions = new HealthCheckActions();


    @When("^IAT Admin is responding to requests$")
    public void iatAdminResponding() throws Throwable {
        assertThat("IAT Admin not Respondek (HTTP/1.1 200)", healthCheckActions.isHealthCheckStatusOK());
    }


}
