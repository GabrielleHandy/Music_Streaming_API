package com.example.musicstreamingapi.controller;

import com.example.musicstreamingapi.service.UserService;

public class UserController {
    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }
}
