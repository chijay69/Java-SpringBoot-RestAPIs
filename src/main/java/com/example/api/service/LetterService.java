package com.example.api.service;

import com.example.api.model.dto.GenerateLetterRequest;
import org.springframework.stereotype.Service;

@Service
public interface LetterService {
    String generateEmployeeLetter(GenerateLetterRequest request);
}
