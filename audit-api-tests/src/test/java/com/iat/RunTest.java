package com.iat;

import com.jayway.restassured.RestAssured;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true,
        features = "src/test/resources",
        format = {"pretty", "json:build/reports/tests/cucumber/cucumber.json", "html:build/reports/tests/cucumber/html"},
        tags = "@MI")
public class RunTest {

    @BeforeClass
    public static void setRestAssuredProperties() {
        Config.setTestEnvironment(System.getProperty("environment.name"));
        System.out.println("\nENV: " + Config.testEnvironment);
        RestAssured.baseURI = Config.apiUrl();

        RestAssured.proxy("10.10.30.1", 8080);
        RestAssured.basePath = "";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
