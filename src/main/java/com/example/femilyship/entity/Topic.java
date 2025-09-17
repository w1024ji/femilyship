package com.example.femilyship.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titleTopic;

    @Column(nullable = false)
    private String contentTopic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_topic_id")
    private User author;

    // Topic은 여러개의 Essay를 가진다
    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Essay> essays = new ArrayList<>();

    // 1. DTO에서 Entity로 변환 시 사용할 생성자 추가
    public Topic(String title_topic, String content_topic, User author) {
        this.titleTopic = title_topic;
        this.contentTopic = content_topic;
        this.author = author;
    }

    // 2. 토픽 수정을 위한 비즈니스 메서드 추가
    public void update(String title_topic, String content_topic) {
        this.titleTopic = title_topic;
        this.contentTopic = content_topic;
    }

}