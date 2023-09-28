package com.example.musicstreamingapi.service;

import com.example.musicstreamingapi.model.User;
import com.example.musicstreamingapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }
}
