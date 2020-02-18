package com.iat.ePoints;

import org.junit.runner.RunWith;

import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
//@Cucumber.Options(format = {"pretty", "html:build/reports/tests/cucumber/html", "json:build/reports/tests/cucumber.json"})
//main
//@Cucumber.Options(monochrome = true, features = "src/test/resources/com/iat/ePoints/Done/SignIn")
@Cucumber.Options(monochrome = true, features = "src/test/resources/com/iat/ePoints/Done/Get")
//format = {"pretty", "html:build/reports/tests/cucumber/html"})

//delete user from db
//@Cucumber.Options(monochrome = true, features = "src/test/resources/com/iat/ePoints/Done/Administration.feature")

public class RunTest {
}

//