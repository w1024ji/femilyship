package com.example.femilyship.service;

import com.example.femilyship.dto.EssayInTopicResponseDto;
import com.example.femilyship.dto.TopicDto;
import com.example.femilyship.entity.Topic;
import com.example.femilyship.entity.User;
import com.example.femilyship.repository.EssayRepository;
import com.example.femilyship.repository.TopicRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;
    private final EssayRepository essayRepository;

    // 1. 모든 토픽 목록 조회 (메인 페이지)
    @Transactional(readOnly = true)
    public List<TopicDto.ListResponse> getAllTopics() {
        return topicRepository.findAll().stream()
                .map(TopicDto.ListResponse::new)
                .collect(Collectors.toList());
    }
    // 2. 특정 토픽 상세 조회
    @Transactional(readOnly = true)
    public TopicDto.DetailResponse getTopicById(Long topicId) {
        Topic topic = topicRepository.findByIdWithEssays(topicId)
                .orElseThrow(() -> new EntityNotFoundException("해당 ID의 토픽을 찾을 수 없습니다: " + topicId));
        // 수정된 DTO를 반환하는 레포지토리 메소드 호출
        List<EssayInTopicResponseDto> essayDtos = essayRepository.findSimpleEssaysByTopicId(topicId);

        return new TopicDto.DetailResponse(topic, essayDtos);
    }
    // 3. 토픽 생성
    @Transactional
    public Topic createTopic(TopicDto.Request requestDto, User author) {
        Topic topic = requestDto.toEntity(author);
        return topicRepository.save(topic);
    }
    // 4. 토픽 수정
    @Transactional
    public Topic updateTopic(Long topicId, TopicDto.Request requestDto, User currentUser) {
        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new EntityNotFoundException("해당 ID의 토픽을 찾을 수 없습니다: " + topicId));

        if (!topic.getAuthor().getId().equals(currentUser.getId())) {
            throw new SecurityException("토픽을 수정할 권한이 없습니다.");
        }
        topic.update(requestDto.getTitle(), requestDto.getContent());
        return topic;
    }
    // 5. 토픽 삭제
    @Transactional
    public void deleteTopic(Long topicId, User currentUser) {
        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new EntityNotFoundException("해당 ID의 토픽을 찾을 수 없습니다: " + topicId));

        if (!topic.getAuthor().getId().equals(currentUser.getId())) {
            throw new SecurityException("토픽을 삭제할 권한이 없습니다.");
        }
        topicRepository.delete(topic);
    }
}

