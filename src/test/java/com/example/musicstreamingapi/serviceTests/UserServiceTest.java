package com.example.musicstreamingapi.serviceTests;

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
    /**
     * This test method verifies the functionality of the code getUserById method
     * in the UserService class. It checks if the method correctly retrieves a user
     * by their ID from the repository and returns the expected user details.
     */
    @Test
    public void testGetUserById(){
        User user = new User(1L, "Marco", "marco@example.com", "password123",null);
        // Call the UserRepository to return the user when findById is called
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        // Call the getUserById method to retrieve a user by their ID
        Optional<User> result = userService.getUserById(userId);
        verify(userRepository, times(1)).findById(userId);
        assertTrue(result.isPresent());
        assertEquals("Marco", result.get().getName());
        assertEquals("marco@example.com",result.get().getEmailAddress());
    }
    /**
     * This test method verifies the behavior of getUserById method.
     * UserService class attempts to retrieve a user by an
     * ID that does not exist in the repository. It checks if the method correctly
     * handles the case where the user is not found and returns an optional.
     */
    @Test
    public void testGetUserByIdNotFound() {
        Long userId = 77L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        Optional<User> result = userService.getUserById(userId);

        verify(userRepository, times(1)).findById(userId);
        assertFalse(result.isPresent());
    }
    /**
     * This test method verifies the functionality of the createUser method
     * in the UserService class. It checks if the method correctly creates
     * a new user by saving the provided user data and returns the new created user
     */
    @Test
    public void testCreateUser(){
        User userToCreate = new User(userId,"Betselot","Bets@gmail.com","newpassword456", null);
        // Configure the UserRepository to return the same user when save is called
        when(userRepository.save(userToCreate)).thenReturn(userToCreate);
        // Call the createUser method to create a new user
        User createdUser = userService.createUser(userToCreate);
        verify(userRepository,times(1)).save(userToCreate);
        assertNotNull(createdUser.getId());
        assertEquals("Betselot",createdUser.getName());
        assertEquals("Bets@gmail.com",createdUser.getEmailAddress());
    }
    // UpdateUser
    /**
     * This test method verifies the functionality of the  updateUser method
     * in the UserService class. It checks if the method correctly updates
     * an existing user's information, including their name,email,and  password
     * returns the updated user object with the expected details.
     */
    @Test
    public void testUpdateUser() {
        Long Id = 2L;
        User currentUser = new User(Id, "Gabrielle", "gabrielle@gmail.com", "oldPassword", null);
        User updatedUser = new User(Id, "Gabrielle H", "gabrielle@gmail.com", "newpassword", null);
        // Configure the UserRepository to return the current user when findById is called
        when(userRepository.findById(Id)).thenReturn(Optional.of(currentUser));
        // Configure the UserRepository to return the updated user when save is called
        when(userRepository.save(currentUser)).thenReturn(updatedUser);
        // Call the updateUser method to update the user's information
        User result = userService.updateUser(Id, updatedUser);
        verify(userRepository, times(1)).findById(Id);
        verify(userRepository, times(1)).save(currentUser);
        assertNotNull(result);
        assertEquals("Gabrielle H", result.getName());
        assertEquals("newpassword", result.getPassword());
    }
    /**
     * This test method verifies the functionality of the deleteUser method
     * in the UserService class. It checks if the method correctly deletes
     * a user by their ID and ensures that the UserRepository's deleteById method is
     * called with the specified user ID.
     */
    @Test
    public void testDeleteUser(){
        Long userIdToDelete = userId;
        userService.deleteUser(userIdToDelete);
        verify(userRepository, times(1)).deleteById(userIdToDelete);
        }
    }
