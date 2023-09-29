package com.example.musicstreamingapi.userTests;

import com.example.musicstreamingapi.model.User;
import com.example.musicstreamingapi.repository.UserRepository;
import com.example.musicstreamingapi.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class UserServiceTest {
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    Long userId = 1L;

    @BeforeEach // Initializes Mockito annotations before each test method.
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testGetUserById(){

        User user = new User(1L, "Marco", "marco@example.com", "password123");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        Optional<User> result = userService.getUserById(userId);

        verify(userRepository, times(1)).findById(userId);
        assertTrue(result.isPresent());

        assertEquals("Marco", result.get().getName());
        assertEquals("marco@example.com",result.get().getEmailAddress());

    }
    @Test
    public void testGetUserByIdNotFound() {
        Long userId = 77L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        Optional<User> result = userService.getUserById(userId);

        verify(userRepository, times(1)).findById(userId);
        assertFalse(result.isPresent());
    }

    // GETALLUSER PROFILEs


    //CreateUser
@Test
    public void testCreateUser(){
        User userToCreate = new User(userId,"Betselot","Bets@gmail.com","newpassword456");
        when(userRepository.save(userToCreate)).thenReturn(userToCreate);

        User createdUser = userService.createUser(userToCreate);

        verify(userRepository,times(1)).save(userToCreate);
        assertNotNull(createdUser.getId());
        assertEquals("Betselot",createdUser.getName());
        assertEquals("Bets@gmail.com",createdUser.getEmailAddress());

    }
    // UpdateUser
    @Test
public void testUpdateUser(){
        Long userId = 3L;
        User newCurrentUser = new User(userId,"Gabrielle","gabrielle@gmail.com","oldPassword");
        User updatedUser = new User(userId,"Gabrielle J","gabrielle@gmail.com","newpassword");

        when(userRepository.findById(userId)).thenReturn(Optional.of(newCurrentUser));
        when(userRepository.save(updatedUser)).thenReturn(updatedUser);

        User result = userService.updateUser(userId,updatedUser);

        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).save(updatedUser);

        assertNotNull(result);

        assertEquals("Gabrielle J",result.getName());
        assertEquals("newpassword", result.getPassWord());

}

    //DeleteUser





}
