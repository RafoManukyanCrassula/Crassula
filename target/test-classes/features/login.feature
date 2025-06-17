Feature: Login functionality

  Scenario: Successful login
    Given the user is on the login page
    When the user enters valid credentials
    And clicks the login button
    Then the user is redirected to the dashboard

  Scenario: Validation for blank email and password
    Given the user is on the login page
    When the user clicks on the login button
    And leaves email and password empty
    Then the error message "The value should not be blank" is displayed

  Scenario: Validation for invalid email
    Given the user is on the login page
    When the user enters an invalid email
    Then the error message "Email address is not valid" is displayed

  Scenario: Invalid credentials alert
    Given the user is on the login page
    When the user enters invalid email and password
    And clicks on the continue button
    Then the alert "The email or password you entered is incorrect" is displayed
    And the user closes the alert