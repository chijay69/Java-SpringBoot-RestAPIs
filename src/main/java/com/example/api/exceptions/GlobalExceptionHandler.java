package com.example.api.exceptions;

import com.example.api.utils.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleLearnSpaceUserManagement(Exception exception) {
        return new ResponseEntity<>(errorResponseBuilder(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
    private static ApiError errorResponseBuilder(String message) {
        return ApiError.builder()
                .message(message)
                .timeStamp(LocalDateTime.now())
                .status(false)
                .build();
    }
}