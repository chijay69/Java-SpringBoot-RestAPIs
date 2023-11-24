package com.example.api.controller;

import com.example.api.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("/{pin}")
    public ResponseEntity<Boolean> confirmEmployee(@PathVariable String pin){
        long startTime = System.currentTimeMillis();
        boolean result = employeeService.confirmEmployee(pin);
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        log.info("Time taken: " + duration + " milliseconds");
        return ResponseEntity.ok(result);
    }
}