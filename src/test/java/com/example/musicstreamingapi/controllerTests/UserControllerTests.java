package com.example.musicstreamingapi.controllerTests;

import com.example.musicstreamingapi.controller.UserController;
import com.example.musicstreamingapi.model.User;
import com.example.musicstreamingapi.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTests {
    @InjectMocks
    private UserController userController;
    @Mock
    private UserService userService;
    private MockMvc mockMvc;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    //TestGetUserID

    @Test
    public void testGetUserById() throws Exception{
        Long userId = 1L;
        User user = new User(userId,"Marco","marco@gmail.com","password123",null);

        when(userService.getUserById(userId)).thenReturn(Optional.of(user));


        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/users/{userId",userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(userId))
                .andExpect(jsonPath("$.name").value("Marco"))
                .andExpect(jsonPath("$.emailAddress").value("marco@example.com"))
                .andReturn();
        verify(userService, times(1)).getUserById(userId);



    }



    //TestGetUserIdNotFound


}
