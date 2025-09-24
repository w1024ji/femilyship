package com.example.femilyship.service;

import com.example.femilyship.dto.RegistrationRequest;
import com.example.femilyship.entity.User;
import com.example.femilyship.repository.UserRepository;
import com.example.femilyship.security.UserRoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    // Inside your AuthService.java file

    public void register(RegistrationRequest registrationRequest) {
        if (userRepository.existsByUsername(registrationRequest.getUsername())) {
            throw new RuntimeException("Error: Username is already taken!");
        }

        // Create new user's account
        User user = new User();
        user.setUsername(registrationRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        user.setRole(UserRoleEnum.USER); // ◀◀◀ 회원가입 시 USER 권한 부여

        userRepository.save(user);
    }
}

