package com.example.femilyship.repository;

import com.example.femilyship.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    // "JOIN"을 "LEFT JOIN"으로 수정해주세요.
    @Query("SELECT t FROM Topic t LEFT JOIN FETCH t.essays")
    List<Topic> findAllWithEssays();

    // 여기도 "JOIN"을 "LEFT JOIN"으로 수정해주세요.
    @Query("SELECT t FROM Topic t LEFT JOIN FETCH t.essays WHERE t.id = :id")
    Optional<Topic> findByIdWithEssays(@Param("id") Long id);
}