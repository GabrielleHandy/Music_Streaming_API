Feature: Rest API functionalities

  Scenario: User able to get a list of songs
    Given A list of songs are available in genre
    When The user requests to get a list of songs
    Then The system should respond with a list of songs
    When The user requests to get songs by genre ID
    Then The system should respond with a list of songs for the specified genre ID



