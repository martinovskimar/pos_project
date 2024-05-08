package com.project.pos_project.service;

import com.project.pos_project.model.User;
import com.project.pos_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Service class for handling user-related operations.
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Constructor to inject UserRepository and PasswordEncoder.
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Add a new user to the repository after encoding their password.
    public User addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    // Retrieve all users from the repository.
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Finds a user by their ID.
    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    // Update the details of an existing user.
    public User updateUser(Long userId, User userDetails) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setUsername(userDetails.getUsername());
        user.setRole(userDetails.getRole());
        return userRepository.save(user);
    }

    // Deletes a user by their ID.
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
