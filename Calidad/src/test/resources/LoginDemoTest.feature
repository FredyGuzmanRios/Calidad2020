Feature: Test User Login

  Scenario Outline: User logins succesfully with credentials
    Given Browser is open
    And user is in login page
    When user enters <username> and <password>
    And user clicks login button
    Then website shows main page

	Examples:
	|username||password|
	|Jorge||12345|
	|Eloy||12345|
  
