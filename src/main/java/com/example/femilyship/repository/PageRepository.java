package com.example.femilyship.repository;

import com.example.femilyship.entity.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The PageRepository interface provides the mechanism for storage, retrieval,
 * and search behavior for Page entities. Spring Data JPA will automatically
 * implement this repository interface at runtime.
 */
@Repository
public interface PageRepository extends JpaRepository<Page, Long> {
    // By extending JpaRepository, we get a lot of CRUD methods for free,
    // like findAll(), findById(), save(), deleteById(), etc.
}

