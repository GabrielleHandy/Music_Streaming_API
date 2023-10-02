package com.example.musicstreamingapi.controller;

import com.example.musicstreamingapi.model.User;
import com.example.musicstreamingapi.model.request.LoginRequest;
import com.example.musicstreamingapi.model.response.LoginResponse;
import com.example.musicstreamingapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
// test complete


    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        Optional<User> user = userService.getUserById(userId);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

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
    @DeleteMapping("/{userId}/")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
           User user = userService.deleteUser(userId);
           response.put("message","deleted successfully");
           response.put("data",user);
            return new ResponseEntity<>(response,HttpStatus.OK);

        }


}
