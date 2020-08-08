Feature:Contact us feature

  Scenario Outline: Successfully testing of Contact us functionality
    Given Navigate to the website
    When I use legit username and password
    Then I am logged in
    Given Navigate to Contact us
    When Fill out the message box "<Subject Heading>", "<Order reference>", and "Product"

    Examples:
      | Subject Heading  | Order reference |
      | Customer service | 225343          |
      | Webmaster        | 225362          |
      | Webmaster        | 225364          |
