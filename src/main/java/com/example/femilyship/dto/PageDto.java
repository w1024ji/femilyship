package com.example.femilyship.dto;

import com.example.femilyship.entity.Page;
import lombok.Data;

@Data
public class PageDto {
    private Long id;
    private String title;
    private String content;
    private String authorUsername; // Add this field

    public PageDto(Page page) {
        this.id = page.getId();
        this.title = page.getTitle();
        this.content = page.getContent();
        // Check if author is not null before accessing username
        if (page.getAuthor() != null) {
            this.authorUsername = page.getAuthor().getUsername();
        }
    }
}