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
@CucumberOptions(monochrome = false,
        features = "src/test/resources/",
        format = {"pretty", "json:build/reports/tests/cucumber/cucumber.json", "html:build/reports/tests/cucumber/html"}
//      ,tags = {"@tests"}
)
public class RunTest {

    @BeforeClass
    public static void setRestAssuredProperties() {
        Config.setTestEnvironment(System.getProperty("environment.name"));
        log.info("ENV: {}", Config.testEnvironment);
        RestAssured.baseURI = Config.getApiUrl();
//        RestAssured.proxy("10.10.30.1", 8080);
        RestAssured.basePath = "";
        RestAssured.config = RestAssured.config().logConfig(
                new LogConfig(new LoggingPrintStream(), true)
                        .enableLoggingOfRequestAndResponseIfValidationFails()
        );
    }
}
