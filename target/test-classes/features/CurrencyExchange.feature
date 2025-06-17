@CurrencyExchange
Feature: Currency Exchange
  As a user
  I want to exchange currencies
  So that I can convert money between different currencies

  Background:
    Given I am on the login page
    When I click the login button
    And I enter email "client@crassula.io" and password "Qwerty123"
    And I create a passcode
    Then I should see the dashboard

  @SuccessfulExchange @Smoke
  Scenario: Successful currency exchange
    Given I am on the dashboard
    When I click the exchange button
    Then I should see the exchange page
    And I should see exchange rate information
    And I should see first amount field
    And I should see first currency icon
    And I should see first currency button
    And I should see first field balance
    And I should see max button
    And I should see second amount field
    And I should see second currency icon
    And I should see second currency button
    And I should see second field balance
    And I should see fee information
    And I should see account to label
    And I should see account selection field
    And I should see account name
    And I should see account amount
    When I click on account selection
    Then I should see account selection modal
    When I select an account
    And I should see exchange button in form
    And I should see account button
    When I click max button and clear amount
    And I click first amount field
    And I enter amount "1"
    Then I should see minus symbol in first field
    And I should see plus symbol in second field
    When I click fee info
    Then I should see fee details modal for amount "1"
    When I close fee details modal
    And I click exchange button in form
    Then I should see confirm exchange title
    And I should see confirmation details for amount "1"
    When I click confirm exchange
    Then I should see success title
    And I should see success message
    And I should see exchange amount
    And I should see transaction details title
    When I click transaction details
    Then I should see transaction details content
    When I click back button
    And I click back to home
    Then I should see transaction on dashboard

  @CurrencySelection @Android
  Scenario: Currency selection validation for Android
    Given I am on the dashboard
    And I am using Android platform
    When I click the exchange button
    Then I should see the exchange page
    When I get first currency button text
    And I click first currency button
    Then I should see currency selection elements
    When I close currency selection
    And I get second currency button text
    And I click second currency button
    Then I should see second currency selection elements
    When I close second currency selection
    Then currency selection should be completed

  @ValidationError @NegativeTest
  Scenario: Insufficient funds validation
    Given I am on the dashboard
    When I click the exchange button
    Then I should see the exchange page
    When I try to exchange amount greater than available balance
    Then I should see insufficient funds error

  @ValidationError @NegativeTest
  Scenario: Unavailable currency pair validation
    Given I am on the dashboard
    When I click the exchange button
    Then I should see the exchange page
    When I try to select unavailable currency pair
    Then I should see currency pair unavailable error