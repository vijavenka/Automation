import cucumber.api.CucumberOptions
import cucumber.api.junit.Cucumber
import org.junit.runner.RunWith

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", format = ["pretty", "html:build/reports/tests/cucumber/html", "json:build/reports/tests/cucumber.json"])
class RunTestsAdminPortal {
}
