Feature: Get the Movie title from the Provided API and arrange in the ascending order

  Scenario Outline: Use the Provided API Endpoint and get the movie titles then sort it in the ascending order
    Given Build the API endpoint and verify the status code "<TitleName>" and "<PageNumber>"
    When Fetch the movie lists per page from the endpoint response "<TitleName>" and "<PageNumber>"
    Then Arrange the movie lists in ascending order "<TitleName>" and "<PageNumber>"

    Examples: 
      | TitleName | PageNumber |
      | spiderman |          0 |
      | spiderman |          1 |
      | spiderman |          2 |
  # Note: This Feature file drive the execution in the below order by passing the user provided parameters.
  # Execution runs 3 times as it created with the pipe iterator concept
  # Execution 1 will takes the SubString Parameter as 'spiderman' and Page Number as '0' and append in the endpooint URI
  # Execution 2 will takes the SubString Parameter as 'spiderman' and Page Number as '1' and append in the endpooint URI
  # Execution 3 will takes the SubString Parameter as 'spiderman' and Page Number as '2' and append in the endpooint URI
