package com.example.api.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateEmailDto {
    @NotBlank(message = "Pin is required")
    private String pin;
    @Email(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Provide valid email address")
    private String email;
}