package com.iat.steps;

import com.iat.repository.UserRepository;
import com.iat.repository.impl.UserRepositoryImpl;
import com.iat.utils.DataExchanger;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;


public class HooksSteps {

    private UserRepository userRepository = new UserRepositoryImpl();
    private DataExchanger dataExchanger = DataExchanger.getInstance();

    @After("@deleteUserAfterTest")
    public void deleteUserById() throws Throwable {
        userRepository.removeUserById(dataExchanger.getUuid());
    }

    @Before
    public void beforeAll(Scenario scenario) {
        if (scenario.getSourceTagNames().contains("@Ews"))
            dataExchanger.setEwsRequest(true);
        else
            dataExchanger.setEwsRequest(false);
    }

}