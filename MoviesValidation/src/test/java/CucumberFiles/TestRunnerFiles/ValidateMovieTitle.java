package CucumberFiles.TestRunnerFiles;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/*    -------------------------------------------------------------
 * 		AUTHOR: Sathiyaseelan Pandiyan
 * 		AUTHOR EMAIL: seelan.pandiyan@gmail.com 
 *    ------------------------------------------------------------- */


@RunWith(Cucumber.class)
@CucumberOptions(
				features = {"src/test/java/CucumberFiles/FeatureFiles/MovieTitle.feature"},
				glue 	 = {"classpath:CucumberFiles/StepDefinitionFiles"},
				plugin 	 = {"pretty",
							"com.cucumber.listener.ExtentCucumberFormatter:CucumberReports/ListMovieTitles/ExtentReport_ListMovieTitles.html",
							"json:CucumberReports/ListMovieTitles/ExtentReport_ListMovieTitles.json",
							"junit:CucumberReports/ListMovieTitles/ExtentReport_ListMovieTitles.xml"}, dryRun = false, strict = false, monochrome = true)


public class ValidateMovieTitle 
{
	@AfterClass
	public static void report()
	{
		Reporter.loadXMLConfig("src/test/resources/extent-config.xml");
		Reporter.setSystemInfo("User Name", System.getProperty("user.name"));
		Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone").toUpperCase());
		Reporter.setSystemInfo("Operating System", System.getProperty("os.name").toUpperCase());
		Reporter.setSystemInfo("Java Version", System.getProperty("java.version").toUpperCase());
	}

}
