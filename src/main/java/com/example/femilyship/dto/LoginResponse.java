package com.example.femilyship.dto;

import lombok.Getter;
import lombok.Setter;

// This class defines the structure of a successful login response,
// matching what the frontend expects.
@Getter
@Setter
public class LoginResponse {
    private String accessToken;
    private String tokenType = "Bearer";

    public LoginResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
