package com.example.femilyship.dto;

import com.example.femilyship.entity.Topic;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class TopicDto {
    private Long id;
    private String name;
    private List<PageDto> pages;

    public TopicDto(Topic topic) {
        this.id = topic.getId();
        this.name = topic.getName();
        this.pages = topic.getPages().stream()
                .map(PageDto::new)
                .collect(Collectors.toList());
    }
}