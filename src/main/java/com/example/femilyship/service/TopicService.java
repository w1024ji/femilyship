package com.example.femilyship.service;

import com.example.femilyship.dto.CreateTopicRequest;
import com.example.femilyship.dto.TopicDto;
import com.example.femilyship.entity.Topic;
import com.example.femilyship.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // Automatically creates a constructor to inject final fields
@Transactional(readOnly = true) // All methods are read-only by default
public class TopicService {

    private final TopicRepository topicRepository;

    @Transactional // Override the class-level readOnly setting
    public TopicDto createTopic(CreateTopicRequest request) {
        // Create a new Topic entity from the request
        Topic newTopic = new Topic();
        newTopic.setName(request.getName());

        // Save it to the database
        Topic savedTopic = topicRepository.save(newTopic);

        // Convert the saved entity to a DTO and return it
        return new TopicDto(savedTopic);
    }

    public List<TopicDto> findAllTopics() {
        return topicRepository.findAll().stream()
                .map(TopicDto::new)
                .collect(Collectors.toList());
    }
}