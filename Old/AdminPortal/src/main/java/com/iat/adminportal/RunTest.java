package com.iat.adminportal;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true,
        features = "src/test/resources",
        format = {"pretty", "html:build/reports/tests/cucumber/html", "json:build/reports/tests/cucumber.json"},
        tags = {"@test"})
public class RunTest {
    @BeforeClass
    public static void setRestAssuredProperties() {
        EnvironmentVariables.setTestEnvironment(System.getProperty("environment.name"));
        System.out.println("\nENV: " + EnvironmentVariables.environment);
        RestAssured.proxy("10.10.30.1", 8080);
    }
}