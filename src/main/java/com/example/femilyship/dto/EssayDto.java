package com.example.femilyship.dto;

import com.example.femilyship.entity.Essay;
import com.example.femilyship.entity.Topic;
import com.example.femilyship.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class EssayDto {

    // 에세이 생성 및 수정 요청 DTO
    @Getter
    @Setter
    @NoArgsConstructor
    public static class Request {
        private Long topicId; // 어느 토픽에 속한 에세이인지
        private String title_essay;
        private String content_essay;

        public Essay toEntity(Topic topic, User author) { // DTO를 Entity로 변환
            return new Essay(this.title_essay, this.content_essay, author, topic);
        }
    }

    // 에세이 상세 조회 응답 DTO
    @Getter
    public static class Response {
        private Long id;
        private String title_essay;
        private String content_essay;
        private String authorUsername;
        private Long topicId;

        public Response(Essay essay) {
            this.id = essay.getId();
            this.title_essay = essay.getTitleEssay();
            this.content_essay = essay.getContent_essay();
            this.authorUsername = essay.getAuthor().getUsername();
            this.topicId = essay.getTopic().getId();
        }
    }
}