Feature: Purchase Order from E-commerce Website


  Background:
    Given I landed on Ecommerce Page


  @Regression
  Scenario Outline: Positive Test of Submitting the order
    Given Logged in with username "<name>" and password "<password>"
    When I add product "<productName>" to Cart
    And Checkout "<productName>" and Submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed confirmationPage

    Examples:
      | name                  | password  | productName     |
      | mrinmoy.blr@gmail.com | Test1234 | ZARA COAT 3     |
#      | mrinmoy.blr@gmail.com | Test1234| ADIDAS ORIGINAL |


