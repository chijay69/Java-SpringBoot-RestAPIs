package com.example.api.controller;

import com.example.api.model.dto.request.UpdateEmailDto;
import com.example.api.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
@Slf4j
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/{pin}")
    public ResponseEntity<Boolean> confirmEmployee(@PathVariable String pin){
        long startTime = System.currentTimeMillis();
        boolean result = employeeService.confirmEmployee(pin);
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        log.info("Time taken: " + duration + " milliseconds");
        return ResponseEntity.ok(result);
    }

    @GetMapping("data/{pin}")
    public ResponseEntity<?> findEmployee(@PathVariable String pin){
        return ResponseEntity.ok(employeeService.findEmployee(pin));
    }
    @PatchMapping("data/email")
    public ResponseEntity<?> updateEmail(@Valid @RequestBody UpdateEmailDto updateEmailDto){
        return ResponseEntity.ok(employeeService.updateEmail(updateEmailDto));
    }
}