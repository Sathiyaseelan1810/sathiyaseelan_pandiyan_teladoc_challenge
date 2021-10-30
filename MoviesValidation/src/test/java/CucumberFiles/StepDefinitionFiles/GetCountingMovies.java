package CucumberFiles.StepDefinitionFiles;

import static org.testng.Assert.assertEquals;

import com.cucumber.listener.Reporter;

import API_Wrapper.BuildAPIEndPoint;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import wrapperMethods.CustomizedMethods;


/*    -------------------------------------------------------------
 * 		AUTHOR: Sathiyaseelan Pandiyan
 * 		AUTHOR EMAIL: seelan.pandiyan@gmail.com 
 *    ------------------------------------------------------------- */



public class GetCountingMovies
{
	static String totalCounts;	
	static String totalPages;
	
	// Test Step1: Build the API End point and verify the status code by passing the substring value from the feature file
	@Given("^Build the API endpoint and verify the status code \"([^\"]*)\"$")
	public void build_the_API_endpoint_and_verify_the_status_code(String TitleName) throws Throwable 
	{		
		if(TitleName.equals("maze"))		
			Reporter.addStepLog(" **** Positive Scenario is Started **** ");
		else if(TitleName.equals("mazes"))		
			Reporter.addStepLog(" **** Negative Scenario is Started **** ");
		CustomizedMethods.fetchEndPoint(BuildAPIEndPoint.buildAPiEndPoint("Total_Movie_Counts"), TitleName);
	}

	// Test Step2: Get the total movie count from the endpoint
	@When("^Fetch the total movie count from the endpoint response \"([^\"]*)\" and \"([^\"]*)\"$")
	public void fetch_the_total_movie_count_from_the_endpoint_response(String TitleName, String TotalCount) throws Throwable 
	{
		totalCounts = CustomizedMethods.getNumberOfMoviesTotal(BuildAPIEndPoint.buildAPiEndPoint("Total_Movie_Counts"), TitleName, TotalCount);
	}
	
	// Test Step3: (Additional Step) Get the total page count from the endpoint
	@When("^Fetch the total page count from the endpoint response \"([^\"]*)\" and \"([^\"]*)\"$")
	public void fetch_the_page_movie_count_from_the_endpoint_response(String TitleName, String TotalPage) throws Throwable 
	{
		totalPages = CustomizedMethods.getNumberOfMoviesTotalPages(BuildAPIEndPoint.buildAPiEndPoint("Total_Movie_Counts"), TitleName, TotalPage);
	}

	// Test Step4: Implement the Assert condition to validate the expected vs actual for the total movie counts
	@Then("^Validate the total movie counts \"([^\"]*)\"$")
	public void validate_the_total_movie_counts(String TotalCount) throws Throwable 
	{
	   assertEquals(totalCounts, TotalCount);
	   Reporter.addStepLog("Actual = " + totalCounts + " " + "Expected = " + TotalCount);	   
	}
	
	// Test Step5: Implement the Assert condition to validate the expected vs actual for the total movie pages
	@Then("^Validate the total page counts \"([^\"]*)\"$")
	public void validate_the_page_movie_counts(String TotalPage) throws Throwable 
	{
	   assertEquals(totalPages, TotalPage);	 
	   Reporter.addStepLog("Actual = " + totalPages + " " + "Expected = " + TotalPage);
	}
	
	
	


}
