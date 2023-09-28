package com.example.musicstreamingapi.userTests;

import com.example.musicstreamingapi.repository.UserRepository;
import com.example.musicstreamingapi.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.User;

public class UserServiceTest {
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;


    @BeforeEach // Initializes Mockito annotations before each test method.
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testGetUserById(){

    }


}
