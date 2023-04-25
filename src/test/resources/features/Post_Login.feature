@API02
Feature: You can log in to the system with your email and password

  Scenario: Success Response

    #Given API user sets the "api/login" path parameters
    #And Prepare the Request Body "email","password" that ıs needed for Login
    And Send Post Request for Login