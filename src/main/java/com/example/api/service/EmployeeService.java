package com.example.api.service;

import com.example.api.model.data.Employees;
import com.example.api.model.dto.EmployeeDto;

public interface EmployeeService {
    Boolean confirmEmployee(String employeePin);
    Employees findEmployee(String pin);
}
