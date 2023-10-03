

Feature: Rest API functionalities

  Scenario: User is manage account
    When I create an account
    Then I get an account and user profile
    When I update the user profile
    Then The user profile is updated
    When I delete the user
    Then The user and user profile gets deleted

