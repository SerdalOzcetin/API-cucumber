@API03
Feature: It is used to list all the countries registered in the database.

  Scenario: Success Response

    Given API user sets the "api/profile/allCountries" path parameters
    Then Send Get request for allCountries