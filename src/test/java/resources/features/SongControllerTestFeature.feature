Feature: Rest API functionalities

  Scenario: User able to add and remove Song
    Given A list of songs are available in genre
    When I add a category to my category list
    Then The category is added
    When I remove category from my category list
    Then The category is removed
    When I update a category
    Then The category is updated


