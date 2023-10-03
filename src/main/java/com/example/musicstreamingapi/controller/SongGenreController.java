package com.example.musicstreamingapi.controller;


import com.example.musicstreamingapi.model.Playlist;
import com.example.musicstreamingapi.model.Song;
import com.example.musicstreamingapi.service.SongService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * Controller class for managing song-related operations.
 */

@RestController
@RequestMapping("/api/songs")
public class SongGenreController {

    private final SongService songService;

    /**
     * Constructor for SongGenreController.
     *
     * @param songService The service responsible for handling song-related operations.
     */

    @Autowired
    public SongGenreController(SongService songService) {
        this.songService = songService;
    }

    /**
     * Retrieves all songs.
     *
     * @return ResponseEntity containing a list of songs or a message indicating no songs are found.
     */

    @Operation(
            summary = "Get songlist by ID",
            description = "Retrieve a  playlist."
    )
    @ApiResponse(
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Playlist.class),
                    examples = @ExampleObject(
                            name = "Example result",
                            value = "{\n" +
                                    "    \"data\": [\n" +
                                    "        {\n" +
                                    "            \"id\": 1,\n" +
                                    "            \"title\": \"Bohemian Rhapsody\"\n" +
                                    "        },\n" +
                                    "        {\n" +
                                    "            \"id\": 2,\n" +
                                    "            \"title\": \"Hotel California\"\n" +
                                    "        },\n" +
                                    "        {\n" +
                                    "            \"id\": 3,\n" +
                                    "            \"title\": \"Imagine\"\n" +
                                    "        },\n" +
                                    "        {\n" +
                                    "            \"id\": 4,\n" +
                                    "            \"title\": \"Lose Yourself\"\n" +
                                    "        },\n" +
                                    "        {\n" +
                                    "            \"id\": 5,\n" +
                                    "            \"title\": \"Sicko Mode\"\n" +
                                    "        },\n" +
                                    "        {\n" +
                                    "            \"id\": 6,\n" +
                                    "            \"title\": \"Jolene\"\n" +
                                    "        },\n" +
                                    "        {\n" +
                                    "            \"id\": 7,\n" +
                                    "            \"title\": \"Wagon Wheel\"\n" +
                                    "        },\n" +
                                    "        {\n" +
                                    "            \"id\": 8,\n" +
                                    "            \"title\": \"Take Me Home, Country Roads\"\n" +
                                    "        }\n" +
                                    "    ],\n" +
                                    "    \"message\": \"Success\"\n" +
                                    "}"
                    )
            )
    )


    @GetMapping
    public ResponseEntity<?> getAllSongs() {
        List<Song> songsList = songService.getAllSongs();
        HashMap<String, Object> message = new HashMap<>();

        if (songsList.isEmpty()) {
            message.put("message", "Cannot find any songs.");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            message.put("message", "Success");
            message.put("data", songsList);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }

    /**
     * Retrieves songs by genre ID.
     *
     * @param genreId The ID of the genre for which songs are retrieved.
     * @return ResponseEntity containing a list of songs for the given genre ID or a message indicating no songs are found.
     */
    @Operation(
            summary = "Get playlist by ID",
            description = "Retrieve a  playlist."
    )
    @ApiResponse(
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Playlist.class),
                    examples = @ExampleObject(
                            name = "Example result",
                            value = "{\n" +
                                    "    \"data\": [\n" +
                                    "        {\n" +
                                    "            \"id\": 1,\n" +
                                    "            \"title\": \"Bohemian Rhapsody\"\n" +
                                    "        },\n" +
                                    "        {\n" +
                                    "            \"id\": 2,\n" +
                                    "            \"title\": \"Hotel California\"\n" +
                                    "        },\n" +
                                    "        {\n" +
                                    "            \"id\": 3,\n" +
                                    "            \"title\": \"Imagine\"\n" +
                                    "        }\n" +
                                    "    ],\n" +
                                    "    \"message\": \"Success\"\n" +
                                    "}"
                    )
            )
    )

    @GetMapping("/Genre/{genreId}")
    public ResponseEntity<?> getSongsByGenre(@PathVariable Long genreId) {
        List<Song> songsList = songService.getAllSongsByGenreId(genreId);
        HashMap<String, Object> message = new HashMap<>();
        if (songsList.isEmpty()) {
            message.put("message", "No songs found for the given genre ID.");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            message.put("message", "Success");
            message.put("data", songsList);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }

    /**
     * Retrieves a song by its ID.
     *
     * @param songId The ID of the song to be retrieved.
     * @return ResponseEntity containing the requested song or a message indicating no song is found.
     */

    @Operation(
            summary = "Get playlist by ID",
            description = "Retrieve a  playlist."
    )
    @ApiResponse(
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Playlist.class),
                    examples = @ExampleObject(
                            name = "Example result",
                            value = "{\n" +
                                    "    \"data\": {\n" +
                                    "        \"id\": 1,\n" +
                                    "        \"title\": \"Bohemian Rhapsody\"\n" +
                                    "    },\n" +
                                    "    \"message\": \"Success\"\n" +
                                    "}"
                    )
            )
    )

    @GetMapping("/{songId}")
    public ResponseEntity<?> getSongById(@PathVariable Long songId) {
        Optional<Song> optionalSong = songService.getSongById(songId);
        HashMap<String, Object> message = new HashMap<>();

        Song song = optionalSong.orElse(null);

        if (song == null) {
            message.put("message", "No song found for the given ID.");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            message.put("message", "Success");
            message.put("data", song);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }
}






