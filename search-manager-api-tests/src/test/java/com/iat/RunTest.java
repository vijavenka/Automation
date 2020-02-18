package com.iat;

import com.iat.utils.logging.LoggingPrintStream;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@Slf4j
@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true,
        features = "src/test/resources/",
        format = {"pretty", "json:build/reports/tests/cucumber/cucumber.json", "html:build/reports/tests/cucumber/html"}
        //, tags = {"@test"}
)
public class RunTest {

    @BeforeClass
    public static void setRestAssuredProperties() {
        RestAssured.baseURI = "http://test-proxy-live-01.iatlimited.com";
        RestAssured.port = 8983;
        //RestAssured.proxy("10.10.30.1", 8080);
        RestAssured.basePath = "";
        RestAssured.config = RestAssured.config().logConfig(
                new LogConfig(new LoggingPrintStream(), true)
                        .enableLoggingOfRequestAndResponseIfValidationFails()
        );
    }
}
