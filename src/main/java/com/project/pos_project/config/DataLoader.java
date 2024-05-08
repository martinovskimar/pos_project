package com.project.pos_project.config;

import com.project.pos_project.model.User;
import com.project.pos_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

// Component that runs when the application starts.
// It implements CommandLineRunner to execute code on application startup.
@Component
public class DataLoader implements CommandLineRunner {

    // Autowiring the UserRepository to interact with the User database.
    @Autowired
    private UserRepository userRepository;

    // Autowiring the PasswordEncoder to encrypt user passwords.
    @Autowired
    private PasswordEncoder passwordEncoder;

    // The run method is executed when the application starts.
    @Override
    public void run(String... args) throws Exception {
        // Check if a user with username 'admin' exists in the database.
        if (userRepository.findByUsername("admin").isEmpty()) {
            // If not, create a new User instance for the admin.
            User user = new User();
            user.setLastName("Martinovski");
            user.setFirstName("Martin");
            user.setUsername("admin");
            // Encrypt the admin password before storing it.
            user.setPassword(passwordEncoder.encode("password"));
            // Set the role of the user as 'ROLE_ADMIN'.
            user.setRole("ROLE_ADMIN");
            // Save the new admin user to the database.
            userRepository.save(user);
        }
    }
}
