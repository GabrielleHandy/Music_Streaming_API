package com.example.musicstreamingapi.controller;

import com.example.musicstreamingapi.model.User;
import com.example.musicstreamingapi.service.UserService;
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

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        response.put("message","success");
        response.put("data", createdUser);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }


}
