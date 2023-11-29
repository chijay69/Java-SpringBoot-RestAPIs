package com.example.api.service;

import com.example.api.model.data.Employees;
import com.example.api.model.dto.request.UpdateEmailDto;

public interface EmployeeService {
    Boolean confirmEmployee(String employeePin);
    Employees findEmployee(String pin);
    Employees updateEmail(UpdateEmailDto updateEmailDto);
}
