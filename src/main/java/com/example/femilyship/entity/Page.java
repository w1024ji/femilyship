package com.example.femilyship.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class Page {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    private String content;

    // Many pages can belong to one topic
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id") // This will create a 'topic_id' column in the Page table
    private Topic topic;

    // Many pages can be written by one user
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") // This will create a 'user_id' column
    private User author;
}
