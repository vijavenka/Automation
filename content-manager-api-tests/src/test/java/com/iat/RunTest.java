package com.iat;

import io.restassured.RestAssured;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true,
        features = "src/test/resources/",
        format = {"pretty", "json:build/reports/tests/cucumber/cucumber.json", "html:build/reports/tests/cucumber/html"},
        tags = {"@test"})
public class RunTest {

    @BeforeClass
    public static void setRestAssuredProperties() {
        RestAssured.baseURI = "https://qa-content.epoints.com";
        RestAssured.port = 443;
        RestAssured.basePath = "";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}