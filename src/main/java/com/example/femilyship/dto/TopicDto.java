package com.example.femilyship.dto;

import com.example.femilyship.entity.Topic;
import com.example.femilyship.entity.User;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

public class TopicDto {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        private String title;
        private String content;
        public Topic toEntity(User author) {
            return new Topic(this.title, this.content, author);
        }
    }
    /**
     * 토픽 전체 목록 조회 응답 DTO (메인 페이지용)
     */
    @Getter
    public static class ListResponse {
        private Long id;
        private String title;
        private String authorUsername;

        public ListResponse(Topic topic) {
            this.id = topic.getId();
            this.title = topic.getTitle();
            this.authorUsername = topic.getAuthor().getUsername();
        }
    }
    /**
     * 토픽 상세 조회 응답 DTO
     */
    @Getter
    public static class DetailResponse {
        private Long id;
        private String title;
        private String content;
        private String authorUsername;
        private List<EssayInTopicResponseDto> essays;

        public DetailResponse(Topic topic, List<EssayInTopicResponseDto> essays) {
            this.id = topic.getId();
            this.title = topic.getTitle();
            this.content = topic.getContent();
            this.authorUsername = topic.getAuthor().getUsername();
            this.essays = essays;
        }
    }
}

