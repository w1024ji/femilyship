package com.example.femilyship.dto;

import com.example.femilyship.entity.Topic;
import com.example.femilyship.entity.User;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

public class TopicDto {
    // 토픽 생성 및 수정 요청 DTO
    @Getter
    @Setter // Controller에서 객체 바인딩을 위해 Setter가 필요할 수 있습니다.
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        private String title_topic;
        private String content_topic;

        public Topic toEntity(User author) { // DTO를 Entity로 변환
            return new Topic(this.title_topic, this.content_topic, author);
        }
    }

    // 토픽 전체 목록 조회 응답 DTO (메인 페이지용)
    @Getter
    public static class ListResponse {
        private Long id;
        private String title_topic;
        private String authorUsername;

        public ListResponse(Topic topic) {
            this.id = topic.getId();
            this.title_topic = topic.getTitleTopic();
            this.authorUsername = topic.getAuthor().getUsername(); // User 엔티티에 getUsername()이 있다고 가정
        }
    }

    // 토픽 상세 조회 응답 DTO
    @Getter
    public static class DetailResponse {
        private Long id;
        private String title_topic;
        private String content_topic;
        private String authorUsername;
        private List<EssayInTopicResponseDto> essays; // 해당 토픽에 속한 에세이 목록

        public DetailResponse(Topic topic) {
            this.id = topic.getId();
            this.title_topic = topic.getTitleTopic();
            this.content_topic = topic.getContentTopic();
            this.authorUsername = topic.getAuthor().getUsername();
            this.essays = topic.getEssays().stream()
                    .map(EssayInTopicResponseDto::new)
                    .collect(Collectors.toList());
        }
    }
}