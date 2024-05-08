package com.project.pos_project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// Entity class representing users in the system.
@Entity
public class User {

    // Unique identifier for the user.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    // First name of the user.
    private String FirstName;

    // Last name of the user.
    private String lastName;

    // Username for user authentication.
    private String username;

    // Password for user authentication.
    private String password;

    // Role or privilege level of the user.
    private String role;

    // Getters and setters for all fields.
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        this.FirstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
