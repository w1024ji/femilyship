package com.example.femilyship.dto;

import com.example.femilyship.entity.Essay;
import lombok.Getter;

@Getter
public class EssayInTopicResponseDto {
    private Long id;
    private String title;
    private String authorUsername;

    public EssayInTopicResponseDto(Long id, String title, String authorUsername){
        this.id = id;
        this.title = title;
        this.authorUsername = authorUsername;
    }
}
