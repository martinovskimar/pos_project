package com.project.pos_project.repository;

import com.project.pos_project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// Repository interface for User entities.
public interface UserRepository extends JpaRepository<User, Long> {

    // Custom method to find a user by their username
    Optional<User> findByUsername(String username);
}
