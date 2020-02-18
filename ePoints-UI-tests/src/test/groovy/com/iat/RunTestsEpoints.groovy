package com.iat

import cucumber.api.CucumberOptions
import cucumber.api.junit.Cucumber
import io.restassured.RestAssured
import org.junit.BeforeClass
import org.junit.runner.RunWith

@RunWith(Cucumber.class)
@CucumberOptions(monochrome = false,
        features = "src/test/resources/features/EpointsFeatures",
        glue = "src/test/groovy/com/iat/stepdefs/",
        format = ["pretty", "html:build/reports/tests/cucumber/html", "json:build/reports/tests/cucumber.json"]
        //, tags = ["@test"]
)
class RunTestsEpoints {
    @BeforeClass
    static void setRestAssuredProperties() {
        Config.setTestEnvironment(System.getProperty("environment.name"))
        Config.setVideoRecordingAndScreenshotMaking(System.getProperty("screenshotAndVideo.isEnabled"))
        System.out.println("\nENV: " + Config.testEnvironment)
        System.out.println("\nVideo and screenshot enabled: " + Config.makeScreenshotAndVideo)
        RestAssured.baseURI = Config.getBaseUrl()
        RestAssured.port = 8911
        //RestAssured.proxy("10.10.30.1", 8080)
        RestAssured.basePath = ""
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails()
    }
}