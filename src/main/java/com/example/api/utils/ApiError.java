package com.example.api.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ApiError {

    private String message;
    private boolean status;
    private LocalDateTime timeStamp;
}
