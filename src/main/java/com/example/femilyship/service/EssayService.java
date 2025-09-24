package com.example.femilyship.service;

import com.example.femilyship.dto.EssayDto;
import com.example.femilyship.entity.Essay;
import com.example.femilyship.entity.Topic;
import com.example.femilyship.entity.User;
import com.example.femilyship.repository.EssayRepository;
import com.example.femilyship.repository.TopicRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class EssayService {
    private final EssayRepository essayRepository;
    private final TopicRepository topicRepository;

    // 1. 에세이 생성
    @Transactional
    public Essay createEssay(EssayDto.Request requestDto, User author) {
        Topic topic = topicRepository.findById(requestDto.getTopicId())
                .orElseThrow(() -> new EntityNotFoundException("해당 ID의 토픽을 찾을 수 없습니다: " + requestDto.getTopicId()));
        Essay essay = requestDto.toEntity(topic, author);
        return essayRepository.save(essay);
    }

    // 2. 에세이 상세 조회
    @Transactional(readOnly = true)
    public EssayDto.Response getEssayById(Long essayId) {
        Essay essay = essayRepository.findById(essayId)
                .orElseThrow(() -> new EntityNotFoundException("해당 ID의 에세이를 찾을 수 없습니다: " + essayId));
        return new EssayDto.Response(essay);
    }

    // 3. 에세이 수정
    @Transactional
    public Essay updateEssay(Long essayId, EssayDto.Request requestDto, User currentUser) {
        Essay essay = essayRepository.findById(essayId)
                .orElseThrow(() -> new EntityNotFoundException("해당 ID의 에세이를 찾을 수 없습니다: " + essayId));
        if (!essay.getAuthor().getId().equals(currentUser.getId())) {
            throw new SecurityException("에세이를 수정할 권한이 없습니다.");
        }
        essay.update(requestDto.getTitle_essay(), requestDto.getContent_essay());
        return essay;
    }

    // 4. 에세이 삭제
    @Transactional
    public void deleteEssay(Long essayId, User currentUser) {
        Essay essay = essayRepository.findById(essayId)
                .orElseThrow(() -> new EntityNotFoundException("해당 ID의 에세이를 찾을 수 없습니다: " + essayId));
        if (!essay.getAuthor().getId().equals(currentUser.getId())) {
            throw new SecurityException("에세이를 삭제할 권한이 없습니다.");
        }
        essayRepository.delete(essay);
    }
}