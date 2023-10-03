<<<<<<<<< Temporary merge branch 1
#Feature: Rest API functionalities
#
#  Scenario: User able to add and remove Category
#    Given A list of categories are available
#    When I add a category to my category list
#    Then The category is added
#    When I remove category from my category list
#    Then The category is removed
#    When I update a category
#    Then The category is updated
#
#
=========
Feature: Rest API functionalities

  Scenario: User is manage account
    When I create an account
    Then I get an account and user profile
    When I update the user profile
    Then The user profile is updated
    When I delete the user
    Then The user and user profile gets deleted
>>>>>>>>> Temporary merge branch 2
