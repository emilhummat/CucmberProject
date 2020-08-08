Feature:Contact us feature

  Scenario Outline: Successfully testing of Contact us functionality
    Given Navigate to the website
    When I use legit username and password
    Then I am logged in
    Given Navigate to Contact us
    When Fill out the message box "<Subject Heading>", "<Order reference>", and "Product"
    Then "<entityName>" has been successfully "<outcome>" to our team.

    Examples:
      | Subject Heading  | Order reference |  entityName  | outcome |
      | Customer service | 225343          | Your message | sent    |
      | Webmaster        | 225362          | Your message | sent    |
      | Webmaster        | 225364          | Your message | sent    |
