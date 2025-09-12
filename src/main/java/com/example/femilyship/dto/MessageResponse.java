package com.example.femilyship.dto;

import lombok.Getter;
import lombok.Setter;

// This is a generic response class for sending simple messages, like errors.
@Setter
@Getter
public class MessageResponse {
    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }

}
