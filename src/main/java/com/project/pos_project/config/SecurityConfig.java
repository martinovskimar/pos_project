package com.project.pos_project.config;

import com.project.pos_project.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// Marks class as a configuration class and enables Spring Security.
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Inject the CustomUserDetailsService to load user-specific data.
    @Autowired
    private CustomUserDetailsService userDetailsService;

    // Define the security filter chain that applies to HTTP requests.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Configure authorization requests.
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                // Ensure that any request to the app is authenticated.
                                .anyRequest().authenticated()
                )
                // Configure form-based authentication.
                .formLogin(form -> form
                        // Specifie the custom login page URL.
                        .loginPage("/login")
                        // Define the landing page after a successful login.
                        .defaultSuccessUrl("/home", true)
                        // Allows everyone to access the login page.
                        .permitAll()
                )
                // Configure the logout functionality.
                .logout(logout -> logout.permitAll());
        // Build and return the SecurityFilterChain instance.
        return http.build();
    }

    // Define a bean for password encoding, using BCrypt hashing algorithm.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

