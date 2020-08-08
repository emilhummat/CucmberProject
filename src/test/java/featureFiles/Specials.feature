Feature: Specials Feature
  Scenario: Testing that shows each item in the Specials page has discount
    Given Navigate to the website
    When I use legit username and password
    Then I am logged in
    Given Navigate to Specials page
    Then I should see the discount on the price
