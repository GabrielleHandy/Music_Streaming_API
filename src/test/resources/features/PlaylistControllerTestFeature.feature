Feature: Rest API functionalities

  Scenario: User able to add and remove a playlist
    Given A list of playlists are available
    When I create a playlist
    Then The playlist is created
    When I remove playlist from my list of playlists
    Then The playlist is removed
    When I update a playlist
    Then The playlist is updated

  Scenario: User able to add and remove song from a playlist
    Given A list of songs are available in a playlist
    When I add a song to the playlist
    Then The song is added to playlist
    When I remove a song from playlist
    Then The song is removed

