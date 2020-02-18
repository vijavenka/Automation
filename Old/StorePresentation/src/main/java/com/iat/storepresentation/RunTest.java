package com.iat.storepresentation;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@Cucumber.Options(monochrome = true, features = "src/test/resources/com/iat/storepresentation/AdminPortal/AcceptanceTests/Bugs/")
public class RunTest {
}
