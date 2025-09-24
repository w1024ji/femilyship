package com.example.femilyship.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
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
    @JsonBackReference("user-topics")
    private User author;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("topic-essays")
    private List<Essay> essays = new ArrayList<>();

    // 1. DTO에서 Entity로 변환 시 사용할 생성자 추가
    public Topic(String title_topic, String content_topic, User author) {
        this.titleTopic = title_topic;
        this.contentTopic = content_topic;
        this.author = author;
    }

    public void update(String title_topic, String content_topic) {
        this.titleTopic = title_topic;
        this.contentTopic = content_topic;
    }

    // --- 수동으로 추가하는 equals() & hashCode() ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topic topic = (Topic) o;
        return Objects.equals(id, topic.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

