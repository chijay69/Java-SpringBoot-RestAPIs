package com.example.api.model.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailDto {
    private String pin;
    private String email;
}