package com.iat

import cucumber.api.CucumberOptions
import cucumber.api.junit.Cucumber
import io.restassured.RestAssured
import org.junit.BeforeClass
import org.junit.runner.RunWith

@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true,
        features = "src/test/resources/features",
        glue = "src/test/groovy/com/iat/stepdefs",
        format = ["pretty", "html:build/reports/tests/cucumber/html", "json:build/reports/tests/cucumber.json"]
        //,tags = ["@test"]
)
class RunTests {
    @BeforeClass
    static void setRestAssuredProperties() {
        Config.setTestEnvironment(System.getProperty("environment.name"))
        Config.setVideoRecordingAndScreenshotMaking(System.getProperty("screenshotAndVideo.isEnabled"))
        System.out.println("\nENV: " + Config.environment)
        System.out.println("Video and screenshot enabled: " + Config.makeScreenshotAndVideo)
        RestAssured.proxy("10.10.30.1", 8080)
    }
}