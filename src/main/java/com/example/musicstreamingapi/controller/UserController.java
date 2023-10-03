package com.example.musicstreamingapi.controller;

import com.example.musicstreamingapi.model.Playlist;
import com.example.musicstreamingapi.model.User;
import com.example.musicstreamingapi.model.UserProfile;
import com.example.musicstreamingapi.model.request.LoginRequest;
import com.example.musicstreamingapi.model.response.LoginResponse;
import com.example.musicstreamingapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/auth/users")
public class UserController {

    private final UserService userService;

    HashMap<String, Object > response = new HashMap<>();

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

        @Operation(
            summary = "Create a user",
            description = "Create a new user."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Successfully created user",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = User.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Result of example1",
                                            value = "{\n" +
                                                    "    \"data\": {\n" +
                                                    "        \"id\": 2,\n" +
                                                    "        \"name\": \"marco\",\n" +
                                                    "        \"emailAddress\": \"@test.com\",\n" +
                                                    "        \"userProfile\": {\n" +
                                                    "            \"id\": 2,\n" +
                                                    "            \"firstName\": null,\n" +
                                                    "            \"lastName\": null,\n" +
                                                    "            \"profileBio\": null,\n" +
                                                    "            \"playlists\": []\n" +
                                                    "        }\n" +
                                                    "    },\n" +
                                                    "    \"message\": \"success\"\n" +
                                                    "}"
                                    )

                            }
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "User already exists")})
        /**
         * Create new user by providing user information in the request body.
         * @param user The user object containing the user's information.
         * @return ResponseEntity containing a success message and the created user's data
         *         with HTTP status code 201
         */

        @PostMapping("/register/")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        response.put("message","success");
        response.put("data", createdUser);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
    @Operation(
            summary = "login the user",
            description = "login user using email and password"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "User is able to login",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = User.class),
                            examples = @ExampleObject(
                                    name = "Example result",
                                    value = "\"jwt\": \"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJAdGVzdC5jb20iLCJpYXQiOjE2OTYyODE1NTIsImV4cCI6MTY5NzE0NTU1Mn0.WmrJODyd3qGzYKHA2tSXtpzhwSQQJNI-GviJ5vopCDs\""
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "User is not found")})
    /**
     * Authenticate a user by processing a login request and returning a JSON Web Token (JWT) if successful.
     * @param loginRequest The login request containing user credentials.
     * @return ResponseEntity containing a LoginResponse object with a JWT token if authentication is successful,
     *         or a ResponseEntity with HTTP status code 401 (Unauthorized) and an error message if authentication fails.
     */
    @PostMapping("/login/")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest loginRequest){
        Optional<String> jwtToken = userService.loginUser(loginRequest);
        if (jwtToken.isPresent()){
            return ResponseEntity.ok(new LoginResponse(jwtToken.get()));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse("Authentication failed"));
    }

        @Operation(
            summary = "Update a user by ID",
            description = "Update an existing user by ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully updated user",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = User.class),
                            examples = @ExampleObject(
                                    name = "Example result",
                                    value = "{\n" +
                                            "    \"data\": {\n" +
                                            "        \"id\": 2,\n" +
                                            "        \"name\": \"Marco\",\n" +
                                            "        \"emailAddress\": \"@test.com\",\n" +
                                            "        \"userProfile\": {\n" +
                                            "            \"id\": 2,\n" +
                                            "            \"firstName\": null,\n" +
                                            "            \"lastName\": null,\n" +
                                            "            \"profileBio\": null,\n" +
                                            "            \"playlists\": []\n" +
                                            "        }\n" +
                                            "    },\n" +
                                            "    \"message\": \"success\"\n" +
                                            "}"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found")})
            /**
             * Updates an existing user's information by providing the user's ID and updated user details.
             * @param userId The ID of the user to be updated.
             * @param updatedUser The updated user object containing the new user information.
             * @return ResponseEntity containing a success message and the updated user's data with HTTP status code 200 (OK)
             *         if the user with the provided ID exists. If the user with the provided ID does not exist, it returns a
             *         ResponseEntity with HTTP status code 404 (Not Found).
         */
    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody User updatedUser) {
        // Check if the user exists
        Optional<User> existingUser = userService.getUserById(userId);

        if (existingUser.isPresent()) {
            User updated = userService.updateUser(userId, updatedUser);
            response.put("message", "success");
            response.put("data", updated);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            // Handle the case where the user with the given ID doesn't exist
            return ResponseEntity.notFound().build();
        }
    }
    @Operation(
            summary = "Update a user profile",
            description = "Update the users first and last name, and their bio"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "User can edit profile",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = User.class),
                            examples = @ExampleObject(
                                    name = "Example result",
                                    value = "{\n" +
                                            "    \"data\": {\n" +
                                            "        \"id\": 2,\n" +
                                            "        \"firstName\": \"Updated name\",\n" +
                                            "        \"lastName\": \"hello\",\n" +
                                            "        \"profileBio\": \"some bio\",\n" +
                                            "        \"playlists\": []\n" +
                                            "    },\n" +
                                            "    \"message\": \"success\"\n" +
                                            "}"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User profile cannot be updated")})
/**
 * Updates an existing user's profile information by providing the profile ID and updated profile details.
 * @param profileId The ID of the user's profile to be updated.
 * @param userProfile The updated user profile object containing the new profile information.
 * @return ResponseEntity containing a success message and the updated user profile data with HTTP status code 200 (OK)
 *         if the user profile with the provided ID exists. If the user profile with the provided ID does not exist,
 *         it returns a ResponseEntity with HTTP status code 404 (Not Found).
 */
    @PutMapping("/profile/{profileId}")
    public ResponseEntity<?> updateUserProfile(@PathVariable Long profileId, @RequestBody UserProfile userProfile){
        Optional<User> userProfileOptional = userService.getUserById(profileId);

        if(userProfileOptional.isPresent()){
            UserProfile updated = userService.updateUserProfile(profileId, userProfile);
            response.put("message", "success");
            response.put("data", updated);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }

    }


    @Operation(
            summary = "Remove a song from a playlist by ID",
            description = "Remove a song from a playlist by specifying both playlist and song IDs."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "User has been deleted",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = User.class),
                            examples = @ExampleObject(
                                    name = "Example result",
                                    value = "{\n" +
                                            "    \"data\": {\n" +
                                            "        \"id\": 2,\n" +
                                            "        \"name\": \"marco\",\n" +
                                            "        \"emailAddress\": \"@test.com\",\n" +
                                            "        \"userProfile\": {\n" +
                                            "            \"id\": 2,\n" +
                                            "            \"firstName\": null,\n" +
                                            "            \"lastName\": null,\n" +
                                            "            \"profileBio\": null,\n" +
                                            "            \"playlists\": []\n" +
                                            "        }\n" +
                                            "    },\n" +
                                            "    \"message\": \"deleted successfully\"\n" +
                                            "}"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User cannot be deleted")})
    @DeleteMapping("/{userId}/")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
           User user = userService.deleteUser(userId);
           response.put("message","deleted successfully");
           response.put("data",user);
            return new ResponseEntity<>(response,HttpStatus.OK);

        }


}
