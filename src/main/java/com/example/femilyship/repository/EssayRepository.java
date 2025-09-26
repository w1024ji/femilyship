package com.example.femilyship.repository;

import com.example.femilyship.dto.EssayInTopicResponseDto;
import com.example.femilyship.entity.Essay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EssayRepository extends JpaRepository<Essay, Long> {
    // "new" 키워드를 사용해 수정된 DTO를 직접 조회하는 쿼리
    @Query("SELECT new com.example.femilyship.dto.EssayInTopicResponseDto(e.id, e.title, e.author.username) " +
            "FROM Essay e WHERE e.topic.id = :topicId")
    List<EssayInTopicResponseDto> findSimpleEssaysByTopicId(@Param("topicId") Long topicId);
}
