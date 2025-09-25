package com.example.femilyship.controller;

import com.example.femilyship.dto.TopicDto;
import com.example.femilyship.entity.Topic;
import com.example.femilyship.entity.User;
import com.example.femilyship.security.UserDetailsImpl;
import com.example.femilyship.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/topics")
@RequiredArgsConstructor
public class TopicController {
    private final TopicService topicService;
    /**
     * 모든 토픽 목록 조회
     */
    @GetMapping
    public ResponseEntity<List<TopicDto.ListResponse>> getAllTopics() {
        List<TopicDto.ListResponse> topics = topicService.getAllTopics();
        return ResponseEntity.ok(topics);
    }
    /**
     * 특정 토픽 상세 조회
     */
    @GetMapping("/{topicId}")
    public ResponseEntity<TopicDto.DetailResponse> getTopicById(@PathVariable Long topicId) {
        TopicDto.DetailResponse topic = topicService.getTopicById(topicId);
        return ResponseEntity.ok(topic);
    }
    /**
     * 새로운 토픽 생성
     */
    @PostMapping
    public ResponseEntity<Topic> createTopic(@RequestBody TopicDto.Request requestDto,
                                             @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User currentUser = userDetails.getUser();
        Topic createdTopic = topicService.createTopic(requestDto, currentUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTopic);
    }
    /**
     * 특정 토픽 수정
     */
    @PutMapping("/{topicId}")
    public ResponseEntity<Topic> updateTopic(@PathVariable Long topicId,
                                             @RequestBody TopicDto.Request requestDto,
                                             @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User currentUser = userDetails.getUser();
        Topic updatedTopic = topicService.updateTopic(topicId, requestDto, currentUser);
        return ResponseEntity.ok(updatedTopic);
    }
    /**
     * 특정 토픽 삭제
     */
    @DeleteMapping("/{topicId}")
    public ResponseEntity<Void> deleteTopic(@PathVariable Long topicId,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User currentUser = userDetails.getUser();
        topicService.deleteTopic(topicId, currentUser);
        return ResponseEntity.noContent().build();
    }
}

