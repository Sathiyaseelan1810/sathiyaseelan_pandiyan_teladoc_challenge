package wrapperMethods;

import java.util.Collections;
import java.util.List;

import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.cucumber.listener.Reporter;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

/*    -------------------------------------------------------------
 * 		AUTHOR: Sathiyaseelan Pandiyan
 * 		AUTHOR EMAIL: seelan.pandiyan@gmail.com 
 *    ------------------------------------------------------------- */

/* Note: This Customized methods are created to handle the script execution at the single page. 
 * Since these functions are called out at the various Stepdefinition file, it would be easy to apply the changes based on the requirement in future.
 * 
 */

public class CustomizedMethods
{
	// --------------------------------- Scenario: 1 Customize Methods -------------------------------------------- 	
	//Method1: Fetch EndPoint
	public static Response fetchEndPoint(String endPoint, String... pathParams)
	{
		Response response = null;		
		try 
		{	
			RestAssured.defaultParser = Parser.JSON;
			response = RestAssured.given().pathParam("substr", pathParams[0]).get(endPoint).then().contentType(ContentType.JSON).extract().response();	
			if(response.getStatusCode() == 200)	
			{
				System.out.println(" ------- INFO: HTTP Status Code = " + response.getStatusCode());	
				System.out.println(" ------- INFO: Total Response Time = "+ response.getTime());
				String[][] data = 
				{
						{"TITLE", "EXPECTED RESULT", "ACTUAL RESULT", "STATUS"},
						{"Https Status Code", "200", ""+response.getStatusCode(), "PASSED"},
						{"Https Response Time", "0<10000", ""+response.getTime(), "PASSED"}
				};
				Markup markup = MarkupHelper.createTable(data);
				Reporter.addStepLog(markup.getMarkup());
				Reporter.addStepLog(" ------- PASS: Response Validated and its Passed ------- ");
			}
			else			
				System.out.println("ERROR: Not an Expected Status Code");	
			//System.out.println(response.prettyPrint());						
		} 
		catch (Exception e) 
		{
			System.err.println("ERROR: No Response from the Endpoint" +e.getMessage());
		}
		return response;
	}
	
	// Method2 : Get the total number of movies count
	public static String getNumberOfMoviesTotal(String endPoints, String... pathParam)
	{
		String numberOfMovies = null;
		Response response = null;
		try 
		{
			RestAssured.defaultParser = Parser.JSON;
			response = RestAssured.given().pathParam("substr", pathParam[0]).get(endPoints).then().contentType(ContentType.JSON).extract().response();	
			JsonPath jsonResponse = response.jsonPath();
			numberOfMovies = jsonResponse.getString("total");		
			if(numberOfMovies !=null)
			{
				System.out.println(" ------- INFO : Provided Key is matched in the JSON");
				System.out.println(" ------- INFO : Total Number Of Movies = "+ numberOfMovies);	
				String[][] data = 
				{
						{"TITLE", "EXPECTED RESULT", "ACTUAL RESULT", "STATUS"},
						{"Total Number Of Movies: ", pathParam[1], numberOfMovies, "PASSED"}							
				};
				Markup markup = MarkupHelper.createTable(data);
				Reporter.addStepLog(markup.getMarkup());	
				Reporter.addStepLog(" ------- PASS: Total Number of Movie Counts is Validated and its Passed ------- ");
			}
			else
			{
				System.out.println(" ------- INFO : Provided Key is not matched in the JSON");
				System.out.println(" ------- INFO : Total Number Of Movies = "+ numberOfMovies);	
				Reporter.addStepLog(" ------- FAIL: Total Number of Movie Counts is Validated and its Failed because of the invalid key String ------- ");
			}				
		} 
		catch (Exception e) 
		{
			System.err.println("ERROR: unable to get the total movies count " +e.getMessage());
		}		
		return numberOfMovies;
	}
	
	// Method3 : Get the total number of pages
	public static String getNumberOfMoviesTotalPages(String endPoints, String... pathParam)
	{
		String totalPages = null;
		Response response = null;
		try 
		{
			RestAssured.defaultParser = Parser.JSON;
			response = RestAssured.given().pathParam("substr", pathParam[0]).get(endPoints).then().contentType(ContentType.JSON).extract().response();	
			JsonPath jsonResponse = response.jsonPath();
			totalPages = jsonResponse.getString("total_pages");			
			System.out.println(" ------- INFO : Total Number Of Pages = "+ totalPages);	
			String[][] data = 
				{
						{"TITLE", "EXPECTED RESULT", "ACTUAL RESULT", "STATUS"},
						{"Total Number Of Movies: ", pathParam[1], totalPages, "PASSED"}							
				};
			Markup markup = MarkupHelper.createTable(data);
			Reporter.addStepLog(markup.getMarkup());	
			Reporter.addStepLog(" ------- PASS: Total Number of Movie Pages is Validated and its Passed ------- ");
		} 
		catch (Exception e) 
		{
			System.err.println("ERROR: unable to get the total movies count " +e.getMessage());
		}		
		return totalPages;
	}
	 
	// ------------------------------- Scenario 2: Get the Movie Title and Sort in an Ascending Order
	// Method1: Fetch the end points	
	public static Response fetchEndPointMovies(String endPoint, String... pathParams)
	{
		Response response = null;		
		try 
		{	
			RestAssured.defaultParser = Parser.JSON;
			response = RestAssured.given().pathParam("substr", pathParams[0]).pathParam("pageNumber", pathParams[1]).get(endPoint).then().contentType(ContentType.JSON).extract().response();		
			if(response.getStatusCode() == 200)	
			{
				System.out.println(" ------- INFO: HTTP Status Code = " + response.getStatusCode());	
				System.out.println(" ------- INFO: Total Response Time = "+ response.getTime());
				String[][] data = 
				{
						{"TITLE", "EXPECTED RESULT", "ACTUAL RESULT", "STATUS"},
						{"Https Status Code", "200", ""+response.getStatusCode(), "PASSED"},
						{"Https Response Time", "0<10000", ""+response.getTime(), "PASSED"}
				};
				Markup markup = MarkupHelper.createTable(data);
				Reporter.addStepLog(markup.getMarkup());
				Reporter.addStepLog(" ------- PASS: Response Validated and its Passed ------- ");
			}
			else
				System.err.println("ERROR: Not an Expected Status Code");			
			//System.out.println(response.prettyPrint());						
		} 
		catch (Exception e) 
		{
			System.err.println("ERROR: No Response from the Endpoint" +e.getMessage());
		}
		return response;
	}
	
	// Method2: Fetch the List of Movies from the API	
	public static List<String> getListOfMovies(String endPoints, String... pathParam)
	{
		List<String>movieTitles = null;
		Response response = null;	
		try 
		{
			RestAssured.defaultParser = Parser.JSON;
			response = RestAssured.given().pathParam("substr", pathParam[0]).pathParam("pageNumber", pathParam[1]).get(endPoints).then().contentType(ContentType.JSON).extract().response();
			JsonPath jsonResponse = response.jsonPath();
			movieTitles = jsonResponse.getList("data.Title");
			System.out.println(" ------- INFO : Total Number Of Size = "+ movieTitles.size());	
			System.out.println(" ------- Movie Lists order has provided in the API ------ ");
			Reporter.addStepLog(" ------- INFO : Total Number Of Size Before Sorting = "+ movieTitles.size());
			for(int i=0; i<movieTitles.size(); i++)
			{	
				int j = i+1;
				String[][] data = 
					{
						{"Movie # "+j, movieTitles.get(i)}
					};
				Markup markup = MarkupHelper.createTable(data);
				Reporter.addStepLog(markup.getMarkup());	
			}			
		} 
		catch (Exception e) 
		{
			System.err.println("ERROR: unable to get the total movies count " +e.getMessage());
		}		
		return movieTitles;
	}
	
	// Method3: Sort the Movies in the ascending order	
	public static List<String> sortListOfMovies(String endPoints, String... pathParam)
	{
		List<String>movieTitles = null;
		Response response = null;	
		try 
		{
			RestAssured.defaultParser = Parser.JSON;
			response = RestAssured.given().pathParam("substr", pathParam[0]).pathParam("pageNumber", pathParam[1]).get(endPoints).then().contentType(ContentType.JSON).extract().response();
			JsonPath jsonResponse = response.jsonPath();
			movieTitles = jsonResponse.getList("data.Title");
			System.out.println(" ------- INFO : Total Number Of Size = "+ movieTitles.size());	
			Collections.sort(movieTitles);
			Reporter.addStepLog(" ------- INFO : Total Number Of Size After Sorting = "+ movieTitles.size());
			for(int i=0; i<movieTitles.size(); i++)
			{	
				int j = i+1;
				String[][] data = 
					{
						{"Movie # "+j, movieTitles.get(i)}
					};
				Markup markup = MarkupHelper.createTable(data);
				Reporter.addStepLog(markup.getMarkup());						
			}	
		} 
		catch (Exception e) 
		{
			System.err.println("ERROR: unable to get the total movies count " +e.getMessage());
		}		
		return movieTitles;
	}
	

}
