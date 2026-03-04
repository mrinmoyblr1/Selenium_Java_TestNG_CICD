Feature: Error validation

  @ErrorValidation
  Scenario Outline: Error validation Test
    Given I landed on Ecommerce Page
    When Logged in with username "<name>" and password "<password>"
    Then "Incorrect email or password." message is displayed


    Examples:
      | name                  | password   |
      | mrinmoy.blr@gmail.com | Test1234|


