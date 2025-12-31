Feature: Login Functionality
  As a user of the Practice Test Automation site
  I want to be able to login with valid credentials
  So that I can access the protected areas of the application

  Background:
    Given a user is on the login page

  @positive @smoke
  Scenario: Successful login with valid credentials
    When the user logs in with valid credentials
    Then the user should be redirected to the logged in successfully page
    And the user should see the success message
    And the user should see the logout button

  @negative
  Scenario Outline: Login fails with invalid credentials
    When the user logs in with username "<username>" and password "<password>"
    Then the user should see an error message displayed
    And the user should see the error message "<errorMessage>"

    Examples:
      | username      | password      | errorMessage                |
      | incorrectUser | Password123   | Your username is invalid!   |
      | student       | incorrectPass | Your password is invalid!   |

  @negative
  Scenario Outline: Login fails with empty credentials
    When the user logs in with username "<username>" and password "<password>"
    Then the user should see an error message displayed

    Examples:
      | username | password    |
      |          | Password123 |
      | student  |             |
