package com.example.musicstreamingapi.service;
import com.example.musicstreamingapi.model.User;
import com.example.musicstreamingapi.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class UserService {
    private final UserRepository userRepository;

    /**
     * Constructs a new instance of the UserService class with the provided
     * UserRepository dependency. This constructor is used to initialize the
     * service with the necessary repository for performing user-related operations.
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    /**
     * Retrieves a user by their unique identifier from the UserRepository.
     * @param userId The unique identifier (ID) of the user to retrieve.
     * @return An Optional containing the user if found, or an empty Optional if not found.
     */
    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }
    /**
     * Creates a new user by saving the provided user object to the UserRepository.
     * @param user The user object to be created and saved.
     * @return The new user object with any assigned identifiers.
     */
    public User createUser(User user) {
        // You can add validation logic here if needed
        return userRepository.save(user);
    }

    /**
     * Updates an existing user's information with the provided data and saves the changes to the UserRepository.
     * This method checks if a user with the given ID exists, and if so, it updates the specified fields (name, email address, and password)
     * with the values from the provided updatedUser object. The updated user is then saved to the repository.
     * If no user with the given ID is found, an IllegalArgumentException is thrown.
     * @param userId The unique identifier (ID) of the user to update.
     * @param updatedUser The user object containing the updated information.
     * @return The updated user object with the changes saved.
     * @throws IllegalArgumentException if no user with the given ID is found.
     */
    public User updateUser(Long userId, User updatedUser) {
        // Check if the user with the given ID exists
        Optional<User> existingUserOptional = userRepository.findById(userId);
        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            // Update the fields you want to change
            existingUser.setName(updatedUser.getName());
            existingUser.setEmailAddress(updatedUser.getEmailAddress());
            existingUser.setPassWord(updatedUser.getPassWord());
            // Save the updated user
            return userRepository.save(existingUser);
        } else {
            // Handle the case where the user with the given ID doesn't exist
            throw new IllegalArgumentException();
        }
    }
    /**
     * Deletes a user with the specified unique identifier (ID) from the UserRepository.
     * This method deletes the user with the given ID from the repository using the deleteById method.
     * @param userId The unique identifier (ID) of the user to delete.
     */
    public  void deleteUser(Long userId){
        userRepository.deleteById(userId);
    }

    public User findUserByEmailAddress(String emailAddress) {
        return userRepository.findByEmailAddress(emailAddress);
    }
}