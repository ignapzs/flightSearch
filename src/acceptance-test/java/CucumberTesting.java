import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        format = {
                "pretty", "html:target/cucumber-html-report",
                "json:target/cucumber_report.json"},
        features = "src/acceptance-test/resources/features/FlightSearch.feature",
        glue = "net.ignapzs.flightSearch.acceptanceTest"
)
public class CucumberTesting {

}

