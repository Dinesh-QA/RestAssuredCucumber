package cucumber.Options;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

import java.io.File;

@CucumberOptions(
		// Path to your .feature files
		features = "src/test/java/features",

		// Step definition package (package name, not file path)
		glue = { "stepdefinations" },

		// Run only scenarios tagged with @FirstTest
		tags = "@LoginApp or @PlaceProduct or @PlaceOrder",

		// Reporting plugins
		plugin = { "pretty", "json:target/cucumber-reports/cucumber.json",
				"html:target/cucumber-reports/html-report.html" }, monochrome = true)

public class TestRunner extends AbstractTestNGCucumberTests {

}
