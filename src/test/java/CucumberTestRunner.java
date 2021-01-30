import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "json:target/cucumber-pretty/cucumber.json"},
        features = {"src/test/resources/features"},
        glue = {"stepdefs"},
        tags = "@Test")

public class CucumberTestRunner {
}
