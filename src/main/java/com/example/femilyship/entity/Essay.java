package com.example.femilyship.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Essay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titleEssay;

    @Column(nullable = false)
    private String content_essay;

    @JsonBackReference("user-essays")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_essay_id")
    private User author;

    @JsonBackReference("topic-essays")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    private Topic topic;

    public void update(String title_essay, String content_essay) {
        this.titleEssay = title_essay;
        this.content_essay = content_essay;
    }

    // --- 수동으로 추가하는 equals() & hashCode() ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Essay essay = (Essay) o;
        return Objects.equals(id, essay.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

