import io.restassured.RestAssured
import cucumber.api.CucumberOptions
import cucumber.api.junit.Cucumber
import org.junit.BeforeClass
import org.junit.runner.RunWith

@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true,
        features = "src/test/resources/features/",
        glue = "src/test/groovy/com/iat/stepdefs",
        format = ["pretty", "html:build/reports/tests/cucumber/html", "json:build/reports/tests/cucumber.json"],
        tags = ["@test"]
)
class RunTests {
    @BeforeClass
    public static void setRestAssuredProperties() {
        RestAssured.proxy("10.10.30.1", 8080);
    }
}