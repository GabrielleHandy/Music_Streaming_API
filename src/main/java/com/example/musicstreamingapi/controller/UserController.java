package com.example.musicstreamingapi.controller;

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
            summary = "Get user by id",
            description = "Retrieve a user"
    )
    @ApiResponse(
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = User.class),
                    examples = @ExampleObject(
                            name = "Example result",
                            value = ""
            )

         )
    )
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        Optional<User> user = userService.getUserById(userId);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
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
    @PostMapping("/register/")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        response.put("message","success");
        response.put("data", createdUser);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }



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


    @DeleteMapping("/{userId}/")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
           User user = userService.deleteUser(userId);
           response.put("message","deleted successfully");
           response.put("data",user);
            return new ResponseEntity<>(response,HttpStatus.OK);

        }


}
