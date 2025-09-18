package com.example.femilyship.dto;

import com.example.femilyship.entity.Essay;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class EssayInTopicResponseDto {
    private Long id;
    private String title;

    public EssayInTopicResponseDto(Essay essay){
        this.id = essay.getId();
        this.title = essay.getTitleEssay();
    }
}
