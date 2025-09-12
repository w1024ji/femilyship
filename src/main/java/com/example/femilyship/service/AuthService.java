package com.example.femilyship.service;

import com.example.femilyship.entity.User;
import com.example.femilyship.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Inject the password encoder

    public User register(String username, String password) {
        // Check if user already exists
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Error: Username is already taken!");
        }

        // Hash the password before saving
        String hashedPassword = passwordEncoder.encode(password);
        User newUser = new User(username, hashedPassword);

        return userRepository.save(newUser);
    }
}

