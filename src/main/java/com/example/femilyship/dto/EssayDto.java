package com.example.femilyship.dto;

import com.example.femilyship.entity.Essay;
import com.example.femilyship.entity.Topic;
import com.example.femilyship.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class EssayDto {

    /**
     * 에세이 생성 및 수정 요청 DTO
      */
    @Getter
    @Setter
    @NoArgsConstructor
    public static class Request {
        private Long topicId;
        private String title;
        private String content;

        public Essay toEntity(Topic topic, User author) {
            return Essay.builder()
                    .title(this.title)
                    .content(this.content)
                    .author(author)
                    .topic(topic)
                    .build();
        }
    }

    /**
     * 에세이 상세 조회 응답 DTO
     */
    @Getter
    public static class Response {
        private Long id;
        private String title;
        private String content;
        private String authorUsername;
        private Long topicId;

        public Response(Essay essay) {
            this.id = essay.getId();
            this.title = essay.getTitle();
            this.content = essay.getContent();
            this.authorUsername = essay.getAuthor().getUsername();
            this.topicId = essay.getTopic().getId();
        }
    }
}

