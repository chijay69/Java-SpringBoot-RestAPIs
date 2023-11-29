package com.example.api.controller;

import com.example.api.model.dto.GenerateLetterRequest;
import com.example.api.service.LetterService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/letter")
public class LetterController {
    private final LetterService letterService;

    public LetterController(LetterService letterService) {
        this.letterService = letterService;
    }

    @PostMapping()
    public String generateLetter(@RequestBody GenerateLetterRequest request){

        return letterService.generateEmployeeLetter(request);
    }
}