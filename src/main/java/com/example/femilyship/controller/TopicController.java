package com.example.femilyship.controller;

import com.example.femilyship.dto.CreateTopicRequest;
import com.example.femilyship.dto.TopicDto;
import com.example.femilyship.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/topics")
@RequiredArgsConstructor
public class TopicController {

    private final TopicService topicService;

    // Endpoint to create a new topic
    @PostMapping
    public ResponseEntity<TopicDto> createTopic(@RequestBody CreateTopicRequest request) {
        TopicDto topic = topicService.createTopic(request);
        return ResponseEntity.ok(topic);
    }

    // Endpoint to get all topics
    @GetMapping
    public ResponseEntity<List<TopicDto>> getAllTopics() {
        List<TopicDto> topics = topicService.findAllTopics();
        return ResponseEntity.ok(topics);
    }

    // Endpoint to get a single topic by its ID
    @GetMapping("/{id}")
    public ResponseEntity<TopicDto> getTopicById(@PathVariable Long id) {
        TopicDto topic = topicService.findTopicById(id);
        return ResponseEntity.ok(topic);
    }
}