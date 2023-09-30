Feature: Rest API functionalities

  Scenario: User unable to add or remove a song
    Given A list of songs are available in genre
    When I, as a user, attempt to create a new song
    Then The creation of a new song is not allowed
    When I, as a user, attempt to remove a song
    Then The removal of a song is not allowed
    When I, as a user, attempt to update a song
    Then The update of a song is not allowed


