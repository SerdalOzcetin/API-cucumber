@API
  Feature: Register Customer

    Scenario: Success Response

      Given API user sets the "api/register" path parameters
      And Prepare the Request Body "serdar","oz","serdaroz","seradroz@gmail.com","123456.0","123456.0","customer","5456787676","678678766565788" that ıs needed for Register Customer
      And Send Post Request for Register Customer