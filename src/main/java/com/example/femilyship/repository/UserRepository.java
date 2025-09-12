package com.example.femilyship.repository;

import com.example.femilyship.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // We'll need this method to find a user by their username during login
    Optional<User> findByUsername(String username);
}
