package com.example.musicstreamingapi.userTests;

import com.example.musicstreamingapi.repository.UserRepository;
import com.example.musicstreamingapi.service.UserService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.User;

public class UserServiceTest {
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;


}
