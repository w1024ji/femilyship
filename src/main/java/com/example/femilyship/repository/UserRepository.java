package com.example.femilyship.repository;

import com.example.femilyship.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    // This is the new method you need to add.
    // Spring Data JPA will automatically implement this method for you
    // just based on its name. It will generate a query like:
    // "SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM User u WHERE u.username = ?"
    boolean existsByUsername(String username);
}
