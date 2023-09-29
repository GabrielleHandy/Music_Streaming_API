package com.example.musicstreamingapi.service;

import com.example.musicstreamingapi.model.User;
import com.example.musicstreamingapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public User createUser(User user) {
        // You can add validation logic here if needed
        return userRepository.save(user);
    }

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
            return null;
        }
    }


    public  void deleteUser(Long userId){
        userRepository.deleteById(userId);
    }
}
