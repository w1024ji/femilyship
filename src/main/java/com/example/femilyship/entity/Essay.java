package com.example.femilyship.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Essay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titleEssay;

    @Column(nullable = false)
    private String content_essay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_essay_id")
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    private Topic topic;

    // DTO-> Entity
    public Essay(String title_essay, String content_essay, User author, Topic topic) {
        this.titleEssay = title_essay;
        this.content_essay = content_essay;
        this.author = author;
        this.topic = topic;
    }

    //== 비즈니스 로직 추가 ==//
    /**
     * 에세이의 제목과 내용을 수정하는 메서드
     */
    public void update(String title_essay, String content_essay) {
        this.titleEssay = title_essay;
        this.content_essay = content_essay;
    }
}
