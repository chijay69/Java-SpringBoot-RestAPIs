package com.example.api.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GenerateLetterRequest {
    private String pin;
    private String country;
    private String state;
}
