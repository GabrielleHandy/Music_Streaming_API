package com.example.musicstreamingapi.controller;

import com.example.musicstreamingapi.model.Playlist;
import com.example.musicstreamingapi.model.User;
import com.example.musicstreamingapi.service.PlaylistService;
import com.example.musicstreamingapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/api/playlists")
public class PlaylistController {

    private final PlaylistService playlistService;

    @Autowired
    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    private final HashMap<String, Object> response = new HashMap<>();

    @Operation(
            summary = "Get all playlists",
            description = "Retrieve a list of all playlists."
    )
    @ApiResponse(
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Playlist.class),
                    examples = @ExampleObject(
                            name = "Example result",
                            value = "{\"data\":[{\"id\":1,\"name\":\"FakePlaylist\",\"dateCreated\":\"2023-09-30\",\"songs\":[{\"id\":8,\"title\":\"Take Me Home, Country Roads\"}]},{\"id\":2,\"name\":\"Playlist 2\",\"dateCreated\":\"2023-09-30\",\"songs\":[]}],\"message\":\"Success\"}"
                    )
            )
    )
    @GetMapping("/")
    public ResponseEntity<?> getAllPlaylists() {
        List<Playlist> playListList = playlistService.getAllPlaylists();
        response.put("data", playListList);
        response.put("message", "Success");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

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
                            value = "{\"data\":{\"id\":1,\"name\":\"FakePlaylist\",\"dateCreated\":\"2023-09-30\",\"songs\":[{\"id\":8,\"title\":\"Take Me Home, Country Roads\"}]},\"message\":\"Success\"}"
                    )
            )
    )
    @GetMapping("/{playlistId}/")
    public ResponseEntity<?> getPlaylistById(@PathVariable Long playlistId) {
        Playlist playList = playlistService.getPlaylistById(playlistId);
        response.put("data", playList);
        response.put("message", "Success");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(
            summary = "Create a playlist",
            description = "Create a new playlist."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Successfully created playlist",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Playlist.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Result of example1",
                                            value = "{\"data\":{\"id\":4,\"name\":\"My Playlist\",\"dateCreated\":\"2023-09-30\",\"songs\":[]},\"message\":\"Successfully created playlist named Playlist 4\"}"
                                    ),
                                    @ExampleObject(
                                            name = "Result of empty input",
                                            value = "{\"data\":{\"id\":4,\"name\":\"Playlist 4\",\"dateCreated\":\"2023-09-30\",\"songs\":[]},\"message\":\"Successfully created playlist named Playlist 4\"}"
                                    )
                            }
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Playlist already exists")})
    @PostMapping("/")
    public ResponseEntity<?> createPlaylist(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Playlist object to be created",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Playlist.class),
                            examples = {@ExampleObject(
                                    name = "example1",
                                    value = "{\"name\":\"My Playlist\"}"


                            ), @ExampleObject(
                                    name = "empty example",
                                    value = "{}"
                            )}
            ))
            @RequestBody Playlist playlist) {
        response.clear();
        Playlist createdPlayList = playlistService.createPlaylist(playlist);
        response.put("data", createdPlayList);
        response.put("message", "Successfully created playlist named " + createdPlayList.getName());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Update a playlist by ID",
            description = "Update an existing playlist by ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully updated playlist",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Playlist.class),
                            examples = @ExampleObject(
                                    name = "Example result",
                                    value = "{\"id\":1,\"name\":\"Party Mix\",\"dateCreated\":\"2023-09-30\",\"userProfile\":{},\"songs\":[]}"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Playlist not found")})
    @PutMapping("/{playlistId}/")
    public ResponseEntity<?> updatePlaylist(
            @Parameter(description = "ID of the playlist to be updated.", required = true)
            @PathVariable(name = "playlistId") Long playlistId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Playlist object to be created",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Playlist.class),
                            examples = @ExampleObject(
                                    name = "Input new name example",
                                    value = "{\"name\":\"Party Mix\"}"
                            )
                    )
            )
            @RequestBody Playlist playlist) {
        response.clear();
        Playlist updatedPlayList = playlistService.updatePlaylist(playlistId, playlist);
        response.put("data", updatedPlayList);
        response.put("message", "Successfully updated playlist");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete a playlist by ID",
            description = "Delete an existing playlist by ID."
    )
    @ApiResponses({
            @ApiResponse(
            responseCode = "200",
            description = "Successfully deleted playlist",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Playlist.class),
                    examples = @ExampleObject(
                            name = "Example result",
                            value = "{\"id\":1,\"name\":\"Party Mix\",\"dateCreated\":\"2023-09-30\",\"userProfile\":{},\"songs\":[]}"
                    )
                )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Playlist not found")})
    @DeleteMapping("/{playlistId}/")
    public ResponseEntity<?> deletePlaylist(
            @Parameter(description = "ID of the playlist to be deleted.", required = true)
            @PathVariable(name = "playlistId") Long playlistId) {
        response.clear();
        Playlist deletedPlayList = playlistService.deletePlaylist(playlistId);
        response.put("data", deletedPlayList);
        response.put("message", "Successfully deleted playlist named " + deletedPlayList.getName());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(
            summary = "Get all songs in a playlist by ID",
            description = "Retrieve all songs in a playlist by ID."
    )
    @ApiResponses({@ApiResponse(responseCode = "200"),
                    @ApiResponse(
                        responseCode = "404",
                        description = "Playlist or Song not found",
                    content = @Content(
                            examples = @ExampleObject(
                                    name = "Example result",
                                    value = "{\"data\":[{\"id\":2,\"title\":\"Hotel California\"},{\"id\":3,\"title\":\"Imagine\"},{\"id\":8,\"title\":\"Take Me Home, Country Roads\"}],\"message\":\"Success\"}"
                            )
                    ))})
    @GetMapping("/{playlistId}/songs/")
    public ResponseEntity<?> getPlaylistSongs(
            @Parameter(description = "ID of the playlist to retrieve songs from.", required = true)
            @PathVariable(name = "playlistId") Long playlistId) {
        response.clear();
        response.put("data", playlistService.getAllSongsInPlaylist(playlistId));
        response.put("message", "Success");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(
            summary = "Add a song to a playlist by ID",
            description = "Add a song to a playlist by specifying both playlist and song IDs."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Song removed from the playlist",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Playlist.class),
                            examples = @ExampleObject(
                                    name = "Example result",
                                    value = "{\"data\":{\"id\":1,\"name\":\"FakePlaylist\",\"dateCreated\":\"2023-10-01\",\"songs\":[{\"id\":3,\"title\":\"Imagine\"},{\"id\":8,\"title\":\"Take Me Home, Country Roads\"}]},\"message\":\"Song removed from the playlist\"}"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Playlist or Song not found"),
            @ApiResponse(
                    responseCode = "409",
                    description = "Song already in playlist")
    })
    @PostMapping("/{playlistId}/songs/{songId}/")
    public ResponseEntity<?> addSongToPlaylist(
            @Parameter(description = "ID of the playlist to add the song to.", required = true)
            @PathVariable(name = "playlistId") Long playlistId,
            @Parameter(description = "ID of the song to be added to the playlist.", required = true)
            @PathVariable(name = "songId") Long songId) {
        response.clear();
        Playlist updatedPlaylist = playlistService.addSongToPlaylist(playlistId, songId);
        response.put("data", updatedPlaylist);
        response.put("message", "Song successfully added");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(
            summary = "Remove a song from a playlist by ID",
            description = "Remove a song from a playlist by specifying both playlist and song IDs."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Song removed from the playlist",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Playlist.class),
                            examples = @ExampleObject(
                                    name = "Example result",
                                    value = "{\"data\":{\"id\":1,\"name\":\"FakePlaylist\",\"dateCreated\":\"2023-10-01\",\"songs\":[{\"id\":3,\"title\":\"Imagine\"},{\"id\":8,\"title\":\"Take Me Home, Country Roads\"}]},\"message\":\"Song removed from the playlist\"}"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Playlist or Song not found or Song not found in Playlist")})
    @DeleteMapping("/{playlistId}/songs/{songId}/")
    public ResponseEntity<?> removeSongFromPlaylist(
            @Parameter(description = "ID of the playlist to add the song to.", required = true)
            @PathVariable(name = "playlistId") Long playlistId,
            @Parameter(description = "ID of the song to be added to the playlist.", required = true)
            @PathVariable(name = "songId") Long songId) {
        response.clear();
        Playlist updatedPlaylist = playlistService.removeSongFromPlaylist(playlistId, songId);
        response.put("data", updatedPlaylist);
        response.put("message", "Song removed from the playlist");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

//package com.example.musicstreamingapi.controller;
//
//        import com.example.musicstreamingapi.model.Playlist;
//        import com.example.musicstreamingapi.model.User;
//        import com.example.musicstreamingapi.model.request.LoginRequest;
//        import com.example.musicstreamingapi.model.response.LoginResponse;
//        import com.example.musicstreamingapi.service.UserService;
//        import io.swagger.v3.oas.annotations.Operation;
//        import io.swagger.v3.oas.annotations.media.Content;
//        import io.swagger.v3.oas.annotations.media.ExampleObject;
//        import io.swagger.v3.oas.annotations.media.Schema;
//        import io.swagger.v3.oas.annotations.responses.ApiResponse;
//        import io.swagger.v3.oas.annotations.responses.ApiResponses;
//        import org.springframework.beans.factory.annotation.Autowired;
//        import org.springframework.http.HttpStatus;
//        import org.springframework.http.ResponseEntity;
//        import org.springframework.web.bind.annotation.*;
//
//        import java.util.HashMap;
//        import java.util.Objects;
//        import java.util.Optional;

//@RestController
//@RequestMapping("/auth/users")
//public class UserController {

//    private final UserService userService;
//
//    HashMap<String, Object > response = new HashMap<>();
//
//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
    // test complete
//    @Operation(
//            summary = "Get user by id",
//            description = "Retrieve a user"
//    )
//    @ApiResponse(
//            content = @Content(
//                    mediaType = "application/json",
//                    schema = @Schema(implementation = User.class),
//                    examples = @ExampleObject(
//                            name = "Example result",
//                            value =
//            )
//
//         )
//    )
//    @GetMapping("/{userId}")
//    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
//        Optional<User> user = userService.getUserById(userId);
//        if (user.isPresent()) {
//            return ResponseEntity.ok(user.get());
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//    @Operation(
//            summary = "Create a user",
//            description = "Create a new user."
//    )
//    @ApiResponses({
//            @ApiResponse(
//                    responseCode = "201",
//                    description = "Successfully created user",
//                    content = @Content(
//                            mediaType = "application/json",
//                            schema = @Schema(implementation = User.class),
//                            examples = {
//                                    @ExampleObject(
//                                            name = "Result of example1",
//                                            value =
//                                    ),
//                                    @ExampleObject(
//                                            name = "Result of empty input",
//                                            value =
//                                    )
//                            }
//                    )
//            ),
//            @ApiResponse(
//                    responseCode = "409",
//                    description = "User already exists")})
//    @PostMapping("/register/")
//    public ResponseEntity<?> createUser(@RequestBody User user) {
//        User createdUser = userService.createUser(user);
//        response.put("message","success");
//        response.put("data", createdUser);
//        return new ResponseEntity<>(response,HttpStatus.CREATED);
//    }
//
//
//
//    @PostMapping("/login/")
//    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest loginRequest){
//        Optional<String> jwtToken = userService.loginUser(loginRequest);
//        if (jwtToken.isPresent()){
//            return ResponseEntity.ok(new LoginResponse(jwtToken.get()));
//        }
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse("Authentication failed"));
//    }
//
//
//    @Operation(
//            summary = "Update a user by ID",
//            description = "Update an existing user by ID."
//    )
//    @ApiResponses({
//            @ApiResponse(
//                    responseCode = "200",
//                    description = "Successfully updated user",
//                    content = @Content(
//                            mediaType = "application/json",
//                            schema = @Schema(implementation = User.class),
//                            examples = @ExampleObject(
//                                    name = "Example result",
//                                    value =
//                            )
//                    )
//            ),
//            @ApiResponse(
//                    responseCode = "404",
//                    description = "User not found")})
//    @PutMapping("/{userId}")
//    public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody User updatedUser) {
//        // Check if the user exists
//        Optional<User> existingUser = userService.getUserById(userId);
//
//        if (existingUser.isPresent()) {
//            User updated = userService.updateUser(userId, updatedUser);
//            response.put("message", "success");
//            response.put("data", updated);
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } else {
//            // Handle the case where the user with the given ID doesn't exist
//            return ResponseEntity.notFound().build();
//        }
//    }
//    @DeleteMapping("/{userId}/")
//    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
//        User user = userService.deleteUser(userId);
//        response.put("message","deleted successfully");
//        response.put("data",user);
//        return new ResponseEntity<>(response,HttpStatus.OK);
//
//    }
//
//
//}
