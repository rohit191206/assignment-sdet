Feature: Check if users from FanCode have more than 50% todos completed

  Scenario: Verify user todos completion rate
    Given User data is fetched from the API
    And Todo data is fetched from the API
    When Users belong to the city FanCode
    Then Each user's completed todo percentage should be greater than 50%