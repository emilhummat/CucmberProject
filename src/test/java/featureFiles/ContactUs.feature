Feature:Contact us feature

  Scenario Outline: Successfully testing of Contact us functionality
    Given Navigate to the website
    When I use legit username and password
    Then I am logged in
    Given Navigate to Contact us
    When Fill out the message box "<Subject Heading>", "<Order reference>", and "<Product>"
    Examples:
      | Subject Heading  | Order reference | Product |
      | Customer service | 225343               | 1       |
      | Webmaster        | 225362               | 1       |
      | Webmaster        | 225364               | 1       |
