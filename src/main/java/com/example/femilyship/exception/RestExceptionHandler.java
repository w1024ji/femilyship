package com.example.femilyship.exception;

import com.example.femilyship.dto.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

// This class acts as a global handler for exceptions thrown by controllers.
@ControllerAdvice
public class RestExceptionHandler {

    // This handler catches the specific error for "Username is already taken!"
    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<MessageResponse> handleRuntimeException(RuntimeException ex, WebRequest request) {
        MessageResponse errorResponse = new MessageResponse(ex.getMessage());
        // Return a 400 Bad Request status, which is appropriate for client errors
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // This handler catches errors for bad login credentials
    @ExceptionHandler(BadCredentialsException.class)
    public final ResponseEntity<MessageResponse> handleBadCredentialsException(BadCredentialsException ex, WebRequest request) {
        MessageResponse errorResponse = new MessageResponse("Invalid username or password");
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED); // 401 Unauthorized
    }
}
