package CucumberFiles.StepDefinitionFiles;
import API_Wrapper.BuildAPIEndPoint;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import wrapperMethods.CustomizedMethods;

/*    -------------------------------------------------------------
 * 		AUTHOR: Sathiyaseelan Pandiyan
 * 		AUTHOR EMAIL: seelan.pandiyan@gmail.com 
 *    ------------------------------------------------------------- */


public class GetMovieTitle
{
	// Test Step1: Build the API End point and verify the status code by passing the substring value from the feature file
	@Given("^Build the API endpoint and verify the status code \"([^\"]*)\" and \"([^\"]*)\"$")
	public void build_the_API_endpoint_and_verify_the_status_code_and(String TitleName, String PageNumber) throws Throwable 
	{
	  CustomizedMethods.fetchEndPointMovies(BuildAPIEndPoint.buildAPiEndPoint("Movie_Name_Lists"), TitleName, PageNumber);
	}

	// Test Step2: Fetch the movie lists per page 	
	@When("^Fetch the movie lists per page from the endpoint response \"([^\"]*)\" and \"([^\"]*)\"$")
	public void fetch_the_movie_lists_per_page_from_the_endpoint_response_and(String TitleName, String PageNumber) throws Throwable 
	{
		CustomizedMethods.getListOfMovies(BuildAPIEndPoint.buildAPiEndPoint("Movie_Name_Lists"), TitleName, PageNumber);
	}

	// Test Step3: Arrange the movie lists in an ascending order
	@Then("^Arrange the movie lists in ascending order \"([^\"]*)\" and \"([^\"]*)\"$")
	public void arrange_the_movie_lists_in_ascending_order_and(String TitleName, String PageNumber) throws Throwable 
	{
		CustomizedMethods.sortListOfMovies(BuildAPIEndPoint.buildAPiEndPoint("Movie_Name_Lists"), TitleName, PageNumber);
	}

	
	
	
}
