package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
        features = "src/test/resources/features",
        glue = {"steps"},
        plugin = {"json:target/reports/CucumberReports.json", "pretty", "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm"},
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        dryRun = false)
public class RunCucumberTests {
}
