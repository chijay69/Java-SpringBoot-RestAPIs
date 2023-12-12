package com.example.api.service;

import com.example.api.model.data.Employees;
import com.example.api.model.dto.request.EmailDto;

public interface EmployeeService {
    Boolean confirmEmployee(String employeePin);
    Employees findEmployee(String pin);
    Employees updateEmail(EmailDto updateEmailDto);
    String authenticateUser(EmailDto emailDto);
    String confirmUser(String token);
}
