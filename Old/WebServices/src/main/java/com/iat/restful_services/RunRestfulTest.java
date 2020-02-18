package com.iat.restful_services;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@Cucumber.Options(monochrome = true, features = "src/main/resources/com/iat/restfultest/webserviceRetrievingPersonalDetailsW20.feature", format = {"pretty", "html:build/reports/tests/cucumber/html"})
public class RunRestfulTest {
	
}