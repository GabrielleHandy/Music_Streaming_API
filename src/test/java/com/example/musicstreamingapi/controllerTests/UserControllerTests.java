package com.example.musicstreamingapi.controllerTests;

import com.example.musicstreamingapi.controller.UserController;
import com.example.musicstreamingapi.service.UserService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;

public class UserControllerTests {
    @InjectMocks
    private UserController userController;
    @Mock
    private UserService userService;
    private MockMvc mockMvc;



}
