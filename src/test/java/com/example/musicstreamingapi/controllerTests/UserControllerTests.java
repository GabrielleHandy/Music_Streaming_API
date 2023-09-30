package com.example.musicstreamingapi.controllerTests;

import com.example.musicstreamingapi.controller.UserController;
import com.example.musicstreamingapi.model.User;
import com.example.musicstreamingapi.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

    @Test
    public void testGetUserById() throws Exception {
        Long userId = 1L;
        User user = new User(userId, "Marco", "marco@gmail.com", "password123", null);

        when(userService.getUserById(userId)).thenReturn(Optional.of(user));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/auth/users/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(userId))
                .andExpect(jsonPath("$.name").value("Marco"))
                .andExpect(jsonPath("$.emailAddress").value("marco@gmail.com"))
                .andReturn();
    }

    @Test
    public void testGetUserByIdNotFound()throws Exception{
        Long userId = 100L;
        when(userService.getUserById(userId)).thenReturn(Optional.empty());
        mockMvc.perform(MockMvcRequestBuilders.get("/auth/users/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNotFound());

        verify(userService, times(1)).getUserById(userId);

    }

    @Test
    public void testCreateUser() throws Exception {
        User userToCreate = new User(1L, "bob", "bob@gmail.com", "newpassword", null);
        User createdUser = new User(1L, "bob", "bob@gmail.com", "newpassword", null);

        when(userService.createUser(any(User.class))).thenReturn(createdUser);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/auth/users/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"bob\",\"emailAddress\":\"bob@gmail.com\",\"passWord\":\"newpassword\"}"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.id").value(1L))
                .andExpect(jsonPath("$.data.name").value("bob"))
                .andExpect(jsonPath("$.data.emailAddress").value("bob@gmail.com"))
                .andReturn();

        verify(userService, times(1)).createUser(any(User.class));
    }


    }
