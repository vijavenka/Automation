package com.iat.b2btests;

import org.junit.runner.RunWith;

import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
//@Cucumber.Options(format = {"pretty", "html:build/reports/tests/cucumber/html", "json:build/reports/tests/cucumber.json"})
@Cucumber.Options(monochrome = true, features = "src/test/resources/progress.feature", format = {"pretty", "html:build/reports/tests/cucumber/html"})
public class RunTest {
}
