package com.example.femilyship.dto;

import com.example.femilyship.entity.Essay;
import lombok.Getter;

@Getter
public class EssayInTopicResponseDto {
    private Long id;
    private String title;
    private String content_essay;

    public EssayInTopicResponseDto(Essay essay){
        this.id = essay.getId();
        this.title = essay.getTitleEssay();
        this.content_essay = essay.getContent_essay();
    }
}
