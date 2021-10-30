Feature: Get the Total Number of Movies Count from the Provided API

  Scenario Outline: Use the Provided API Endpoint and get the total number of movies count based on the movietitle
    Given Build the API endpoint and verify the status code "<TitleName>"
    When Fetch the total movie count from the endpoint response "<TitleName>" and "<TotalCount>"
    And Fetch the total page count from the endpoint response "<TitleName>" and "<TotalPage>"
    Then Validate the total movie counts "<TotalCount>"
    And Validate the total page counts "<TotalPage>"

    Examples: 
      | TitleName | TotalCount | TotalPage |
      | maze      |         97 |        10 |
      | mazes     |          0 |         0 |
      
      
  # Note: This Feature file drive the execution in the below order by passing the user provided parameters.
  # Execution runs 2 times as it created with the pipe iterator concept
  # Execution 1 will take the SubString Parameter as 'maze' and validate the Total count as '97' and ToalPage as '10' from the response (Positive Approach)
  # Execution 2 will take the SubString Parameter as 'mazes' and validate the Total count as '0' and ToalPage as '0' from the response (Negative Approach to validate the response)
